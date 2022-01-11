package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Car {
    String address;
    String make;
    String model;
    String year;
    String engine;
    String fuel;
    String gear;
    String wheelsDrive;
    String doors;
    String seats;
    String carClass;
    String fuelConsumption;
    String carRegistrationNumber;
    String price;
    String distanceIncluded;
    String features;
    String about;
    String photoPath;
}
