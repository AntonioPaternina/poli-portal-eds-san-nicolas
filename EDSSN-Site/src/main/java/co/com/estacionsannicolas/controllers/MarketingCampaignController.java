package co.com.estacionsannicolas.controllers;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.service.MarketingCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marketing-campaigns")
public class MarketingCampaignController extends BaseController {

    @Autowired
    private MarketingCampaignService marketingCampaignService;

    @Secured(RoleEntity.ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<MarketingCampaignBean> getAll() {
        return marketingCampaignService.findAll();
    }
}
