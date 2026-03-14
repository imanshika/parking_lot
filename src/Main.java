import model.*;
import service.ParkingService;
import strategy.NearestSpotStrategy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void showOccupiedSlots(ParkingService parkingService){
        //Show occupied slots (per type)
        System.out.println("---------- Occupied slots (per type)-----------");
        System.out.println("Occupied slots for CAR: " + parkingService.getOccupiedSlots(VehicleType.CAR));
        System.out.println("Occupied slots for BIKE: " + parkingService.getOccupiedSlots(VehicleType.BIKE));
        System.out.println("Occupied slots for AUTO: " + parkingService.getOccupiedSlots(VehicleType.AUTO));
        System.out.println("Occupied slots for CYCLE: " + parkingService.getOccupiedSlots(VehicleType.CYCLE));
    }

    public static void showFreeSlots(ParkingService parkingService){
        //Show free slots (per type)
        System.out.println("---------- Free slots (per type)-----------");
        System.out.println("Free slot count for CAR: " + parkingService.getFreeSlots(VehicleType.CAR));
        System.out.println("Free slot count for BIKE: " + parkingService.getFreeSlots(VehicleType.BIKE));
        System.out.println("Free slot count for AUTO: " + parkingService.getFreeSlots(VehicleType.AUTO));
        System.out.println("Free slot count for CYCLE: " + parkingService.getFreeSlots(VehicleType.CYCLE));
    }

    public static void main(String[] args) {

        //Create parking lot
        ParkingLot parkingLot = new ParkingLot(10, 10);
        ParkingService parkingService = new ParkingService(parkingLot, new NearestSpotStrategy());
        

        //Park vehicle
        Vehicle vehicle = new Vehicle("1234567890", VehicleType.CAR, VehicleColor.RED);
        System.out.println("---------- Parking vehicle: " + vehicle.getVehicleNumber() + "-----------");
        Ticket ticket = parkingService.parkVehicle(vehicle);
        if (ticket != null) {
            System.out.println("Vehicle parked with ticket: " + ticket);
        } else {
            System.out.println("No available spot found");
        }

        // Show Available Slots
        showFreeSlots(parkingService);

        //Show occupied slots (per type)
        showOccupiedSlots(parkingService);

        //Unpark vehicle
        System.out.println("---------- Unpark vehicle: " + vehicle.getVehicleNumber() + "-----------");
        if (ticket != null) {
            parkingService.unparkVehicle(ticket);
        }

        //Show occupied slots (per type)
        showOccupiedSlots(parkingService);

        // Show Available Slots
        showFreeSlots(parkingService);

    }
}