package com.humbertojpt.juegoingles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.humbertojpt.juegoingles.Adapters.Information1;
import com.humbertojpt.juegoingles.Adapters.ViewAdapter;
import com.humbertojpt.juegoingles.EstudiarFragments.Estudiar;
import com.humbertojpt.juegoingles.EvaluarFragments.Evaluar;
import com.humbertojpt.juegoingles.PracticarFragments.Practicar;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Etapas extends AppCompatActivity implements ViewAdapter.RecyclerClickListner {

    private ViewAdapter viewAdapter;
    private RecyclerView mRecycleView;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private LinearLayout escogerEtapas;
    private boolean habilitar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas);

        mRecycleView = (RecyclerView) findViewById(R.id.recycle);
        escogerEtapas = (LinearLayout) findViewById(R.id.escogerEtapas);

        List<Information1> data = new ArrayList<>();
        String[] titulo = getResources().getStringArray(R.array.data);


        for (int i = 0; i < titulo.length;i++){
            Information1 info = new Information1(titulo[i]);
            data.add(info);
        }
        viewAdapter= new ViewAdapter(this,data);
        viewAdapter.setRecyclerClickListner(this);
        mRecycleView.setAdapter(viewAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        escogerEtapas.setVisibility(View.INVISIBLE);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.inicio:
                Intent intent=new Intent(this, Inicio.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.cursos:
                Intent intent1=new Intent(this, Estudiantes.class);
                finish();
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void itemClick(View view, int position) {
        final SharedPreferences.Editor editor1 = mSharedPreferences.edit();
        if (position == 0){

            final ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.whereEqualTo("letra", 0);
            query.findInBackground(new FindCallback<ParseObject>() {

                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        for (ParseObject datos : objects) {
                            habilitar = datos.getBoolean("habilitar");
                        }
                        if (habilitar == true) {
                            Toast.makeText(Etapas.this, "Letra A Seleccionada", Toast.LENGTH_SHORT).show();
                            escogerEtapas.setVisibility(View.VISIBLE);
                            editor1.putString(LETRA, "A");
                        } else {
                            Toast.makeText(Etapas.this, "Letra A No disponible", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else{
            final ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.whereEqualTo("letra", 1);
            query.findInBackground(new FindCallback<ParseObject>() {

                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        for (ParseObject datos : objects) {
                            habilitar = datos.getBoolean("habilitar");
                        }
                        if (habilitar == true) {
                            Toast.makeText(Etapas.this, "Letra B Seleccionada", Toast.LENGTH_SHORT).show();
                            escogerEtapas.setVisibility(View.VISIBLE);
                            editor1.putString(LETRA, "B");
                        } else {
                            Toast.makeText(Etapas.this, "Letra B No disponible", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        editor1.commit();
    }

    public void estudiar(View view) {
        Intent intent = new Intent(this, Estudiar.class);
        startActivity(intent);
    }

    public void Practicar(View view) {
        Intent intent = new Intent(this, Practicar.class);
        startActivity(intent);
    }

    public void Evaluar(View view) {
        Intent intent = new Intent(this, Evaluar.class);
        startActivity(intent);
    }
}
