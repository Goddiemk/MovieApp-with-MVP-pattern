package com.example.moviemvppattern.network;

public class ApiService {

    private static ApiInterface apiService;

    public static ApiInterface getApiService() {
        if (apiService == null) {
            apiService = ApiClient.getClient().create(ApiInterface.class);
        }
        return apiService;
    }
}
