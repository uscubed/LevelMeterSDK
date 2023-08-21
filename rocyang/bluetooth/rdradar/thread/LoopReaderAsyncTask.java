package me.rocyang.bluetooth.rdradar.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class LoopReaderAsyncTask extends AsyncTask<String[], Void, Void> implements Protocol {
  private static final String TAG = "LoopReaderAsyncTask";
  
  private RWHandleListener rwHandleListener;
  
  private Handler writeHandle = new Handler() {
      final LoopReaderAsyncTask this$0;
      
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 20480) {
          if (i != 22136) {
            switch (i) {
              default:
                return;
              case 22146:
                LoopReaderAsyncTask.this.rwHandleListener.threadStopEvent();
              case 22145:
                LoopReaderAsyncTask.this.rwHandleListener.threadForceStopEvent();
              case 22144:
                break;
            } 
            LoopReaderAsyncTask.this.rwHandleListener.readDateOverTimeEvent();
          } 
          String str = (String)param1Message.obj;
          if (param1Message.arg1 == 1)
            LoopReaderAsyncTask.this.rwHandleListener.writeTextCmdEvent(str); 
          if (param1Message.arg1 == 2)
            LoopReaderAsyncTask.this.rwHandleListener.writeCommCmdEvent(HexUtil.decodeHex(str)); 
        } 
        RWData rWData = (RWData)param1Message.obj;
        LoopReaderAsyncTask.this.rwHandleListener.commandResultEvent(rWData);
      }
    };
  
  public LoopReaderAsyncTask(RWHandleListener paramRWHandleListener) {
    this.rwHandleListener = paramRWHandleListener;
  }
  
  protected Void doInBackground(String[]... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0) {
      String[] arrayOfString = paramVarArgs[0];
      while (!isCancelled()) {
        for (byte b = 0; b < arrayOfString.length && !isCancelled(); b++) {
          RWData rWData2 = new RWData(arrayOfString[b], 2);
          this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData2.getCmd()));
          try {
            rWData2.setResultByte(Protocol$_CC.getBinaryResult());
          } catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("doInBackground: ");
            stringBuilder1.append(interruptedException.getMessage());
            Log.i("LoopReaderAsyncTask", stringBuilder1.toString());
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("doInBackground: =====================================>");
          stringBuilder.append(rWData2.getResult());
          Log.i("LoopReaderAsyncTask", stringBuilder.toString());
          RWData rWData1 = rWData2;
          if (rWData2.getResultByte() == null) {
            rWData1 = new RWData(arrayOfString[b], 2);
            this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData1.getCmd()));
            try {
              rWData1.setResultByte(Protocol$_CC.getBinaryResult());
            } catch (InterruptedException interruptedException) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("doInBackground: ");
              stringBuilder2.append(interruptedException.getMessage());
              Log.i("LoopReaderAsyncTask", stringBuilder2.toString());
            } 
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("doInBackground: =====================================>");
            stringBuilder1.append(rWData1.getResult());
            Log.i("LoopReaderAsyncTask", stringBuilder1.toString());
          } 
          rWData2 = rWData1;
          if (rWData1.getResultByte() == null) {
            rWData2 = new RWData(arrayOfString[b], 2);
            this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData2.getCmd()));
            try {
              rWData2.setResultByte(Protocol$_CC.getBinaryResult());
            } catch (InterruptedException interruptedException) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("doInBackground: ");
              stringBuilder2.append(interruptedException.getMessage());
              Log.i("LoopReaderAsyncTask", stringBuilder2.toString());
            } 
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("doInBackground: =====================================>");
            stringBuilder1.append(rWData2.getResult());
            Log.i("LoopReaderAsyncTask", stringBuilder1.toString());
          } 
          this.writeHandle.sendMessage(Protocol$_CC.getDataMessage(rWData2));
          try {
            Thread.sleep(10L);
          } catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("doInBackground: ");
            stringBuilder1.append(interruptedException.getMessage());
            Log.i("LoopReaderAsyncTask", stringBuilder1.toString());
          } 
        } 
      } 
      this.writeHandle.sendMessage(Protocol$_CC.getMessage(22146));
    } 
    return null;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/LoopReaderAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */