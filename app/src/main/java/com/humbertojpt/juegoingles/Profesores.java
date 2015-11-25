package com.humbertojpt.juegoingles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.humbertojpt.juegoingles.Adapters.Information;
import com.humbertojpt.juegoingles.Adapters.customAdapter;
import com.humbertojpt.juegoingles.Calificaciones.ProfCalifi;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Profesores extends AppCompatActivity implements customAdapter.RecyclerClickListner {

    private RecyclerView mRecycleView;
    private TextView mProfNameText;
    private String profename;

    protected SharedPreferences mSharedPreferences;
    protected String NAMEPROF = "NombreProf";
    private customAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        mRecycleView = (RecyclerView) findViewById(R.id.recycleCursos);
        mProfNameText = (TextView) findViewById(R.id.ProfNameText);

       mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       profename = mSharedPreferences.getString(NAMEPROF, "NombreProf");

       mProfNameText.setText(profename+" sus Cursos: ");

        Bundle bundle = getIntent().getExtras();
        String x = String.valueOf(bundle.getInt("codprofe"));
        getData(x);

    }

    @Override
    public void itemClick(View view, int position) {

        Intent intent = new Intent(this, ProfCalifi.class);
        Bundle bundle = new Bundle();
        if (position == 0){
            bundle.putString("codcurso", "01");
        }else{
            if(position == 1){
                bundle.putString("codcurso", "02");
            }else{
                if (position == 2){
                    bundle.putString("codcurso", "03");
                }else{
                    bundle.putString("codcurso", "04");
                }
            }
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void CrearCurso(View view) {
        Intent intent = new Intent(this, CrearCurso.class);
        startActivity(intent);
    }

    public void getData(String codprofe){
        final List<Information> data = new ArrayList<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("cursos");
        query.whereEqualTo("codprof", codprofe);
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject datos : objects) {
                        Information information = new Information();
                        information.idcurso = datos.getString("idcurso");
                        information.nombrecurso = datos.getString("nombrecurso");
                        information.codprof = datos.getString("codprof");
                        data.add(information);
                    }
                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }

                mRecycleView.setLayoutManager(new LinearLayoutManager(Profesores.this));
                mAdapter = new customAdapter(getApplicationContext(), data);
                mRecycleView.setAdapter(mAdapter);
                mAdapter.setRecyclerClickListner(Profesores.this);
            }
        });
    }

    public void letras(View view) {
        Intent intent = new Intent(this, GestionLetras.class);
        startActivity(intent);
    }
}
