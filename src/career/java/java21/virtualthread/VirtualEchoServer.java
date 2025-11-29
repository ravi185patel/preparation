package career.java.java21.virtualthread;// File: VirtualEchoServer.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class VirtualEchoServer {
    public static void main(String[] args) throws Exception {
        int port = 12345;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("VirtualEchoServer listening on " + port);
            while (true) {
                Socket client = server.accept(); // accept returns quickly
                // Create a virtual thread to handle the connection
//                Thread.ofVirtual().start(() -> handle(client));
            }
        }
    }

    private static void handle(Socket socket) {
        try (socket;
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var out = new PrintWriter(socket.getOutputStream(), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                out.println("echo: " + line);
            }
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}
