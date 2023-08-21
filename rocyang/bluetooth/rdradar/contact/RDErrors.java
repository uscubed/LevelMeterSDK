package me.rocyang.bluetooth.rdradar.contact;

import me.rocyang.bluetooth.rdradar.utils.CRC16Util;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class RDErrors {
  public static final byte[] OVER_BUFFER = "over buffer".getBytes();
  
  public static final String OVER_BUFFER_STR = "over buffer";
  
  public static final String[] RESULT_ERRORS = new String[] { "808401d2e8", "80840292e9", "8084035329", "80840412eb", "808301d0d8", "80830290d9", "8083035119", "80830410db" };
  
  public static final String RESULT_ERROR_1 = "808401d2e8";
  
  public static final String RESULT_ERROR_2 = "80840292e9";
  
  public static final String RESULT_ERROR_3 = "8084035329";
  
  public static final String RESULT_ERROR_4 = "80840412eb";
  
  public static final String RESULT_ERROR_5 = "808301d0d8";
  
  public static final String RESULT_ERROR_6 = "80830290d9";
  
  public static final String RESULT_ERROR_7 = "8083035119";
  
  public static final String RESULT_ERROR_8 = "80830410db";
  
  public static void main(String... paramVarArgs) {
    paramVarArgs = new String[4];
    paramVarArgs[0] = "808301";
    paramVarArgs[1] = "808302";
    paramVarArgs[2] = "808303";
    paramVarArgs[3] = "808304";
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++) {
      String str = HexUtil.encodeHexStr(CRC16Util.getData(HexUtil.decodeHex(paramVarArgs[b])));
      System.out.println(str);
    } 
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/RDErrors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */