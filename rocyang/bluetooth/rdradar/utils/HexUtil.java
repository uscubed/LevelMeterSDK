package me.rocyang.bluetooth.rdradar.utils;

import java.util.Arrays;

public class HexUtil {
  private static final char[] DIGITS_LOWER = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  private static final char[] DIGITS_UPPER = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  public static int byte2ToUnsignedShort(byte[] paramArrayOfbyte) {
    return byte2ToUnsignedShort(paramArrayOfbyte, 0);
  }
  
  public static int byte2ToUnsignedShort(byte[] paramArrayOfbyte, int paramInt) {
    byte b = paramArrayOfbyte[paramInt];
    return paramArrayOfbyte[paramInt + 1] & 0xFF | b << 8 & 0xFF00;
  }
  
  public static int byte4ToInt(byte[] paramArrayOfbyte, int paramInt) {
    byte b1 = paramArrayOfbyte[paramInt];
    byte b2 = paramArrayOfbyte[paramInt + 1];
    byte b3 = paramArrayOfbyte[paramInt + 2];
    return paramArrayOfbyte[paramInt + 3] & 0xFF | (b1 & 0xFF) << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8;
  }
  
  public static byte[] copy(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return Arrays.copyOfRange(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public static byte[] decodeHex(String paramString) {
    return decodeHex(paramString.toCharArray());
  }
  
  public static byte[] decodeHex(char[] paramArrayOfchar) {
    int i = paramArrayOfchar.length;
    if ((i & 0x1) == 0) {
      byte[] arrayOfByte = new byte[i >> 1];
      byte b1 = 0;
      for (byte b2 = 0; b1 < i; b2++) {
        int j = toDigit(paramArrayOfchar[b1], b1);
        int k = toDigit(paramArrayOfchar[++b1], b1);
        b1++;
        arrayOfByte[b2] = (byte)((j << 4 | k) & 0xFF);
      } 
      return arrayOfByte;
    } 
    throw new RuntimeException("Odd number of characters.");
  }
  
  public static char[] encodeHex(byte[] paramArrayOfbyte) {
    return encodeHex(paramArrayOfbyte, true);
  }
  
  protected static char[] encodeHex(byte[] paramArrayOfbyte, int paramInt, char[] paramArrayOfchar) {
    char[] arrayOfChar = new char[paramInt << 1];
    byte b = 0;
    int i = 0;
    while (b < paramInt) {
      int j = i + 1;
      arrayOfChar[i] = paramArrayOfchar[(paramArrayOfbyte[b] & 0xF0) >>> 4];
      i = j + 1;
      arrayOfChar[j] = paramArrayOfchar[paramArrayOfbyte[b] & 0xF];
      b++;
    } 
    return arrayOfChar;
  }
  
  public static char[] encodeHex(byte[] paramArrayOfbyte, boolean paramBoolean) {
    char[] arrayOfChar;
    int i = paramArrayOfbyte.length;
    if (paramBoolean) {
      arrayOfChar = DIGITS_LOWER;
    } else {
      arrayOfChar = DIGITS_UPPER;
    } 
    return encodeHex(paramArrayOfbyte, i, arrayOfChar);
  }
  
  public static String encodeHexStr(byte[] paramArrayOfbyte) {
    return encodeHexStr(paramArrayOfbyte, true);
  }
  
  public static String encodeHexStr(byte[] paramArrayOfbyte, int paramInt) {
    return encodeHexStr(paramArrayOfbyte, paramInt, true);
  }
  
  public static String encodeHexStr(byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    char[] arrayOfChar;
    if (paramBoolean) {
      arrayOfChar = DIGITS_LOWER;
    } else {
      arrayOfChar = DIGITS_UPPER;
    } 
    return encodeHexStr(paramArrayOfbyte, paramInt, arrayOfChar);
  }
  
  protected static String encodeHexStr(byte[] paramArrayOfbyte, int paramInt, char[] paramArrayOfchar) {
    return new String(encodeHex(paramArrayOfbyte, paramInt, paramArrayOfchar));
  }
  
  public static String encodeHexStr(byte[] paramArrayOfbyte, boolean paramBoolean) {
    char[] arrayOfChar;
    int i = paramArrayOfbyte.length;
    if (paramBoolean) {
      arrayOfChar = DIGITS_LOWER;
    } else {
      arrayOfChar = DIGITS_UPPER;
    } 
    return encodeHexStr(paramArrayOfbyte, i, arrayOfChar);
  }
  
  public static String formatHex(String paramString) {
    int i = paramString.length();
    StringBuilder stringBuilder = new StringBuilder();
    for (int j = 0; j < i; j = k) {
      int k = j + 8;
      stringBuilder.append(paramString.substring(j, Math.min(k, paramString.length())));
      stringBuilder.append(' ');
    } 
    return stringBuilder.toString();
  }
  
  public static byte[] getInt4ByteLow(int paramInt) {
    return Arrays.copyOfRange(intToByte4(paramInt), 2, 4);
  }
  
  public static String int2HexStr(int paramInt1, int paramInt2) {
    String str1 = Integer.toHexString(paramInt1);
    paramInt1 = str1.length();
    if (paramInt1 > paramInt2)
      return str1.substring(paramInt1 - paramInt2); 
    String str2 = str1;
    if (paramInt1 < paramInt2) {
      StringBuilder stringBuilder = new StringBuilder(paramInt2);
      while (paramInt1 < paramInt2) {
        stringBuilder.append('0');
        paramInt1++;
      } 
      stringBuilder.append(str1);
      str2 = stringBuilder.toString();
    } 
    return str2;
  }
  
  public static byte[] intToByte4(int paramInt) {
    byte b1 = (byte)(paramInt & 0xFF);
    byte b2 = (byte)(paramInt >> 8 & 0xFF);
    byte b3 = (byte)(paramInt >> 16 & 0xFF);
    return new byte[] { (byte)(paramInt >> 24 & 0xFF), b3, b2, b1 };
  }
  
  public static byte[] longToByte8(long paramLong) {
    byte[] arrayOfByte = new byte[8];
    for (byte b = 0; b < 8; b++)
      arrayOfByte[b] = (byte)(int)(paramLong >>> (arrayOfByte.length - 1 - b) * 8 & 0xFFL); 
    return arrayOfByte;
  }
  
  protected static int toDigit(char paramChar, int paramInt) {
    int i = Character.digit(paramChar, 16);
    if (i != -1)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Illegal hexadecimal character ");
    stringBuilder.append(paramChar);
    stringBuilder.append(" at index ");
    stringBuilder.append(paramInt);
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public static int toInt(byte[] paramArrayOfbyte, int paramInt) {
    byte b1 = paramArrayOfbyte[paramInt + 3];
    byte b2 = paramArrayOfbyte[paramInt + 2];
    byte b3 = paramArrayOfbyte[paramInt + 1];
    return paramArrayOfbyte[paramInt] & 0xFF | b1 << 24 & 0xFF000000 | b2 << 16 & 0xFF0000 | b3 << 8 & 0xFF00;
  }
  
  public static int toInt(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = 0;
    for (int j = paramInt1; j < paramInt2 + paramInt1; j++)
      i = i << 8 | paramArrayOfbyte[j] & 0xFF; 
    return i;
  }
  
  public static short toShort(byte[] paramArrayOfbyte, int paramInt) {
    byte b = paramArrayOfbyte[paramInt + 1];
    return (short)(paramArrayOfbyte[paramInt] & 0xFF | b << 8 & 0xFF00);
  }
  
  public static byte[] unsignedShortToByte2(int paramInt) {
    return new byte[] { (byte)(paramInt >> 8 & 0xFF), (byte)(paramInt & 0xFF) };
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/utils/HexUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */