package me.rocyang.bluetooth.rdradar.thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;

public class DataReaderAsyncTask extends AsyncTask<String, Void, Void> implements Protocol {
  private static final String TAG = "DataReaderAsyncTask";
  
  private RWHandleListener rwHandleListener;
  
  private Handler writeHandle = new Handler() {
      final DataReaderAsyncTask this$0;
      
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 20480) {
          if (i != 22136) {
            switch (i) {
              default:
                return;
              case 22146:
                DataReaderAsyncTask.this.rwHandleListener.threadStopEvent();
              case 22145:
                DataReaderAsyncTask.this.rwHandleListener.threadForceStopEvent();
              case 22144:
                break;
            } 
            DataReaderAsyncTask.this.rwHandleListener.readDateOverTimeEvent();
          } 
          String str = (String)param1Message.obj;
          if (param1Message.arg1 == 1)
            DataReaderAsyncTask.this.rwHandleListener.writeTextCmdEvent(str); 
          if (param1Message.arg1 == 2)
            DataReaderAsyncTask.this.rwHandleListener.writeCommCmdEvent(HexUtil.decodeHex(str)); 
        } 
        RWData rWData = (RWData)param1Message.obj;
        DataReaderAsyncTask.this.rwHandleListener.commandResultEvent(rWData);
      }
    };
  
  public DataReaderAsyncTask(RWHandleListener paramRWHandleListener) {
    this.rwHandleListener = paramRWHandleListener;
  }
  
  protected Void doInBackground(String... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0) {
      for (byte b = 0; b < paramVarArgs.length && !isCancelled(); b++) {
        RWData rWData1 = new RWData(paramVarArgs[b], 2);
        this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData1.getCmd()));
        try {
          rWData1.setResultByte(Protocol$_CC.getBinaryResult());
        } catch (InterruptedException interruptedException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("doInBackground: ");
          stringBuilder1.append(interruptedException.getMessage());
          Log.i("DataReaderAsyncTask", stringBuilder1.toString());
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("doInBackground: =====================================>");
        stringBuilder.append(rWData1.getResult());
        Log.i("DataReaderAsyncTask", stringBuilder.toString());
        RWData rWData2 = rWData1;
        if (rWData1.getResultByte() != null) {
          rWData2 = rWData1;
          if (rWData1.getResultStr().contains("no commond")) {
            rWData1 = new RWData("<->", 1);
            this.writeHandle.sendMessage(Protocol$_CC.getAMessage(1, rWData1.getCmd()));
            try {
              rWData1.setResultByte(Protocol$_CC.getStartResult());
            } catch (InterruptedException interruptedException) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("doInBackground: ");
              stringBuilder1.append(interruptedException.getMessage());
              Log.i("DataReaderAsyncTask", stringBuilder1.toString());
            } 
            rWData2 = new RWData(paramVarArgs[b], 2);
            this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData2.getCmd()));
            try {
              rWData2.setResultByte(Protocol$_CC.getBinaryResult());
            } catch (InterruptedException interruptedException) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("doInBackground: ");
              stringBuilder1.append(interruptedException.getMessage());
              Log.i("DataReaderAsyncTask", stringBuilder1.toString());
            } 
          } 
        } 
        rWData1 = rWData2;
        if (rWData2.getResultByte() == null) {
          rWData1 = new RWData(paramVarArgs[b], 2);
          this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData1.getCmd()));
          try {
            rWData1.setResultByte(Protocol$_CC.getBinaryResult());
          } catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("doInBackground: ");
            stringBuilder2.append(interruptedException.getMessage());
            Log.i("DataReaderAsyncTask", stringBuilder2.toString());
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("doInBackground: =====================================>");
          stringBuilder1.append(rWData1.getResult());
          Log.i("DataReaderAsyncTask", stringBuilder1.toString());
        } 
        rWData2 = rWData1;
        if (rWData1.getResultByte() == null) {
          rWData2 = new RWData(paramVarArgs[b], 2);
          this.writeHandle.sendMessage(Protocol$_CC.getAMessage(2, rWData2.getCmd()));
          try {
            rWData2.setResultByte(Protocol$_CC.getBinaryResult());
          } catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("doInBackground: ");
            stringBuilder2.append(interruptedException.getMessage());
            Log.i("DataReaderAsyncTask", stringBuilder2.toString());
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("doInBackground: =====================================>");
          stringBuilder1.append(rWData2.getResult());
          Log.i("DataReaderAsyncTask", stringBuilder1.toString());
        } 
        this.writeHandle.sendMessage(Protocol$_CC.getDataMessage(rWData2));
        try {
          Thread.sleep(10L);
        } catch (InterruptedException interruptedException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("doInBackground: ");
          stringBuilder1.append(interruptedException.getMessage());
          Log.i("DataReaderAsyncTask", stringBuilder1.toString());
        } 
      } 
      this.writeHandle.sendMessage(Protocol$_CC.getMessage(22146));
    } 
    return null;
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/thread/DataReaderAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */