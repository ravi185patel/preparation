package career.lld.LLDParkingLotSystem;

import java.util.List;
import java.time.*;
import java.util.*;

// ======================================
//  MAIN ENTITY CLASSES
// ======================================

class ParkingLot {

	private List<ParkingFloor> parkingFloors = new ArrayList<>();
	private List<Entrance> entrances = new ArrayList<>();
	private List<Exit> exits = new ArrayList<>();

	private Address address;
	private String parkingLotName;

	// Check if any suitable parking space is available
	public boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle) {
		for (ParkingFloor floor : parkingFloors) {
			for (ParkingSpace space : floor.getParkingSpaces()) {
				if (space.isFree() && space.getParkingSpaceType() == getSpotType(vehicle.getVehicleType())) {
					return true;
				}
			}
		}
		return false;
	}

	// Example helper method to match vehicle type to parking space type
	private ParkingSpaceType getSpotType(VehicleType type) {
		switch (type) {
			case BIKE: return ParkingSpaceType.BIKE_PARKING;
			case CAR: return ParkingSpaceType.CAR_PARKING;
			case TRUCK: return ParkingSpaceType.TRUCK_PARKING;
			default: throw new IllegalArgumentException("Unknown vehicle type");
		}
	}

	public boolean updateParkingAttendant(ParkingAttendant parkingAttendant, int gateId) {
		for (Entrance entrance : entrances) {
			if (entrance.getGateId() == gateId) {
				entrance.setParkingAttendant(parkingAttendant);
				return true;
			}
		}
		for (Exit exit : exits) {
			if (exit.getGateId() == gateId) {
				exit.setParkingAttendant(parkingAttendant);
				return true;
			}
		}
		return false;
	}

	// Getters and setters
	public List<ParkingFloor> getParkingFloors() { return parkingFloors; }
	public List<Entrance> getEntrances() { return entrances; }
	public List<Exit> getExits() { return exits; }
}

// ======================================
//  FLOOR & SPACE
// ======================================

class ParkingFloor {

	private int levelId;
	private List<ParkingSpace> parkingSpaces = new ArrayList<>();
	private ParkingDisplayBoard parkingDisplayBoard;

	public int getLevelId() { return levelId; }
	public List<ParkingSpace> getParkingSpaces() { return parkingSpaces; }
	public ParkingDisplayBoard getParkingDisplayBoard() { return parkingDisplayBoard; }
}

class ParkingSpace {

	private int spaceId;
	private boolean isFree = true;
	private double costPerHour;
	private Vehicle vehicle;
	private ParkingSpaceType parkingSpaceType;

	public boolean isFree() { return isFree; }
	public void occupy(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.isFree = false;
	}

	public void vacate() {
		this.vehicle = null;
		this.isFree = true;
	}

	public double getCostPerHour() { return costPerHour; }
	public ParkingSpaceType getParkingSpaceType() { return parkingSpaceType; }
}

// ======================================
//  GATES & DISPLAY
// ======================================

abstract class Gate {

	protected int gateId;
	protected ParkingAttendant parkingAttendant;

	public int getGateId() { return gateId; }
	public ParkingAttendant getParkingAttendant() { return parkingAttendant; }
	public void setParkingAttendant(ParkingAttendant attendant) { this.parkingAttendant = attendant; }
}

class Entrance extends Gate {

	public ParkingTicket getParkingTicket(Vehicle vehicle) {
		ParkingTicket ticket = new ParkingTicket();
		ticket.setTicketId(new Random().nextInt(100000));
		ticket.setVehicleEntryDateTime(LocalDateTime.now());
		ticket.setParkingTicketStatus(ParkingTicketStatus.ACTIVE);
		vehicle.setParkingTicket(ticket);
		return ticket;
	}
}

class Exit extends Gate {

	private PricingStrategy pricingStrategy = new HourlyPricingStrategy();

	public PaymentInfo payForParking(ParkingTicket parkingTicket, PaymentType paymentType) {
		parkingTicket.setVehicleExitDateTime(LocalDateTime.now());
		parkingTicket.updateTotalCost(pricingStrategy);
		Payment payment = new Payment();
		return payment.makePayment(parkingTicket, paymentType);
	}
}

class ParkingDisplayBoard {

	private Map<ParkingSpaceType, Integer> freeSpotsAvailableMap = new HashMap<>();

	public void updateFreeSpotsAvailable(ParkingSpaceType type, int spaces) {
		freeSpotsAvailableMap.put(type, spaces);
	}

	public void displayStatus() {
		System.out.println("=== Parking Availability ===");
		for (Map.Entry<ParkingSpaceType, Integer> entry : freeSpotsAvailableMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}

// ======================================
//  ACCOUNT CLASSES
// ======================================

class Address {
	private String country;
	private String state;
	private String city;
	private String street;
	private String pinCode;
}

class Account {

	protected String name;
	protected String email;
	protected String password;
	protected String empId;
	protected Address address;
}

class Admin extends Account {

	public boolean addParkingFloor(ParkingLot lot, ParkingFloor floor) {
		return lot.getParkingFloors().add(floor);
	}

	public boolean addParkingSpace(ParkingFloor floor, ParkingSpace space) {
		return floor.getParkingSpaces().add(space);
	}

	public boolean addParkingDisplayBoard(ParkingFloor floor, ParkingDisplayBoard board) {
		floor.getParkingDisplayBoard().updateFreeSpotsAvailable(ParkingSpaceType.CAR_PARKING, 0);
		return true;
	}
}

class ParkingAttendant extends Account {

	private Payment paymentService = new Payment();

	public boolean processVehicleEntry(Vehicle vehicle) {
		ParkingTicket ticket = new Entrance().getParkingTicket(vehicle);
		vehicle.setParkingTicket(ticket);  // explicitly assign here
		// optionally mark parking space as occupied, update display, etc.
		return ticket!=null;
	}

	public PaymentInfo processPayment(ParkingTicket ticket, PaymentType type) {
		return paymentService.makePayment(ticket, type);
	}
}

// ======================================
//  VEHICLE, TICKET & PAYMENT
// ======================================

class Vehicle {

	private String licenseNumber;
	private VehicleType vehicleType;
	private ParkingTicket parkingTicket;
	private PaymentInfo paymentInfo;

	public VehicleType getVehicleType() { return vehicleType; }
	public ParkingTicket getParkingTicket() { return parkingTicket; }
	public void setParkingTicket(ParkingTicket ticket) { this.parkingTicket = ticket; }
}

class ParkingTicket {

	private int ticketId;
	private int levelId;
	private int spaceId;
	private LocalDateTime vehicleEntryDateTime;
	private LocalDateTime vehicleExitDateTime;
	private ParkingSpaceType parkingSpaceType;
	private double totalCost;
	private ParkingTicketStatus parkingTicketStatus;

	public void updateTotalCost(PricingStrategy strategy) {
		this.totalCost = strategy.calculatePrice(this);
	}

	public void updateVehicleExitTime(LocalDateTime exitTime) {
		this.vehicleExitDateTime = exitTime;
	}

	// Getters and setters
	public void setTicketId(int id) { this.ticketId = id; }
	public void setVehicleEntryDateTime(LocalDateTime dt) { this.vehicleEntryDateTime = dt; }
	public void setVehicleExitDateTime(LocalDateTime dt) { this.vehicleExitDateTime = dt; }
	public void setParkingTicketStatus(ParkingTicketStatus status) { this.parkingTicketStatus = status; }
	public LocalDateTime getVehicleEntryDateTime() { return vehicleEntryDateTime; }
	public LocalDateTime getVehicleExitDateTime() { return vehicleExitDateTime; }

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}

// ======================================
//  STRATEGY PATTERN FOR PRICING
// ======================================

interface PricingStrategy {
	double calculatePrice(ParkingTicket ticket);
}

class HourlyPricingStrategy implements PricingStrategy {
	@Override
	public double calculatePrice(ParkingTicket ticket) {
		long hours = Duration.between(ticket.getVehicleEntryDateTime(), ticket.getVehicleExitDateTime()).toHours();
		return Math.max(10, hours * 20.0); // base price 10
	}
}

// ======================================
//  PAYMENT SYSTEM
// ======================================

class Payment {

	public PaymentInfo makePayment(ParkingTicket ticket, PaymentType type) {
		PaymentInfo info = new PaymentInfo();
		info.setAmount(ticket.getTotalCost());
		info.setPaymentDate(LocalDateTime.now());
		info.setPaymentStatus(PaymentStatus.COMPLETED);
		info.setTransactionId(new Random().nextInt(1000000));
		return info;
	}
}

class PaymentInfo {

	private double amount;
	private LocalDateTime paymentDate;
	private int transactionId;
	private PaymentStatus paymentStatus;

	// Getters and setters
	public void setAmount(double a) { this.amount = a; }
	public void setPaymentDate(LocalDateTime dt) { this.paymentDate = dt; }
	public void setTransactionId(int id) { this.transactionId = id; }
	public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }
}

// ======================================
//  ENUMS
// ======================================

enum PaymentType {
	CASH, CREDIT_CARD, DEBIT_CARD, UPI
}

enum ParkingSpaceType {
	BIKE_PARKING, CAR_PARKING, TRUCK_PARKING
}

enum VehicleType {
	BIKE, CAR, TRUCK
}

enum ParkingTicketStatus {
	PAID, ACTIVE
}

enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED
}

/*
https://www.youtube.com/watch?v=V9NEOLpt3tg
class ParkingLot {
	
	List<ParkingFloor> parkingFloors;
	List<Entrance> entrances;
	List<Exit> exits;

	Address address; 

	String parkingLotName;

	public boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle);
	public boolean updateParkingAttndant(ParkingAttendant parkingAttendant, int gateId)

}

class ParkingFloor {
	
	int levelId;
	List<ParkingSpace> parkingSpaces;

	ParkingDisplayBoard parkingDisplayBoard;

}

class Gate {

	int gateId;
	ParkingAttendant parkingAttendant;

}

class Entrance extends Gate {

	public ParkingTicket getParkingTicket(Vehicle vehicle);

}

class Exit extends Gate {

	public ParkingTicket payForParking(ParkingTicket parkingTicket, PaymentType paymentType);

}


class Address {

	String country;
	String state;
	String city;
	String street;
	String pinCode; //ZipCode
}

class ParkingSpace {
	
	int spaceId;
	boolean isFree;
	double costPerHour;
	Vehicle vehicle;
	ParkingSpaceType parkingSpaceType;

}

class ParkingDisplayBoard {

	Map<ParkingSpaceType, Integer> freeSpotsAvailableMap;

	public void updateFreeSpotsAvailable(ParkingSpaceType parkingSpaceType, int spaces);

}


class Account {

	String name;
	String email;
	String password;
	String empId;
	Address address;

}

class Admin extends Account {

	public boolean addParkingFloor(ParkingLot parkingLot, ParkingFloor floor);
	public boolean addParkingSpace(ParkingFloor floor, ParkingSpace parkingSpace);
	public boolean addParkingDisplayBoard(ParkingFloor floor, ParkingDisplayBoard parkingDisplayBoard);
	.
	.
	.

}

class ParkingAttendant extends Account {

	Payment paymentService;

	public boolean processVehicleEntry(Vehicle vehicle);
	public PaymentInfo processPayment(ParkingTicket parkingTicket, PaymentType paymentType);

}

class Vehicle {
	
	String licenseNumber;
	VehicleType vehicleType;
	ParkingTicket parkingTicket;
	PaymentInfo paymentInfo;

}

class ParkingTicket {
	
	int ticketId;
	int levelId;
	int spaceId;
	Date vehicleEntryDateTime;
	Date vehicleExitDateTime;
	ParkingSpaceType parkingSpaceType;
	double totalCost;
	ParkingTicketStatus parkingTicketStatus;

	public void updateTotalCost();
	public void updateVehicleExitTime(Date vehicleExitDateTime);

} 

public enum PaymentType {

	CASH, CEDIT_CARD, DEBIT_CARD, UPI;
}

public enum ParkingSpaceType {
	
	BIKE_PARKING, CAR_PARKING, TRUCK_PARKING

}

class Payment {

	public PaymentInfo makePayment(ParkingTicket parkingTicket, PaymentType paymentType);
}

public class PaymentInfo {

	double amount;
	Date paymentDate;
	int transactionId;
	ParkingTicket parkingTicket;
	PaymentStatus paymentStatus;

}

public enum VehicleType {
	
	BIKE, CAR, TRUCK;
}

public enum ParkingTicketStatus {
	
	PAID, ACTIVE;
}

public enum PaymentStatus {

	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED;

}*/
