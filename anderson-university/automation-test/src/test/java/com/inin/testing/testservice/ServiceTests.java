package com.inin.testing.testservice;

import com.inin.testing.api.endpoint.RestResponse;
import com.inin.testing.common.utils.Assert;
import com.inin.testing.test.BaseApiTest;
import com.inin.testing.testservice.api.dto.HealthCheck;
import com.inin.testing.testservice.api.dto.User;
import com.inin.testing.testservice.api.workflow.ServiceWorkflow;
import org.testng.annotations.Test;

/**
 * Created by brianschultz on 11/2/15.
 */
public class ServiceTests extends BaseApiTest{

    String serviceName = "Anderson Internship Test Service";
    String version = "1.0";

    /**
     * Runs a test to grab the health endpoint, verifying a 200 OK, and that expected fields match
     */
    @Test(enabled = true, groups = {"servicetest.p1"})
    public void testGetHealthCheck(){

        RestResponse<HealthCheck> healthCheckRestResponse = new ServiceWorkflow().getHealthCheck();
        Assert.assertTrue(healthCheckRestResponse.getStatus() == 200, "Invalid Status");
        Assert.assertEquals(healthCheckRestResponse.getBody().getService(),serviceName, "Incorrect Service Name");
        Assert.assertEquals(healthCheckRestResponse.getBody().getVersion(),version, "Incorrect version");
    }


    /***
     * Generates a DTO for the User object.
     * @param userName - String
     * @param role - String
     * @return - User object
     */
    private User getUserDto(String userName, String role){
        User user = new User();
        user.setRole(role);
        user.setUserName(userName);
        return user;
    }


}
