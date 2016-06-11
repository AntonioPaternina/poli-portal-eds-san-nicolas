package co.com.estacionsannicolas.initializer;

import co.com.estacionsannicolas.beans.*;
import co.com.estacionsannicolas.entities.RoleEntity;
import co.com.estacionsannicolas.enums.DefaultMarketingCampaigns;
import co.com.estacionsannicolas.enums.GenderEnum;
import co.com.estacionsannicolas.enums.RoleTypeEnum;
import co.com.estacionsannicolas.enums.VehicleTypeEnum;
import co.com.estacionsannicolas.repositories.RoleRepository;
import co.com.estacionsannicolas.service.AwardService;
import co.com.estacionsannicolas.service.MarketingCampaignService;
import co.com.estacionsannicolas.service.PromotionCodeService;
import co.com.estacionsannicolas.service.UserService;
import co.com.estacionsannicolas.service.exceptions.EdssnServiceException;
import co.com.estacionsannicolas.service.exceptions.RequiredParameterException;
import co.com.estacionsannicolas.service.exceptions.TooManyPromotionCodesToCreateException;
import co.com.estacionsannicolas.service.exceptions.UsernameIsNotUniqueException;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
public class SeedDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    public static final int NUMBER_OF_CODES_TO_CREATE = 1000;
    public static final GenderEnum[] GENDERS = GenderEnum.values();
    public static final String[] COMMON_FEMALE_NAMES = new String[]{"Martina", "Sofia", "Florencia", "Valentina",
            "Isidora", "Antonella",
            "Antonia",
            "Emilia", "Catalina", "Fernanda", "Constanza", "Javiera", "Belen", "Victoria", "Gabriela",
            "Pascal"};
    public static final String[] COMMON_MALE_NAMES = new String[]{"Benjamin", "Vicente", "Martin", "Matias", "Joaquin",
            "Agustin", "Cristobal",
            "Maximiliano", "Sebastian", "Tomas", "Diego", "Jose", "Nicolás", "Juan", "Gabriel", "Ignacio",
            "Francisco"};
    public static final String[] COMMON_LAST_NAMES = new String[]{"Garcia", "Lopez", "Perez", "Gonzales", "Sanchez",
            "Martinez", "Rodriguez",
            "Fernandez", "Gomez", "Ruiz", "Diaz", "Alvarez", "Moreno", "Muñoz", "Suarez", "Ramirez", "Vazquez"};
    public static final String[] COMMON_BRANDS = new String[]{"BMW", "Mercedes", "Lamborghini", "Audi", "Ferrari",
            "Porsche", "Ford", "Toyota",
            "Volkswagen", "Honda", "Chevrolet", "Dodge", "Jaguar", "Nissan", "Mazda"};
    public static final VehicleTypeEnum[] VEHICLE_TYPES = VehicleTypeEnum.values();
    private static final boolean INSERT_TEST_DATA = true;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
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
    private List<PromotionCodeBean> testPromotionCodes;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeUserRoles();
        createTanquearSiPagaCampaign();
        if (INSERT_TEST_DATA) {
            try {
                createDefaultPromotionCodesForTanquearSiPaga();
                createDefaultUsers();
            } catch (EdssnServiceException e) {
                logger.error("error during initialization", e);
            }
        }
    }

    private void createDefaultPromotionCodesForTanquearSiPaga() throws TooManyPromotionCodesToCreateException, RequiredParameterException {
        MarketingCampaignBean tanquearSiPaga = marketingCampaignService.findByName(DefaultMarketingCampaigns.TANQUEAR_SI_PAGA.getName());
        if (tanquearSiPaga != null && promotionCodeService.getCountByCampaign(tanquearSiPaga) == 0) {
            PromotionCodeBatchRequestBean batchRequestInfo = new PromotionCodeBatchRequestBean();
            batchRequestInfo.setAwardPointsPercode(1000);
            batchRequestInfo.setCodeLength(12);
            batchRequestInfo.setMarketingCampaign(tanquearSiPaga);
            batchRequestInfo.setNumberOfCodesToCreate(NUMBER_OF_CODES_TO_CREATE);

            testPromotionCodes = promotionCodeService.generateRandomCodes(batchRequestInfo);
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

        AwardBean perfumeHombre = new AwardBean();
        perfumeHombre.setName("Perfume Hombre Invictus Paco Rabanne");
        perfumeHombre.setDescription("Un perfume original Invictus Paco Rabanne de 150 ml");
        perfumeHombre.setCostInPoints(10000L);
        perfumeHombre.setImageLocation("https://s3-sa-east-1.amazonaws.com/edssn-image-bucket/AwardImages/perfume-hombre-paco-rabanne.webp");
        perfumeHombre.setMarketingCampaigns(campaigns);
        perfumeHombre.setReference("001");
        perfumeHombre.setPrice(new BigDecimal("200000"));
        awardService.save(perfumeHombre);

        AwardBean llavero = new AwardBean();
        llavero.setName("Gobadges Kc002 Cooper Mini Llavero");
        llavero.setDescription("Cuero protector de llaves. Simple, elegante y personalizable.");
        llavero.setCostInPoints(2000L);
        llavero.setImageLocation("https://s3-sa-east-1.amazonaws.com/edssn-image-bucket/AwardImages/gobadges-kc002-cooper-mini-llavero-negro-con-punto-rojo--D_NQ_NP_440321-MCO20737448253_052016-O.webp");
        llavero.setMarketingCampaigns(campaigns);
        llavero.setReference("001");
        llavero.setPrice(new BigDecimal("100000"));
        awardService.save(llavero);

        AwardBean testAward = new AwardBean();
        testAward.setName("Manos Libres Bluetooth");
        testAward.setDescription("Un manos libres para dos celulares Bluetooth");
        testAward.setCostInPoints(300L);
        testAward.setImageLocation("https://s3-sa-east-1.amazonaws.com/edssn-image-bucket/AwardImages/manos-libres-carro.webp");
        testAward.setMarketingCampaigns(campaigns);
        testAward.setReference("001");
        testAward.setPrice(new BigDecimal("60000"));
        awardService.save(testAward);
    }

    private void createDefaultUsers() throws UsernameIsNotUniqueException {
        if (userService.findByUsername("admin") == null) {
            createDefaultAdmin();
            createDefaultCustomers();
        }
    }

    private void createDefaultAdmin() throws UsernameIsNotUniqueException {
        UserBean admin = new UserBean();
        admin.setUsername("admin");
        admin.setPassword("Admin01.");
        admin.setAcive(true);
        admin.setEmail("edssn_admin@gmail.com");
        admin.setFullName("Administrador");
        admin.setNationalId("123456789");
        userService.create(admin, RoleTypeEnum.ADMIN);
    }

    private void createDefaultCustomers() throws UsernameIsNotUniqueException {
        for (int i = 0; i < 50; i++) {
            createRandomCustomer();
        }
    }

    private void createRandomCustomer() throws UsernameIsNotUniqueException {
        GenderEnum gender = GENDERS[RandomUtils.nextInt(2)];
        StringBuilder fullName = new StringBuilder();
        StringBuilder username = new StringBuilder();
        generateRandomNameAndUsernameBasedOnGender(gender, fullName, username);

        UserBean customer = new UserBean();
        customer.setUsername(username.toString());
        customer.setPassword("Admin01.");
        customer.setAcive(true);
        customer.setEmail(username.append("@gmail.com").toString());
        customer.setFullName(fullName.toString());
        customer.setNationalId(RandomStringUtils.randomNumeric(10));
        customer.setGender(gender);
        customer.setAddress(RandomStringUtils.randomAlphanumeric(20));
        customer.setBirthdate(getRandomBirthDate());

        createRandomVehicleForCustomer(customer);

        customer = userService.create(customer, RoleTypeEnum.CUSTOMER);

        randomlyAssignTestCodes(customer);
    }

    private void randomlyAssignTestCodes(UserBean customer) {
        if (RandomUtils.nextBoolean()) {
            try {
                promotionCodeService.usePromotionCode(customer, getRandomPromotionCode());
            } catch (Exception e) {
                logger.error("Error while assigning test promotion code to test user", e);
            }
        }
    }

    private String getRandomPromotionCode() {
        return testPromotionCodes.get(RandomUtils.nextInt(testPromotionCodes.size())).getCode();
    }

    private void createRandomVehicleForCustomer(UserBean customer) {
        Set<VehicleBean> vehicleBeanSet = new HashSet<>();
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setBrand(COMMON_BRANDS[RandomUtils.nextInt(COMMON_BRANDS.length)]);
        vehicleBean.setLicensePlate(RandomStringUtils.randomAlphanumeric(6));
        vehicleBean.setModel(RandomStringUtils.randomNumeric(3));
        vehicleBean.setUser(customer);
        vehicleBean.setVehicleType(VEHICLE_TYPES[RandomUtils.nextInt(VEHICLE_TYPES.length)]);
        vehicleBeanSet.add(vehicleBean);
        customer.setVehicles(vehicleBeanSet);
    }

    private Date getRandomBirthDate() {
        long seventyYearsInMillis = 70L * 365L * 24L * 60L * 60L * 1000L;
        Random random = new Random();
        long randomYearOffsetInMillis = (long) (random.nextDouble() * seventyYearsInMillis);
        return new Date(System.currentTimeMillis() - randomYearOffsetInMillis);
    }

    private void generateRandomNameAndUsernameBasedOnGender(GenderEnum gender, StringBuilder fullName, StringBuilder username) {
        String firstName;
        if (GenderEnum.FEMALE.equals(gender)) {
            firstName = getCommonFemaleName();
            fullName.append(firstName).append(" ")
                    .append(getCommonFemaleName()).append(" ")
                    .append(getCommonLastName()).append(" ")
                    .append(getCommonLastName());
            username.append(firstName.toLowerCase())
                    .append(RandomStringUtils.randomNumeric(6));
        } else if (GenderEnum.MALE.equals(gender)) {
            firstName = getCommonMaleName();
            fullName.append(firstName).append(" ")
                    .append(getCommonMaleName()).append(" ")
                    .append(getCommonLastName()).append(" ")
                    .append(getCommonLastName());
            username.append(firstName.toLowerCase())
                    .append(RandomStringUtils.randomNumeric(6));
        }
    }

    private String getCommonMaleName() {
        return COMMON_MALE_NAMES[RandomUtils.nextInt(COMMON_MALE_NAMES.length)];
    }

    private String getCommonLastName() {
        return COMMON_LAST_NAMES[RandomUtils.nextInt(COMMON_LAST_NAMES.length)];
    }

    private String getCommonFemaleName() {
        return COMMON_FEMALE_NAMES[RandomUtils.nextInt(COMMON_FEMALE_NAMES.length)];
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
