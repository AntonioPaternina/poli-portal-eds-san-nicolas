package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import java.util.List;



public interface UserProfileService {

	RoleEntity findById(int id);

	RoleEntity findByType(UserRoleTypeEnum type);
	
	List<RoleEntity> findAll();
	
}
