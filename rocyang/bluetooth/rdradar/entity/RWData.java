package me.rocyang.bluetooth.rdradar.entity;

import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class RWData {
  public static final int BINARY_COMMAND = 2;
  
  public static final int TEXT_COMMAND = 1;
  
  private String cmd;
  
  private byte[] cmdByte;
  
  private String cmdPart = "";
  
  private String[] endFlag;
  
  private String result;
  
  private byte[] resultByte;
  
  private int resultLength;
  
  private String resultStr;
  
  private int status;
  
  private int type;
  
  private String valuePart = "";
  
  private int waitSeconds;
  
  public RWData(String paramString, int paramInt) {
    this.type = paramInt;
    setCmd(paramString);
  }
  
  public String getCmd() {
    return this.cmd;
  }
  
  public byte[] getCmdByte() {
    return this.cmdByte;
  }
  
  public String getCmdPart() {
    return this.cmdPart;
  }
  
  public String[] getEndFlag() {
    return this.endFlag;
  }
  
  public String getResult() {
    return this.result;
  }
  
  public byte[] getResultByte() {
    return this.resultByte;
  }
  
  public int getResultLength() {
    return this.resultLength;
  }
  
  public String getResultStr() {
    return this.resultStr;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public int getType() {
    return this.type;
  }
  
  public String getValuePart() {
    return this.valuePart;
  }
  
  public int getWaitSeconds() {
    return this.waitSeconds;
  }
  
  public void setCmd(String paramString) {
    this.cmd = paramString;
    int i = this.type;
    if (i != 1) {
      if (i == 2)
        this.cmdByte = HexUtil.decodeHex(paramString); 
    } else {
      this.cmdByte = paramString.getBytes();
      String[] arrayOfString = paramString.split(" ");
      if (arrayOfString.length == 2) {
        this.cmdPart = arrayOfString[0];
        this.valuePart = arrayOfString[1];
      } 
    } 
  }
  
  public void setEndFlag(String... paramVarArgs) {
    this.endFlag = paramVarArgs;
  }
  
  public void setResultByte(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return; 
    this.resultByte = paramArrayOfbyte;
    this.result = HexUtil.encodeHexStr(paramArrayOfbyte);
    this.resultStr = new String(paramArrayOfbyte);
    this.resultLength = paramArrayOfbyte.length;
  }
  
  public void setStatus(int paramInt) {
    this.status = paramInt;
  }
  
  public void setWaitSeconds(int paramInt) {
    this.waitSeconds = paramInt;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/entity/RWData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */