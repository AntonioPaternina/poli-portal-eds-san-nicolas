package co.com.estacionsannicolas.repositorios;

import co.com.estacionsannicolas.model.LoginPersistido;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepository
        implements PersistentTokenRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateTokenRepository.class);

    @Autowired
    private LoginPersistidoRepositorio loginRepositorio;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        LOGGER.info("Creando token para el usuario: {}", token.getUsername());
        LoginPersistido persistentLogin = new LoginPersistido();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        loginRepositorio.saveAndFlush(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        LOGGER.info("Buscando token para: {}", seriesId);
        PersistentRememberMeToken persistentRememberMeToken = null;
        try {
            LoginPersistido persistentLogin = loginRepositorio.findOne(seriesId);
            if (persistentLogin != null) {
                persistentRememberMeToken = new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                        persistentLogin.getToken(), persistentLogin.getLast_used());
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
            LoginPersistido persistentLogin = loginRepositorio.findByUsername(username);
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
            LoginPersistido persistentLogin = loginRepositorio.findOne(seriesId);
            persistentLogin.setToken(tokenValue);
            persistentLogin.setLast_used(lastUsed);
            loginRepositorio.saveAndFlush(persistentLogin);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar token para el id: {}", seriesId);
        }
    }
}
