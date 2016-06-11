package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.PersistentLoginEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public class PersistentTokenRepositoryImpl
        implements PersistentTokenRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentTokenRepository.class);

    @Autowired
    private PersistentLoginRepository loginRepositorio;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        LOGGER.info("Creando token para el usuario: {}", token.getUsername());
        PersistentLoginEntity persistentLogin = new PersistentLoginEntity();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLastUsed(token.getDate());
        loginRepositorio.saveAndFlush(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        LOGGER.info("Buscando token para: {}", seriesId);
        PersistentRememberMeToken persistentRememberMeToken = null;
        try {
            PersistentLoginEntity persistentLogin = loginRepositorio.findBySeries(seriesId);
            if (persistentLogin != null) {
                persistentRememberMeToken = new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                        persistentLogin.getToken(), persistentLogin.getLastUsed());
            } else {
                LOGGER.info("token no encontrado");
            }
        } catch (Exception e) {
            LOGGER.error("Error encontrando token para: {}", seriesId);
        }

        return persistentRememberMeToken;
    }

    @Override
    public void removeUserTokens(String username) {
        LOGGER.info("Eliminando token para usuario: {}", username);
        try {
            PersistentLoginEntity persistentLogin = loginRepositorio.findByUsername(username);
            if (persistentLogin != null) {
                LOGGER.info("rememberMe was selected");
                loginRepositorio.delete(persistentLogin);
            }
        } catch (Exception e) {
            LOGGER.error("Error al borrar el token para el usuario: {}", username);
        }
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        LOGGER.info("Actualizando token para el id: {}", seriesId);
        try {
            PersistentLoginEntity persistentLogin = loginRepositorio.findBySeries(seriesId);
            persistentLogin.setToken(tokenValue);
            persistentLogin.setLastUsed(lastUsed);
            loginRepositorio.saveAndFlush(persistentLogin);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar token para el id: {}", seriesId);
        }
    }
}
