package co.com.estacionsannicolas.service;


import co.com.estacionsannicolas.beans.AwardRedeemRequestBean;
import co.com.estacionsannicolas.service.exceptions.NotEnoughPointsToRedeemAwardException;

public interface AwardRedeemRequestService {
    AwardRedeemRequestBean create(AwardRedeemRequestBean awardRedeemRequest) throws NotEnoughPointsToRedeemAwardException;

}
