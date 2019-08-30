package com.vodden.music.yaeta;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import javax.inject.Inject;

public class AndroidLauncher extends AndroidApplication {

  /* Create placeholder for user's consent to record_audio permission.
   * This will be used in handling callback
   */
  private static final int PERMISSION_RECORD_AUDIO = 2088547093;

  @Inject
  Application application;

  @Inject
  public AndroidLauncher() {
    super();
    AndroidComponent androidComponent = DaggerAndroidComponent.builder().androidModule(new AndroidModule()).build();
    androidComponent.inject(this);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useAccelerometer = false;
    config.useCompass = false;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (this.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
        this.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_RECORD_AUDIO);
      }
    }

    initialize(application, config);
  }
}
