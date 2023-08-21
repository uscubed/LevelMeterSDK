package me.rocyang.bluetooth.rdradar.contact;

public class MyBroadcast {
  private static final String BA_ROOT = "com.rocyang.bluetooth.broadcast.action.";
  
  public static class Connect {
    private static final String ROOT = "CONNECT.";
    
    public static final String START = "com.rocyang.bluetooth.broadcast.action.START";
    
    public static final String SUCCESS = "com.rocyang.bluetooth.broadcast.action.SUCCESS";
  }
  
  public static class Scan {
    public static final String START = "com.rocyang.bluetooth.broadcast.action.SCAN_START";
    
    public static final String STOP = "com.rocyang.bluetooth.broadcast.action.SCAN_STOP";
    
    public static final String SUCCESS = "com.rocyang.bluetooth.broadcast.action.SUCCESS";
    
    public static class Param {
      public static final String BLEDEVICE = "com.rocyang.bluetooth.broadcast.action.PARAM_SCAN_BELDEVICE";
    }
  }
  
  public static class Param {
    public static final String BLEDEVICE = "com.rocyang.bluetooth.broadcast.action.PARAM_SCAN_BELDEVICE";
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/MyBroadcast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */