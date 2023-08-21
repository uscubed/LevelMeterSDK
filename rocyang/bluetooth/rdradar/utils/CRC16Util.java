package me.rocyang.bluetooth.rdradar.utils;

import java.util.Arrays;

public class CRC16Util {
  public static String byteTo16String(byte paramByte) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramByte < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(Integer.toString(paramByte + 256, 16));
      stringBuilder.append(" ");
      stringBuffer.append(stringBuilder.toString());
    } else if (paramByte == 0) {
      stringBuffer.append("00 ");
    } else if (paramByte > 0 && paramByte <= 15) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0");
      stringBuilder.append(Integer.toString(paramByte, 16));
      stringBuilder.append(" ");
      stringBuffer.append(stringBuilder.toString());
    } else if (paramByte > 15) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(Integer.toString(paramByte, 16));
      stringBuilder.append(" ");
      stringBuffer.append(stringBuilder.toString());
    } 
    return stringBuffer.toString();
  }
  
  public static String byteTo16String(byte[] paramArrayOfbyte) {
    StringBuffer stringBuffer = new StringBuffer();
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++)
      stringBuffer.append(byteTo16String(paramArrayOfbyte[b])); 
    return stringBuffer.toString();
  }
  
  private static byte[] getCrc16(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    int j = 65535;
    for (byte b = 0; b < i; b++) {
      j = j & 0xFF ^ paramArrayOfbyte[b] & 0xFF | 0xFF00 & j;
      for (byte b1 = 0; b1 < 8; b1++) {
        if ((j & 0x1) > 0) {
          j = j >> 1 ^ 0xA001;
        } else {
          j >>= 1;
        } 
      } 
    } 
    return intToBytes(j);
  }
  
  public static byte[] getData(byte[] paramArrayOfbyte) {
    byte[] arrayOfByte1 = getCrc16(paramArrayOfbyte);
    byte[] arrayOfByte2 = new byte[paramArrayOfbyte.length + arrayOfByte1.length];
    System.arraycopy(paramArrayOfbyte, 0, arrayOfByte2, 0, paramArrayOfbyte.length);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, paramArrayOfbyte.length, arrayOfByte1.length);
    return arrayOfByte2;
  }
  
  public static byte[] getData(String... paramVarArgs) {
    byte[] arrayOfByte = new byte[0];
    byte b = 0;
    while (b < paramVarArgs.length) {
      byte b1 = (byte)Integer.parseInt(paramVarArgs[b], 16);
      byte[] arrayOfByte1 = new byte[arrayOfByte.length + 1];
      byte[] arrayOfByte2 = new byte[1];
      arrayOfByte2[0] = b1;
      System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, arrayOfByte.length);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, arrayOfByte.length, arrayOfByte2.length);
      b++;
      arrayOfByte = arrayOfByte1;
    } 
    return getData(arrayOfByte);
  }
  
  private static byte[] intToBytes(int paramInt) {
    byte b = (byte)(paramInt >> 8 & 0xFF);
    return new byte[] { (byte)(paramInt & 0xFF), b };
  }
  
  public static Boolean testCrc16(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    boolean bool1 = false;
    Boolean bool = Boolean.valueOf(false);
    if (i < 3)
      return bool; 
    byte[] arrayOfByte = getCrc16(Arrays.copyOfRange(paramArrayOfbyte, 0, paramArrayOfbyte.length - 2));
    if (arrayOfByte.length != 2)
      return bool; 
    boolean bool2 = bool1;
    if (arrayOfByte[0] == paramArrayOfbyte[paramArrayOfbyte.length - 2]) {
      bool2 = bool1;
      if (arrayOfByte[1] == paramArrayOfbyte[paramArrayOfbyte.length - 1])
        bool2 = true; 
    } 
    return Boolean.valueOf(bool2);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/utils/CRC16Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */