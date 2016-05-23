package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.repositories.MarketingCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketingCampaignServiceImpl implements MarketingCampaignService {

    @Autowired
    private MarketingCampaignRepository marketingCampaignRepository;

    @Override
    public MarketingCampaignEntity save(MarketingCampaignEntity campaign) {
        return marketingCampaignRepository.saveAndFlush(campaign);
    }

    @Override
    public MarketingCampaignEntity findByName(String campaignName) {
        return marketingCampaignRepository.findByName(campaignName);
    }

}
