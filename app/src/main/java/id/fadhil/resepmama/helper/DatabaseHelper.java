package id.fadhil.resepmama.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import id.fadhil.resepmama.model.Model;
import id.fadhil.resepmama.model.ModelSqlite;

/**
 * Created by riyan on 20/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "dbResep";
    private final static String DATABASE_TABLE = "table_resep";
    private final static String RESEP_ID = "id_resep";
    private final static String NAMA_USER = "nama_user";
    private final static String NAMA_RESEP = "nama_resep";
    private final static String ALAT_BAHAN = "alat_bahan";
    private final static String CARA_MASAK = "cara_masak";
    private final static String GAMBAR = "gambar";
    private final static String KATEGORI = "kategori";
    private final static int DATABASE_VERSION = 8;
    private final static String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE
            + " (" + RESEP_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + NAMA_USER + " VARCHAR(200), "
            + NAMA_RESEP + " VARCHAR(200), "
            + ALAT_BAHAN + " TEXT, "
            + CARA_MASAK + " VARCHAR(20), "
            + GAMBAR + " VARCHAR(20), "
            + KATEGORI + " VARCHAR(20));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertData(Integer idResep,
                           String namaUser,
                           String namaResep,
                           String alatbahan,
                           String caramasak,
                           String gambar,
                           String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESEP_ID, idResep);
        contentValues.put(NAMA_USER, namaUser);
        contentValues.put(NAMA_RESEP, namaResep);
        contentValues.put(ALAT_BAHAN, alatbahan);
        contentValues.put(CARA_MASAK, caramasak);
        contentValues.put(GAMBAR, gambar);
        contentValues.put(KATEGORI, kategori);
        long id = db.insert(DATABASE_TABLE, null, contentValues);
        db.close();
        return id;
    }

    public ArrayList<Model> getDataFavorite() {
        ArrayList<Model> listResepFavorite = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columName = {
                RESEP_ID,
                NAMA_USER,
                NAMA_RESEP,
                ALAT_BAHAN,
                CARA_MASAK,
                GAMBAR,
                KATEGORI};
        Cursor cursor = db.query(DATABASE_TABLE,
                columName,
                null,
                null,
                null,
                null,
                null);
        if(cursor!=null){
//            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int idResep = cursor.getInt(cursor.getColumnIndex(RESEP_ID));
                String namaUser = cursor
                        .getString(cursor.getColumnIndex(NAMA_USER));
                String namaResep = cursor
                        .getString(cursor.getColumnIndex(NAMA_RESEP));
                String alatBahan = cursor
                        .getString(cursor.getColumnIndex(ALAT_BAHAN));
                String caraMasak = cursor
                        .getString(cursor.getColumnIndex(CARA_MASAK));
                String gambar = cursor
                        .getString(cursor.getColumnIndex(GAMBAR));
                String katgori = cursor
                        .getString(cursor.getColumnIndex(KATEGORI));


                Model resepFavorite = new Model(String.valueOf(idResep),
                        namaUser,
                        namaResep,
                        alatBahan,
                        caraMasak,
                        gambar,
                        katgori);

                listResepFavorite.add(resepFavorite);
            }
        }
        db.close();
        return listResepFavorite;
    }

    public int delete(String namaResep){
        SQLiteDatabase db = this.getWritableDatabase();
        String namaKolomnya = NAMA_RESEP + " = ?";
        String[] nilaiFieldnya = {namaResep};

        int count = db.delete(DATABASE_TABLE,namaKolomnya,nilaiFieldnya);
        return count;
    }


}
