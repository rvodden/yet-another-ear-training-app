package com.vodden.music.yaeta;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

class IOSLauncher extends IOSApplication.Delegate {
  public static void main(String[] argv) {
    NSAutoreleasePool pool = new NSAutoreleasePool();
    UIApplication.main(argv, null, IOSLauncher.class);
    pool.close();
  }

  @Override
  protected IOSApplication createApplication() {
    IOSApplicationConfiguration config = new IOSApplicationConfiguration();
    /* FIXME: need to write and inject an IOS AudioDispatcher */
    return new IOSApplication(new Application(null, null), config);
  }
}