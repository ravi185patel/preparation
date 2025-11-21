package career.java.java8.streams.custome;

import career.java.java8.streams.custome.common.StageItr;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Type-safe CustomStreamIteratorAdvanced.
 *
 * S = source element type (type of original list)
 * T = current element type after pipeline

 ******************** Notes, caveats & interview talking points  ********************
 * Lazy pull model: Each terminal operation creates a fresh Iterator from the source by calling source.iterator() and pulls elements through the composed StageItr chain. This mirrors the pull-based evaluation used by many streaming libraries.
 * No whole-pipeline buffering: Most StageItrs (map, filter, flatMap, distinct) operate element-by-element, holding only small per-StageItr state (e.g., seen set for distinct). Only sorted() is inherently stateful and buffers everything.
 * Source re-usable: The source is an Iterable (backed by a Collection), so you can call terminal ops multiple times and each time a new iterator is used. If the source is a one-shot Iterable, behavior will be single-use.
 * Thread-safety: This implementation is not thread-safe. For parallelism you'd need to implement a Spliterator and combine results with proper collectors.
 * flatMap: The provided flatMap expects a mapper returning an Iterable<? extends R> to make it easy to create inner iterators lazily; you can adapt to Stream-like semantics if you implement Spliterators.
 */
public final class CustomStreamIterator<S, T> {

    private final Iterable<S> source; // keep source as Iterable so we can create iterators multiple times
    private final StageItr<S, T> pipeline;

    private CustomStreamIterator(Iterable<S> source, StageItr<S, T> pipeline) {
        this.source = Objects.requireNonNull(source);
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    public static <T> CustomStreamIterator<T, T> of(Collection<T> col) {
        // wrap collection with Iterable so iterator() can be called each terminal op
        return new CustomStreamIterator<>(col, StageItr.identity());
    }

    // -------------------------
    // Intermediate (lazy) ops
    // -------------------------

    public CustomStreamIterator<S, T> filter(Predicate<? super T> predicate) {
        StageItr<T, T> filterStageItr = in -> new Iterator<T>() {
            Iterator<T> it = in;
            T nextItem = null;
            boolean hasNextEvaluated = false;

            private void advance() {
                while (!hasNextEvaluated && it.hasNext()) {
                    T cand = it.next();
                    if (predicate.test(cand)) {
                        nextItem = cand;
                        hasNextEvaluated = true;
                        return;
                    }
                }
                hasNextEvaluated = true; // exhausted or found
            }

            @Override
            public boolean hasNext() {
                advance();
                return nextItem != null;
            }

            @Override
            public T next() {
                advance();
                if (nextItem == null) throw new NoSuchElementException();
                T r = nextItem;
                nextItem = null;
                hasNextEvaluated = false;
                return r;
            }
        };

        return new CustomStreamIterator<>(source, pipeline.andThen(filterStageItr));
    }

    public <R> CustomStreamIterator<S, R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper");

        StageItr<T, R> mapStageItr = in -> new Iterator<R>() {
            private final Iterator<T> it = Objects.requireNonNull(in);

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public R next() {
                T t = it.next();              // may throw NoSuchElementException — okay
                return mapper.apply(t);       // may throw NPE or other runtime exceptions from mapper
            }

            @Override
            public void remove() {
                // optional: delegate if underlying iterator supports it, otherwise throw
                throw new UnsupportedOperationException("remove");
            }
        };

        return new CustomStreamIterator<>(source, pipeline.andThen(mapStageItr));
    }

    /**
     * flatMap: mapper returns a Collection<? extends R> (could be an Iterable).
     * We lazily traverse each inner iterable.
     */
    public <R> CustomStreamIterator<S, R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper) {
        StageItr<T, R> flatStageItr = in -> new Iterator<R>() {
            Iterator<T> outer = in;
            Iterator<? extends R> inner = Collections.emptyIterator();

            private void ensureInner() {
                while ((inner == null || !inner.hasNext()) && outer.hasNext()) {
                    T outerVal = outer.next();
                    Iterable<? extends R> nextIterable = mapper.apply(outerVal);
                    inner = nextIterable == null ? Collections.emptyIterator() : nextIterable.iterator();
                }
            }

            @Override
            public boolean hasNext() {
                ensureInner();
                return inner != null && inner.hasNext();
            }

            @Override
            public R next() {
                ensureInner();
                if (inner == null || !inner.hasNext()) throw new NoSuchElementException();
                return inner.next();
            }
        };

        return new CustomStreamIterator<>(source, pipeline.andThen(flatStageItr));
    }

    /**
     * distinct(): uses a HashSet to filter duplicates while preserving encounter order.
     * This is lazy but keeps visited set state.
     */
    public CustomStreamIterator<S, T> distinct() {
        StageItr<T, T> distinctStageItr = in -> new Iterator<T>() {
            Iterator<T> it = in;
            Set<T> seen = new HashSet<>();
            T nextItem = null;
            boolean hasNextEvaluated = false;

            private void advance() {
                while (!hasNextEvaluated && it.hasNext()) {
                    T cand = it.next();
                    if (seen.add(cand)) { // add returns true if not present
                        nextItem = cand;
                        hasNextEvaluated = true;
                        return;
                    }
                }
                hasNextEvaluated = true;
            }

            @Override
            public boolean hasNext() {
                advance();
                return nextItem != null;
            }

            @Override
            public T next() {
                advance();
                if (nextItem == null) throw new NoSuchElementException();
                T r = nextItem;
                nextItem = null;
                hasNextEvaluated = false;
                return r;
            }
        };

        return new CustomStreamIterator<>(source, pipeline.andThen(distinctStageItr));
    }

    /**
     * sorted: stateful - buffers all remaining elements, sorts them, returns iterator over sorted list.
     * NOTE: this is not streaming-friendly for infinite streams.
     */
    public CustomStreamIterator<S, T> sorted(Comparator<? super T> comparator) {
        StageItr<T, T> sortStageItr = in -> {
            List<T> buffer = new ArrayList<>();
            in.forEachRemaining(buffer::add); // exhaust input iterator into buffer
            buffer.sort(comparator);
            return buffer.iterator();
        };

        return new CustomStreamIterator<>(source, pipeline.andThen(sortStageItr));
    }

    // -------------------------
    // Terminal ops (pull from pipeline)
    // -------------------------

    public List<T> toList() {
        List<T> out = new ArrayList<>();
        Iterator<T> it = pipeline.apply(source.iterator());
        it.forEachRemaining(out::add);
        return out;
    }

    public void forEach(Consumer<? super T> action) {
        Iterator<T> it = pipeline.apply(source.iterator());
        while (it.hasNext()) action.accept(it.next());
    }

    // convenience: collect into provided collection
    public <C extends Collection<? super T>> C collect(Supplier<C> collectionFactory) {
        C c = collectionFactory.get();
        forEach(c::add);
        return c;
    }

    // limit (optional) - lazy
    public CustomStreamIterator<S, T> limit(long maxSize) {
        if (maxSize < 0) throw new IllegalArgumentException("maxSize < 0");
        StageItr<T, T> limitStageItr = in -> new Iterator<T>() {
            Iterator<T> it = in;
            long remaining = maxSize;

            @Override
            public boolean hasNext() {
                return remaining > 0 && it.hasNext();
            }

            @Override
            public T next() {
                if (remaining <= 0) throw new NoSuchElementException();
                remaining--;
                return it.next();
            }
        };
        return new CustomStreamIterator<>(source, pipeline.andThen(limitStageItr));
    }

    // skip (optional) - lazy
    public CustomStreamIterator<S, T> skip(long n) {
        if (n <= 0) return this;
        StageItr<T, T> skipStageItr = in -> new Iterator<T>() {
            Iterator<T> it = in;
            boolean skipped = false;

            private void doSkip() {
                if (!skipped) {
                    long toSkip = n;
                    while (toSkip > 0 && it.hasNext()) {
                        it.next();
                        toSkip--;
                    }
                    skipped = true;
                }
            }

            @Override
            public boolean hasNext() {
                doSkip();
                return it.hasNext();
            }

            @Override
            public T next() {
                doSkip();
                return it.next();
            }
        };
        return new CustomStreamIterator<>(source, pipeline.andThen(skipStageItr));
    }
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Alex", "Brian", "Anna");

        List<String> result = CustomStreamIterator.of(names)
                .filter(s -> s.startsWith("A"))      // lazy filter
                .distinct()                           // lazy distinct (keeps seen set)
                .map(String::toUpperCase)             // lazy map
                .sorted(String::compareTo)            // stateful: buffers & sorts
                .toList();                            // terminal op: drives iteration

        System.out.println(result); // [ALEX, ALICE, ANNA]
    }
}