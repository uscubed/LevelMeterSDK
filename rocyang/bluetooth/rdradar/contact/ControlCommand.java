package me.rocyang.bluetooth.rdradar.contact;

public class ControlCommand {
  public static final String QUERY_ALL_PARAMS_POSTFIX = "############";
  
  public static final String QUERY_APN = "catapn";
  
  public static final String QUERY_APN_PASSWORD = "catapw";
  
  public static final String QUERY_APN_USER_NAME = "catana";
  
  public static final String QUERY_CAT_WAIT = "catcw";
  
  public static final String QUERY_DATE = "date";
  
  public static final String QUERY_GPRS = "gprs";
  
  public static final String QUERY_ID = "catid";
  
  public static final String QUERY_IP = "catip";
  
  public static final byte[] QUERY_OLD_VERSION_POSTFIX;
  
  public static final String QUERY_OLD_VERSION_POSTFIX_STRING;
  
  public static final String QUERY_PARAM = "catep";
  
  public static final String QUERY_PORT = "catport";
  
  public static final String QUERY_POSTFIX = "@@";
  
  public static final String QUERY_SAVE_INTERVAL = "catsai";
  
  public static final String QUERY_SEND_INTERVAL = "catsei";
  
  public static final String QUERY_SENSOR = "catv";
  
  public static final String QUERY_SIGNLE = "csq";
  
  public static final String QUERY_STATE = "cates";
  
  public static final String QUERY_TASK = "task";
  
  public static final String QUERY_TEMP = "temp";
  
  public static final String QUERY_VOL = "vol";
  
  public static final String QUERY_WORK = "work";
  
  public static final String REBOOT = "reboot";
  
  public static final String[] REBOOT_END;
  
  public static final String REBOOT_FAIL = "reboot bad";
  
  public static final String REBOOT_SUCCESS = "reboot ok";
  
  public static final String RESET = "restore";
  
  public static final String[] RESET_END = new String[] { "restore ok", "restore bad" };
  
  public static final String RESET_FAIL = "restore bad";
  
  public static final String RESET_SUCCESS = "restore ok";
  
  public static final String SET_APN = "setapn";
  
  public static final String SET_APN_PASSWORD = "setapw";
  
  public static final String SET_APN_USER_NAME = "setana";
  
  public static final String SET_BLUENAME_FAIL_POSTFIX = " bad";
  
  public static final String SET_BLUENAME_SUCCESS_POSTFIX = " ok";
  
  public static final String SET_BLUE_NAME = "setble";
  
  public static final String SET_CAT_WAIT = "setcw";
  
  public static final String SET_DATE = "setdate";
  
  public static final String SET_FAIL = "bad";
  
  public static final String SET_FAIL_OLD_VERSION_POSTFIX;
  
  public static final String SET_FAIL_POSTFIX = " bad@@";
  
  public static final String SET_ID = "setid";
  
  public static final String SET_IP1 = "setceip1";
  
  public static final String SET_IP1_POSTFIX = "setceip [1] ok@@";
  
  public static final String SET_IP2 = "setceip2";
  
  public static final String SET_IP2_POSTFIX = "setceip [2] ok@@";
  
  public static final String SET_PORT1 = "setport1";
  
  public static final String SET_PORT1_POSTFIX = "setport [1] ok@@";
  
  public static final String SET_PORT2 = "setport2";
  
  public static final String SET_PORT2_POSTFIX = "setport [2] ok@@";
  
  public static final String SET_SAVE_INTERVAL = "setsai";
  
  public static final String SET_SEND_INTERVAL = "setsei";
  
  public static final String SET_SUCCESS = "ok";
  
  public static final String SET_SUCCESS_OLD_VERSION_POSTFIX;
  
  public static final String SET_SUCCESS_POSTFIX = " ok@@";
  
  static {
    REBOOT_END = new String[] { "reboot ok", "reboot bad" };
    QUERY_OLD_VERSION_POSTFIX = new byte[] { 13, 10 };
    QUERY_OLD_VERSION_POSTFIX_STRING = new String(QUERY_OLD_VERSION_POSTFIX);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" ok");
    stringBuilder.append(QUERY_OLD_VERSION_POSTFIX_STRING);
    SET_SUCCESS_OLD_VERSION_POSTFIX = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(" bad");
    stringBuilder.append(QUERY_OLD_VERSION_POSTFIX_STRING);
    SET_FAIL_OLD_VERSION_POSTFIX = stringBuilder.toString();
  }
  
  public static String getQueryGprsValue(int paramInt) {
    switch (paramInt) {
      default:
        return "";
      case 10:
        return "PPP失败";
      case 9:
        return "PPP已连接";
      case 8:
        return "连接到网络";
      case 7:
        return "关机";
      case 6:
        return "休眠";
      case 5:
        return "信号强度正常";
      case 4:
        return "没有连接到网络";
      case 3:
        return "无SIM卡";
      case 2:
        return "GPRS信号强度低";
      case 1:
        return "设备错误";
      case 0:
        break;
    } 
    return "复位";
  }
  
  public static String getQueryTaskValue(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 6) ? ((paramInt != 7) ? ((paramInt != 8) ? ((paramInt != 10) ? ((paramInt != 11) ? "" : "等待状态") : "关闭状态") : "发送数据") : "读取收到的数据") : "等待接收数据或发送数据") : "设置服务器和IP端口号") : "设置服务类型";
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/ControlCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */