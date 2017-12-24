package id.fadhil.resepmama;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.fadhil.resepmama.helper.DatabaseHelper;
import id.fadhil.resepmama.helper.VariableConstant;
import id.fadhil.resepmama.model.Model;

public class DetailScrolling extends AppCompatActivity {


    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView detailGambar;
    private Toolbar toolbar;
    private TextView namaUser;
    private TextView detailAlatbahan;
    private TextView detailCaramasak;
    private FloatingActionButton fab;

    ArrayList<Model> list;
    int posisi;
    private ShineButton poImage2;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        initView();

        sp = getSharedPreferences(VariableConstant.SHARED_USER, MODE_PRIVATE);
        editor = sp.edit();
        databaseHelper = new DatabaseHelper(this);

        list = getIntent().getParcelableArrayListExtra("list");
        posisi = getIntent().getIntExtra("position", 0);

        if (sp.getBoolean(VariableConstant.FAVORITE+""+list.get(posisi).getId(),false)){
            poImage2.setChecked(true);
        }else{
            poImage2.setChecked(false);
        }

        getSupportActionBar().setTitle(list.get(posisi).getNamaResep());
        namaUser.setText("Ditulis oleh: "+"" + list.get(posisi).getNamaUser());
        detailAlatbahan.setText("" + list.get(posisi).getAlatBahan());
        detailCaramasak.setText("" + list.get(posisi).getCaraMasak());
        Picasso.with(this)
                .load("https://muhafadhil.000webhostapp.com/ResepMama/image/" + list.get(posisi).getGambar())
                .into(detailGambar);

        poImage2.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (poImage2.isChecked()){
                 saveDatabase();
                }else {
                    removeDatabase();
                }
            }
        });
    }

    private void removeDatabase() {
        editor.remove(VariableConstant.ID_RESEP);
        editor.putBoolean(VariableConstant.FAVORITE+""+list.get(posisi).getId(),false);
        editor.commit();
        databaseHelper.delete(list.get(posisi).getNamaResep());
        Toast.makeText(this, "GAK FAVORITE LAGI, BUANG AJA", Toast.LENGTH_SHORT).show();
    }

    private void saveDatabase() {
        editor.putString(VariableConstant.ID_RESEP, list.get(posisi).getId());
        editor.putBoolean(VariableConstant.FAVORITE+""+list.get(posisi).getId(),true);
        editor.commit();
        databaseHelper.insertData(
                Integer.valueOf(list.get(posisi).getId()),
                list.get(posisi).getNamaUser(),
                list.get(posisi).getNamaResep(),
                list.get(posisi).getAlatBahan(),
                list.get(posisi).getCaraMasak(),
                list.get(posisi).getGambar(),
                list.get(posisi).getKategori());
        Toast.makeText(this, "Favorited!", Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        detailGambar = (ImageView) findViewById(R.id.detail_gambar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        namaUser = (TextView) findViewById(R.id.nama_user);
        detailAlatbahan = (TextView) findViewById(R.id.detail_alatbahan);
        detailCaramasak = (TextView) findViewById(R.id.detail_caramasak);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        poImage2 = (ShineButton) findViewById(R.id.po_image2);
    }
}
