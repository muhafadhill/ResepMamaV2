package id.fadhil.resepmama.fragment;


import android.app.ProgressDialog;
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


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;

import id.fadhil.resepmama.R;
import id.fadhil.resepmama.adapter.HomeAdapter;
import id.fadhil.resepmama.model.Model;
import id.fadhil.resepmama.rest.ApiRequest;
import id.fadhil.resepmama.rest.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mSlider;
    private PagerIndicator mCustomIndicator;
    private PagerIndicator mCustomIndicator2;
    private TabHost mTabhost;
    private TabWidget mTabs;
    private FrameLayout mTabcontent;
    private ScrollView mSvSemua;
    private ScrollView mSvNasi;
    private ScrollView mSvSayur;
    private ScrollView mSvKue;
    private RecyclerView mRvSemua;
    private RecyclerView mRvNasi;
    private RecyclerView mRvSayur;
    private RecyclerView mRvKue;


    public HomeFragment() {
        // Required empty public constructor
    }

    ArrayList<Model> listSemua = new ArrayList<>();
    ArrayList<Model> listNasi = new ArrayList<>();
    ArrayList<Model> listSayuran = new ArrayList<>();
    ArrayList<Model> listKue = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View rootview =  inflater.inflate(R.layout.fragment_home, container, false); // Inflate the layout for this fragment
//        rv = (RecyclerView) rootview.findViewById(R.id.card);
//        gridLayoutManager = new GridLayoutManager(context,2);
//        list = new ArrayList<>();
//        Model mdl = new Model();
//
//        //get data
//        list.add(mdl);
//        rv.setLayoutManager(gridLayoutManager);
//        rv.addItemDecoration(new GridMarginDecoration(context, 2, 2, 2, 2));
//        ha = new HomeAdapter(list, getActivity());
//        rv.setAdapter(ha);
//
//
//        return rootview;
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);

        ambilData();

        mRvNasi.setHasFixedSize(true);
        mRvNasi.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mRvSemua.setHasFixedSize(true);
        mRvSemua.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		/*Setting of RecyclerView*/

        mRvSayur.setHasFixedSize(true);
        mRvSayur.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mRvKue.setHasFixedSize(true);
        mRvKue.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //TODO Setting Tab Layout
		/*Setting of Tab Layout*/
        mTabhost.setup();
        //Setting Tab Layout
        TabHost.TabSpec mTabSpec = mTabhost.newTabSpec("Semua");
        //tab semua
        mTabSpec.setContent(R.id.sv_semua);
        mTabSpec.setIndicator("Semua");
        mTabhost.addTab(mTabSpec);
        //tab patung
        mTabSpec = mTabhost.newTabSpec("Nasi");
        mTabSpec.setContent(R.id.sv_nasi);
        mTabSpec.setIndicator("Nasi");
        mTabhost.addTab(mTabSpec);
        //tab lukisan
        mTabSpec = mTabhost.newTabSpec("Sayur");
        mTabSpec.setContent(R.id.sv_sayuran);
        mTabSpec.setIndicator("Sayur");
        mTabhost.addTab(mTabSpec);
        //tab lain2
        mTabSpec = mTabhost.newTabSpec("Kue");
        mTabSpec.setContent(R.id.sv_kue);
        mTabSpec.setIndicator("Kue");
        mTabhost.addTab(mTabSpec);
		/*Setting of Tab Layout*/

        //TODO Setting Slider
		/*Setting Slider*/
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Icon", R.drawable.splash);
//        file_maps.put("Semicolon", R.drawable.semicolon);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mSlider.addSlider(textSliderView);
        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);
		/*Setting Slider*/

        return v;
    }

    private void initView(View v) {
        mSlider = v.findViewById(R.id.slider);
        mCustomIndicator = v.findViewById(R.id.custom_indicator);
        mCustomIndicator2 = v.findViewById(R.id.custom_indicator2);
        mTabhost = v.findViewById(R.id.tabhost);
//		mTabs = v.findViewById(R.id.tabs);
//		mTabcontent = v.findViewById(R.id.tabcontent);
        mSvSemua = v.findViewById(R.id.sv_semua);
        mSvNasi = v.findViewById(R.id.sv_nasi);
        mRvNasi = v.findViewById(R.id.rv_nasi);
        mSvSayur = v.findViewById(R.id.sv_sayuran);
        mSvKue = v.findViewById(R.id.sv_kue);
        mRvSemua = v.findViewById(R.id.rv_semua);
        mRvSayur = v.findViewById(R.id.rv_sayuran);
        mRvKue = v.findViewById(R.id.rv_kue);
    }

    private void ambilData() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Harap tunggu...", false, false);
        ApiRequest api = Config.getRetrofit().create(ApiRequest.class);
        Call<ArrayList<Model>> call = api.getResep();
        call.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(Call<ArrayList<Model>> call, Response<ArrayList<Model>> response) {
                loading.dismiss();
                listSemua = response.body();
                mRvSemua.setAdapter(new HomeAdapter(getActivity(), listSemua));
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
                        mRvNasi.setAdapter(new HomeAdapter(getActivity(), listNasi));
                    } else if (listSemua.get(i).getKategori().equalsIgnoreCase("sayur")) {
                        Model model = new Model();
                        model.setId(listSemua.get(i).getId());
                        model.setNamaUser(listSemua.get(i).getNamaUser());
                        model.setNamaResep(listSemua.get(i).getNamaResep());
                        model.setAlatBahan(listSemua.get(i).getAlatBahan());
                        model.setCaraMasak(listSemua.get(i).getCaraMasak());
                        model.setGambar(listSemua.get(i).getGambar());
                        model.setKategori(listSemua.get(i).getKategori());
                        listSayuran.add(model);
                        mRvSayur.setAdapter(new HomeAdapter(getActivity(), listSayuran));
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
                        mRvKue.setAdapter(new HomeAdapter(getActivity(), listKue));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Model>> call, Throwable t) {
                Toast.makeText(getActivity(), "Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
