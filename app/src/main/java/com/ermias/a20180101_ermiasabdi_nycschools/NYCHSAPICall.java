package com.ermias.a20180101_ermiasabdi_nycschools;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NYCHSAPICall {

    String BASE_URL = "https://data.cityofnewyork.us/";

    @GET("resource/f9bf-2cp4.json")
    Call<List<NYCHighSchoolData>> getNYCHSDatas();

    @GET("resource/s3k6-pzi2.json")
    Call<List<NYHSData>> getNYHSDatas();
}
