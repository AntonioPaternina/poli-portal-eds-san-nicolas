package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;
import co.com.estacionsannicolas.service.exceptions.RequiredParameterException;

import java.util.List;

public interface AwardService {

    AwardBean save(AwardBean awardToSave) throws RequiredParameterException;

    List<AwardBean> getAwardsForMarketingCampaign(Long marketingCampaignId);

    List<AwardBean> getAll();

    void delete(Long awardId);
}
