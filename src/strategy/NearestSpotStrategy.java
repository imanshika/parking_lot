package strategy;

import model.Floor;
import model.ParkingLot;
import model.ParkingSpot;
import model.VehicleType;

public class NearestSpotStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        for (Floor floor : parkingLot.getFloors()) {
            for (ParkingSpot spot : floor.getParkingSpotList()) {
                if (spot.isAvailable() && spot.getSpotType() == vehicleType) {
                    return spot;
                }
            }
        }
        return null;
    }
}

