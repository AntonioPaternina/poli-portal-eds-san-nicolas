package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.*;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.PromotionCodeEntity;
import co.com.estacionsannicolas.repositories.PromotionCodeRepository;
import co.com.estacionsannicolas.repositories.specifications.PromotionCodeSpecification;
import co.com.estacionsannicolas.service.exceptions.InexistentPromotionCodeException;
import co.com.estacionsannicolas.service.exceptions.PromotionCodeAlreadyUsedException;
import co.com.estacionsannicolas.util.DozerHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PromotionCodeServiceImpl extends BaseService implements PromotionCodeService {

    private static final String PROMOTION_CODE_ALLOWED_CHARACTERS = "ABCDEFHJKMNOPQRTUVWXYZ23478";
    private static final int MAX_ATTEMPTS_TO_SAVE = 10;

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @Autowired
    private UserService userService;

    private static String generateRandomString(PromotionCodeBatchRequestBean batchRequestInformation) {
        return StringUtils.upperCase(RandomStringUtils.random(batchRequestInformation.getCodeLength(), PROMOTION_CODE_ALLOWED_CHARACTERS));
    }

    @Override
    public PromotionCodeBean save(PromotionCodeBean promotionCodeToSave) {
        PromotionCodeBean savedPromotionCode = null;
        PromotionCodeEntity promotionCodeEntity = mapper.map(promotionCodeToSave, PromotionCodeEntity.class);
        promotionCodeEntity = promotionCodeRepository.saveAndFlush(promotionCodeEntity);
        savedPromotionCode = mapper.map(promotionCodeEntity, PromotionCodeBean.class);
        return savedPromotionCode;
    }

    @Override
    public PageBean<PromotionCodeBean> getAll(Pageable pageRequest) {
        Page<PromotionCodeEntity> promotionCodeEntityPage = promotionCodeRepository.findAll(pageRequest);
        return DozerHelper.map(mapper, promotionCodeEntityPage, PromotionCodeBean.class);
    }

    @Override
    public PageBean<PromotionCodeBean> getAll(PromotionCodeSpecification specification, Pageable pageRequest) {
        Page<PromotionCodeEntity> promotionCodeEntityPage = promotionCodeRepository.findAll(specification, pageRequest);
        return DozerHelper.map(mapper, promotionCodeEntityPage, PromotionCodeBean.class);
    }

    @Override
    public void delete(long id) {
        promotionCodeRepository.delete(id);
    }

    @Override
    public List<PromotionCodeBean> generateRandomCodes(PromotionCodeBatchRequestBean batchRequestInformation) {
        List<PromotionCodeBean> promotionCodeBeanList = null;
        List<PromotionCodeEntity> newPromotionCodes = createAndSaveNewRandomPromotionCodes(batchRequestInformation);
        promotionCodeBeanList = DozerHelper.map(mapper, newPromotionCodes, PromotionCodeBean.class);
        return promotionCodeBeanList;
    }

    private List<PromotionCodeEntity> createAndSaveNewRandomPromotionCodes(PromotionCodeBatchRequestBean batchRequestInformation) {
        List<PromotionCodeEntity> newPromotionCodes = new ArrayList<>();
        MarketingCampaignEntity marketingCampaignEntity = mapper.map(batchRequestInformation.getMarketingCampaign(), MarketingCampaignEntity.class);
        for (int i = 0; i < batchRequestInformation.getNumberOfCodesToCreate(); i++) {
            boolean isDuplicateCode;
            int attemptsToSave = 0;
            do {
                String codeString = generateRandomString(batchRequestInformation);
                try {
                    newPromotionCodes.add(attemptToSavePromotionCode(marketingCampaignEntity, batchRequestInformation, codeString));
                    isDuplicateCode = false;
                    logger.info("Created promotion code {} for campaign {}", codeString, marketingCampaignEntity.getName());
                } catch (ConstraintViolationException e) {
                    logger.info("Failed attempt to save codeString {} because it was duplicate. Reattempting now", codeString, e);
                    isDuplicateCode = attemptsToSave < MAX_ATTEMPTS_TO_SAVE;
                    attemptsToSave++;
                }
            } while (isDuplicateCode);
        }
        return newPromotionCodes;
    }

    private PromotionCodeEntity attemptToSavePromotionCode(MarketingCampaignEntity marketingCampaignEntity, PromotionCodeBatchRequestBean batchRequestInformation, String codeString) {
        PromotionCodeEntity newPromotionCode = initializeNewPromotionCodeEntity(marketingCampaignEntity, batchRequestInformation, codeString);
        return promotionCodeRepository.saveAndFlush(newPromotionCode);
    }

    private PromotionCodeEntity initializeNewPromotionCodeEntity(MarketingCampaignEntity marketingCampaignEntity, PromotionCodeBatchRequestBean batchRequestInformation, String codeString) {
        PromotionCodeEntity newPromotionCode;
        newPromotionCode = new PromotionCodeEntity();
        newPromotionCode.setMarketingCampaign(marketingCampaignEntity);
        newPromotionCode.setPoints(batchRequestInformation.getAwardPointsPercode());
        newPromotionCode.setCode(codeString);
        return newPromotionCode;
    }

    @Override
    public void usePromotionCode(UserBean user, String code) throws InexistentPromotionCodeException, PromotionCodeAlreadyUsedException {
        logger.info("attempting to assign code {}", code);
        PromotionCodeBean promotionCode = getByCode(StringUtils.upperCase(StringUtils.trim(code)));
        validate(promotionCode);
        updateUserAwardPoints(user, promotionCode);
        save(promotionCode);
        logger.info("Assigned promotion code {} to user {}", code, user.getUsername());
    }

    private void validate(PromotionCodeBean promotionCode) throws PromotionCodeAlreadyUsedException, InexistentPromotionCodeException {
        if (promotionCode == null) {
            throw new InexistentPromotionCodeException();
        }
        if (promotionCode.getUsed() != null && promotionCode.getUsed()) {
            logger.error("the promotion code {} has already been used", promotionCode.getCode());
            throw new PromotionCodeAlreadyUsedException();
        }
    }

    private void updateUserAwardPoints(UserBean user, PromotionCodeBean promotionCode) {
        Set<AwardPointBean> userAwardPoints = user.getAwardPoints();
        for (AwardPointBean awardPoint : userAwardPoints) {
            if (awardPoint.getMarketingCampaign().equals(promotionCode.getMarketingCampaign())) {
                awardPoint.addPoints(promotionCode.getPoints());
                promotionCode.setUsed(Boolean.TRUE);
                break;
            }
        }
        userService.update(user);
    }

    @Override
    public long getCountByCampaign(MarketingCampaignBean marketingCampaign) {
        MarketingCampaignEntity marketingCampaignEntity = mapper.map(marketingCampaign, MarketingCampaignEntity.class);
        return promotionCodeRepository.countByMarketingCampaign(marketingCampaignEntity);
    }

    @Override
    public long getCount() {
        return promotionCodeRepository.count();
    }


    @Override
    public PromotionCodeBean getByCode(String code) {
        PromotionCodeBean promotionCode = null;
        PromotionCodeEntity promotionCodeEntity = promotionCodeRepository.findByCode(code);
        if (promotionCodeEntity != null) {
            promotionCode = mapper.map(promotionCodeEntity, PromotionCodeBean.class);
        }
        return promotionCode;
    }

}
