package co.com.estacionsannicolas.initializer;

import co.com.estacionsannicolas.beans.*;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.GenderEnum;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.repositories.RoleRepository;
import co.com.estacionsannicolas.service.AwardService;
import co.com.estacionsannicolas.service.MarketingCampaignService;
import co.com.estacionsannicolas.service.PromotionCodeService;
import co.com.estacionsannicolas.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SeedDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final boolean INSERT_TEST_DATA = true;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MarketingCampaignService marketingCampaignService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private PromotionCodeService promotionCodeService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeUserRoles();
        createTanquearSiPagaCampaign();
        if (INSERT_TEST_DATA) {
            createDefaultUsers();
            createDefaultPromotionCodesForTanquearSiPaga();
        }
    }

    private void createDefaultPromotionCodesForTanquearSiPaga() {
        MarketingCampaignBean tanquearSiPaga = marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());
        if (tanquearSiPaga != null && promotionCodeService.getCountByCampaign(tanquearSiPaga) == 0) {
            PromotionCodeBatchRequestBean batchRequestInfo = new PromotionCodeBatchRequestBean();
            batchRequestInfo.setAwardPointsPercode(100);
            batchRequestInfo.setCodeLength(12);
            batchRequestInfo.setMarketingCampaign(tanquearSiPaga);
            batchRequestInfo.setNumberOfCodesToCreate(100);

            promotionCodeService.generateRandomCodes(batchRequestInfo);
        }
    }

    private void createTanquearSiPagaCampaign() {
        if (marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName()) == null) {
            MarketingCampaignBean tanquearSiPagaCampaign = new MarketingCampaignBean();
            tanquearSiPagaCampaign.setName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());
            tanquearSiPagaCampaign = marketingCampaignService.save(tanquearSiPagaCampaign);

            createTestAwards(tanquearSiPagaCampaign);
        }
    }

    private void createTestAwards(MarketingCampaignBean tanquearSiPagaCampaign) {
        List<MarketingCampaignBean> campaigns = new ArrayList<>();
        campaigns.add(tanquearSiPagaCampaign);

        for (int i = 1; i < 5; i++) {
            AwardBean testAward = new AwardBean();
            testAward.setCostInPoints(1000L);
            testAward.setDescription("This is a test award " + i);
            testAward.setName("Test award" + i);
            testAward.setImageLocation("path_to_image_" + i);
            testAward.setMarketingCampaigns(campaigns);
            testAward.setReference("00" + i);
            testAward.setPrice(BigDecimal.ONE);
            awardService.save(testAward);
        }
    }

    private void createDefaultUsers() {
        if (userService.findByUsername("admin") == null) {
            createDefaultAdmin();
            createDefaultCustomers();
        }
    }

    private void createDefaultAdmin() {
        UserBean admin = new UserBean();
        admin.setUsername("admin");
        admin.setPassword("Admin01.");
        admin.setAcive(true);
        admin.setEmail("edssn_test1@gmail.com");
        admin.setFullName("Antonio Paternina");
        admin.setNationalId("123456789");
        userService.create(admin, RoleTypeEnum.ADMIN);
    }

    private void createDefaultCustomers() {
        for (int i = 0; i < 30; i++) {
            GenderEnum[] genders = GenderEnum.values();

            UserBean customer = new UserBean();
            customer.setUsername("customer" + RandomStringUtils.randomAlphanumeric(5));
            customer.setPassword("Admin01.");
            customer.setAcive(true);
            customer.setEmail(RandomStringUtils.randomAlphabetic(6) + "@gmail.com");
            customer.setFullName("Test Customer " + RandomStringUtils.randomAlphanumeric(5));
            customer.setNationalId(RandomStringUtils.randomNumeric(8));
            customer.setGender(genders[RandomUtils.nextInt(2)]);

            Set<VehicleBean> vehicleBeanSet = new HashSet<>();
            VehicleBean vehicleBean = new VehicleBean();
            vehicleBean.setBrand("Marca");
            vehicleBean.setLicensePlate(RandomStringUtils.randomAlphanumeric(6));
            vehicleBean.setModel(RandomStringUtils.randomNumeric(3));
            vehicleBean.setUser(customer);
            vehicleBeanSet.add(vehicleBean);
            customer.setVehicles(vehicleBeanSet);

            userService.create(customer, RoleTypeEnum.CUSTOMER);
        }
    }

    private void initializeUserRoles() {
        if (roleRepository.count() == 0) {
            List<RoleEntity> roles = new ArrayList<>();
            RoleEntity customerRole = new RoleEntity();
            customerRole.setType(RoleTypeEnum.CUSTOMER);
            roles.add(customerRole);

            RoleEntity adminRole = new RoleEntity();
            adminRole.setType(RoleTypeEnum.ADMIN);
            roles.add(adminRole);

            roleRepository.save(roles);
        }
    }
}
