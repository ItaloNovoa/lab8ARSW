package edu.eci.persistences;

import java.util.List;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;

public class CarMemoryRepository implements ICarRepository{

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Car entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Car entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Car o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Car getCarByLicensePlate(String licensePlate) {
		// TODO Auto-generated method stub
		return null;
	}

}
