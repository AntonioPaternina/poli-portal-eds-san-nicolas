package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardPointBean;
import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.entities.AwardPointEntity;
import co.com.estacionsannicolas.entities.UserEntity;
import co.com.estacionsannicolas.repositories.AwardPointRepository;
import co.com.estacionsannicolas.util.DozerHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends BaseService implements AccountService {

    @Autowired
    private AwardPointRepository awardPointRepository;

    @Override
    public List<AwardPointBean> findAwardPointsByUser(UserBean user) {
        List<AwardPointEntity> awardPointEntityList = awardPointRepository.findByUser(mapper.map(user, UserEntity.class));
        return DozerHelper.map(mapper, awardPointEntityList, AwardPointBean.class);
    }

}
