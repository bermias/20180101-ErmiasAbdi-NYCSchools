package nycSchoolLibrary;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private Retrofit retrofit;
    private NYCHSAPICall nychsapiCall;

    public Retrofit getRetrofitInstance() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(nychsapiCall.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
