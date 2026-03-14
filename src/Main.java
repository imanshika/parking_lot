import model.ParkingLot;
import model.Ticket;
import model.Vehicle;
import model.VehicleColor;
import model.VehicleType;
import service.ParkingService;
import strategy.NearestSpotStrategy;

import java.util.Optional;

public class Main {

    public static void showOccupiedSlots(ParkingService parkingService){
        System.out.println("---------- Occupied slots (per type)-----------");
        System.out.println("Occupied slots for CAR: " + parkingService.getOccupiedSlots(VehicleType.CAR));
        System.out.println("Occupied slots for BIKE: " + parkingService.getOccupiedSlots(VehicleType.BIKE));
        System.out.println("Occupied slots for AUTO: " + parkingService.getOccupiedSlots(VehicleType.AUTO));
        System.out.println("Occupied slots for CYCLE: " + parkingService.getOccupiedSlots(VehicleType.CYCLE));
    }

    public static void showFreeSlots(ParkingService parkingService){
        System.out.println("---------- Free slots (per type)-----------");
        System.out.println("Free slots for CAR: " + parkingService.getFreeSlots(VehicleType.CAR));
        System.out.println("Free slots for BIKE: " + parkingService.getFreeSlots(VehicleType.BIKE));
        System.out.println("Free slots for AUTO: " + parkingService.getFreeSlots(VehicleType.AUTO));
        System.out.println("Free slots for CYCLE: " + parkingService.getFreeSlots(VehicleType.CYCLE));
        System.out.println("Free slot count for CAR: " + parkingService.getFreeSlotCount(VehicleType.CAR));
    }

    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot(10, 10);
        ParkingService parkingService = new ParkingService(parkingLot, new NearestSpotStrategy());

        Vehicle vehicle = new Vehicle("1234567890", VehicleType.CAR, VehicleColor.RED);
        System.out.println("---------- Parking vehicle: " + vehicle.getVehicleNumber() + "-----------");

        Optional<Ticket> maybeTicket = parkingService.park(vehicle);
        if (maybeTicket.isPresent()) {
            Ticket ticket = maybeTicket.get();
            System.out.println("Vehicle parked with ticket: " + ticket);

            showFreeSlots(parkingService);
            showOccupiedSlots(parkingService);

            System.out.println("---------- Unpark vehicle: " + vehicle.getVehicleNumber() + "-----------");
            parkingService.unpark(ticket.getTicketId())
                    .ifPresentOrElse(
                            result -> System.out.println("Unparked: " + result),
                            () -> System.out.println("Invalid ticket")
                    );

            showOccupiedSlots(parkingService);
            showFreeSlots(parkingService);
        } else {
            System.out.println("No available spot found");
        }
    }
}