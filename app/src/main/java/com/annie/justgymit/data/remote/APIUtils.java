package com.annie.justgymit.data.remote;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "http://jgiapi.herokuapp.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
