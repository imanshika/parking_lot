package service;

import model.Floor;
import model.ParkingLot;
import model.ParkingSpot;
import model.Ticket;
import model.UnparkResult;
import model.Vehicle;
import model.VehicleType;
import strategy.ParkingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final ParkingStrategy parkingStrategy;

    // ticketId -> spot (vehicle is stored inside spot)
    private final Map<String, ParkingSpot> ticketToSpot = new HashMap<>();

    public ParkingService(ParkingLot parkingLot, ParkingStrategy parkingStrategy) {
        this.parkingLot = Objects.requireNonNull(parkingLot);
        this.parkingStrategy = Objects.requireNonNull(parkingStrategy);
    }

    public Optional<Ticket> park(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingStrategy.findSpot(parkingLot, vehicle.getVehicleType());
        if (parkingSpot == null) {
            return Optional.empty();
        }

        parkingSpot.occupy(vehicle);
        Ticket ticket = Ticket.create(parkingSpot, vehicle);
        ticketToSpot.put(ticket.getTicketId(), parkingSpot);
        return Optional.of(ticket);
    }

    public Optional<UnparkResult> unpark(String ticketId) {
        ParkingSpot parkingSpot = ticketToSpot.remove(ticketId);
        if (parkingSpot == null) {
            return Optional.empty();
        }

        Vehicle vehicle = parkingSpot.vacate();
        if (vehicle == null) {
            return Optional.empty();
        }

        return Optional.of(new UnparkResult(ticketId, parkingSpot, vehicle));
    }

    public long getFreeSlotCount(VehicleType vehicleType) {
        return parkingLot.getFloors().stream()
                .flatMap(floor -> floor.getParkingSpotList().stream())
                .filter(spot -> spot.getSpotType() == vehicleType && spot.isAvailable())
                .count();
    }

    public List<ParkingSpot> getFreeSlots(VehicleType vehicleType) {
        return parkingLot.getFloors().stream()
                .flatMap(floor -> floor.getParkingSpotList().stream())
                .filter(spot -> spot.getSpotType() == vehicleType && spot.isAvailable())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<ParkingSpot> getOccupiedSlots(VehicleType vehicleType) {
        return parkingLot.getFloors().stream()
                .flatMap(floor -> floor.getParkingSpotList().stream())
                .filter(spot -> spot.getSpotType() == vehicleType && !spot.isAvailable())
                .collect(Collectors.toUnmodifiableList());
    }
}

