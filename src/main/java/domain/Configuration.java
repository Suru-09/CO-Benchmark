package domain;

import com.google.gson.annotations.Expose;

public class Configuration {
    @Expose
    private String cpu;
    @Expose
    private String ram;

    public Configuration(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
