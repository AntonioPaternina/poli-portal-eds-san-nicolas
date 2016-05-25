package co.com.estacionsannicolas.service;

import co.com.estacionsannicolas.beans.AwardPointBean;
import co.com.estacionsannicolas.beans.UserBean;
import java.util.List;

public interface AccountService {

    List<AwardPointBean> findAwardPointsByUser(UserBean user);
}
