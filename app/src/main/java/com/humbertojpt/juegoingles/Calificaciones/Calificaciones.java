package com.humbertojpt.juegoingles.Calificaciones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.humbertojpt.juegoingles.Etapas;
import com.humbertojpt.juegoingles.EvaluarFragments.Evaluar;
import com.humbertojpt.juegoingles.R;
import com.parse.ParseObject;

public class Calificaciones extends AppCompatActivity {


    private String profename, NameEstud , curso , letra, codCurso ;
    protected SharedPreferences mSharedPreferences;
    protected String NAMEPROF = "NombreProf";
    protected String NAMEESTUD = "NombreEstud";
    protected String NAMECURSO = "NombreCurso";
    protected String CODCURSO = "CodCurso";


    protected String LETRA = "Letra";
    private TextView mText1,mText2,mText3,mText4,mText5;
    private int calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificaciones);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        profename = mSharedPreferences.getString(NAMEPROF, "NombreProf");
        NameEstud = mSharedPreferences.getString(NAMEESTUD, "NombreEstud");
        curso = mSharedPreferences.getString(NAMECURSO, "NombreCurso");
        letra = mSharedPreferences.getString(LETRA, "letra");
        codCurso = mSharedPreferences.getString(CODCURSO, "");


        calificacion = Evaluar.calif;
        Log.d("calif", calificacion + " califica ");

        mText1 = (TextView) findViewById(R.id.Estudiante);
        mText2 = (TextView) findViewById(R.id.Profesor);
        mText3 = (TextView) findViewById(R.id.Curso);
        mText4 = (TextView) findViewById(R.id.Letra);
        mText5 = (TextView) findViewById(R.id.Calificacion);

        mText1.setText(NameEstud);
        mText2.setText(profename);
        mText3.setText(curso);
        mText4.setText(letra);
        mText5.setText(calificacion+"");
    }

    public void RegresarAMenu(View view) {
        finish();
        Intent intent = new Intent(this, Etapas.class);
        startActivity(intent);
        new SendData().execute();
    }

    private class SendData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ParseObject testObject = new ParseObject("estudiante");
            testObject.put("nombrestud", NameEstud);
            testObject.put("nombreprof", profename);
            testObject.put("curso", curso);
            testObject.put("codcurso", codCurso);
            testObject.put("letra",letra);
            testObject.put("calificacion", calificacion);
            testObject.saveInBackground();
            return null;
        }
    }
}
