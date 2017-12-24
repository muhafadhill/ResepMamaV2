package id.fadhil.resepmama.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import id.fadhil.resepmama.R;
import id.fadhil.resepmama.model.AddModel;
import id.fadhil.resepmama.model.Result;
import id.fadhil.resepmama.model.ResultConfig;
import id.fadhil.resepmama.permission.PermissionActivity;
import id.fadhil.resepmama.permission.PermissionChecker;
import id.fadhil.resepmama.rest.ApiRequest;
import id.fadhil.resepmama.rest.Config;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    private CircleImageView mCivGambar;
    private EditText mEdtNamaUser;
    private EditText mEdtNamaResep;
    private EditText mEdtAlatBahan;
    private EditText mEdtCaraMasak;
    private Button mBtnUpload;
    private CircleImageView mGetPhoto;
    private EditText mTvKategori;
    private Spinner mSpinner;

    String imagePath;
    private static final String[] PERMISSION_READ_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    PermissionChecker permissionChecker;
    String h;
    String nama_user, nama_resep, alat_bahan, cara_masak, gambar, kategori;
    String id = "";
    SharedPreferences pref;


    List<String> categories;
    String selectedItemText;
    private EditText mEdtHarga;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        initView(v);

        categories = new ArrayList<String>();
        categories.add("Nasi");
        categories.add("Sayur");
        categories.add("Kue");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    kategori = (String) adapterView.getItemAtPosition(i);
                }
                mTvKategori.setText("" + adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mTvKategori.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mSpinner.performClick();
                return true;

            }
        });

        mTvKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//				mSpinner.performClick();
            }
        });

        permissionChecker = new PermissionChecker(getActivity());
        mCivGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mGetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePopup(view);
            }
        });

        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();

//                String CharSet = "asdfghjkl1234567890";
//                for (int a = 1; a <= 5; a++) {
//                    id += CharSet.charAt(new Random().nextInt(CharSet.length()));
//                }
////
                nama_user = mEdtNamaUser.getText().toString().trim();
                Toast.makeText(getActivity(), ""+nama_user, Toast.LENGTH_SHORT).show();
                nama_resep = mEdtNamaResep.getText().toString().trim();
                Toast.makeText(getActivity(), ""+nama_resep, Toast.LENGTH_SHORT).show();
                alat_bahan = mEdtAlatBahan.getText().toString().trim();
                Toast.makeText(getActivity(), ""+alat_bahan, Toast.LENGTH_SHORT).show();
                cara_masak = mEdtCaraMasak.getText().toString().trim();
                Toast.makeText(getActivity(), ""+cara_masak, Toast.LENGTH_SHORT).show();
                gambar = h;
                kategori = mTvKategori.getText().toString();
                Toast.makeText(getActivity(), ""+kategori, Toast.LENGTH_SHORT).show();
                postDatabase();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }, 2000);
            }
        });

        return v;
    }

    private void postDatabase() {
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("send data .....");
        progress.setCancelable(false);
        progress.show();

        ApiRequest request = Config.getRetrofit().create(ApiRequest.class);

        Call<AddModel> insert = request.addItem(
                nama_user,
                nama_resep,
                alat_bahan,
                cara_masak,
                gambar,
                kategori
        );
        Log.d("Retrofit", "onClick: " + insert);
        insert.enqueue(new Callback<AddModel>() {
            @Override
            public void onResponse(Call<AddModel> call, Response<AddModel> response) {
                Toast.makeText(getActivity(), "response", Toast.LENGTH_SHORT).show();
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                progress.dismiss();

                if (kode.equals("1")) {
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RETRO", "Falure : " + t.getMessage());
            }
        });
    }

    private void showImagePopup(View view) {
        if (permissionChecker.lacksPermissions(PERMISSION_READ_STORAGE)) {
            startPermissionActivity(PERMISSION_READ_STORAGE);
        } else {
            Intent qq = new Intent(Intent.ACTION_PICK);
            qq.setType("image/*");
            startActivityForResult(Intent.createChooser(qq, "Pilih Foto"), 100);
        }
    }

    private void startPermissionActivity(String[] permissionReadStorage) {
        PermissionActivity.startActivityForResult(getActivity(), 0, permissionReadStorage);
    }

    private void uploadImage() {
        final ProgressDialog p;
        p = new ProgressDialog(getActivity());
        p.setMessage("Proses Upload Foto");
        p.show();

        File f = new File(imagePath);
        Toast.makeText(getActivity(), "Gambar " + h, Toast.LENGTH_SHORT).show();

        ApiRequest s = ResultConfig.getService();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
        final MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postImage(part); // Fungsing paling penting
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                p.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success"))
                        Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Upload Fail", Toast.LENGTH_SHORT).show();
                p.dismiss();
                return;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getActivity(), "Gambar Tidak Ada", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri selectImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor c = getActivity().getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
            if (c != null) {
                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePathColumn[0]);
                imagePath = c.getString(columnIndex);

                Picasso.with(getActivity()).load(new File(imagePath)).into(mCivGambar);
                h = new File(imagePath).getName();
                c.close();
            } else {
                Toast.makeText(getActivity(), "Gambar Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView(View v) {
        mCivGambar = v.findViewById(R.id.civ_gambar);
        mEdtNamaUser = v.findViewById(R.id.edt_nama);
        mEdtNamaResep = v.findViewById(R.id.edt_nama_resep);
        mEdtAlatBahan = v.findViewById(R.id.edt_alat_bahan);
        mEdtCaraMasak = v.findViewById(R.id.edt_cara_masak);
        mBtnUpload = v.findViewById(R.id.btn_save);
        mGetPhoto = v.findViewById(R.id.getPhoto);
        mTvKategori = v.findViewById(R.id.tv_kategori);
        mSpinner = v.findViewById(R.id.spinner);
    }
}
