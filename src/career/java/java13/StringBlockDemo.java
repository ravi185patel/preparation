package career.java.java13;

/*
“Text blocks help write multi-line strings (JSON/SQL/HTML)
 more cleanly without escape characters. Introduced as a preview in Java 13 and finalized later.”

 “Java 13 refined switch expressions introduced in Java 12, stabilizing yield and improving multi-line case blocks.”

 “Java 13 expanded ZGC beyond Linux, making ultra-low pause GC available across OSes.”
 */
public class StringBlockDemo {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"name\": \"Aryan\",\n" +
                "  \"role\": \"Developer\"\n" +
                "}";

        json = """
        {
            "name": "Aryan",
            "role": "Developer"
        }
        """;

    }
}
