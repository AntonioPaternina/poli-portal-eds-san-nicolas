package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardBean;
import co.com.estacionsannicolas.entities.AwardEntity;
import co.com.estacionsannicolas.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AwardServiceImpl extends BaseService implements AwardService {

    @Autowired
    private AwardRepository awardRepository;

    @Override
    public AwardBean save(AwardBean awardToSave) {
        AwardEntity awardEntity = mapper.map(awardToSave, AwardEntity.class);
        awardEntity = awardRepository.saveAndFlush(awardEntity);
        return mapper.map(awardEntity, AwardBean.class);
    }

}
