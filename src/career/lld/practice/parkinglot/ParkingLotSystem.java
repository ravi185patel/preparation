package career.lld.practice.parkinglot;

import career.lld.practice.parkinglot.constant.VehicleType;
import career.lld.practice.parkinglot.pojo.*;
import career.lld.practice.parkinglot.pojo.payment.CardPaymentStrategy;
import career.lld.practice.parkinglot.pojo.payment.PaymentStrategy;
import career.lld.practice.parkinglot.pojo.price.HourlyPricingStrategy;
import career.lld.practice.parkinglot.pojo.spot.NearestSpotStrategy;
import career.lld.practice.parkinglot.pojo.spot.SpotAllocationStrategy;
import career.lld.practice.parkinglot.service.ParkingProcess;
import career.lld.practice.parkinglot.service.ParkingSpotManager;
import career.lld.practice.parkinglot.service.VehicleFactory;

import java.util.Arrays;

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingFloor floor = new ParkingFloor("1", Arrays.asList(
        new ParkingSpot("1","1", VehicleType.CAR),
        new ParkingSpot("2","1", VehicleType.BIKE),
        new ParkingSpot("3","1", VehicleType.CAR)
        ));

        ParkingFloor floor1 = new ParkingFloor("2", Arrays.asList(
                new ParkingSpot("1","2", VehicleType.CAR),
                new ParkingSpot("2","2", VehicleType.BIKE),
                new ParkingSpot("3","2", VehicleType.CAR)
        ));
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
        EntryGate entryGate = new EntryGate(parkingSpotManager);
        ExitGate exitGate = new ExitGate(parkingSpotManager,new HourlyPricingStrategy());

        SpotAllocationStrategy spotStrategy = new NearestSpotStrategy();
        PaymentStrategy paymentStrategy = new CardPaymentStrategy();
        ParkingProcess process =
                new ParkingProcess(spotStrategy, paymentStrategy);

        VehicleFactory vehicleFactory = new VehicleFactory();
        Ticket t = process.processParking(vehicleFactory.getVehicle(VehicleType.CAR,"MH01AB1234"));
        System.out.println(t);
    }

    public static void thirdApproach(String[] args) {
        ParkingFloor floor = new ParkingFloor("1", Arrays.asList(
                new ParkingSpot("1","1", VehicleType.CAR),
                new ParkingSpot("2","1", VehicleType.BIKE),
                new ParkingSpot("3","1", VehicleType.CAR)
        ));

        ParkingFloor floor1 = new ParkingFloor("2", Arrays.asList(
                new ParkingSpot("1","2", VehicleType.CAR),
                new ParkingSpot("2","2", VehicleType.BIKE),
                new ParkingSpot("3","2", VehicleType.CAR)
        ));
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
        EntryGate entryGate = new EntryGate(parkingSpotManager);
        ExitGate exitGate = new ExitGate(parkingSpotManager,new HourlyPricingStrategy());
        ParkingLot parkingLot = ParkingLot.getParkingLot("1",Arrays.asList(floor1,floor),entryGate,exitGate);
        for(ParkingFloor parkingFloor:parkingLot.getParkingFloors()){
            for(ParkingSpot spot:parkingFloor.getParkingSpots()) {
                parkingSpotManager.addParkingSpot(spot);
            }
        }
        VehicleFactory vehicleFactory = new VehicleFactory();
        Ticket ticket = parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123"));
        System.out.println(ticket);
        System.out.println(parkingLot.unParkVehicle(ticket));
    }
    public static void secondApproach(String[] args) {
        ParkingFloor floor = new ParkingFloor("1", Arrays.asList(
                new ParkingSpot("1","1", VehicleType.CAR),
                new ParkingSpot("2","1", VehicleType.BIKE),
                new ParkingSpot("3","1", VehicleType.CAR)
        ));

        ParkingFloor floor1 = new ParkingFloor("2", Arrays.asList(
                new ParkingSpot("1","2", VehicleType.CAR),
                new ParkingSpot("2","2", VehicleType.BIKE),
                new ParkingSpot("3","2", VehicleType.CAR)
        ));
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
        ParkingLot parkingLot = ParkingLot.getParkingLot("1",Arrays.asList(floor1,floor),parkingSpotManager);
        for(ParkingFloor parkingFloor:parkingLot.getParkingFloors()){
            for(ParkingSpot spot:parkingFloor.getParkingSpots()) {
                parkingSpotManager.addParkingSpot(spot);
            }
        }
        VehicleFactory vehicleFactory = new VehicleFactory();
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
    }
//    public static void firstApproach(){
//        ParkingFloor floor = new ParkingFloor("1", Arrays.asList(
//                new ParkingSpot("1","1", VehicleType.CAR),
//                new ParkingSpot("2","1", VehicleType.BIKE),
//                new ParkingSpot("3","1", VehicleType.CAR)
//        ));
//
//        ParkingFloor floor1 = new ParkingFloor("2", Arrays.asList(
//                new ParkingSpot("1","2", VehicleType.CAR),
//                new ParkingSpot("2","2", VehicleType.BIKE),
//                new ParkingSpot("3","2", VehicleType.CAR)
//        ));
//        ParkingLot parkingLot = new ParkingLot("1",Arrays.asList(floor1,floor));
//        VehicleFactory vehicleFactory = new VehicleFactory();
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"123")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.BIKE,"124")));
//        System.out.println(parkingLot.parkVehicle(vehicleFactory.getVehicle(VehicleType.CAR,"125")));
//    }
}
