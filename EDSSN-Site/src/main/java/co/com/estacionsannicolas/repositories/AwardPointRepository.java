package co.com.estacionsannicolas.repositories;

import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardPointRepository extends JpaRepository<AwardPointEntity, Long> {

    List<AwardPointEntity> findByUser(UserEntity user);

    AwardPointEntity findByUserAndMarketingCampaign(UserEntity user, MarketingCampaignEntity marketingCampaignEntity);
}
