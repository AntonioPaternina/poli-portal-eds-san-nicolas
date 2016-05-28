package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.PromotionCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionCodeRepository extends JpaRepository<PromotionCodeEntity, Long> {

    long countByCode(String code);

    long countByMarketingCampaign(MarketingCampaignEntity marketingCampaign);

    PromotionCodeEntity findByCode(String code);
}
