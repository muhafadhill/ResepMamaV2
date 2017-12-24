package id.fadhil.resepmama.model;

import id.fadhil.resepmama.rest.ApiRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by riyan on 20/12/2017.
 */

public class ResultConfig {
    private static final String ROOT_URL = "https://muhafadhil.000webhostapp.com/ResepMama/image/";

    public ResultConfig() {
    }

    private static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static ApiRequest getService(){
        return getRetrofitClient().create(ApiRequest.class);
    }
}
