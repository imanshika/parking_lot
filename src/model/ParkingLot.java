package model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int numberOfFloors;
    private final int spotsPerFloor;
    private final List<Floor> floors;

    public ParkingLot(int numberOfFloors, int spotsPerFloor) {
        this.numberOfFloors = numberOfFloors;
        this.spotsPerFloor = spotsPerFloor;
        this.floors = createFloors();
    }

    private List<Floor> createFloors() {
        List<Floor> floors = new ArrayList<>();

        for (int floorNo = 1; floorNo <= numberOfFloors; floorNo++) {
            List<ParkingSpot> spots = new ArrayList<>();

            for (int spotNo = 1; spotNo <= spotsPerFloor; spotNo++) {
                VehicleType type = getSpotTypeForIndex(spotNo);
                spots.add(new ParkingSpot(floorNo, spotNo, type));
            }

            floors.add(new Floor(floorNo, spots));
        }

        return floors;
    }

    private VehicleType getSpotTypeForIndex(int spotNo) {
        if (spotNo <= 2) {
            return VehicleType.CYCLE;
        } else if (spotNo <= 5) {
            return VehicleType.BIKE;
        } else if (spotNo <= 7) {
            return VehicleType.AUTO;
        } else {
            return VehicleType.CAR;
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getSpotsPerFloor() {
        return spotsPerFloor;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
