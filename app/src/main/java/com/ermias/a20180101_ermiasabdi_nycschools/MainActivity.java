package com.ermias.a20180101_ermiasabdi_nycschools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private HashMap<String, String> hm ;
    private List<String> nycsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NYCSchoolAdapter nycSchoolAdapter;
    private RetrofitInstance retrofitInstance = new RetrofitInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hm = new HashMap<>();

        context = getApplicationContext();
        recyclerView = findViewById(R.id.reyclerview_1);
        getAll();
    }

    private void getAll(){
        // Retorfit
        Retrofit retrofit = retrofitInstance.getRetrofitInstance();
        // instance for interface
        NYCHSAPICall nychsapiCall = retrofit.create(NYCHSAPICall.class);

        Call<List<NYCHighSchoolData>> call = nychsapiCall.getNYCHSDatas();
        call.enqueue(new Callback<List<NYCHighSchoolData>>() {
            @Override
            public void onResponse(Call<List<NYCHighSchoolData>> call, Response<List<NYCHighSchoolData>> response) {
                System.out.println("I am here 1.");
                if(!response.isSuccessful()){
                    Toast.makeText(context,response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                List<NYCHighSchoolData> data1 = response.body();

                for(int i = 0; i < data1.size(); i++){
                    String output = "";
                    output = "DBN= " +data1.get(i).getDbn() +
                            "\nSchool name = " + data1.get(i).getSchool_name() +
                            "\nNumber Sat Test = " + data1.get(i).getNum_of_sat_test_takers()+
                            "\nMath Average Score = " + data1.get(i).getSat_math_avg_score() +
                            "\nWriting Average Score = " + data1.get(i).getSat_writing_avg_score() +
                            "\nCritical Reading Average Score = " +
                            data1.get(i).getSat_critical_reading_avg_score();

                    getHashMap(hm,data1.get(i).getDbn(),output);
                }
            }

            @Override
            public void onFailure(Call<List<NYCHighSchoolData>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Retrofit 0 : " + t.getMessage());
            }
        });

        Retrofit retrofit1 = retrofitInstance.getRetrofitInstance();

        NYCHSAPICall nychsapiCall1 = retrofit1.create(NYCHSAPICall.class);
        Call<List<NYHSData>> call1 = nychsapiCall1.getNYHSDatas();
        call1.enqueue(new Callback<List<NYHSData>>() {
            @Override
            public void onResponse(Call<List<NYHSData>> call, Response<List<NYHSData>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                List<NYHSData> nyhsDataList = response.body();
                for(int i = 0; i < nyhsDataList.size(); i++) {
                    String output1 = "City = " + nyhsDataList.get(i).getCity()+
                            "\nSchool Location = " + nyhsDataList.get(i).getLocation() +
                            "\nSchool Email = "+ nyhsDataList.get(i).getSchool_email() +
                            "\nSchool Website = " + nyhsDataList.get(i).getWebsite();

                    getHashMap(hm,nyhsDataList.get(i).getDbn(),output1);
                }
                nycsList = getNycsList(hm);
                nycSchoolAdapter = new NYCSchoolAdapter(context,nycsList);
                LinearLayoutManager linearLayoutManager = new
                        LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(nycSchoolAdapter);
            }

            @Override
            public void onFailure(Call<List<NYHSData>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Retrofit 1 : " + t.getMessage());
            }

        });

    }

    void getHashMap(HashMap<String, String> hm, String s1, String s2){
        if(!hm.containsKey(s1)){
            hm.put(s1, s2);
        }
        else{
            String s3 = hm.get(s1);
            s3 +="\n" + s2;
            hm.put(s1, s3);
        }
    }
    List<String> getNycsList(HashMap<String, String> h){
        Set<String> set = h.keySet();
        Iterator<String> iterator = set.iterator();
        List<String> stringList = new ArrayList<>();
        while(iterator.hasNext()){
            stringList.add(h.get(iterator.next()));
        }

        return stringList;
    }
}