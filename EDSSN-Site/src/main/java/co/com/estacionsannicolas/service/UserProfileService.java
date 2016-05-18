package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.UserRoleEntity;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;



public interface UserProfileService {

	UserRoleEntity findById(int id);

	UserRoleEntity findByType(UserRoleTypeEnum type);
	
	List<UserRoleEntity> findAll();
	
}
