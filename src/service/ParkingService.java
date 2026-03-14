package service;

import model.Floor;
import model.ParkingLot;
import model.ParkingSpot;
import model.Ticket;
import model.Vehicle;
import model.VehicleType;
import strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final ParkingStrategy parkingStrategy;
    private final Map<String, ParkingSpot> ticketSpotMap = new HashMap<>();
    private final Map<String, Vehicle> ticketVehicleMap = new HashMap<>();

    public ParkingService(ParkingLot parkingLot, ParkingStrategy parkingStrategy) {
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingStrategy.findSpot(parkingLot, vehicle.getVehicleType());
        if (parkingSpot == null) {
            return null;
        }

        parkingSpot.setParkedVehicle(vehicle);
        parkingSpot.setAvailable(false);

        Ticket ticket = Ticket.create(parkingSpot, vehicle);
        ticketSpotMap.put(ticket.getTicketId(), parkingSpot);
        ticketVehicleMap.put(ticket.getTicketId(), vehicle);

        return ticket;
    }

    public void unparkVehicle(Ticket ticket) {
        if (ticket == null) {
            return;
        }

        ParkingSpot parkingSpot = ticketSpotMap.remove(ticket.getTicketId());
        Vehicle vehicle = ticketVehicleMap.remove(ticket.getTicketId());

        if (parkingSpot == null || vehicle == null) {
            return;
        }

        parkingSpot.setParkedVehicle(null);
        parkingSpot.setAvailable(true);

    }

    public int getFreeSlotCount(VehicleType vehicleType){

        int freeSlotCount = 0;

        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot parkingSpot : floor.getParkingSpotList()) {
                if (parkingSpot.isAvailable() && parkingSpot.getSpotType() == vehicleType) {
                    freeSlotCount++;
                }
            }
        }
        return freeSlotCount;
    }

    public List<ParkingSpot> getFreeSlots(VehicleType vehicleType){

        List<ParkingSpot> freeSlots = new ArrayList<>();

        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot parkingSpot : floor.getParkingSpotList()) {
                if (parkingSpot.isAvailable() && parkingSpot.getSpotType() == vehicleType) {
                    freeSlots.add(parkingSpot);
                }
            }
        }
        return freeSlots;
    }

    public List<ParkingSpot> getOccupiedSlots(VehicleType vehicleType){

        List<ParkingSpot> occupiedSlots = new ArrayList<>();

        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot parkingSpot : floor.getParkingSpotList()) {
                if (!parkingSpot.isAvailable() && parkingSpot.getSpotType() == vehicleType) {
                    occupiedSlots.add(parkingSpot);
                }
            }
        }
        return occupiedSlots;
    }
}
