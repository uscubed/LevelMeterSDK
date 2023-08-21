package me.rocyang.bluetooth.rdradar.contact;

public enum RTUCmdType {
  QUERY("QUERY"),
  SET("SET");
  
  private static final RTUCmdType[] $VALUES;
  
  private String name;
  
  static {
    $VALUES = new RTUCmdType[] { QUERY, SET };
  }
  
  RTUCmdType(String paramString1) {
    this.name = paramString1;
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/RTUCmdType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */