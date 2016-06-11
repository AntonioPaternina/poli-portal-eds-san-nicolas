package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;
import co.com.estacionsannicolas.entities.AwardEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.repositories.AwardRepository;
import co.com.estacionsannicolas.repositories.MarketingCampaignRepository;
import co.com.estacionsannicolas.service.exceptions.RequiredParameterException;
import org.apache.commons.lang3.StringUtils;
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
    public AwardBean save(AwardBean awardToSave) throws RequiredParameterException {
        AwardEntity awardEntity = mapper.map(awardToSave, AwardEntity.class);

        validateAward(awardEntity);

        return mapper.map(awardRepository.saveAndFlush(awardEntity), AwardBean.class);
    }

    private void validateAward(AwardEntity awardEntity) throws RequiredParameterException {
        if (awardEntity == null ||
                awardEntity.getCostInPoints() == null ||
                awardEntity.getMarketingCampaigns() == null ||
                awardEntity.getMarketingCampaigns().isEmpty() ||
                StringUtils.isEmpty(awardEntity.getReference()) ||
                StringUtils.isEmpty(awardEntity.getName())) {
            throw new RequiredParameterException();
        }
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

    @Override
    public void delete(Long awardId) {
        awardRepository.delete(awardId);
    }

}
