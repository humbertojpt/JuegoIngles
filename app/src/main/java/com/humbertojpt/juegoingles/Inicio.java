package com.humbertojpt.juegoingles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Random;

public class Inicio extends AppCompatActivity {

    private EditText mTextname;
    private String nombreEstud;
    private int codEstud;
    private EditText mTextProfName;
    private EditText mTextPass;
    private String nomProfe;
    private String Password;
    protected SharedPreferences mSharedPreferences;
    protected String NAMEESTUD = "NombreEstud";
    protected String NAMEPROF = "NombreProf";
    protected String  CODESTUD = "CodEstud";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pestañas
        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        mTextname = (EditText) findViewById(R.id.EstudName);
        mTextProfName = (EditText) findViewById(R.id.ProfName);
        mTextPass = (EditText) findViewById(R.id.PassText);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Estudiante", null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Profesor", null);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        codEstud();
    }

    public void codEstud(){
        Random r = new Random();
         codEstud = r.nextInt(100 - 1) + 1;
    }



    public void ContProfe(View view) {


        boolean isName = isEmptyNameP();
        boolean isPass = isEmptyPassword();
        if (isName || isPass) {
            Snackbar.make(view, "Campos Incompletos", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        } else {

            nomProfe = mTextProfName.getText().toString();
            Password = mTextPass.getText().toString();


            ParseUser.logInInBackground(nomProfe, Password, new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if (user != null) {
                        int codprofe = user.getInt("codProfe");
                        SharedPreferences.Editor editor1 = mSharedPreferences.edit();
                        editor1.putString(NAMEPROF, nomProfe);
                        editor1.commit();
                        Intent intent = new Intent(Inicio.this, Profesores.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("codprofe", codprofe);
                        intent.putExtras(bundle);
                        Log.d("inicio",codprofe+"");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Inicio.this, "Error al Iniciar Sesion", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void IrEstudiantes(View view) {
        boolean isName = isEmptyName();
        if (isName) {
            Snackbar.make(view, "Añada su nombre.", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        } else {
            finish();
            Intent intent = new Intent(this, Estudiantes.class);
            nombreEstud = mTextname.getText().toString();

            SharedPreferences.Editor editor1 = mSharedPreferences.edit();
            editor1.putString(NAMEESTUD, nombreEstud);
            editor1.putInt(CODESTUD, codEstud);
            editor1.commit();
            startActivity(intent);
        }
    }

    public boolean isEmptyName() {
        return mTextname.getText()== null
                ||mTextname.getText().toString()==null
                ||mTextname.getText().toString().isEmpty();
    }

    private boolean isEmptyNameP() {
        return mTextProfName.getText()== null
                ||mTextProfName.getText().toString()==null
                ||mTextProfName.getText().toString().isEmpty();
    }

    private boolean isEmptyPassword() {
        return mTextPass.getText()== null
                ||mTextPass.getText().toString()==null
                ||mTextPass.getText().toString().isEmpty();
    }

    public void CrearProfe(View view) {
        Intent intent = new Intent(this, CrearProfe.class);
        startActivity(intent);
    }
}
