package id.fadhil.resepmama.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import java.util.ArrayList;

import id.fadhil.resepmama.R;
import id.fadhil.resepmama.adapter.HomeAdapter;
import id.fadhil.resepmama.helper.DatabaseHelper;
import id.fadhil.resepmama.model.Model;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    private TabHost tabhost;
    private TabWidget tabs;
    private FrameLayout tabcontent;
    private ScrollView svSemua;
    private RecyclerView rvSemua;
    private ScrollView svNasi;
    private RecyclerView rvNasi;
    private ScrollView svSayuran;
    private RecyclerView rvSayuran;
    private ScrollView svKue;
    private RecyclerView rvKue;
    DatabaseHelper dh;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    ArrayList<Model> listSemua;
    ArrayList<Model> listNasi;
    ArrayList<Model> listSayuran;
    ArrayList<Model> listKue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        initView(v);

        dh = new DatabaseHelper(getActivity());

        listSemua = new ArrayList<>();
        listNasi = new ArrayList<>();
        listSayuran = new ArrayList<>();
        listKue = new ArrayList<>();

        rvNasi.setHasFixedSize(true);
        rvNasi.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        rvSemua.setHasFixedSize(true);
        rvSemua.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		/*Setting of RecyclerView*/

        rvSayuran.setHasFixedSize(true);
        rvSayuran.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        rvKue.setHasFixedSize(true);
        rvKue.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //TODO Setting Tab Layout
		/*Setting of Tab Layout*/
        tabhost.setup();
        //Setting Tab Layout
        TabHost.TabSpec mTabSpec = tabhost.newTabSpec("Semua");
        //tab semua
        mTabSpec.setContent(R.id.sv_semua);
        mTabSpec.setIndicator("Semua");
        tabhost.addTab(mTabSpec);
        //tab patung
        mTabSpec = tabhost.newTabSpec("Nasi");
        mTabSpec.setContent(R.id.sv_nasi);
        mTabSpec.setIndicator("Nasi");
        tabhost.addTab(mTabSpec);
        //tab lukisan
        mTabSpec = tabhost.newTabSpec("Sayuran");
        mTabSpec.setContent(R.id.sv_sayuran);
        mTabSpec.setIndicator("Sayuran");
        tabhost.addTab(mTabSpec);
        //tab lain2
        mTabSpec = tabhost.newTabSpec("Kue");
        mTabSpec.setContent(R.id.sv_kue);
        mTabSpec.setIndicator("Kue");
        tabhost.addTab(mTabSpec);
		/*Setting of Tab Layout*/

        ambilData();
		return v;
    }

    private void ambilData() {
        listSemua = dh.getDataFavorite();
       // Toast.makeText(getActivity(), ""+dh.getDataFavorite(), Toast.LENGTH_SHORT).show();
        rvSemua.setAdapter(new HomeAdapter(getActivity(), listSemua));
        //Toast.makeText(getActivity(), ""+listSemua.size(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < listSemua.size(); i++) {
            if (listSemua.get(i).getKategori().equalsIgnoreCase("nasi")) {
                Model model = new Model();
                model.setId(listSemua.get(i).getId());
                model.setNamaUser(listSemua.get(i).getNamaUser());
                model.setNamaResep(listSemua.get(i).getNamaResep());
                model.setAlatBahan(listSemua.get(i).getAlatBahan());
                model.setCaraMasak(listSemua.get(i).getCaraMasak());
                model.setGambar(listSemua.get(i).getGambar());
                model.setKategori(listSemua.get(i).getKategori());
                listNasi.add(model);
                rvNasi.setAdapter(new HomeAdapter(getActivity(), listNasi));
            } else if (listSemua.get(i).getKategori().equalsIgnoreCase("sayuran")) {
                Model model = new Model();
                model.setId(listSemua.get(i).getId());
                model.setNamaUser(listSemua.get(i).getNamaUser());
                model.setNamaResep(listSemua.get(i).getNamaResep());
                model.setAlatBahan(listSemua.get(i).getAlatBahan());
                model.setCaraMasak(listSemua.get(i).getCaraMasak());
                model.setGambar(listSemua.get(i).getGambar());
                model.setKategori(listSemua.get(i).getKategori());
                listSayuran.add(model);
                rvSayuran.setAdapter(new HomeAdapter(getActivity(), listSayuran));
            } else if (listSemua.get(i).getKategori().equalsIgnoreCase("kue")) {
                Model model = new Model();
                model.setId(listSemua.get(i).getId());
                model.setNamaUser(listSemua.get(i).getNamaUser());
                model.setNamaResep(listSemua.get(i).getNamaResep());
                model.setAlatBahan(listSemua.get(i).getAlatBahan());
                model.setCaraMasak(listSemua.get(i).getCaraMasak());
                model.setGambar(listSemua.get(i).getGambar());
                model.setKategori(listSemua.get(i).getKategori());
                listKue.add(model);
                rvKue.setAdapter(new HomeAdapter(getActivity(), listKue));
            }
        }
    }

    private void initView(View v) {
        tabhost = (TabHost) v.findViewById(R.id.tabhost);
//        tabs = (TabWidget) v.findViewById(R.id.tabs);
//        tabcontent = (FrameLayout) v.findViewById(R.id.tabcontent);
        svSemua = (ScrollView) v.findViewById(R.id.sv_semua);
        rvSemua = (RecyclerView) v.findViewById(R.id.rv_semua);
        svNasi = (ScrollView) v.findViewById(R.id.sv_nasi);
        rvNasi = (RecyclerView) v.findViewById(R.id.rv_nasi);
        svSayuran = (ScrollView) v.findViewById(R.id.sv_sayuran);
        rvSayuran = (RecyclerView) v.findViewById(R.id.rv_sayuran);
        svKue = (ScrollView) v.findViewById(R.id.sv_kue);
        rvKue = (RecyclerView) v.findViewById(R.id.rv_kue);
    }



}
