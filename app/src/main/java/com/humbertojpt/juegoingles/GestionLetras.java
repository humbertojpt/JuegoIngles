package com.humbertojpt.juegoingles;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.humbertojpt.juegoingles.Adapters.Information;
import com.humbertojpt.juegoingles.Adapters.Information1;
import com.humbertojpt.juegoingles.Adapters.ViewAdapter;
import com.humbertojpt.juegoingles.Adapters.customAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class GestionLetras extends AppCompatActivity implements ViewAdapter.RecyclerClickListner{

    private ViewAdapter viewAdapter;
    private RecyclerView mRecycleView;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private boolean habilitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_letras);

        mRecycleView = (RecyclerView) findViewById(R.id.recycle);


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

    }

    @Override
    public void itemClick(View view, int position) {
        if (position == 0){
            final ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.whereEqualTo("letra", 0);
            query.findInBackground(new FindCallback<ParseObject>() {

                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        for (ParseObject datos : objects) {
                            habilitar = datos.getBoolean("habilitar");
                            if (habilitar == true){
                                datos.put("habilitar",false);
                                datos.saveInBackground();
                                Toast.makeText(GestionLetras.this, "Letra A Deshabilitada", Toast.LENGTH_SHORT).show();
                            }else{

                                datos.put("habilitar",true);
                                datos.saveInBackground();
                                Toast.makeText(GestionLetras.this, "Letra A Habilitada", Toast.LENGTH_SHORT).show();
                            }
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
                            if (habilitar == true) {
                                datos.put("habilitar", false);
                                datos.saveInBackground();
                                Toast.makeText(GestionLetras.this, "Letra B Deshabilitada", Toast.LENGTH_SHORT).show();
                            } else {

                                datos.put("habilitar", true);
                                datos.saveInBackground();
                                Toast.makeText(GestionLetras.this, "Letra B Habilitada", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }
}
