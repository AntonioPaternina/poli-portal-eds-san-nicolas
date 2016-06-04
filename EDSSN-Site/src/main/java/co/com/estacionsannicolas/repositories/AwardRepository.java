package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.AwardEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<AwardEntity, Long> {
    List<AwardEntity> findByMarketingCampaigns(MarketingCampaignEntity marketingCampaignEntity);
}
