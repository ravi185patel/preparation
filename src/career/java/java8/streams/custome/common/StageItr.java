package career.java.java8.streams.custome.common;

import java.util.Iterator;

/**
 * Stage: transforms an Iterator<I> into an Iterator<O>.
 */
@FunctionalInterface
public interface StageItr<I, O> {
    Iterator<O> apply(Iterator<I> in);

    default <V> StageItr<I, V> andThen(StageItr<O, V> next) {
        return in -> next.apply(this.apply(in));
    }

    static <T> StageItr<T, T> identity() {
        return in -> in;
    }
}