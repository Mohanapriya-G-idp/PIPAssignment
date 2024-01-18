package org.zemoso.interfaces;

import org.zemoso.inheritance.Transport;

public class Car extends Transport implements Vehicle {
    @Override
    public void fuelType() {
        System.out.println("Fuel type: Petrol");
    }

    @Override
    public void tankCapacity() {
        System.out.println("tank capacity : 10 lit");
    }

    @Override
    public void countOfGears() {
        System.out.println("no.of gears: 6");

    }

    @Override
    public void positionOfGear() {
        System.out.println("position of gear : hand");

    }

    @Override
    public void driverControl() {
        System.out.println("driver control: steering");
    }

    @Override
    public void countOfWheels() {
        System.out.println("count of wheels: 4");

    }

    @Override
    public void brakeType() {
        System.out.println("brake type : disc and drums");
    }
    public static void methodGears(Gears gears){
        gears.countOfGears();
        gears.positionOfGear();
    }
    public static void methodWheels(Wheels wheels){
        wheels.countOfWheels();
        wheels.brakeType();
    }
    public static void methodFuels(Fuels fuels){
        fuels.fuelType();
        fuels.tankCapacity();
    }
public static void methodVehicle(Vehicle vehicle){
        vehicle.driverControl();
}
    public static void main(String[] args) {
        Car c = new Car();
        methodFuels(c);
        methodVehicle(c);
        methodGears(c);

    }
}
