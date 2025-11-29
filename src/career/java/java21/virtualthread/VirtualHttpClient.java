package career.java.java21.virtualthread;// File: VirtualHttpClient.java
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class VirtualHttpClient {
    public static void main(String[] args) throws Exception {
//        List<String> urls = List.of(
//            "https://example.com",
//            "https://httpbin.org/delay/1",
//            "https://httpbin.org/get",
//            "https://httpbin.org/status/200"
//            // add more urls...
//        );

        // Create an ExecutorService that spawns a virtual thread per task.
//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            HttpClient client = HttpClient.newBuilder()
//                    .executor(executor) // use virtual threads for async callbacks too
//                    .connectTimeout(Duration.ofSeconds(10))
//                    .build();
//
//            for (String u : urls) {
//                // Run each fetch in its own virtual thread via executor
//                executor.submit(() -> {
//                    try {
//                        HttpRequest req = HttpRequest.newBuilder()
//                                .uri(URI.create(u))
//                                .GET()
//                                .build();
//                        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
//                        System.out.println(Thread.currentThread() + " -> " + u + " : " + resp.statusCode() + " body-size=" + resp.body().length());
//                    } catch (Exception e) {
//                        System.err.println("Failed to fetch " + u + ": " + e);
//                    }
//                });
//            }

            // The executor will be auto-closed at the end of try-with-resources,
            // which will wait for submitted tasks to finish.
//        }
    }
}
