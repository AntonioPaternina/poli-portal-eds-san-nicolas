package co.com.estacionsannicolas.beans;

import co.com.estacionsannicolas.enums.VehicleTypeEnum;

public class VehicleBean extends BaseBean {

    private String licencePlate;

    private String brand;

    private String model;

    private UserBean user;

    private VehicleTypeEnum vehicleType;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

}
