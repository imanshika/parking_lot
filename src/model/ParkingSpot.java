package model;

public class ParkingSpot {
    public int getFloorNumber() {
        return floorNumber;
    }

    private final int floorNumber;
    private final int spotNumber;
    private final VehicleType spotType;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(int floorNumber, int spotNumber, VehicleType spotType) {
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.parkedVehicle = null;
        this.isAvailable = true;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    @Override
    public String toString() {
        return "Floor " + floorNumber + " - Spot " + spotNumber + " (" + spotType + ")";
    }
}
