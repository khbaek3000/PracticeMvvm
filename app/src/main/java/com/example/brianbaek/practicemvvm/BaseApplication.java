package com.example.brianbaek.practicemvvm;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.brianbaek.practicemvvm.di.component.AppComponent;
import com.example.brianbaek.practicemvvm.di.component.DaggerAppComponent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.json.JsonFactory;
import com.google.gson.Gson;
import com.nimbusds.jose.JWSObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Random;
import java.util.UUID;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class BaseApplication extends DaggerApplication {

    byte[] nonce;
    Random random = new SecureRandom();

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // This is check is able to use GooglePlayServices.
        if(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext())
                == ConnectionResult.SUCCESS){
            String nonceString = UUID.randomUUID().toString();
            nonce = getRequestNonce();
            Log.d("nonce", "nonceToString" + nonce.toString());
            //Log.d("nonce" , "noncebytes" + nonceString);
            //compatibilityCheckRequest();
        }

    }
    private byte[] getRequestNonce(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        try {
            byteArrayOutputStream.write(bytes);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] getRequestNonce(String data){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        try {
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(data.getBytes());
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    // (nonce, apikey)
    private void compatibilityCheckRequest(){
        SafetyNet.getClient(getApplicationContext()).attest(nonce, "AIzaSyAY4sJWP7oP7QhypWrX-TG9J-Zs0Ed-pxQ")
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.AttestationResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.AttestationResponse attestationResponse) {
                        Log.d("compatibilityCheckOk", attestationResponse.getJwsResult());
                        try {
                            JWSObject jwsObject = JWSObject.parse(attestationResponse.getJwsResult());
                            Log.d("jws" , "header = " + jwsObject.getHeader() );
                            Log.d("jws" , "header = " + jwsObject.getHeader().getX509CertChain() );
                            Log.d("jws" , "paylod = " + jwsObject.getPayload().toJSONObject() );
                            Log.d("jws" , "signature = " + jwsObject.getSignature() );
                            Log.d("jws" , "signature = " + jwsObject.getSignature().decodeToString() );


                        } catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("compatibilityCheckfail", "compatibilityCheckfail");
                    }
                });
    }
}
