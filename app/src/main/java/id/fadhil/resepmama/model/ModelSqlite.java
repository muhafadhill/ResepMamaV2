package id.fadhil.resepmama.model;

/**
 * Created by riyan on 20/12/2017.
 */

public class ModelSqlite {
    int id;
    String nama_user, nama_resep, alarbahan,caramasak,gambar,kategori;

    public ModelSqlite(int id, String nama_user, String nama_resep, String alarbahan, String caramasak, String gambar, String kategori) {
        this.id = id;
        this.nama_user = nama_user;
        this.nama_resep = nama_resep;
        this.alarbahan = alarbahan;
        this.caramasak = caramasak;
        this.gambar = gambar;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getNama_resep() {
        return nama_resep;
    }

    public void setNama_resep(String nama_resep) {
        this.nama_resep = nama_resep;
    }

    public String getAlarbahan() {
        return alarbahan;
    }

    public void setAlarbahan(String alarbahan) {
        this.alarbahan = alarbahan;
    }

    public String getCaramasak() {
        return caramasak;
    }

    public void setCaramasak(String caramasak) {
        this.caramasak = caramasak;
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
