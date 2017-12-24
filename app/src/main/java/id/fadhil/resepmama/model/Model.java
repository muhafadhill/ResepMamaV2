package id.fadhil.resepmama.model;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by riyan on 18/12/2017.
 */

public class Model implements Parcelable{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama_user")
    @Expose
    private String namaUser;

    @SerializedName("nama_resep")
    @Expose
    private String namaResep;

    @SerializedName("alat_bahan")
    @Expose
    private String alatBahan;

    @SerializedName("cara_masak")
    @Expose
    private String caraMasak;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("kategori")
    @Expose
    private String kategori;

    public Model(){

    }

    protected Model (Parcel in){
        id = in.readString();
        namaUser = in.readString();
        namaResep = in.readString();
        alatBahan = in.readString();
        caraMasak = in.readString();
        gambar = in.readString();
        kategori = in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int i) {
            return new Model[i];
        }
    };

    public Model(String id, String namaUser, String namaResep, String alatBahan, String caraMasak, String gambar, String kategori) {
        this.id = id;
        this.namaUser = namaUser;
        this.namaResep = namaResep;
        this.alatBahan = alatBahan;
        this.caraMasak = caraMasak;
        this.gambar = gambar;
        this.kategori = kategori;
    }


    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(namaUser);
        parcel.writeString(namaResep);
        parcel.writeString(alatBahan);
        parcel.writeString(caraMasak);
        parcel.writeString(gambar);
        parcel.writeString(kategori);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaResep() {
        return namaResep;
    }

    public void setNamaResep(String namaResep) {
        this.namaResep = namaResep;
    }

    public String getAlatBahan() {
        return alatBahan;
    }

    public void setAlatBahan(String alatBahan) {
        this.alatBahan = alatBahan;
    }

    public String getCaraMasak() {
        return caraMasak;
    }

    public void setCaraMasak(String caraMasak) {
        this.caraMasak = caraMasak;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
