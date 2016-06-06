package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;

import java.util.List;

public interface AwardService {

    AwardBean save(AwardBean awardToSave);

    List<AwardBean> getAwardsForMarketingCampaign(Long marketingCampaignId);

    List<AwardBean> getAll();
}
