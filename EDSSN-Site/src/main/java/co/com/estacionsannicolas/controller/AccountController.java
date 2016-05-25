package co.com.estacionsannicolas.controller;

import co.com.estacionsannicolas.beans.AwardPointBean;
import co.com.estacionsannicolas.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/award-points", method = RequestMethod.GET)
    public List<AwardPointBean> getAwardPointsForCurrentUser() {
        return accountService.findAwardPointsByUser(getCurrentUser());
    }
}
