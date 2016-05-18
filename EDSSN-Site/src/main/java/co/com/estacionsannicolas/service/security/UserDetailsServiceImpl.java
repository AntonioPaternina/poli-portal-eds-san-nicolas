package co.com.estacionsannicolas.service.security;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.service.BaseService;
import co.com.estacionsannicolas.service.UserService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserBean user = userService.findByUsername(username);
        logger.info("Usuario : {}", user);
        if (user == null) {
            logger.info("Usuario no encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, getGrantedAuhtorities(user));
    }

    private List<GrantedAuthority> getGrantedAuhtorities(UserBean userBean) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        userBean.getUserRoles().stream().map((userProfile) -> {
            logger.info("PerfilUsuario : {}", userProfile);
            return userProfile;
        }).forEach((userProfile) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        });
        logger.info("perfiles: {}", grantedAuthorities);
        return grantedAuthorities;
    }

}
