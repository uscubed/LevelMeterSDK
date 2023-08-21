package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.exception.BleException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD300Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.LoopReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class Rd300InfoActivity extends BaseActivity {
  private static final String TAG = "Rd300InfoActivity";
  
  private RWHandleListener handleListener = new RWHandleListener() {
      final Rd300InfoActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        Rd300InfoActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {
        Rd300InfoActivity.this.blueTooth.readRssi(Rd300InfoActivity.this.bleDevice, Rd300InfoActivity.this.rssiEvent);
      }
      
      public void threadForceStopEvent() {
        if (Rd300InfoActivity.this.hud.isShowing())
          Rd300InfoActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (Rd300InfoActivity.this.hud.isShowing())
          Rd300InfoActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
        rd300InfoActivity.write(rd300InfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
        rd300InfoActivity.write(rd300InfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private LoopReaderAsyncTask loopTask;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final Rd300InfoActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("Rd300InfoActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("Rd300InfoActivity", stringBuilder.toString());
        if (Rd300InfoActivity.this.testTask != null && Rd300InfoActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (Rd300InfoActivity.this.loopTask != null && Rd300InfoActivity.this.loopTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("Rd300InfoActivity", "onNotifySuccess: 通知打开失败，请重新连接");
        Rd300InfoActivity.this.finish();
      }
      
      public void onNotifySuccess() {
        Log.i("Rd300InfoActivity", "onNotifySuccess: 通知打开成功");
        Rd300InfoActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (Rd300InfoActivity.this.handler != null)
          Rd300InfoActivity.this.handler.sendMessage(message); 
      }
    };
  
  private int retryTimes = 0;
  
  private BleRssiCallback rssiEvent = new BleRssiCallback() {
      final Rd300InfoActivity this$0;
      
      public void onRssiFailure(BleException param1BleException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRssiFailure: ");
        stringBuilder.append(param1BleException.getDescription());
        Log.i("Rd300InfoActivity", stringBuilder.toString());
      }
      
      public void onRssiSuccess(int param1Int) {
        Rd300InfoActivity.this.tvRssiValue.setText(String.valueOf(param1Int));
        if (param1Int > -80) {
          Rd300InfoActivity.this.tvRssiIcon.setText(Rd300InfoActivity.this.getString(2131755129));
        } else if (param1Int > -90) {
          Rd300InfoActivity.this.tvRssiIcon.setText(Rd300InfoActivity.this.getString(2131755128));
        } else if (param1Int > -100) {
          Rd300InfoActivity.this.tvRssiIcon.setText(Rd300InfoActivity.this.getString(2131755127));
        } else {
          Rd300InfoActivity.this.tvRssiIcon.setText(Rd300InfoActivity.this.getString(2131755126));
        } 
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final Rd300InfoActivity this$0;
      
      public void testFail() {
        if (Rd300InfoActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(Rd300InfoActivity.this.getString(2131755279));
          Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
          Rd300InfoActivity.access$402(rd300InfoActivity, rd300InfoActivity.retryTimes + 1);
          Rd300InfoActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(Rd300InfoActivity.this.getString(2131755273));
          Rd300InfoActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        Rd300InfoActivity.this.startRWThread(RD300Command.infoParamCommand, (String)null);
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
        rd300InfoActivity.write(rd300InfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
        rd300InfoActivity.write(rd300InfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296711)
  TextView tvEmptyHighValue;
  
  @BindView(2131296727)
  TextView tvIconEmptyHigh;
  
  @BindView(2131296746)
  TextView tvIconSignalLevel;
  
  @BindView(2131296758)
  TextView tvIconWaterLevel;
  
  @BindView(2131296763)
  TextView tvLastUpdateTime;
  
  @BindView(2131296766)
  TextView tvMacValue;
  
  @BindView(2131296774)
  TextView tvRssiIcon;
  
  @BindView(2131296775)
  TextView tvRssiValue;
  
  @BindView(2131296788)
  TextView tvSignalLevelValue;
  
  @BindView(2131296805)
  TextView tvWaterLevelValue;
  
  private void initIcon() {
    this.tvIconSignalLevel.setTypeface(RdDevices.iconfont);
    this.tvIconEmptyHigh.setTypeface(RdDevices.iconfont);
    this.tvIconWaterLevel.setTypeface(RdDevices.iconfont);
    this.tvRssiIcon.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("Rd300InfoActivity", "startProtocolTest: 开始协议适配");
    this.testTask = new ProtocolTestAsyncTask(this.testHandleListener);
    this.testTask.execute((Object[])new Void[0]);
  }
  
  private void startRWThread(String[] paramArrayOfString, String paramString) {
    if (paramArrayOfString == null)
      return; 
    if (this.hud.isShowing())
      this.hud.dismiss(); 
    if (paramString != null) {
      initHud();
      this.hud.setLabel(paramString).setDetailsLabel(this.bleDevice.getName()).show();
    } 
    this.loopTask = new LoopReaderAsyncTask(this.handleListener);
    this.loopTask.execute((Object[])new String[][] { paramArrayOfString });
  }
  
  private void stopProtocol() {
    ProtocolTestAsyncTask protocolTestAsyncTask = this.testTask;
    if (protocolTestAsyncTask != null && protocolTestAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.testTask.cancel(true); 
    LoopReaderAsyncTask loopReaderAsyncTask = this.loopTask;
    if (loopReaderAsyncTask != null && loopReaderAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.loopTask.cancel(true); 
    RdDevices.recvQueue.clear();
  }
  
  public void dealData(RWData paramRWData) {
    String str1;
    byte[] arrayOfByte = paramRWData.getResultByte();
    if (arrayOfByte == null)
      return; 
    if (isReturnErrors(paramRWData).booleanValue()) {
      ToastUtil.ShowTextLong(getString(2131755245));
      return;
    } 
    String str2 = paramRWData.getCmd();
    String str3 = Calendar.getInstance().getTime().toString();
    TextView textView = this.tvLastUpdateTime;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getString(2131755261));
    stringBuilder.append(str3);
    textView.setText(stringBuilder.toString());
    textView = null;
    int i = RD300Command.getData(arrayOfByte);
    byte b = -1;
    int j = str2.hashCode();
    if (j != -693331779) {
      if (j != 1114092893) {
        if (j == 1795494400 && str2.equals("800400030001dfdb"))
          b = 0; 
      } else if (str2.equals("8004000100017e1b")) {
        b = 2;
      } 
    } else if (str2.equals("8004000200018e1b")) {
      b = 1;
    } 
    if (b != 0) {
      if (b != 1) {
        if (b != 2) {
          str1 = "";
        } else {
          textView = this.tvWaterLevelValue;
          str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        } 
      } else {
        textView = this.tvEmptyHighValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
      } 
    } else {
      textView = this.tvSignalLevelValue;
      str1 = String.valueOf(i);
    } 
    if (textView != null)
      textView.setText(str1); 
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492900);
    ButterKnife.bind((Activity)this);
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    initIcon();
  }
  
  protected void onPause() {
    stopProtocol();
    super.onPause();
  }
  
  protected void onResume() {
    super.onResume();
    if (this.bleDevice != null) {
      if (this.blueTooth.isConnected(this.bleDevice)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.bleDevice.getName().trim());
        stringBuilder.append(getString(2131755058));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        initHud();
        this.hud.setLabel(getString(2131755257)).show();
        this.blueTooth.readRssi(this.bleDevice, this.rssiEvent);
        this.toolbar.setTitle(this.bleDevice.getName().trim());
        this.tvMacValue.setText(this.bleDevice.getMac());
        TimerTask timerTask = new TimerTask() {
            final Rd300InfoActivity this$0;
            
            public void run() {
              Rd300InfoActivity rd300InfoActivity = Rd300InfoActivity.this;
              rd300InfoActivity.openNotify(rd300InfoActivity.bleDevice, Rd300InfoActivity.this.notifyEvent);
            }
          };
        (new Timer()).schedule(timerTask, 2000L);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.bleDevice.getName().trim());
        stringBuilder.append(getString(2131755057));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        finish();
      } 
    } else {
      finish();
    } 
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd300InfoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */