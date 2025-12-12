package career.java.concept.concurrency.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private double x, y;
    private final StampedLock lock = new StampedLock();

    // Writer: moves the point
    public void move(double dx, double dy) {
        long stamp = lock.writeLock();      // Exclusive lock
        try {
            x += dx;
            y += dy;
        } finally {
            lock.unlockWrite(stamp);        // Must unlock with stamp
        }
    }

    // Optimistic read (fast path)
    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();  // Non-blocking
        double cx = x;
        double cy = y;

        // Validate: did a write occur?
        if (!lock.validate(stamp)) {
            // Fallback to full read lock
            stamp = lock.readLock();
            try {
                cx = x;
                cy = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        return Math.sqrt(cx * cx + cy * cy);
    }
}
