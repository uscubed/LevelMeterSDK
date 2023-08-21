package me.rocyang.bluetooth.rdradar.utils;

import android.widget.Toast;
import me.rocyang.bluetooth.rdradar.RdDevices;

public class ToastUtil {
  public static void ShowTextLong(String paramString) {
    Toast.makeText(RdDevices.getInstance().getApplicationContext(), paramString, 1).show();
  }
  
  public static void ShowTextShort(String paramString) {
    Toast.makeText(RdDevices.getInstance().getApplicationContext(), paramString, 0).show();
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/utils/ToastUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */