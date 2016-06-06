package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;

import java.util.List;

public interface MarketingCampaignService {

    MarketingCampaignBean findByName(String campaignName);

    MarketingCampaignBean save(MarketingCampaignBean campaign);

    List<MarketingCampaignBean> findAll();
}
