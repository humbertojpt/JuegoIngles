package com.humbertojpt.juegoingles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Estudiantes extends AppCompatActivity implements customAdapter.RecyclerClickListner{


    public int vecesjugadas;
    private RecyclerView mRecycleView;
    private String NameEstud , cod ,curso,L;
    private int CodEstud;
    private TextView mTextName;
    protected SharedPreferences mSharedPreferences;
    protected String NAMEESTUD = "NombreEstud";
    protected String CODCURSO = "CodCurso";
    protected String NAMEPROF = "NombreProf";
    protected String NAMECURSO = "NombreCurso";
    protected String  CODESTUD = "CodEstud";
    protected String juegos = "juegos";
    private customAdapter mAdapter;
    private String prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        mRecycleView = (RecyclerView) findViewById(R.id.recycleCursos);
        mTextName = (TextView) findViewById(R.id.NameEstudiante);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        NameEstud = mSharedPreferences.getString(NAMEESTUD, "NombreEstud");
        CodEstud = mSharedPreferences.getInt(CODESTUD, 0);


        Log.d("estud", NameEstud + "");
        Log.d("estud", CodEstud + "");


        if(mSharedPreferences.getInt(juegos, 0)!=0){
            vecesjugadas = mSharedPreferences.getInt(juegos, 0);
            vecesjugadas++;
        }else{
            vecesjugadas=1;
            finish();
            Intent intent1 = new Intent(Estudiantes.this, Inicio.class);
            startActivity(intent1);
        }

        SharedPreferences.Editor editor1 = mSharedPreferences.edit();
        editor1.putInt(juegos, vecesjugadas);
        editor1.commit();
        mTextName.setText("Bienvenido " + NameEstud);


        getData();
    }


    public void getData(){
        final List<Information> data = new ArrayList<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("cursos");
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

                mAdapter = new customAdapter(getApplicationContext(), data);
                mAdapter.setRecyclerClickListner(Estudiantes.this);
                mRecycleView.setAdapter(mAdapter);
                mRecycleView.setLayoutManager(new LinearLayoutManager(Estudiantes.this));

            }
        });
    }

    @Override
    public void itemClick(View view, int position) {
        SharedPreferences.Editor editor1 = mSharedPreferences.edit();
        if(position == 0){
            L ="IV0O9oljs3"; curso = "Transicion A"; prof ="augusto"; cod = "01";
        }else{
            if(position == 1){
                L="oqu1XKirUX";curso = "Transicion B"; prof ="augusto"; cod = "02";
            }else{
                L="0hMzUFIPoa";curso = "Transicion C"; prof ="eduardo"; cod ="10";

            }
        }/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("cursos");
        query.getInBackground(L, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    curso = object.getString("nombrecurso");
                    cod = object.getString("idcurso");
                    prof = object.getString("nombreprof");
                } else {
                    e.printStackTrace();
                }
            }

        });*/
        Log.d("estud", curso + " califica ");
        Log.d("estud", cod + " califica ");
        Log.d("estud", prof + " califica ");
        editor1.putString(NAMECURSO, curso);
        editor1.putString(CODCURSO, cod);
        editor1.putString(NAMEPROF, prof);
        editor1.commit();
        finish();
        Intent intent = new Intent(this, Etapas.class);
        startActivity(intent);
    }

    public void CambiarEstudiante(View view) {
        finish();
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }
}
