package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.entities.MarketingCampaignEntity;

public interface MarketingCampaignService {

    MarketingCampaignEntity findByName(String campaignName);

    MarketingCampaignEntity save(MarketingCampaignEntity campaign);
}
