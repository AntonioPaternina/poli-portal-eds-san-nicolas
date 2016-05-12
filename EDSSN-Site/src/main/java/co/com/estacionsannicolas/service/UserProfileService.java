package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.model.UserProfile;
import java.util.List;



public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
