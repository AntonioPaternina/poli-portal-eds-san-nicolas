package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;
import co.com.estacionsannicolas.entities.AwardEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.repositories.AwardRepository;
import co.com.estacionsannicolas.repositories.MarketingCampaignRepository;
import co.com.estacionsannicolas.util.DozerHelper;
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
        awardEntity = awardRepository.saveAndFlush(awardEntity);
        return mapper.map(awardEntity, AwardBean.class);
    }

    @Override
    public List<AwardBean> getAwardsForMarketingCampaign(Long marketingCampaignId) {
        List<AwardBean> awardBeanList = null;
        try {
            MarketingCampaignEntity marketingCampaignEntity = marketingCampaignRepository.findOne(marketingCampaignId);
            awardBeanList = DozerHelper
                    .map(mapper, awardRepository.findByMarketingCampaigns(marketingCampaignEntity), AwardBean.class);
        } catch (Exception e) {
            logger.error("Error while retrieving awards", e);
        }
        return awardBeanList;
    }

}
