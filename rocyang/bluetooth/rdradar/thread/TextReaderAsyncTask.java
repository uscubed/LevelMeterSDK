package me.rocyang.bluetooth.rdradar.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class TextReaderAsyncTask extends AsyncTask<RWData, Void, Void> implements Protocol {
  private static final String TAG = "DataReaderAsyncTask";
  
  private RWHandleListener rwHandleListener;
  
  private Handler writeHandle = new Handler() {
      final TextReaderAsyncTask this$0;
      
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 20480) {
          if (i != 22136) {
            switch (i) {
              default:
                return;
              case 22146:
                TextReaderAsyncTask.this.rwHandleListener.threadStopEvent();
              case 22145:
                TextReaderAsyncTask.this.rwHandleListener.threadForceStopEvent();
              case 22144:
                break;
            } 
            TextReaderAsyncTask.this.rwHandleListener.readDateOverTimeEvent();
          } 
          String str = (String)param1Message.obj;
          if (param1Message.arg1 == 1)
            TextReaderAsyncTask.this.rwHandleListener.writeTextCmdEvent(str); 
          if (param1Message.arg1 == 2)
            TextReaderAsyncTask.this.rwHandleListener.writeCommCmdEvent(HexUtil.decodeHex(str)); 
        } 
        RWData rWData = (RWData)param1Message.obj;
        TextReaderAsyncTask.this.rwHandleListener.commandResultEvent(rWData);
      }
    };
  
  public TextReaderAsyncTask(RWHandleListener paramRWHandleListener) {
    this.rwHandleListener = paramRWHandleListener;
  }
  
  protected Void doInBackground(RWData... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0) {
      for (byte b = 0; b < paramVarArgs.length && !isCancelled(); b++) {
        RWData rWData = paramVarArgs[b];
        this.writeHandle.sendMessage(Protocol$_CC.getAMessage(1, rWData.getCmd()));
        try {
          rWData.setResultByte(Protocol$_CC.getTextResult(rWData.getEndFlag(), rWData.getWaitSeconds()));
        } catch (InterruptedException interruptedException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("doInBackground: recive data error:");
          stringBuilder1.append(interruptedException.getMessage());
          Log.e("DataReaderAsyncTask", stringBuilder1.toString());
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("doInBackground: =====================================>");
        stringBuilder.append(rWData.getResultStr());
        Log.i("DataReaderAsyncTask", stringBuilder.toString());
        this.writeHandle.sendMessage(Protocol$_CC.getDataMessage(rWData));
        try {
          Thread.sleep(10L);
        } catch (InterruptedException interruptedException) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("doInBackground: recive data error:");
          stringBuilder.append(interruptedException.getMessage());
          Log.e("DataReaderAsyncTask", stringBuilder.toString());
        } 
      } 
      this.writeHandle.sendMessage(Protocol$_CC.getMessage(22146));
    } 
    return null;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/TextReaderAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */