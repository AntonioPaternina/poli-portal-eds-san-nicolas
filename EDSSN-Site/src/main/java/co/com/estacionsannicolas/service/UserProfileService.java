package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import java.util.List;



public interface UserProfileService {

	RoleEntity findById(int id);

	RoleEntity findByType(RoleTypeEnum type);
	
	List<RoleEntity> findAll();
	
}
