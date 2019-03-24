package edu.eci.persistences.repositories;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import edu.eci.models.Car;

@Repository
public interface ICarRepository extends DAO<Car, UUID> {
	
	Car getCarByLicensePlate(String licensePlate);

}