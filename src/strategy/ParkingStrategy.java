package strategy;

import model.ParkingLot;
import model.ParkingSpot;
import model.VehicleType;

public interface ParkingStrategy {

    ParkingSpot findSpot(ParkingLot parkingLot, VehicleType vehicleType);
}

