package Trwalosc;
// Generated Nov 9, 2021 9:51:43 PM by Hibernate Tools 4.3.1




public class Motorcycles  implements java.io.Serializable {


     private int modelId;
     private String name;
     private String brand;
     private String engineCapacity;
     private String power;
     private Integer noTraces;
     private String requireDrivingLicense;

    public Motorcycles() {
    }

	
    public Motorcycles(int modelId) {
        this.modelId = modelId;
    }
    public Motorcycles(int modelId, String name, String brand, String engineCapacity, String power, Integer noTraces, String requireDrivingLicense) {
       this.modelId = modelId;
       this.name = name;
       this.brand = brand;
       this.engineCapacity = engineCapacity;
       this.power = power;
       this.noTraces = noTraces;
       this.requireDrivingLicense = requireDrivingLicense;
    }
   
    public int getModelId() {
        return this.modelId;
    }
    
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getEngineCapacity() {
        return this.engineCapacity;
    }
    
    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public String getPower() {
        return this.power;
    }
    
    public void setPower(String power) {
        this.power = power;
    }
    public Integer getNoTraces() {
        return this.noTraces;
    }
    
    public void setNoTraces(Integer noTraces) {
        this.noTraces = noTraces;
    }
    public String getRequireDrivingLicense() {
        return this.requireDrivingLicense;
    }
    
    public void setRequireDrivingLicense(String requireDrivingLicense) {
        this.requireDrivingLicense = requireDrivingLicense;
    }


}


