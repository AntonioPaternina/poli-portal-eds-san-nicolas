package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.*;
import co.com.estacionsannicolas.repositories.specifications.PromotionCodeSpecification;
import co.com.estacionsannicolas.service.exceptions.InexistentPromotionCodeException;
import co.com.estacionsannicolas.service.exceptions.PromotionCodeAlreadyUsedException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionCodeService {

    PromotionCodeBean getByCode(String code);

    PromotionCodeBean save(PromotionCodeBean promotionCode);

    PageBean<PromotionCodeBean> getAll(Pageable pageRequest);

    PageBean<PromotionCodeBean> getAll(PromotionCodeSpecification specification, Pageable pageRequest);

    void delete(long id);

    List<PromotionCodeBean> generateRandomCodes(PromotionCodeBatchRequestBean batchRequestInformation);

    void usePromotionCode(UserBean user, String code) throws InexistentPromotionCodeException, PromotionCodeAlreadyUsedException;

    long getCountByCampaign(MarketingCampaignBean marketingCampaign);

    long getCount();
}
