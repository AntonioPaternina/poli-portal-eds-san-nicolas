package co.com.estacionsannicolas.initializer;

import co.com.estacionsannicolas.beans.UserBean;
import co.com.estacionsannicolas.entities.MarketingCampaignEntity;
import co.com.estacionsannicolas.entities.UserRoleEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.UserRoleTypeEnum;
import co.com.estacionsannicolas.repositories.UserRoleRepository;
import co.com.estacionsannicolas.service.MarketingCampaignService;
import co.com.estacionsannicolas.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SeedDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MarketingCampaignService marketingCampaignService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeUserRoles();
        createDefaultUsers();

        if (marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName()) == null) {
            MarketingCampaignEntity tanquearSiPagaCampaign = new MarketingCampaignEntity();
            tanquearSiPagaCampaign.setName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());

            // TODO add default awards to the campaign
            
            marketingCampaignService.save(tanquearSiPagaCampaign);
        }
    }

    private void createDefaultUsers() {
        if (userService.findByUsername("admin") == null) {
            UserBean admin = new UserBean();
            admin.setUsername("admin");
            admin.setPassword("Admin01.");
            admin.setAcive(true);
            admin.setEmail("acpaternina@poli.edu.co");
            admin.setFullName("Antonio Paternina");
            admin.setNationalId("123456789");
            userService.create(admin, UserRoleTypeEnum.ADMIN);
        }
    }

    private void initializeUserRoles() {
        if (userRoleRepository.count() == 0) {
            List<UserRoleEntity> roles = new ArrayList<>();
            UserRoleEntity customerRole = new UserRoleEntity();
            customerRole.setType(UserRoleTypeEnum.CUSTOMER);
            roles.add(customerRole);

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setType(UserRoleTypeEnum.ADMIN);
            roles.add(adminRole);

            userRoleRepository.save(roles);
        }
    }
}
