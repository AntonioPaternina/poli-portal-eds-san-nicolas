package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingCampaignRepository extends JpaRepository<MarketingCampaignEntity, Long> {

    MarketingCampaignEntity findByName(String campaignName);
}
