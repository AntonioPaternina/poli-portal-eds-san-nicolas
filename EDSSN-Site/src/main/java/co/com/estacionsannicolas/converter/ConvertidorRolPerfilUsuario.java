package co.com.estacionsannicolas.converter;

import co.com.estacionsannicolas.entities.UserRoleEntity;
import co.com.estacionsannicolas.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class ConvertidorRolPerfilUsuario implements Converter<Object, UserRoleEntity> {

    static final Logger logger = LoggerFactory.getLogger(ConvertidorRolPerfilUsuario.class);

    @Autowired
    private UserProfileService userProfileService;

    /**
     * Gets UserProfile by Id
     *
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserRoleEntity convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserRoleEntity profile = userProfileService.findById(id);
        logger.info("Profile : {}", profile);
        return profile;
    }

}
