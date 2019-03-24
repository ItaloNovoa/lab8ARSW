package edu.eci.services.contracts;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.eci.models.Car;


@Service
public interface ICarServices {
	List<Car> list();
    Car create(Car car);
    Car get(String licencePlate);
	Car updateCar(Car car);
	Car delete(String licencePlate);
	 
	 
}
