package me.rocyang.bluetooth.rdradar.contact;

public enum DeviceType {
  RD300s, RD306, RD600s, UNKNOW;
  
  private static final DeviceType[] $VALUES;
  
  static {
    RD600s = new DeviceType("RD600s", 1);
    RD300s = new DeviceType("RD300s", 2);
    RD306 = new DeviceType("RD306", 3);
    $VALUES = new DeviceType[] { UNKNOW, RD600s, RD300s, RD306 };
  }
  
  public String toString() {
    return name();
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/DeviceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */