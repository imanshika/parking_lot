package model;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpotList;

    public Floor(int floorNumber, List<ParkingSpot> parkingSpotList) {
        this.floorNumber = floorNumber;
        this.parkingSpotList = parkingSpotList;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

}