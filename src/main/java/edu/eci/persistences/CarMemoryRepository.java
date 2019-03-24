package edu.eci.persistences;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.ICarRepository;

@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository{

	
	public static List<Car> carsContainer;
    public static List<Car> getContainer(){
        if(CarMemoryRepository.carsContainer == null)
            CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }
	
    @Override
	public Car getCarByLicensePlate(String licensePlate) {
    	return CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> licensePlate.equals(u.getLicencePlate().toString()))
                .findFirst()
                .get();
	}
    
	@Override
	public List<Car> findAll() {
		return CarMemoryRepository.getContainer();
	}

	@Override
	public Car find(UUID id) {
		 Optional<Car> answer = CarMemoryRepository.getContainer()
	                .stream()
	                .filter(u -> id.equals(u.getLicencePlate()))
	                .findFirst();
	        return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public UUID save(Car entity) {
		CarMemoryRepository.getContainer().add(entity);
        return entity.getLicencePlate();
        
	}

	@Override
	public void update(Car entity) {
		CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .map(u -> u.getLicencePlate().equals(entity.getLicencePlate()) ? entity : u)
                .collect(toList());
		
	}

	@Override
	public void delete(Car o) {
		 CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
	                .stream()
	                .filter(u -> !u.getLicencePlate().equals(o.getLicencePlate()))
	                .collect(toList());		
	}

	@Override
	public void remove(Long licencePlate) {
		CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> !u.getLicencePlate().equals(licencePlate))
                .collect(toList());
		
	}

	

}
