package id.fadhil.resepmama.rest;

import java.util.ArrayList;

import id.fadhil.resepmama.model.AddModel;
import id.fadhil.resepmama.model.Model;
import id.fadhil.resepmama.model.Result;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by riyan on 19/12/2017.
 */

public interface ApiRequest {
    @GET("get.php")
    Call<ArrayList<Model>> getResep();

    @Multipart
    @POST("upload_photo.php")
    Call<Result> postImage(@Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("post.php")
    Call<AddModel> addItem(
            @Field("nama_user") String nama_user,
            @Field("nama_resep") String nama_resep,
            @Field("alat_bahan") String alat_bahan,
            @Field("cara_masak") String cara_masak,
            @Field("gambar") String gambar,
            @Field("kategori") String kategori);

}
