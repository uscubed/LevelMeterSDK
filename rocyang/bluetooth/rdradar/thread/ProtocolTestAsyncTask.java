package me.rocyang.bluetooth.rdradar.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class ProtocolTestAsyncTask extends AsyncTask<Void, Void, Void> implements Protocol {
  private static final String TAG = "ProtocolTestAsyncTask";
  
  private ProtocolTestHandleListener rwHandleListener;
  
  private Handler writeHandle = new Handler() {
      final ProtocolTestAsyncTask this$0;
      
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 22135) {
          if (i != 22136) {
            if (i != 22160) {
              if (i == 22161)
                ProtocolTestAsyncTask.this.rwHandleListener.testFail(); 
            } else {
              ProtocolTestAsyncTask.this.rwHandleListener.testSuccess();
            } 
            return;
          } 
        } else {
          RdDevices.recvQueue.clear();
        } 
        String str = (String)param1Message.obj;
        if (param1Message.arg1 == 1) {
          ProtocolTestAsyncTask.this.rwHandleListener.writeTextCmdEvent(str);
        } else if (param1Message.arg1 == 2) {
          ProtocolTestAsyncTask.this.rwHandleListener.writeCommCmdEvent(HexUtil.decodeHex(str));
        } 
      }
    };
  
  public ProtocolTestAsyncTask(ProtocolTestHandleListener paramProtocolTestHandleListener) {
    this.rwHandleListener = paramProtocolTestHandleListener;
  }
  
  protected Void doInBackground(Void... paramVarArgs) {
    if (this.writeHandle == null) {
      Log.e("ProtocolTestAsyncTask", "run: 未为writeHandle初始化，线程退出");
      return null;
    } 
    Log.i("ProtocolTestAsyncTask", "run: 线程开始");
    try {
      RWData rWData = new RWData();
      this("<->", 1);
      RdDevices.recvQueue.clear();
      this.writeHandle.sendMessage(Protocol$_CC.getRestMessage(1, rWData.getCmd()));
      rWData.setResultByte(Protocol$_CC.getTestResult());
      byte[] arrayOfByte = rWData.getResultByte();
      if (arrayOfByte == null) {
        Log.i("ProtocolTestAsyncTask", "run: 开启成功了");
        Message message = new Message();
        this();
        message.what = 22160;
        this.writeHandle.sendMessage(message);
      } else if (rWData.getResultStr().contains("Bluetooth and 485 passthrough are on.")) {
        Log.i("ProtocolTestAsyncTask", "run: 开启成功了");
        Message message = new Message();
        this();
        message.what = 22160;
        this.writeHandle.sendMessage(message);
      } else if ("over buffer".equals(rWData.getResultStr())) {
        RWData rWData1 = new RWData();
        this("<!>", 1);
        RdDevices.recvQueue.clear();
        this.writeHandle.sendMessage(Protocol$_CC.getRestMessage(1, rWData1.getCmd()));
        rWData1.setResultByte(Protocol$_CC.getTestResult());
        Thread.sleep(100L);
        rWData1 = new RWData();
        this("<->", 1);
        RdDevices.recvQueue.clear();
        this.writeHandle.sendMessage(Protocol$_CC.getRestMessage(1, rWData1.getCmd()));
        rWData1.setResultByte(Protocol$_CC.getTestResult());
        if (rWData1.getResultStr().contains("Bluetooth and 485 passthrough are on.")) {
          Log.i("ProtocolTestAsyncTask", "run: 开启成功了");
          Message message = new Message();
          this();
          message.what = 22160;
          this.writeHandle.sendMessage(message);
        } else {
          Log.i("ProtocolTestAsyncTask", "run: 开启失败了");
          Message message = new Message();
          this();
          message.what = 22161;
          this.writeHandle.sendMessage(message);
        } 
      } else {
        Log.i("ProtocolTestAsyncTask", "run: 开启失败了");
        Message message = new Message();
        this();
        message.what = 22161;
        this.writeHandle.sendMessage(message);
      } 
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
      Log.i("ProtocolTestAsyncTask", "run: 开启失败了");
      Message message = new Message();
      this();
      message.what = 22161;
      this.writeHandle.sendMessage(message);
    } finally {}
    Log.i("ProtocolTestAsyncTask", "readParamsRunnable: 线程结束");
    return null;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/ProtocolTestAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */