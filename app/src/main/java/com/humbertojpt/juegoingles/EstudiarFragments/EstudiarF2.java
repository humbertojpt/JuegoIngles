package com.humbertojpt.juegoingles.EstudiarFragments;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public class EstudiarF2 extends Fragment {

    private String  letra ;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EstudiarF2 newInstance(int sectionNumber) {
        EstudiarF2 fragment = new EstudiarF2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.estudiar_f2, container, false);

        final ImageView image_expert = (ImageView) root.findViewById(R.id.imageView2);
        final TextView texto = (TextView) root.findViewById(R.id.textImagen);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        if (letra.equals("A") ){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.getInBackground("8NazA7GIOr", new GetCallback<ParseObject>() {
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
        }else{
            ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.getInBackground("CzL6GZoweh", new GetCallback<ParseObject>() {
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
        }





        return root;
    }

}
