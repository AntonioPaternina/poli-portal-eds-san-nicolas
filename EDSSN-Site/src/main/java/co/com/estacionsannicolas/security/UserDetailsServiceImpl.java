package co.com.estacionsannicolas.security;

import co.com.estacionsannicolas.model.User;
import co.com.estacionsannicolas.service.UserService;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.buscarPorSsoId(ssoId);
        LOGGER.info("Usuario : {}", user);
        if (user == null) {
            LOGGER.info("Usuario no encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
                true, true, true, true, getPerfilesDelUsuario(user));
    }

    private List<GrantedAuthority> getPerfilesDelUsuario(User usuario) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        usuario.getUserProfiles().stream().map((userProfile) -> {
            LOGGER.info("PerfilUsuario : {}", userProfile);
            return userProfile;
        }).forEach((userProfile) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        });
        LOGGER.info("perfiles: {}", grantedAuthorities);
        return grantedAuthorities;
    }

}
