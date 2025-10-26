package career.lld.LLDConferenceRoom;

import java.time.LocalDateTime;
import java.util.*;

public class ConferenceRoomBookingSystem {
    private List<Building> buildings;
    private List<User> users;
    private BookingService bookingService;

    public ConferenceRoomBookingSystem() {
        this.buildings = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookingService = new BookingService();
    }

    public List<ConferenceRoom> searchAvailableRooms(LocalDateTime start, LocalDateTime end, int capacity) {
        List<ConferenceRoom> availableRooms = new ArrayList<>();
        for (Building building : buildings) {
            for (Floor floor : building.getFloors()) {
                for (ConferenceRoom room : floor.getRooms()) {
                    if (room.isAvailable(start, end) && room.getCapacity() >= capacity) {
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }

    public Booking bookRoom(User user, ConferenceRoom room, LocalDateTime start, LocalDateTime end) {
        return bookingService.createBooking(user, room, start, end);
    }

    public static void main(String[] args) {
        Admin admin = new Admin("A1", "Priya", "priya@corp.com");
        Building b1 = new Building("B1", "Tech Park");
        Floor f1 = new Floor(1);

        ConferenceRoom r1 = new ConferenceRoom("R1", "Board Room", 4);//, RoomType.SMALL);
        ConferenceRoom r2 = new ConferenceRoom("R2", "Main Hall", 20);//, RoomType.LARGE);

        admin.addFloor(b1, f1);
        admin.addRoom(f1, r1);
        admin.addRoom(f1, r2);

        ConferenceRoomBookingSystem system = new ConferenceRoomBookingSystem();
        List<ConferenceRoom> res = system.searchAvailableRooms(LocalDateTime.now(), LocalDateTime.now().plusHours(2), 10);//, RoomType.LARGE);
        for(ConferenceRoom conferenceRoom:res){
            System.out.println(conferenceRoom.getRoomId());
        }

    }
}

// ---------------- Building and Floor ----------------

class Building {
    public String buildingId;
    private String name;
    private List<Floor> floors;

    public Building(String buildingId, String name) {
        this.buildingId = buildingId;
        this.name = name;
        this.floors = new ArrayList<>();
    }

    public List<Floor> getFloors() { return floors; }
}

class Floor {
    private int floorNumber;
    private List<ConferenceRoom> rooms = new ArrayList<>();

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ConferenceRoom> getRooms() { return rooms; }
}

// ---------------- ConferenceRoom ----------------

class ConferenceRoom {
    private String roomId;
    private String name;
    private int capacity;
    private List<String> amenities;
    private List<Booking> bookings;

    public ConferenceRoom(String id, String name, int capacity) {
        this.roomId = id;
        this.name = name;
        this.capacity = capacity;
        this.bookings = new ArrayList<>();
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        for (Booking booking : bookings) {
            if (booking.getStatus() == BookingStatus.ACTIVE &&
                booking.getTimeSlot().overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public int getCapacity() { return capacity; }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}

// ---------------- Booking ----------------

class Booking {
    private String bookingId;
    private User user;
    private String roomId; // instead of ConferenceRoom reference
    private TimeSlot timeSlot;
    private BookingStatus status;

    public Booking(String id, User user, String roomId, TimeSlot slot) {
        this.bookingId = id;
        this.user = user;
        this.roomId = roomId;
        this.timeSlot = slot;
        this.status = BookingStatus.ACTIVE;
    }

    public String getRoomId() { return roomId; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public BookingStatus getStatus() { return status; }
}


// ---------------- TimeSlot ----------------

class TimeSlot {
    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(LocalDateTime s, LocalDateTime e) {
        boolean isOverlaps = !(e.isBefore(start) || s.isAfter(end));
        System.out.println(s+" "+e+" << "+isOverlaps);
        return isOverlaps;
    }
}

// ---------------- User and Admin ----------------

class User {
    private String userId;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.userId = id;
        this.name = name;
        this.email = email;
    }
}

class Admin extends User {
    public Admin(String id, String name, String email) {
        super(id, name, email);
    }

    public void addRoom(Floor floor, ConferenceRoom room) {
        floor.getRooms().add(room);
    }

    public void addFloor(Building building, Floor floor) {
        building.getFloors().add(floor);
    }
}

// ---------------- BookingService ----------------

class BookingService {
    private Map<String, Booking> bookingMap = new HashMap<>();

    public Booking createBooking(User user, ConferenceRoom room, LocalDateTime start, LocalDateTime end) {
        if (!room.isAvailable(start, end)) {
            throw new IllegalStateException("Room not available in the given time slot");
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), user, room.getRoomId(), new TimeSlot(start, end));
        room.addBooking(booking);
        bookingMap.put(booking.getRoomId(), booking);
        return booking;
    }

}

enum BookingStatus {
    ACTIVE, CANCELLED, COMPLETED;
}
