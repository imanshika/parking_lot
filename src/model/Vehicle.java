package model;

public class Vehicle {
    private final String vehicleNumber;
    private final VehicleType vehicleType;
    private VehicleColor vehicleColor;

    public Vehicle(String vehicleNumber, VehicleType vehicleType, VehicleColor vehicleColor) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }
}
