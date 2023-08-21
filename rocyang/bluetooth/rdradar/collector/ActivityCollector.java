package me.rocyang.bluetooth.rdradar.collector;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import me.rocyang.bluetooth.rdradar.view.MainActivity;

public class ActivityCollector {
  private static List<Activity> activities = new ArrayList<Activity>();
  
  public static void add(Activity paramActivity) {
    if (activities.contains(paramActivity))
      return; 
    activities.add(paramActivity);
  }
  
  public static void finishAll() {
    for (Activity activity : activities) {
      if (!activity.isFinishing())
        activity.finish(); 
    } 
    activities.clear();
  }
  
  public static void finishConnected() {
    for (Activity activity : activities) {
      if (!activity.getClass().equals(MainActivity.class) && !activity.isFinishing())
        activity.finish(); 
    } 
  }
  
  public static void remove(Activity paramActivity) {
    activities.remove(paramActivity);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/collector/ActivityCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */