package career.java.java14;

public class PatternMatching {
    public static void main(String[] args) {
//        before
        Object obj = "Hello Java";

        if (obj instanceof String) {
            String s = (String) obj;   // manual cast
            System.out.println(s.toUpperCase());
        }

//        after

//        if (obj instanceof String s) {
//            System.out.println(s.toLowerCase());
//        }


//        switch (obj) {
//            case String s -> System.out.println("String: " + s);
//            case Integer i -> System.out.println("Integer: " + i);
//            default -> System.out.println("Unknown");
//        }

        /*
        🚀 4. Microservices Example – Handling API responses
        public void processResponse(Object response) {

    if (response instanceof ErrorResponse err) {
        System.out.println("Error: " + err.message());
    }

    if (response instanceof SuccessResponse s) {
        System.out.println("Success: " + s.data());
    }
}

         */
    }


}
