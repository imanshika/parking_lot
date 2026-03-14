package model;

public class UnparkResult {

    private final String ticketId;
    private final ParkingSpot parkingSpot;
    private final Vehicle vehicle;

    public UnparkResult(String ticketId, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "UnparkResult{" +
                "ticketId='" + ticketId + '\'' +
                ", parkingSpot=" + parkingSpot +
                ", vehicle=" + vehicle.getVehicleNumber() +
                '}';
    }
}

