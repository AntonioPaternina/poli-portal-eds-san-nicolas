package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.MarketingCampaignBean;
import co.com.estacionsannicolas.beans.PromotionCodeBatchRequestBean;
import co.com.estacionsannicolas.beans.PromotionCodeBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.service.exceptions.InexistentPromotionCodeException;
import co.com.estacionsannicolas.service.exceptions.PromotionCodeAlreadyUsedException;
import java.util.List;

public interface PromotionCodeService {

    PromotionCodeBean getByCode(String code);

    PromotionCodeBean save(PromotionCodeBean promotionCode);

    List<PromotionCodeBean> getAll();

    void delete(PromotionCodeBean promotionCode);

    List<PromotionCodeBean> generateRandomCodes(PromotionCodeBatchRequestBean batchRequestInformation);

    void usePromotionCode(UserBean user, String code) throws InexistentPromotionCodeException, PromotionCodeAlreadyUsedException;

    long getCountByCampaign(MarketingCampaignBean marketingCampaign);
}
