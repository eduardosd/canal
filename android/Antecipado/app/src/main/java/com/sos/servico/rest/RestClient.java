package com.sos.servico.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class RestClient {

    //private static final String BASE_URL = "http://127.0.0.1:8000";
    private static final String BASE_URL = "http://192.168.1.105:8000";
    //private static final String BASE_URL = "http://52.6.23.157:8000";
    //private static final String BASE_URL = "http://192.168.1.7:8000";
    private ServiceRest apiService;

    public RestClient() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ServiceRest.class);
    }

    public ServiceRest getApiService() {
        return apiService;
    }


}
