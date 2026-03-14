package model;

public class Ticket {
    private final String ticketId;
    private final int floorNumber;
    private final int spotNumber;
    private final String vehicleNumber;

    private Ticket(String ticketId, int floorNumber, int spotNumber, String vehicleNumber) {
        this.ticketId = ticketId;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.vehicleNumber = vehicleNumber;
    }

    public static Ticket create(ParkingSpot spot, Vehicle vehicle) {
        String id = "F" + spot.getFloorNumber()
                + "-S" + spot.getSpotNumber()
                + "-" + vehicle.getVehicleNumber();
        return new Ticket(id, spot.getFloorNumber(), spot.getSpotNumber(), vehicle.getVehicleNumber());
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", floorNumber=" + floorNumber +
                ", spotNumber=" + spotNumber +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}

