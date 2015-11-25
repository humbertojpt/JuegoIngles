package com.humbertojpt.juegoingles.EvaluarFragments;


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
import android.widget.TextView;
import android.widget.Toast;

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
public class EvaluarF2 extends Fragment {


    private EditText texto2;
    private Button e2;
    private ImageView mImageView;
    private String textoImagen , letra , L;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EvaluarF2 newInstance(int sectionNumber) {
        EvaluarF2 fragment = new EvaluarF2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.evaluar_f2, container, false);

        mImageView = (ImageView) rootView.findViewById(R.id.imageView2);
        texto2 = (EditText) rootView.findViewById(R.id.editText2);
        e2 = (Button) rootView.findViewById(R.id.e2);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        if (letra.equals("A") ){
            L = "8NazA7GIOr";
        }else{
            L = "y3z95MxF4x";
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

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = texto2.getText().toString();
                if (texto.equals(textoImagen)) {
                    Evaluar.calif = Evaluar.calif+1;
                }
                Log.d("calif", Evaluar.calif + "");
                Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
