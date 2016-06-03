package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.AwardPointBean;
import co.com.estacionsannicolas.service.AccountService;
import co.com.estacionsannicolas.service.PromotionCodeService;
import co.com.estacionsannicolas.service.exceptions.ServiceException;
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

    @Autowired
    private PromotionCodeService promotionCodeService;

    @RequestMapping(value = "/award-points", method = RequestMethod.GET)
    public List<AwardPointBean> getAwardPointsForCurrentUser() {
        return accountService.findAwardPointsByUser(getCurrentUser());
    }

    @RequestMapping(value = "/assign-promotion-code", method = RequestMethod.POST)
    public void assignPromotionCodeToCurrentUser(String code) throws ServiceException {
        try {
            promotionCodeService.usePromotionCode(getCurrentUser(), code);
        } catch (Exception e) {
            logger.error("Error assigning promotion code {}", code);
        }
    }
}
