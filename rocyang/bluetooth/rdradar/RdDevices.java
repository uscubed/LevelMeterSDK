package me.rocyang.bluetooth.rdradar;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.clj.fastble.BleManager;
import java.util.concurrent.LinkedBlockingQueue;
import me.rocyang.bluetooth.rdradar.contact.DeviceType;
import me.rocyang.bluetooth.rdradar.entity.RWData;

public class RdDevices extends Application {
  public static int ScreenHeight = 0;
  
  public static int ScreenWidth = 0;
  
  private static final String TAG = "RdDevices";
  
  public static DeviceType deviceType = DeviceType.UNKNOW;
  
  public static Typeface iconfont;
  
  private static RdDevices instance;
  
  public static Boolean isLogined = Boolean.valueOf(false);
  
  public static LinkedBlockingQueue<byte[]> recvQueue;
  
  public RWData deviceParam;
  
  public static Application getInstance() {
    return instance;
  }
  
  public static int packageCode(Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    int i = 0;
    try {
      int j = (packageManager.getPackageInfo(paramContext.getPackageName(), 0)).versionCode;
      i = j;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("packageCode: ");
      stringBuilder.append(nameNotFoundException.getMessage());
      Log.e("RdDevices", stringBuilder.toString());
    } 
    return i;
  }
  
  public static String packageName(Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      String str = (packageManager.getPackageInfo(paramContext.getPackageName(), 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("packageCode: ");
      stringBuilder.append(nameNotFoundException.getMessage());
      Log.e("RdDevices", stringBuilder.toString());
      nameNotFoundException = null;
    } 
    return (String)nameNotFoundException;
  }
  
  public void onCreate() {
    super.onCreate();
    instance = this;
    BleManager.getInstance().init(this);
    iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
    recvQueue = (LinkedBlockingQueue)new LinkedBlockingQueue<byte>();
    WindowManager windowManager = (WindowManager)getSystemService("window");
    DisplayMetrics displayMetrics = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    ScreenWidth = displayMetrics.widthPixels;
    ScreenHeight = displayMetrics.heightPixels;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/RdDevices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */