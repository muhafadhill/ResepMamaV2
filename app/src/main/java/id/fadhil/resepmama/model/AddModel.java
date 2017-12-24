package id.fadhil.resepmama.model;

import java.util.ArrayList;

/**
 * Created by riyan on 20/12/2017.
 */

public class AddModel {
    String kode,pesan;
    ArrayList<Model> galeri;

    public ArrayList<Model> getGaleri() {
        return galeri;
    }

    public void setGaleri(ArrayList<Model> galeri) {
        this.galeri = galeri;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
