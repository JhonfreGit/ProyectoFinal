package com.jhonfre.proyectofinal;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FaceActivity extends AppCompatActivity {

    private CallbackManager cM;
    private LoginButton lB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        cM = CallbackManager.Factory.create();

        getFbKeyHash("2jmj7l5rSw0yVb/vlWAYkK/YBwk=");

        setContentView(R.layout.activity_face);

        lB = (LoginButton)findViewById(R.id.login_button);

        lB.registerCallback(cM, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(FaceActivity.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(FaceActivity.this, "¡Inicio de sesión cancelado!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(FaceActivity.this, "¡Inicio de sesión NO exitoso!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFbKeyHash(String packageName){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                System.out.println("KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

    }
    protected void onActivityResult(int reqCode, int resCode, Intent i){
        cM.onActivityResult(reqCode, resCode, i);
    }
}

