package com.humbertojpt.juegoingles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Random;
import java.util.jar.Attributes;

public class CrearProfe extends AppCompatActivity {

    public EditText mTextName;
    private EditText mTextPass;
    private EditText mTextMail;
    private EditText mTextTel;
    private int codProfe;
    private SharedPreferences mSharedPreferences;
    protected String CODIGOPROFE = "codProfe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profe);

        mTextName = (EditText) findViewById(R.id.editNombre);
        mTextPass = (EditText) findViewById(R.id.editPass);
        mTextMail = (EditText) findViewById(R.id.editMail);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        codProfe();
    }

    public void GuardarProfe(View view) {

        boolean isName = isEmptyName();
        boolean isContra = isEmptyContra();
        boolean isMail = isEmptyMail();

        if (isName || isContra || isMail) {
            Snackbar.make(view, "Uno o mas campos estan vacios.", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        } else {
                String NameProfe = mTextName.getText().toString();
                String Contra = mTextPass.getText().toString();
                String Mail = mTextMail.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(NameProfe);
                user.setPassword(Contra);
                user.setEmail(Mail);
                user.put("codProfe", codProfe);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {

                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });

            Intent intent = new Intent(getApplicationContext(), Profesores.class);
            Bundle bundle = new Bundle();
            SharedPreferences.Editor editor1 = mSharedPreferences.edit();
            editor1.putString(CODIGOPROFE, codProfe + "");
            Log.d("prof", codProfe + "");
            editor1.commit();
            intent.putExtras(bundle);
            }
        finish();
        }

    public boolean isEmptyName() {
        return mTextName.getText()== null
                ||mTextName.getText().toString()==null
                ||mTextName.getText().toString().isEmpty();
    }
    public boolean isEmptyContra() {
        return mTextPass.getText()== null
                ||mTextPass.getText().toString()==null
                ||mTextPass.getText().toString().isEmpty();
    }
    public boolean isEmptyMail() {
        return mTextMail.getText()== null
                ||mTextMail.getText().toString()==null
                ||mTextMail.getText().toString().isEmpty();
    }

    public void codProfe(){
        Random r = new Random();
        codProfe = r.nextInt(100 - 1) + 1;
    }
}
