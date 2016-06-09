package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardRedeemRequestBean;
import co.com.estacionsannicolas.entities.*;
import co.com.estacionsannicolas.enums.RequestStatusEnum;
import co.com.estacionsannicolas.repositories.*;
import co.com.estacionsannicolas.service.exceptions.NotEnoughPointsToRedeemAwardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AwardRedeemRequestServiceImpl extends BaseService implements AwardRedeemRequestService {

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private AwardPointRepository awardPointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarketingCampaignRepository marketingCampaignRepository;

    @Autowired
    private AwardRedeemRequestRepository awardRedeemRequestRepository;

    @Override
    public AwardRedeemRequestBean create(AwardRedeemRequestBean awardRedeemRequest) throws NotEnoughPointsToRedeemAwardException {
        AwardRedeemRequestBean savedRequest = null;

        AwardEntity award = awardRepository.findOne(awardRedeemRequest.getAward().getId());
        UserEntity user = userRepository.findOne(awardRedeemRequest.getUser().getId());
        MarketingCampaignEntity marketingCampaign =
                marketingCampaignRepository.findOne(awardRedeemRequest.getMarketingCampaign().getId());
        AwardPointEntity userAwardPoints = awardPointRepository.findByUserAndMarketingCampaign(user, marketingCampaign);

        processValidations(award, user, marketingCampaign, userAwardPoints);

        decreateUserPoints(award, userAwardPoints);

        savedRequest = mapper.map(createAndSaveRequest(award, user, marketingCampaign), AwardRedeemRequestBean.class);
        return savedRequest;
    }

    private void decreateUserPoints(AwardEntity award, AwardPointEntity userAwardPoints) {
        userAwardPoints.decreaseNumberOfPoints(award.getCostInPoints());
        awardPointRepository.saveAndFlush(userAwardPoints);
    }

    private AwardRedeemRequestEntity createAndSaveRequest(AwardEntity award, UserEntity user, MarketingCampaignEntity marketingCampaign) {
        AwardRedeemRequestEntity newAwardRequest = new AwardRedeemRequestEntity();
        newAwardRequest.setAward(award);
        newAwardRequest.setMarketingCampaign(marketingCampaign);
        newAwardRequest.setUser(user);
        newAwardRequest.setRequestDate(new Date());
        newAwardRequest.setStatus(RequestStatusEnum.APPROVED);
        return awardRedeemRequestRepository.saveAndFlush(newAwardRequest);
    }

    private void processValidations(AwardEntity award, UserEntity user, MarketingCampaignEntity marketingCampaign, AwardPointEntity userAwardPoints) throws NotEnoughPointsToRedeemAwardException {
        validateUserHasEnoughPoints(award, user, marketingCampaign, userAwardPoints);
    }

    private void validateUserHasEnoughPoints(AwardEntity award, UserEntity user, MarketingCampaignEntity marketingCampaign, AwardPointEntity userAwardPoints) throws NotEnoughPointsToRedeemAwardException {
        if (award.getCostInPoints() > userAwardPoints.getNumberOfPoints()) {
            logger.error("user {} does not have enough points to redeem award {}", user, award);
            throw new NotEnoughPointsToRedeemAwardException();
        }
    }
}
