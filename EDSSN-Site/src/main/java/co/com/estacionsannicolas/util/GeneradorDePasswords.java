package co.com.estacionsannicolas.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Esta clase de utilidad se puede usar para codificar una contraseña de usuario
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
public class GeneradorDePasswords {

    public static void main(String[] args) {
        String password = "test";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }
}
