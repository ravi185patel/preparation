package career.java.java8.streams.custome.common;

import java.util.ArrayList;
import java.util.List;

/**
 * A Stage transforms a List<I> into a List<O>.
 * Stages can be composed with andThen to build pipelines in a type-safe way.
 */
@FunctionalInterface
public interface Stage<I, O> {
    List<O> apply(List<I> input);

    default <V> Stage<I, V> andThen(Stage<O, V> next) {
        return input -> {
            List<O> middle = this.apply(input);
            return next.apply(middle);
        };
    }

    static <T> Stage<T, T> identity() {
        return input -> new ArrayList<>(input); // return a copy to avoid modifying source
    }
}