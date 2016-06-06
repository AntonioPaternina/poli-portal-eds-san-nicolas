package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.repositories.MarketingCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarketingCampaignServiceImpl extends BaseService implements MarketingCampaignService {

    @Autowired
    private MarketingCampaignRepository marketingCampaignRepository;

    @Override
    public MarketingCampaignBean save(MarketingCampaignBean campaign) {
        MarketingCampaignEntity campaignEntity = mapper.map(campaign, MarketingCampaignEntity.class);
        campaignEntity = marketingCampaignRepository.saveAndFlush(campaignEntity);
        return mapper.map(campaignEntity, MarketingCampaignBean.class);
    }

    @Override
    public List<MarketingCampaignBean> findAll() {
        return map(marketingCampaignRepository.findAll(), MarketingCampaignBean.class);
    }

    @Override
    public MarketingCampaignBean findByName(String campaignName) {
        MarketingCampaignBean campaignBean = null;
        try {
            MarketingCampaignEntity campaignEntity = marketingCampaignRepository.findByName(campaignName);
            if (campaignEntity != null) {
                campaignBean = mapper.map(campaignEntity, MarketingCampaignBean.class);
            }
        } catch (Exception e) {
            logger.error("Error finding capaign with name: " + campaignName, e);
        }

        return campaignBean;
    }

}
