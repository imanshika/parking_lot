package model;

public class ParkingSpot {

    private final int floorNumber;
    private final int spotNumber;
    private final VehicleType spotType;
    private Vehicle parkedVehicle; // null => free

    public ParkingSpot(int floorNumber, int spotNumber, VehicleType spotType) {
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void occupy(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Spot already occupied");
        }
        if (vehicle.getVehicleType() != spotType) {
            throw new IllegalArgumentException("Incompatible vehicle type for this spot");
        }
        this.parkedVehicle = vehicle;
    }

    public Vehicle vacate() {
        Vehicle previous = this.parkedVehicle;
        this.parkedVehicle = null;
        return previous;
    }

    @Override
    public String toString() {
        return "Floor " + floorNumber + " - Spot " + spotNumber + " (" + spotType + ")";
    }
}
