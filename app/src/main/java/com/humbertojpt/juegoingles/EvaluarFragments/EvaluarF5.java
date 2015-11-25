package com.humbertojpt.juegoingles.EvaluarFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.humbertojpt.juegoingles.Calificaciones.Calificaciones;
import com.humbertojpt.juegoingles.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluarF5 extends Fragment {

    private EditText texto5;
    private ImageView mImageView;
    protected String CALIFI5 = "calificacion";
    protected SharedPreferences mSharedPreferences;
    private String textoImagen;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button EnviarExamen;
    private String  letra ;
    protected String LETRA = "Letra";
    private java.lang.String L;

    public static EvaluarF5 newInstance(int sectionNumber) {
        EvaluarF5 fragment = new EvaluarF5();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.evaluar_f5, container, false);

        mImageView = (ImageView) rootView.findViewById(R.id.imageView5);
        EnviarExamen = (Button) rootView.findViewById(R.id.Enviar);
        texto5 = (EditText) rootView.findViewById(R.id.editText5);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        if (letra.equals("A") ){
            L = "Ucg6rn4nzq";
        }else{
            L = "eP3zBSK2tX";
        }
        ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
        query.getInBackground(L, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    textoImagen = object.getString("texto");
                    ParseFile applicantResume = (ParseFile) object.get("imagen");
                    applicantResume.getDataInBackground(new GetDataCallback() {
                        public void done(byte[] data, ParseException e) {
                            if (e == null) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                mImageView.setImageBitmap(bmp);
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    e.printStackTrace();
                }
            }

        });

        EnviarExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = texto5.getText().toString();
                SharedPreferences.Editor editor1 = mSharedPreferences.edit();

                if (texto.equals(textoImagen)) {
                    Evaluar.calif = Evaluar.calif+1;
                }
                Log.d("calif", Evaluar.calif + "");
                Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity().getApplication(), Calificaciones.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }


}
