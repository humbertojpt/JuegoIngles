package com.humbertojpt.juegoingles.EstudiarFragments;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.humbertojpt.juegoingles.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstudiarF1 extends Fragment {

    private TextView TextoImagen;
    private ImageView mImageView;
    private String  letra ;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EstudiarF1 newInstance(int sectionNumber) {
        EstudiarF1 fragment = new EstudiarF1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.estudiar_f1, container, false);

        TextoImagen = (TextView) rootView.findViewById(R.id.textImagen);
        mImageView = (ImageView) rootView.findViewById(R.id.imageView);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        if (letra.equals("A") ){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.getInBackground("wQbpqhDjiM", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        TextoImagen.setText(object.getString("texto"));
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
        }else{
            ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
            query.getInBackground("eP3zBSK2tX", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        TextoImagen.setText(object.getString("texto"));
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
        }


        return rootView;
    }

}
