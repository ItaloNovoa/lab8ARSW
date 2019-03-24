package edu.eci.models;

import java.io.Serializable;
import java.util.UUID;

public class Car implements Serializable{

    private UUID licencePlate;
    private String brand;
    
    
    
	public Car() {
		
	}

	public Car(UUID licencePlate, String brand) {
		
		this.licencePlate = licencePlate;
		this.brand = brand;
	}

	public UUID getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(UUID licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	

    

}
