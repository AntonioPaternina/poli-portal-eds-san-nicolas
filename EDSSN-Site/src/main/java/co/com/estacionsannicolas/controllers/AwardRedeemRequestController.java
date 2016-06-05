package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.AwardRedeemRequestBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.service.AwardRedeemRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/award-redeem-request")
public class AwardRedeemRequestController extends BaseController {

    @Autowired
    private AwardRedeemRequestService awardRedeemRequestService;

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public AwardRedeemRequestBean redeemAwardForUserAndCampaign(@RequestBody AwardRedeemRequestBean awardRedeemRequest) {
        return awardRedeemRequestService.create(awardRedeemRequest);
    }
}
