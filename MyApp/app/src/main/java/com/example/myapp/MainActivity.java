package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CountryCodePicker codePickerNegara;
    TextView jumlahhariini,jumlahkasus,jumlahkasusaktif,jumlahkasusaktifhariini,jumlahsembuh,jumlahsembuhhariini,jumlahmeninggal,jumlahmeninggalhariini;
    String negara;
    TextView filter;
    Spinner spinner;
    String[] types={"total kasus", "meninggal", "sembuh", "kasus aktif"};
    private List<Model> ListModel;
    private List<Model> ListModel2;
    PieChart pie;
    private RecyclerView recyclerView;
    com.example.myapp.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        codePickerNegara=findViewById(R.id.negara);
        jumlahhariini=findViewById(R.id.kasushariini);
        jumlahkasus=findViewById(R.id.kasus);
        jumlahkasusaktif=findViewById(R.id.aktif);
        jumlahkasusaktifhariini=findViewById(R.id.aktifhariini);
        jumlahmeninggal=findViewById(R.id.meninggal);
        jumlahmeninggalhariini=findViewById(R.id.matihariini);
        jumlahsembuh=findViewById(R.id.sembuh);
        jumlahsembuhhariini=findViewById(R.id.sembuhhariini);
        pie=findViewById(R.id.piechart);
        spinner=findViewById(R.id.filterspinner);
        filter=findViewById(R.id.filter);
        recyclerView=findViewById(R.id.viewRecycler);
        ListModel=new ArrayList<>();
        ListModel2=new ArrayList<>();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        UtilityAPI.getAPI().getdatanegara().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                ListModel2.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
        adapter=new Adapter(getApplicationContext(),ListModel2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        codePickerNegara.setAutoDetectedCountry(true);
        negara=codePickerNegara.getSelectedCountryName();
        codePickerNegara.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                negara=codePickerNegara.getSelectedCountryName();
                fetchdata();
            }
        });

        fetchdata();
    }

    private void fetchdata(){
        UtilityAPI.getAPI().getdatanegara().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                ListModel.addAll(response.body());
                for (int n=0;n<ListModel.size();n++){
                    if (ListModel.get(n).getNegara().equals(negara)){
                        jumlahkasusaktif.setText((ListModel.get(n).getAktif()));
                        jumlahmeninggalhariini.setText((ListModel.get(n).getMeninggalhariini()));
                        jumlahsembuhhariini.setText((ListModel.get(n).getSembuhhariini()));
                        jumlahhariini.setText((ListModel.get(n).getKasushariini()));
                        jumlahkasus.setText((ListModel.get(n).getKasus()));
                        jumlahmeninggal.setText((ListModel.get(n).getMeninggal()));
                        jumlahsembuh.setText((ListModel.get(n).getSembuh()));

                        int aktif, jumlahkasus, sembuh, meninggal;
                        aktif=Integer.parseInt(ListModel.get(n).getAktif());
                        jumlahkasus=Integer.parseInt(ListModel.get(n).getKasus());
                        sembuh=Integer.parseInt(ListModel.get(n).getSembuh());
                        meninggal=Integer.parseInt(ListModel.get(n).getMeninggal());

                        updateGraph(aktif, jumlahkasus, sembuh, meninggal);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
    }

    private void updateGraph(int aktif, int jumlahkasus, int sembuh, int meninggal) {
        pie.clearChart();
        pie.addPieSlice(new PieModel("Konfirmasi Kasus", jumlahkasus, Color.parseColor("#9CA4CD")));
        pie.addPieSlice(new PieModel("Kasus Aktif", aktif, Color.parseColor("#E36037")));
        pie.addPieSlice(new PieModel("Total Sembuh", sembuh, Color.parseColor("#FF03DAC5")));
        pie.addPieSlice(new PieModel("Total Meninggal", meninggal, Color.parseColor("#EC0404")));
        pie.startAnimation();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=types[position];
        filter.setText(item);
        adapter.filter(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}