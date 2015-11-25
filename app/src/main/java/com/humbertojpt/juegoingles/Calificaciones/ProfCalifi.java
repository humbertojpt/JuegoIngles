package com.humbertojpt.juegoingles.Calificaciones;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.humbertojpt.juegoingles.Adapters.EstudAdapter;
import com.humbertojpt.juegoingles.Adapters.InfoEstud;
import com.humbertojpt.juegoingles.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ProfCalifi extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private EstudAdapter mAdapter;

    protected SharedPreferences mSharedPreferences;
    protected String NAMEPROF = "NombreProf" , profename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_califi);

        mRecycleView = (RecyclerView) findViewById(R.id.recycleEstud);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        profename = mSharedPreferences.getString(NAMEPROF, "NombreProf");

        Bundle bundle = getIntent().getExtras();
        String x = bundle.getString("codcurso");
        getData(x);
    }

    public void getData(String codcurso){
        final List<InfoEstud> data = new ArrayList<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("estudiante");
        query.whereEqualTo("codcurso", codcurso);
        query.whereEqualTo("nombreprof", profename);
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject datos : objects) {
                        InfoEstud information = new InfoEstud();
                        information.name = datos.getString("nombrestud");
                        information.letra = datos.getString("letra");
                        information.calif = datos.getInt("calificacion");
                        data.add(information);
                    }
                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }

                mRecycleView.setLayoutManager(new LinearLayoutManager(ProfCalifi.this));
                mAdapter = new EstudAdapter(getApplicationContext(), data);
                mRecycleView.setAdapter(mAdapter);
            }
        });
    }
}
