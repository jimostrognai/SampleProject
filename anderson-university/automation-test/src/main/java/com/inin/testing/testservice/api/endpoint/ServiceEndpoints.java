package com.inin.testing.testservice.api.endpoint;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Header;

/**
 * Created by brianschultz on 11/2/15.
 */
public interface ServiceEndpoints {

    @GET("/health")
    Response getHealthCheck();

    @GET("/users/{userName}")
    Response getUser(@Path("userName") String userName);

    @POST("/users")
    <T> Response postUser(@Body T userData, @Header("Content-Type") String contentTypeHeader);

}
