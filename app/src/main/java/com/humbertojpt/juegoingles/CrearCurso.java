package com.humbertojpt.juegoingles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;

public class CrearCurso extends AppCompatActivity {

    private String nombreCurso;
    private EditText mTextname;
    private String cod;
    private EditText mTextCod;
    private String profeId;
    private SharedPreferences mSharedPreferences;
    protected String CODIGOPROFE = "codProfe";
    protected String NAMEPROF = "NombreProf" , profename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_curso);


        mTextCod = (EditText) findViewById(R.id.CodText);
        mTextname = (EditText) findViewById(R.id.CursoText);


        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        profeId = mSharedPreferences.getString(CODIGOPROFE, "0");
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        profename = mSharedPreferences.getString(NAMEPROF, "NombreProf");

    }

    private class SendData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ParseObject testObject = new ParseObject("cursos");
            testObject.put("idcurso", cod);
            testObject.put("nombrecurso", nombreCurso);
            testObject.put("codprof", profeId);
            testObject.put("nombreprof",profename);
            testObject.saveInBackground();
            return null;
        }
    }

    public void Crear(View view) {
        nombreCurso = mTextname.getText().toString();
        cod = mTextCod.getText().toString();
        new SendData().execute();
        mTextname.setText("");
        mTextCod.setText("");
        finishFromChild(this);
        Intent intent = new Intent(this, Profesores.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTextname.getWindowToken(), 0);
    }
}
