package edu.eci.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;

@Component
public class CarServices implements ICarServices {

	@Autowired
	@Qualifier("CarPostgresRepository")
	private ICarRepository carRepository;

	@Override
	public List<Car> list() {
		//Car c=new Car(new UUID(1, 5), "mercedes");
		//carRepository.save(c);
		return carRepository.findAll();
	}

	@Override
	public Car create(Car car) {
		 if(null == car.getLicencePlate())
	            throw new RuntimeException("LicencePlate invalid");
	        else if(carRepository.find(car.getLicencePlate()) != null)
	            throw new RuntimeException("The user exists");
	        else
	            carRepository.save(car);
	        return car;
	}

	@Override
	public Car get(UUID licencePlate) {
		return carRepository.find(licencePlate);
	}
		

	@Override
	public Car updateCar(Car car) {
		if(null ==car.getLicencePlate())
            throw new RuntimeException("LicencePlate invalid");
        else if(carRepository.find(car.getLicencePlate()) == null)
        	carRepository.save(car);			 
        else {
        	Car car1=carRepository.find(car.getLicencePlate());
        	car1.setBrand(car.getBrand());
        	carRepository.update(car1);
        	return car1;	
        }
		return car;
	}

	@Override
	public Car delete(UUID licencePlate) {
		System.out.println("hola");
		if(null == licencePlate)
            throw new RuntimeException("licencePlate invalid");
        else if(carRepository.find(licencePlate) == null)
        	throw new RuntimeException("carro no se encuentra");          
        else {
        	Car car1=carRepository.find(licencePlate);
        	carRepository.delete(car1);
        	return car1;	
        }
	}

	

}
