package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

	 String dbUrl = "jdbc:postgresql://ec2-75-101-131-79.compute-1.amazonaws.com:5432/d1p0sj27t0c1kt?user=nacuxovudkvbue&password=865918416e2cbf7e629f43a812772fd1d979859e5fc743943b35e67bb1e177ea&sslmode=require";


    @Autowired    
    private DataSource dataSource;

    @Override
    public User getUserByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAll() { 
    	System.out.println(dbUrl+" dd");
        String query = "SELECT * FROM users";
        List<User> users=new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(UUID id) {
        return null;
    }

    @Override
    public UUID save(User entity) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User o) {

    }

    @Override
    public void remove(Long id) {

    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
