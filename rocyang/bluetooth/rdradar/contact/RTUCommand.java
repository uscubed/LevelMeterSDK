package me.rocyang.bluetooth.rdradar.contact;

import java.util.HashMap;
import java.util.Map;

public enum RTUCommand {
  QUERY_ANA,
  QUERY_APN,
  QUERY_APW,
  QUERY_CAT_WAIT,
  QUERY_DATE,
  QUERY_GPRS,
  QUERY_ID,
  QUERY_IP,
  QUERY_PARAM,
  QUERY_PORT,
  QUERY_SAVE_INTERVAL,
  QUERY_SEND_INTERVAL,
  QUERY_SENSOR,
  QUERY_SIGNLE("csq", 2131755225, 2131755267, 0),
  QUERY_STATE("csq", 2131755225, 2131755267, 0),
  QUERY_TASK("csq", 2131755225, 2131755267, 0),
  QUERY_TEMP("csq", 2131755225, 2131755267, 0),
  QUERY_VOL("csq", 2131755225, 2131755267, 0),
  QUERY_WORK("csq", 2131755225, 2131755267, 0),
  SET_BLUE_NAME("csq", 2131755225, 2131755267, 0),
  SET_CAT_WAIT("csq", 2131755225, 2131755267, 0),
  SET_DATE("csq", 2131755225, 2131755267, 0),
  SET_ID("csq", 2131755225, 2131755267, 0),
  SET_IP1("csq", 2131755225, 2131755267, 0),
  SET_IP2("csq", 2131755225, 2131755267, 0),
  SET_PORT1("csq", 2131755225, 2131755267, 0),
  SET_PORT2("csq", 2131755225, 2131755267, 0),
  SET_SAVE_INTERVAL("csq", 2131755225, 2131755267, 0),
  SET_SEND_INTERVAL("csq", 2131755225, 2131755267, 0);
  
  private static final RTUCommand[] $VALUES;
  
  private static final Map<String, RTUCommand> CmdMap;
  
  private String cmd;
  
  private int divNum;
  
  private int str;
  
  private int unit;
  
  static {
    QUERY_GPRS = new RTUCommand("QUERY_GPRS", 1, "gprs", 2131755255, 2131755267, 0);
    QUERY_TASK = new RTUCommand("QUERY_TASK", 2, "task", 2131755341, 2131755267, 0);
    QUERY_TEMP = new RTUCommand("QUERY_TEMP", 3, "temp", 2131755342, 2131755212, 0);
    QUERY_VOL = new RTUCommand("QUERY_VOL", 4, "vol", 2131755392, 2131755345, 0);
    QUERY_WORK = new RTUCommand("QUERY_WORK", 5, "work", 2131755395, 2131755267, 0);
    QUERY_ID = new RTUCommand("QUERY_ID", 6, "catid", 2131755206, 2131755267, 0);
    QUERY_SAVE_INTERVAL = new RTUCommand("QUERY_SAVE_INTERVAL", 7, "catsai", 2131755209, 2131755266, 0);
    QUERY_SEND_INTERVAL = new RTUCommand("QUERY_SEND_INTERVAL", 8, "catsei", 2131755210, 2131755266, 0);
    QUERY_CAT_WAIT = new RTUCommand("QUERY_CAT_WAIT", 9, "catcw", 2131755203, 2131755312, 0);
    QUERY_PORT = new RTUCommand("QUERY_PORT", 10, "catport", 2131755208, 2131755267, 0);
    QUERY_IP = new RTUCommand("QUERY_IP", 11, "catip", 2131755207, 2131755267, 0);
    QUERY_DATE = new RTUCommand("QUERY_DATE", 12, "date", 2131755229, 2131755267, 0);
    QUERY_APN = new RTUCommand("QUERY_APN", 13, "catapn", 2131755193, 2131755267, 0);
    QUERY_ANA = new RTUCommand("QUERY_ANA", 14, "catana", 2131755195, 2131755267, 0);
    QUERY_APW = new RTUCommand("QUERY_APW", 15, "catapw", 2131755194, 2131755267, 0);
    QUERY_SENSOR = new RTUCommand("QUERY_SENSOR", 16, "catv", 2131755211, 2131755267, 0);
    QUERY_PARAM = new RTUCommand("QUERY_PARAM", 17, "catep", 2131755204, 2131755267, 0);
    QUERY_STATE = new RTUCommand("QUERY_STATE", 18, "cates", 2131755205, 2131755267, 0);
    SET_BLUE_NAME = new RTUCommand("SET_BLUE_NAME", 19, "setble", 2131755316, 2131755267, 0);
    SET_ID = new RTUCommand("SET_ID", 20, "setid", 2131755325, 2131755267, 0);
    SET_SAVE_INTERVAL = new RTUCommand("SET_SAVE_INTERVAL", 21, "setsai", 2131755330, 2131755266, 0);
    SET_SEND_INTERVAL = new RTUCommand("SET_SEND_INTERVAL", 22, "setsei", 2131755332, 2131755266, 0);
    SET_CAT_WAIT = new RTUCommand("SET_CAT_WAIT", 23, "setcw", 2131755321, 2131755312, 0);
    SET_PORT1 = new RTUCommand("SET_PORT1", 24, "setport1", 2131755327, 2131755267, 0);
    SET_IP1 = new RTUCommand("SET_IP1", 25, "setceip1", 2131755318, 2131755267, 0);
    SET_PORT2 = new RTUCommand("SET_PORT2", 26, "setport2", 2131755328, 2131755267, 0);
    SET_IP2 = new RTUCommand("SET_IP2", 27, "setceip2", 2131755319, 2131755267, 0);
    SET_DATE = new RTUCommand("SET_DATE", 28, "setdate", 2131755323, 2131755267, 0);
    RTUCommand rTUCommand = QUERY_SIGNLE;
    byte b = 0;
    $VALUES = new RTUCommand[] { 
        rTUCommand, QUERY_GPRS, QUERY_TASK, QUERY_TEMP, QUERY_VOL, QUERY_WORK, QUERY_ID, QUERY_SAVE_INTERVAL, QUERY_SEND_INTERVAL, QUERY_CAT_WAIT, 
        QUERY_PORT, QUERY_IP, QUERY_DATE, QUERY_APN, QUERY_ANA, QUERY_APW, QUERY_SENSOR, QUERY_PARAM, QUERY_STATE, SET_BLUE_NAME, 
        SET_ID, SET_SAVE_INTERVAL, SET_SEND_INTERVAL, SET_CAT_WAIT, SET_PORT1, SET_IP1, SET_PORT2, SET_IP2, SET_DATE };
    CmdMap = new HashMap<String, RTUCommand>();
    RTUCommand[] arrayOfRTUCommand = values();
    int i = arrayOfRTUCommand.length;
    while (b < i) {
      rTUCommand = arrayOfRTUCommand[b];
      CmdMap.put(rTUCommand.getCmd(), rTUCommand);
      b++;
    } 
  }
  
  RTUCommand(String paramString1, int paramInt1, int paramInt2, int paramInt3) {
    this.cmd = paramString1;
    this.str = paramInt1;
    this.unit = paramInt2;
    this.divNum = paramInt3;
  }
  
  public static RTUCommand getByCmd(String paramString) {
    for (RTUCommand rTUCommand : values()) {
      if (rTUCommand.getCmd().equals(paramString))
        return rTUCommand; 
    } 
    return null;
  }
  
  public static RTUCommand startWith(String paramString) {
    for (RTUCommand rTUCommand : values()) {
      if (paramString.startsWith(rTUCommand.getCmd()))
        return rTUCommand; 
    } 
    return null;
  }
  
  public String getCmd() {
    return this.cmd;
  }
  
  public int getDivNum() {
    return this.divNum;
  }
  
  public RTUCmdType getRTUCmdType() {
    RTUCmdType rTUCmdType;
    if (name().startsWith(RTUCmdType.QUERY.getName())) {
      rTUCmdType = RTUCmdType.QUERY;
    } else {
      rTUCmdType = RTUCmdType.SET;
    } 
    return rTUCmdType;
  }
  
  public int getStr() {
    return this.str;
  }
  
  public int getUnit() {
    return this.unit;
  }
  
  public String toString() {
    return name();
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/RTUCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */