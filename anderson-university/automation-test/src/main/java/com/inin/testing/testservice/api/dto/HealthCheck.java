package com.inin.testing.testservice.api.dto;

/**
 * Created by brianschultz on 11/2/15.
 */
public class HealthCheck {

    String service;
    String version;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
