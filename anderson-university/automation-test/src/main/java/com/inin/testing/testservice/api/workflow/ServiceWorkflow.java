package com.inin.testing.testservice.api.workflow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.inin.testing.api.endpoint.RestEndpointUtils;
import com.inin.testing.api.endpoint.RestResponse;
import com.inin.testing.common.api.rest.PureCloudEndpointFactory;
import com.inin.testing.testservice.api.dto.User;
import com.inin.testing.testservice.api.endpoint.ServiceEndpoints;
import com.inin.testing.testservice.api.dto.HealthCheck;
import retrofit.client.Response;

/**
 * Created by brianschultz on 11/2/15.
 */
public class ServiceWorkflow {
    private ServiceEndpoints endpoints;
    String domain = "http://localhost:5000";

    /**
     * Constructor for the class.  Instantiates the retrofit framework to work with the endpoints in the ServiceEndpoints interface
     */
    public ServiceWorkflow() {
        endpoints = PureCloudEndpointFactory.getPrivateEndpointAdapter(ServiceEndpoints.class, domain);
    }

    /***
     * getHealthCheck
     * @return RestResponse<HealthCheck>
     */
    public RestResponse<HealthCheck> getHealthCheck() {
        return RestEndpointUtils.exceptionCatchingCall(() -> {
            Response response = endpoints.getHealthCheck();
            return RestEndpointUtils.responseToRestResponse(response, new TypeReference<HealthCheck>() {
            });
        });
    }

    /***
     * postUser
     * @param user - User object
     * @param contentTypeHeader - String. expects application/json
     * @return RestResponse<User>
     */
    public RestResponse<User> postUser(User user, String contentTypeHeader) {
        return RestEndpointUtils.exceptionCatchingCall(() -> {
            Response response = endpoints.postUser(user, contentTypeHeader);
            return RestEndpointUtils.responseToRestResponse(response, new TypeReference<User>() {
            });
        });
    }


}
