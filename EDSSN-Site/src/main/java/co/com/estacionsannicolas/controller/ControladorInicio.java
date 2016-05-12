package co.com.estacionsannicolas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Antonio Paternina <acpaternina@poli.edu.co>
 */
@Controller
public class ControladorInicio extends ControladorBase {

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String irAHome() {
        return "index";
    }
}
