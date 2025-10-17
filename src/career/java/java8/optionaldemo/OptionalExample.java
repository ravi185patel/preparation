package career.java.java8.optionaldemo;

import java.util.Optional;

/*
| Method                                | Description                                       |
| ------------------------------------- | ------------------------------------------------- |
| `empty()`                             | Returns an empty Optional.                        |
| `of(T value)`                         | Returns Optional of non-null value.               |
| `ofNullable(T value)`                 | Returns Optional of nullable value.               |
| `get()`                               | Returns value if present, else throws.            |
| `isPresent()`                         | True if value present.                            |
| `isEmpty()`                           | True if no value present.                         |
| `ifPresent(Consumer)`                 | Runs action if value present.                     |
| `ifPresentOrElse(Consumer, Runnable)` | Runs action or emptyAction depending on presence. |
| `filter(Predicate)`                   | Returns Optional if value matches predicate.      |
| `map(Function)`                       | Transforms value if present.                      |
| `flatMap(Function)`                   | Transforms with function returning Optional.      |
| `orElse(T other)`                     | Returns value or other.                           |
| `orElseGet(Supplier)`                 | Returns value or supplier result (lazy).          |
| `orElseThrow()`                       | Returns value or throws.                          |
| `orElseThrow(Supplier)`               | Returns value or throws supplied exception.       |
| `equals(Object)`                      | Compares for equality.                            |
| `hashCode()`                          | Returns hash code.                                |
| `toString()`                          | String representation.                            |

 */
public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> maybeName = Optional.of("Alice");

        // Check if value is present
        if (maybeName.isPresent()) {
            System.out.println("Name is: " + maybeName.get());
        }

        // Use ifPresent to run code only if value exists
        maybeName.ifPresent(name -> System.out.println("Hello, " + name));

        // Provide a default value if Optional is empty
        String nameOrDefault = maybeName.orElse("Unknown");
        System.out.println("Name or default: " + nameOrDefault);

        // Transform the value if present
        Optional<Integer> nameLength = maybeName.map(String::length);
        nameLength.ifPresent(len -> System.out.println("Length of name: " + len));

        // Empty Optional example
        Optional<String> emptyOpt = Optional.empty();
        System.out.println("Empty optional or default: " + emptyOpt.orElse("No name"));

        // Chaining with orElseGet (lazy)
        String lazyDefault = emptyOpt.orElseGet(() -> "Lazy default");
        System.out.println("Lazy default: " + lazyDefault);

        Optional<String> optional = Optional.empty();
        Optional<String> opt = Optional.of("Hello");
        opt = Optional.ofNullable(null);  // empty Optional
        String val = opt.get();
        if (opt.isPresent()) { /* do something */ }
        if (opt.isEmpty()) { /* no value */ }
        opt.ifPresentOrElse(
                value -> System.out.println("Value: " + value),
                () -> System.out.println("No value")
        );
        val = opt.orElse("default");
        val = opt.orElseGet(() -> "lazy default");

    }
}
