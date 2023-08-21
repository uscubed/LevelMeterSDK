package me.rocyang.bluetooth.rdradar.thread;

public interface Protocol {
  public static final int Clear_And_Write = 22135;
  
  public static final int Command_Result = 20480;
  
  public static final int Force_Stop_Thread = 22145;
  
  public static final int Protocol_Fail = 22161;
  
  public static final int Protocol_Success = 22160;
  
  public static final char[] Text_Command_End = new char[] { '\r', '\n' };
  
  public static final String Text_Command_End_Str = new String(Text_Command_End);
  
  public static final int Thread_Stop = 22146;
  
  public static final int Wait_Over_Time = 22144;
  
  public static final int Write_CMD = 22136;
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/Protocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */