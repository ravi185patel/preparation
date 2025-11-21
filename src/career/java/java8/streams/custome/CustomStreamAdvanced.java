package career.java.java8.streams.custome;

import career.java.java8.streams.custome.common.Stage;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Type-safe CustomStreamAdvanced.
 *
 * S = source element type (type of original list)
 * T = current element type after pipeline
 *
 * Notes, tradeoffs & possible extensions
 *
 * Buffering: This simple implementation buffers whole intermediate results as Lists (like many simple stream demos). Real java.util.stream uses spliterators/iterators and fuses stages for better memory/perf.
 *
 * Performance: For production you may prefer iterator-based lazy evaluation (pull-style) or a Spliterator-backed design to support parallelism.
 *
 * More ops: You can add limit, skip, peek, reduce, and collect(Collector) — some require extra care (stateful ops, associativity).
 *
 * Parallel: To support parallel execution properly you'd need a typed Spliterator and thread-safe collectors.
 */
public final class CustomStreamAdvanced<S, T> {

    private final List<S> source;
    private final Stage<S, T> pipeline;

    private CustomStreamAdvanced(List<S> source, Stage<S, T> pipeline) {
        this.source = Objects.requireNonNull(source);
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    /** Factory: returns a stream whose source and current type are both T. */
    public static <T> CustomStreamAdvanced<T, T> of(List<T> list) {
        return new CustomStreamAdvanced<>(list, Stage.identity());
    }

    // -------------------------
    // Intermediate operations
    // -------------------------

    /** Filter keeps only elements matching the predicate. */
    public CustomStreamAdvanced<S, T> filter(Predicate<? super T> predicate) {
        Stage<T, T> filterStage = input -> {
            List<T> out = new ArrayList<>();
            for (T t : input) {
                if (predicate.test(t)) out.add(t);
            }
            return out;
        };
        return new CustomStreamAdvanced<>(source, pipeline.andThen(filterStage));
    }

    /** Map transforms each element T -> R and returns a stream of R. */
    public <R> CustomStreamAdvanced<S, R> map(Function<? super T, ? extends R> mapper) {
        Stage<T, R> mapStage = input -> {
            List<R> out = new ArrayList<>(input.size());
            for (T t : input) out.add(mapper.apply(t));
            return out;
        };
        return new CustomStreamAdvanced<>(source, pipeline.andThen(mapStage));
    }

    /** flatMap: each T -> Collection<? extends R>, result is flattened to R. */
    public <R> CustomStreamAdvanced<S, R> flatMap(Function<? super T, ? extends Collection<? extends R>> mapper) {
        Stage<T, R> flat = input -> {
            List<R> out = new ArrayList<>();
            for (T t : input) {
                Collection<? extends R> c = mapper.apply(t);
                if (c != null) out.addAll(c);
            }
            return out;
        };
        return new CustomStreamAdvanced<>(source, pipeline.andThen(flat));
    }

    /** distinct preserves encounter order (LinkedHashSet). */
    public CustomStreamAdvanced<S, T> distinct() {
        Stage<T, T> distinctStage = input -> {
            LinkedHashSet<T> set = new LinkedHashSet<>(input);
            return new ArrayList<>(set);
        };
        return new CustomStreamAdvanced<>(source, pipeline.andThen(distinctStage));
    }

    /** sorted using provided comparator. */
    public CustomStreamAdvanced<S, T> sorted(Comparator<? super T> comparator) {
        Stage<T, T> sortStage = input -> {
            List<T> copy = new ArrayList<>(input);
            copy.sort(comparator);
            return copy;
        };
        return new CustomStreamAdvanced<>(source, pipeline.andThen(sortStage));
    }

    // -------------------------
    // Terminal operations
    // -------------------------

    /** Execute pipeline and return list of T. */
    public List<T> toList() {
        return pipeline.apply(source);
    }

    /** Execute pipeline and consume with action. */
    public void forEach(Consumer<? super T> action) {
        for (T t : toList()) action.accept(t);
    }

    // Convenience: collect to a specific Collection
    public <C extends Collection<? super T>> C collect(Supplier<C> collectionFactory) {
        C c = collectionFactory.get();
        for (T t : toList()) c.add(t);
        return c;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Alex", "Brian");

        // Build pipeline: names -> filter start with A -> distinct -> map to uppercase -> sort
        List<String> result = CustomStreamAdvanced.of(names)
                .filter(s -> s.startsWith("A"))
                .distinct()
                .map(String::toUpperCase)
                .sorted(String::compareTo)
                .toList();

        System.out.println(result); // [ALEX, ALICE, ANNA]
    }
}