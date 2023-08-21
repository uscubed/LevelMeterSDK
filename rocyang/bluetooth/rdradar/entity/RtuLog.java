package me.rocyang.bluetooth.rdradar.entity;

public class RtuLog {
  private String cmd;
  
  private String content;
  
  private Long createTime;
  
  private LogType logType;
  
  public RtuLog() {}
  
  public RtuLog(LogType paramLogType, String paramString1, String paramString2) {
    this.logType = paramLogType;
    this.content = paramString1;
    this.cmd = paramString2;
  }
  
  public String getCmd() {
    return this.cmd;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public Long getCreateTime() {
    return this.createTime;
  }
  
  public LogType getLogType() {
    return this.logType;
  }
  
  public void setCmd(String paramString) {
    this.cmd = paramString;
  }
  
  public void setContent(String paramString) {
    this.content = paramString;
  }
  
  public void setCreateTime(Long paramLong) {
    this.createTime = paramLong;
  }
  
  public void setLogType(LogType paramLogType) {
    this.logType = paramLogType;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RtuLog{logType=");
    stringBuilder.append(this.logType);
    stringBuilder.append(", content='");
    stringBuilder.append(this.content);
    stringBuilder.append('\'');
    stringBuilder.append(", cmd='");
    stringBuilder.append(this.cmd);
    stringBuilder.append('\'');
    stringBuilder.append(", createTime=");
    stringBuilder.append(this.createTime);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public enum LogType {
    RECEIVE_RESULT, SEND_CMD;
    
    private static final LogType[] $VALUES;
    
    static {
      $VALUES = new LogType[] { SEND_CMD, RECEIVE_RESULT };
    }
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/entity/RtuLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */