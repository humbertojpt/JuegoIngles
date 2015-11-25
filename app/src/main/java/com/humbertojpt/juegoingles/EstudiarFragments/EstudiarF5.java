package com.humbertojpt.juegoingles.EstudiarFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.humbertojpt.juegoingles.CrearProfe;
import com.humbertojpt.juegoingles.Etapas;
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
public class EstudiarF5 extends Fragment {

    private Button mButton;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private java.lang.String L;
    private String  letra ;

    public static EstudiarF5 newInstance(int sectionNumber) {
        EstudiarF5 fragment = new EstudiarF5();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.estudiar_f5, container, false);

        final ImageView image_expert = (ImageView) rootView.findViewById(R.id.imageView5);
        final TextView texto = (TextView) rootView.findViewById(R.id.textImagen5);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        if (letra.equals("A") ){
            L = "Ucg6rn4nzq";
        }else{
            L = "2koXmGX0ak";
        }
        ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
        query.getInBackground(L, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    texto.setText(object.getString("texto"));
                    ParseFile applicantResume = (ParseFile) object.get("imagen");
                    applicantResume.getDataInBackground(new GetDataCallback() {
                        public void done(byte[] data, ParseException e) {
                            if (e == null) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                image_expert.setImageBitmap(bmp);
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

        return rootView;
    }


}
