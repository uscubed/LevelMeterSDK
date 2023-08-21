package me.rocyang.bluetooth.rdradar.thread;

import me.rocyang.bluetooth.rdradar.entity.RWData;

public interface RWHandleListener {
  void commandResultEvent(RWData paramRWData);
  
  void readDateOverTimeEvent();
  
  void readRssi();
  
  void threadForceStopEvent();
  
  void threadStopEvent();
  
  void writeCommCmdEvent(byte[] paramArrayOfbyte);
  
  void writeTextCmdEvent(String paramString);
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/RWHandleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */