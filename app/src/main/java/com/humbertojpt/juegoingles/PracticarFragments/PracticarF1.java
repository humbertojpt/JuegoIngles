package com.humbertojpt.juegoingles.PracticarFragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
public class PracticarF1 extends Fragment {

    private ImageView mImageView;
    private RadioButton rbOpcion1, rbOpcion2, rbOpcion3, rbOpcion4;
    private RadioGroup rgOpciones;

    private String  letra , L ;
    protected SharedPreferences mSharedPreferences;
    protected String LETRA = "Letra";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button validar;

    public static PracticarF1 newInstance(int sectionNumber) {
        PracticarF1 fragment = new PracticarF1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.practicarf1, container, false);

        mImageView = (ImageView) rootView.findViewById(R.id.imageView1);
        validar = (Button) rootView.findViewById(R.id.validar);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        letra = mSharedPreferences.getString(LETRA, "letra");

        rbOpcion1 = (RadioButton)rootView.findViewById(R.id.RbOpcion1);
        rbOpcion2 = (RadioButton)rootView.findViewById(R.id.RbOpcion2);
        rbOpcion3 = (RadioButton)rootView.findViewById(R.id.RbOpcion3);
        rbOpcion4 = (RadioButton)rootView.findViewById(R.id.RbOpcion4);
        final Activity activity = getActivity();
        rgOpciones = (RadioGroup) rootView.findViewById(R.id.GrbGrupo1);

        if (letra.equals("A") ){
            L = "wQbpqhDjiM";
            rbOpcion1.setText("Apple");
            rbOpcion2.setText("Pear");
            rbOpcion3.setText("Watermelon");
            rbOpcion4.setText("Orange");
        }else{
            L = "eP3zBSK2tX";
            rbOpcion1.setText("Balloon");
            rbOpcion2.setText("Boat");
            rbOpcion3.setText("Beer");
            rbOpcion4.setText("Backend");
        }
        ParseQuery<ParseObject> query = ParseQuery.getQuery("imagenes");
        query.getInBackground(L, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    //TextoImagen.setText(object.getString("texto"));
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

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rbOpcion1.isChecked()) {
                    Toast.makeText(activity, "Opcion Correcta", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Opcion Incorrecta", Toast.LENGTH_SHORT).show();
                }
                rbOpcion1.setChecked(false);
                rbOpcion2.setChecked(false);rbOpcion3.setChecked(false);rbOpcion4.setChecked(false);
            }
        });

        return rootView;
    }

}
