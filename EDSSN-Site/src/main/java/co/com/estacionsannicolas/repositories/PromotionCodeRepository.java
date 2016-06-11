package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.PromotionCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionCodeRepository extends JpaRepository<PromotionCodeEntity, Long>, JpaSpecificationExecutor<PromotionCodeEntity> {

    long countByCode(String code);

    long countByMarketingCampaign(MarketingCampaignEntity marketingCampaign);

    PromotionCodeEntity findByCode(String code);
}
