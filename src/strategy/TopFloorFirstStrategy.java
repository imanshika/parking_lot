package strategy;

import model.Floor;
import model.ParkingLot;
import model.ParkingSpot;
import model.VehicleType;

/**
 * Example alternative strategy:
 * prefers higher floors first, then lowest spot number on that floor.
 */
public class TopFloorFirstStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        // Iterate floors from top to bottom
        for (int i = parkingLot.getFloors().size() - 1; i >= 0; i--) {
            Floor floor = parkingLot.getFloors().get(i);
            for (ParkingSpot spot : floor.getParkingSpotList()) {
                if (spot.isAvailable() && spot.getSpotType() == vehicleType) {
                    return spot;
                }
            }
        }
        return null;
    }
}

