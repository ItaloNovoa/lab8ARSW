package edu.eci.services;

import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import edu.eci.services.contracts.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServices implements IUserServices{

    @Autowired
    @Qualifier("UserPostgresRepository")
    private IUserRepository userRepository;

    @Override
    public List<User> list() {
    	User u=new User("italo", new UUID(3, 7));
    	userRepository.save(u);
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(user.getId()) != null)
            throw new RuntimeException("The user exists");
        else
            userRepository.save(user);
        return user;
    }

    @Override
    public User get(UUID id) {    	
        return userRepository.find(id);
    }

    @Override
    public User get(String name) {
        return userRepository.getUserByUserName(name);
    }
    

    @Override
	public User updateUser(User user) {
		if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(user.getId()) == null)
        	userRepository.save(user);            
        else {
        	User us1=userRepository.find(user.getId());
        	us1.setName(user.getName());
        	userRepository.update(us1);
        	return us1;	
        }
        return user;		
	}

	@Override
	public User delete(UUID id) {
		System.out.println("hola");
		if(null == id)
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(id) == null)
        	throw new RuntimeException("Usuario no se encuentra");          
        else {
        	User us1=userRepository.find(id);
        	userRepository.delete(us1);
        	return us1;	
        }
	}
}
