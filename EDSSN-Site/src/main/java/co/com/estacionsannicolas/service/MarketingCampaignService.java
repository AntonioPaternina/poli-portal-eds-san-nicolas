package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;

public interface MarketingCampaignService {

    MarketingCampaignBean findByName(String campaignName);

    MarketingCampaignBean save(MarketingCampaignBean campaign);
}
