package career.java.concept.concurrency.stampedlock;

public class StampedDemo {
    public static void main(String[] args) {
        Point point = new Point();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                point.move(1, 1);
                System.out.println("Writer moved point.");
                sleep(500);
            }
        });

        // Reader thread using optimistic read
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                double dist = point.distanceFromOrigin();
                System.out.println("Reader distance: " + dist);
                sleep(200);
            }
        });

        writer.start();
        reader.start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
