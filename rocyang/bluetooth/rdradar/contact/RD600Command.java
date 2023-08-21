package me.rocyang.bluetooth.rdradar.contact;

import me.rocyang.bluetooth.rdradar.utils.CRC16Util;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class RD600Command {
  public static final String BACK_OFF = "Bluetooth and 485 passthrough are off.";
  
  public static final String BACK_ON = "Bluetooth and 485 passthrough are on.";
  
  public static final String CMD_CUMULATIVE_FLOW_H = "8004000500013fda";
  
  public static final String CMD_CUMULATIVE_FLOW_L = "8004000700019e1a";
  
  public static final String CMD_CUMULATIVE_FLOW_M = "800400060001cfda";
  
  public static final String CMD_EMPTY_HEIGHT = "800400030001dfdb";
  
  public static final String CMD_END_COMM = "<!>";
  
  public static final String CMD_FLOW_METER_SIGNAL_STRENGTH = "800400080001ae19";
  
  public static final String CMD_FLOW_RATE = "8004000100017e1b";
  
  public static final String CMD_INSTANTANEOUS_FLOW = "8004000400016e1a";
  
  public static final String CMD_START_COMM = "<->";
  
  public static final String CMD_VERTICAL_ANGLE = "8004000c0001efd8";
  
  public static final String CMD_WATER_LEVEL = "8004000200018e1b";
  
  public static final String CMD_WATER_LEVEL_SIGNAL_STRENGTH = "800400090001ffd9";
  
  private static final int DATA_INDEX = 3;
  
  private static final int OTHER_LENGTH = 5;
  
  public static final String READ_DITCH_CLASS = "8003001600017bdf";
  
  public static final String READ_DITCH_TYPE = "8003000f0001aa18";
  
  public static final String READ_FLOW_LOW_SPEED = "8003003000019a14";
  
  public static final String READ_FLOW_MEASURE_TIMES = "800300310001cbd4";
  
  public static final String READ_FLOW_METER_FILTER_TIMES = "8003000d00010bd8";
  
  public static final String READ_FLOW_METER_GEAR = "8003001700012a1f";
  
  public static final String READ_FLOW_METER_NOISE = "8003000b0001ebd9";
  
  public static final String READ_FLOW_METER_RADAR_DIRECTION = "8003001800011a1c";
  
  public static final String READ_FLOW_RELATIVE = "8003003300016a14";
  
  public static final String READ_FLOW_STABLE = "8003002f0001abd2";
  
  public static final String READ_HORIZONTAL_ANGLE = "8003001e0001fa1d";
  
  public static final String READ_MODULUS_A = "8003002900014bd3";
  
  public static final String READ_MODULUS_B = "8003002a0001bbd3";
  
  public static final String READ_MODULUS_C = "8003002b0001ea13";
  
  public static final String READ_RAINY_MODE_SETTING = "8003001f0001abdd";
  
  public static final String READ_ROUND_R = "800300110001ca1e";
  
  public static final String READ_SENSOR_HEIGHT = "8003001000019bde";
  
  public static final String READ_SHORE_COEFFICIENT = "8003001500018bdf";
  
  public static final String READ_SLEEP_TIME_SETTING = "8003002000019bd1";
  
  public static final String READ_STILL_WATER_SETTING = "8003002C00015bd2";
  
  public static final String READ_TRAPEZOID_BOTTOM = "8003001200013a1e";
  
  public static final String READ_TRAPEZOID_HEIGTH = "800300140001da1f";
  
  public static final String READ_TRAPEZOID_TOP = "8003001300016bde";
  
  public static final String READ_VERION = "8004000e00014e18";
  
  public static final String READ_WATER_LEVEL_FILTERING_TIMES = "8003000e0001fbd8";
  
  public static final String READ_WATER_LEVEL_METER_NOISE = "8003000c00015a18";
  
  public static final int VALUE_AUTOMATIC = 3;
  
  public static final int VALUE_CLASS_NORMAL = 2;
  
  public static final int VALUE_CLASS_SPLASH = 5;
  
  public static final int VALUE_CLASS_STRAIGHT = 1;
  
  public static final int VALUE_CLASS_TILT = 4;
  
  public static final int VALUE_CLASS_TURBULENT_FLOW = 3;
  
  public static final int VALUE_COUNTER_CURRENT = 2;
  
  public static final int VALUE_DICTH_TYPE_ROUND = 1;
  
  public static final int VALUE_DICTH_TYPE_TRAPEZOID = 2;
  
  public static final int VALUE_DICTH_TYPE_U = 3;
  
  public static final int VALUE_DOWNSTREAM = 1;
  
  public static final int VALUE_GEAR_1 = 1;
  
  public static final int VALUE_GEAR_2 = 2;
  
  public static final int VALUE_GEAR_3 = 3;
  
  public static final int VALUE_LP_1 = 1;
  
  public static final int VALUE_LP_2 = 2;
  
  public static final int VALUE_LP_3 = 3;
  
  public static final int VALUE_LP_4 = 4;
  
  public static final int VALUE_LP_5 = 5;
  
  public static final int VALUE_LP_CLOSE = 0;
  
  public static final int VALUE_RAIN_CLOSE = 2;
  
  public static final int VALUE_RAIN_OPEN = 1;
  
  public static final String WRITE_DITCH_CLASS = "80060016";
  
  public static final String WRITE_DITCH_TYPE = "8006000f";
  
  public static final String WRITE_FLOW_LOW_SPEED = "80060030";
  
  public static final String WRITE_FLOW_MEASURE_TIMES = "80060031";
  
  public static final String WRITE_FLOW_METER_FILTER_TIMES = "8006000d";
  
  public static final String WRITE_FLOW_METER_GEAR = "80060017";
  
  public static final String WRITE_FLOW_METER_NOISE = "8006000b";
  
  public static final String WRITE_FLOW_METER_RADAR_DIRECTION = "80060018";
  
  public static final String WRITE_FLOW_RELATIVE = "80060033";
  
  public static final String WRITE_FLOW_STABLE = "8006002f";
  
  public static final String WRITE_HORIZONTAL_ANGLE = "8006001e";
  
  public static final String WRITE_MODULUS_A = "80060029";
  
  public static final String WRITE_MODULUS_B = "8006002a";
  
  public static final String WRITE_MODULUS_C = "8006002b";
  
  public static final String WRITE_RAINY_MODE = "8006001f";
  
  public static final String WRITE_RESET = "80060025000147d0";
  
  public static final String WRITE_ROUND_R = "80060011";
  
  public static final String WRITE_SENSOR_HEIGHT = "80060010";
  
  public static final String WRITE_SHORE_COEFFICIENT = "80060015";
  
  public static final String WRITE_SLEEP_TIME_SETTING = "80060020";
  
  public static final String WRITE_STILL_WATER_SETTING = "8006002c";
  
  public static final String WRITE_TRAPEZOID_BOTTOM = "80060012";
  
  public static final String WRITE_TRAPEZOID_HEIGTH = "80060014";
  
  public static final String WRITE_TRAPEZOID_TOP = "80060013";
  
  public static final String WRITE_WATER_LEVEL_FILTERING_TIMES = "8006000e";
  
  public static final String WRITE_WATER_LEVEL_METER_NOISE = "8006000c";
  
  public static String[] advancedParamRead;
  
  public static String[] ditchAttriRead;
  
  public static String[] ditchParamRead;
  
  public static String[] infoParamCommand = new String[] { "8004000c0001efd8", "800400080001ae19", "800400090001ffd9", "8004000100017e1b", "800400030001dfdb", "8004000200018e1b", "8004000400016e1a", "8004000500013fda", "800400060001cfda", "8004000700019e1a" };
  
  public static String[] sensorParamRead;
  
  static {
    ditchParamRead = new String[] { "8003000f0001aa18", "800300110001ca1e", "8003001200013a1e", "8003001300016bde", "800300140001da1f", "8003001000019bde" };
    ditchAttriRead = new String[] { "8003001600017bdf", "8003001500018bdf" };
    sensorParamRead = new String[] { 
        "8003001700012a1f", "8003001800011a1c", "8003000b0001ebd9", "8003000c00015a18", "8003000d00010bd8", "8003000e0001fbd8", "8003001e0001fa1d", "8003001f0001abdd", "8003002000019bd1", "8003002C00015bd2", 
        "8003002900014bd3", "8003002a0001bbd3", "8003002b0001ea13" };
    advancedParamRead = new String[] { "8003003000019a14", "800300310001cbd4", "8003002f0001abd2", "8003003300016a14" };
  }
  
  public static String createCMDStr(String paramString, int paramInt) {
    byte[] arrayOfByte;
    if (paramInt < 65536) {
      arrayOfByte = HexUtil.getInt4ByteLow(paramInt);
    } else {
      arrayOfByte = HexUtil.intToByte4(paramInt);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(HexUtil.encodeHexStr(arrayOfByte));
    return HexUtil.encodeHexStr(CRC16Util.getData(HexUtil.decodeHex(stringBuilder.toString())));
  }
  
  public static int getData(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length == 0)
      return -1; 
    byte[] arrayOfByte = getDataArray(paramArrayOfbyte);
    return HexUtil.toInt(getDataArray(paramArrayOfbyte), 0, arrayOfByte.length);
  }
  
  public static byte[] getDataArray(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length == 0)
      return null; 
    int i = paramArrayOfbyte.length - 5;
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfbyte, 3, arrayOfByte, 0, i);
    return arrayOfByte;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/RD600Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */