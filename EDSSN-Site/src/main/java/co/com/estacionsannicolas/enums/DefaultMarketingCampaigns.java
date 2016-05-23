package co.com.estacionsannicolas.enums;

public enum DefaultMarketingCampaigns {
    TANQUEAR_SI_PAGA("Tanquear Sí Paga");

    private String name;

    DefaultMarketingCampaigns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
