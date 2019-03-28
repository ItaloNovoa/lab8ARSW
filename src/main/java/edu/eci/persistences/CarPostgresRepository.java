package edu.eci.persistences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;


@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository{

	String dbUrl = "jdbc:postgresql://ec2-75-101-131-79.compute-1.amazonaws.com:5432/d1p0sj27t0c1kt?user=nacuxovudkvbue&password=865918416e2cbf7e629f43a812772fd1d979859e5fc743943b35e67bb1e177ea&sslmode=require";

	@Autowired    
	private DataSource dataSource;


	
	@Override
	public List<Car> findAll() {
		String query = "SELECT * FROM cars";
		List<Car> cars=new ArrayList<>();

		try(Connection connection = dataSource.getConnection()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()){
				Car car = new Car();
				car.setBrand(rs.getString("brand"));
				car.setLicencePlate(UUID.fromString(rs.getString("licencePlate")));
				cars.add(car);
			}
			return cars;
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}


	@Override
	public Car find(UUID id) {
		String query = "SELECT * FROM cars where id='"+id+"';";
		try(Connection connection = dataSource.getConnection()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Car car = new Car();
			car.setBrand(rs.getString("brand"));
			car.setLicencePlate(UUID.fromString(rs.getString("licencePlate")));
			return car;
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public UUID save(Car entity) {
		 String query = "insert into cars(licencePlate,brand) values ('"+entity.getLicencePlate()+"','"+entity.getBrand()+"');";
         System.out.println(query);
         try(Connection connection = dataSource.getConnection()){
             Statement stmt = connection.createStatement();
             stmt.execute(query);
             return entity.getLicencePlate();
         }catch (Exception e){
             System.out.println(e.getMessage());
             throw new RuntimeException(e);
         }
	}


	@Override
	public void update(Car entity) {
		String query = "update cars SET brand='"+entity.getBrand()+"'Where licencePlate='"+entity.getLicencePlate()+"';";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }




	@Override
	public void delete(Car o) {
		String query = "DELETE FROM cars WHERE licencePlate='"+o.getLicencePlate()+"';";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

	}




	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}




	@Override
	public Car getCarByLicensePlate(String licensePlate) {
		String query = "SELECT * FROM cars where licencePlatee='"+licensePlate+"';";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = new Car();
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate(UUID.fromString(rs.getString("licencePlate")));           
            return car;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
	}



	@Bean
	public DataSource dataSource1() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}

}
