package career.java.java11;

import java.nio.file.Files;
import java.nio.file.Path;

/*
| Feature                              | Description                                   |
| ------------------------------------ | --------------------------------------------- |
| `var` in lambda                      | Use `var` in lambda parameters                |
| New String methods                   | `strip()`, `isBlank()`, `lines()`, `repeat()` |
| `HttpClient` API                     | Standardized HTTP client                      |
| `Files.readString()`/`writeString()` | Simplified file I/O                           |
| Removed modules                      | Java EE / CORBA removed                       |
| Launch `.java` file directly         | No compilation needed for simple files        |

 */
public class FileReadWriteExample {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("example.txt");

        // Write string to file
        Files.writeString(path, "Hello from Java 11!");

        // Read string from file
        String content = Files.readString(path);
        System.out.println(content);
    }
}
