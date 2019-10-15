package com.pickeremailphone;

import javax.annotation.Nonnull;
import android.app.PendingIntent;
import android.content.IntentSender;
import com.google.android.gms.auth.api.Auth;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.common.api.GoogleApiClient;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.google.android.gms.auth.api.credentials.HintRequest;

public class PickEmailAndPhone extends ReactContextBaseJavaModule {

    String TAG = "PickEmailAndPhone";
    GoogleApiClient googleApiClient;

    public PickEmailAndPhone(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        MainApplication.setReactContext(reactContext);
        googleApiClient = new GoogleApiClient.Builder(reactContext)
                .addApi(Auth.CREDENTIALS_API)
                .build();
    }

    @Nonnull
    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getEmail() {
        requestHint("show_email");
    }

    @ReactMethod
    public void getPhoneNumber() {
        requestHint("show_phone");
    }

    private void requestHint(String type) {
        if(type=="show_phone") {
            HintRequest hintRequest = new HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build();
            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
            try {
                MainApplication.getReactContext().getCurrentActivity().startIntentSenderForResult(intent.getIntentSender(), 10001, null, 0, 0, 0);
            }
            catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
        if(type=="show_email") {
            HintRequest hintRequest = new HintRequest.Builder()
                    .setEmailAddressIdentifierSupported(true)
                    .build();
            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
            try {
                MainApplication.getReactContext().getCurrentActivity().startIntentSenderForResult(intent.getIntentSender(), 10002, null, 0, 0, 0);
            }
            catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }
}
