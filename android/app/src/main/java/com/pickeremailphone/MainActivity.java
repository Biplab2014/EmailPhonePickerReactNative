package com.pickeremailphone;

import android.content.Intent;
import com.facebook.react.ReactActivity;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.auth.api.credentials.Credential;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "pickeremailphone";
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 10001) {
      if (resultCode == RESULT_OK) {
        Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);
        MainApplication.getReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("user_phone_number", cred.getId());
      }
    }

    if (requestCode == 10002) {
      if (resultCode == RESULT_OK) {
        Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);
        MainApplication.getReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("user_email", cred.getId());
      }
    }
  }

}
