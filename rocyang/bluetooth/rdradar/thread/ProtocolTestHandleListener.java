package me.rocyang.bluetooth.rdradar.thread;

public interface ProtocolTestHandleListener {
  void testFail();
  
  void testSuccess();
  
  void writeCommCmdEvent(byte[] paramArrayOfbyte);
  
  void writeTextCmdEvent(String paramString);
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/ProtocolTestHandleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */