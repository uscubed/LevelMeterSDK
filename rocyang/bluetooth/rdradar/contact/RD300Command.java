package me.rocyang.bluetooth.rdradar.contact;

import me.rocyang.bluetooth.rdradar.utils.CRC16Util;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class RD300Command {
  public static final String BACK_OFF = "Bluetooth and 485 passthrough are off.";
  
  public static final String BACK_ON = "Bluetooth and 485 passthrough are on.";
  
  public static final String CMD_CUMULATIVE_FLOW_H = "800400090001ffd9";
  
  public static final String CMD_CUMULATIVE_FLOW_L = "8004000b00015e19";
  
  public static final String CMD_CUMULATIVE_FLOW_M = "8004000a00010fd9";
  
  public static final String CMD_EMPTY_HEIGHT = "8004000200018e1b";
  
  public static final String CMD_END_COMM = "<!>";
  
  public static final String CMD_INSTANTANEOUS_FLOW_H = "8004000700019e1a";
  
  public static final String CMD_INSTANTANEOUS_FLOW_L = "800400080001ae19";
  
  public static final String CMD_START_COMM = "<->";
  
  public static final String CMD_WATER_LEVEL = "8004000100017e1b";
  
  public static final String CMD_WATER_LEVEL_SIGNAL_STRENGTH = "800400030001dfdb";
  
  private static final int DATA_INDEX = 3;
  
  private static final int OTHER_LENGTH = 5;
  
  public static final String READ_HOUDAO = "8003001300016bde";
  
  public static final String READ_PIPE_TYPE = "8003000f0001aa18";
  
  public static final String READ_SENSOR_HEIGHT = "8003000600017a1a";
  
  public static final String READ_VERION = "800400060001cfda";
  
  public static final String READ_WATER_LEVEL_FILTERING_TIMES = "8003000500018a1a";
  
  public static final String READ_WATER_LEVEL_METER_NOISE = "800300040001dbda";
  
  public static final int VALUE_HOUDAO_0_025 = 1;
  
  public static final int VALUE_HOUDAO_0_051 = 2;
  
  public static final int VALUE_HOUDAO_0_076 = 3;
  
  public static final int VALUE_HOUDAO_0_152 = 4;
  
  public static final int VALUE_HOUDAO_0_228 = 5;
  
  public static final int VALUE_HOUDAO_0_25 = 6;
  
  public static final int VALUE_HOUDAO_0_30 = 7;
  
  public static final int VALUE_HOUDAO_0_45 = 8;
  
  public static final int VALUE_HOUDAO_0_60 = 9;
  
  public static final int VALUE_HOUDAO_0_75 = 10;
  
  public static final int VALUE_HOUDAO_0_90 = 11;
  
  public static final int VALUE_HOUDAO_12_19 = 24;
  
  public static final int VALUE_HOUDAO_15_24 = 25;
  
  public static final int VALUE_HOUDAO_1_00 = 12;
  
  public static final int VALUE_HOUDAO_1_20 = 13;
  
  public static final int VALUE_HOUDAO_1_50 = 14;
  
  public static final int VALUE_HOUDAO_1_80 = 15;
  
  public static final int VALUE_HOUDAO_2_10 = 16;
  
  public static final int VALUE_HOUDAO_2_40 = 17;
  
  public static final int VALUE_HOUDAO_3_05 = 18;
  
  public static final int VALUE_HOUDAO_3_66 = 19;
  
  public static final int VALUE_HOUDAO_4_57 = 20;
  
  public static final int VALUE_HOUDAO_6_10 = 21;
  
  public static final int VALUE_HOUDAO_7_62 = 22;
  
  public static final int VALUE_HOUDAO_9_14 = 23;
  
  public static final int VALUE_PIPE_CLOSE = 2;
  
  public static final int VALUE_PIPE_OPEN = 1;
  
  public static final String WRITE_HOUDAO = "80060013";
  
  public static final String WRITE_PIPE_TYPE = "8006000f";
  
  public static final String WRITE_RESET = "8006000d0001c7d8";
  
  public static final String WRITE_SENSOR_HEIGHT = "80060006";
  
  public static final String WRITE_WATER_LEVEL_FILTERING_TIMES = "80060005";
  
  public static final String WRITE_WATER_LEVEL_METER_NOISE = "80060004";
  
  public static final String[] infoParamCommand = new String[] { "8004000100017e1b", "8004000200018e1b", "800400030001dfdb", "8004000700019e1a", "800400080001ae19", "800400090001ffd9", "8004000a00010fd9", "8004000b00015e19" };
  
  public static final String[] sensorParamRead = new String[] { "800300040001dbda", "8003000500018a1a", "8003000600017a1a", "8003000f0001aa18", "8003001300016bde" };
  
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
  
  public static void main(String... paramVarArgs) {
    byte[] arrayOfByte = CRC16Util.getData(HexUtil.decodeHex("8003000f0001aa18"));
    System.out.println(HexUtil.encodeHexStr(arrayOfByte));
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/contact/RD300Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */