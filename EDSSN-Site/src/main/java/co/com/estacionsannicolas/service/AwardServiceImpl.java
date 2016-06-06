package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;
import co.com.estacionsannicolas.entities.AwardEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.repositories.AwardRepository;
import co.com.estacionsannicolas.repositories.MarketingCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AwardServiceImpl extends BaseService implements AwardService {

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private MarketingCampaignRepository marketingCampaignRepository;

    @Override
    public AwardBean save(AwardBean awardToSave) {
        AwardEntity awardEntity = mapper.map(awardToSave, AwardEntity.class);
        return mapper.map(awardRepository.saveAndFlush(awardEntity), AwardBean.class);
    }

    @Override
    public List<AwardBean> getAwardsForMarketingCampaign(Long marketingCampaignId) {
        MarketingCampaignEntity marketingCampaignEntity = marketingCampaignRepository.findOne(marketingCampaignId);
        return map(awardRepository.findByMarketingCampaigns(marketingCampaignEntity), AwardBean.class);
    }

    @Override
    public List<AwardBean> getAll() {
        return map(awardRepository.findAll(), AwardBean.class);
    }

}
