package career.java.java8.streams.custome;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.*;
import java.util.function.*;

public class CustomStream<T> {

    // pipeline is a list of functions that transform a List<?> -> List<?>
    // each step is responsible for returning a list of the next step's element type
    private final List<?> source; // actual element type of source
    private final List<Function<List<?>, List<?>>> pipeline;

    private CustomStream(List<?> source, List<Function<List<?>, List<?>>> pipeline) {
        this.source = source;
        this.pipeline = pipeline;
    }

    // factory
    public static <T> CustomStream<T> of(List<T> list) {
        return new CustomStream<>(list, new ArrayList<>());
    }

    // ------------------------------
    // Intermediate ops
    // ------------------------------

    public CustomStream<T> filter(Predicate<T> predicate) {
        // create new pipeline list for the resulting stream
        List<Function<List<?>, List<?>>> newPipe = new ArrayList<>(pipeline);

        // add a filter step that expects List<T> and returns List<T>
        newPipe.add(prev -> {
            @SuppressWarnings("unchecked")
            List<T> in = (List<T>) prev;
            List<T> out = new ArrayList<>();
            for (T item : in) {
                if (predicate.test(item)) out.add(item);
            }
            return out;
        });

        return new CustomStream<>(source, newPipe);
    }

    public <R> CustomStream<R> map(Function<T, R> mapper) {
        List<Function<List<?>, List<?>>> newPipe = new ArrayList<>(pipeline);

        // add a mapping step that expects List<T> and returns List<R>
        newPipe.add(prev -> {
            @SuppressWarnings("unchecked")
            List<T> in = (List<T>) prev;
            List<R> out = new ArrayList<>(in.size());
            for (T item : in) {
                out.add(mapper.apply(item));
            }
            return out;
        });

        // Create new stream with same source but new pipeline and R as type parameter
        return new CustomStream<>(source, newPipe);
    }

    public CustomStream<T> distinct() {
        List<Function<List<?>, List<?>>> newPipe = new ArrayList<>(pipeline);
        newPipe.add(prev -> {
            @SuppressWarnings("unchecked")
            List<T> in = (List<T>) prev;
            return new ArrayList<>(new LinkedHashSet<>(in));
        });
        return new CustomStream<>(source, newPipe);
    }

    public CustomStream<T> sorted(Comparator<T> cmp) {
        List<Function<List<?>, List<?>>> newPipe = new ArrayList<>(pipeline);
        newPipe.add(prev -> {
            @SuppressWarnings("unchecked")
            List<T> in = (List<T>) prev;
            List<T> copy = new ArrayList<>(in);
            copy.sort(cmp);
            return copy;
        });
        return new CustomStream<>(source, newPipe);
    }

    // ------------------------------
    // Terminal ops
    // ------------------------------

    @SuppressWarnings("unchecked")
    public List<T> toList() {
        List<?> current = (List<?>) source;
        for (Function<List<?>, List<?>> step : pipeline) {
            current = step.apply(current);
        }
        return (List<T>) current;
    }

    public void forEach(Consumer<T> action) {
        for (T item : toList()) action.accept(item);
    }

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 5);

        List<Integer> result = CustomStream.of(nums)
                .filter(n -> n % 2 == 1)
                .distinct()
                .map(n -> n * 10)
                .sorted(Integer::compareTo)
                .toList();

        System.out.println(result);
    }
}
