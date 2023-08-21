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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD600Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.LoopReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class AttributesInfoActivity extends BaseActivity {
  private static final String TAG = "AttributesInfoActivity";
  
  private RWHandleListener handleListener = new RWHandleListener() {
      final AttributesInfoActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        AttributesInfoActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {
        AttributesInfoActivity.this.blueTooth.readRssi(AttributesInfoActivity.this.bleDevice, AttributesInfoActivity.this.rssiEvent);
      }
      
      public void threadForceStopEvent() {
        if (AttributesInfoActivity.this.hud.isShowing())
          AttributesInfoActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (AttributesInfoActivity.this.hud.isShowing())
          AttributesInfoActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        AttributesInfoActivity attributesInfoActivity = AttributesInfoActivity.this;
        attributesInfoActivity.write(attributesInfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        AttributesInfoActivity attributesInfoActivity = AttributesInfoActivity.this;
        attributesInfoActivity.write(attributesInfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private LoopReaderAsyncTask loopTask;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final AttributesInfoActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("AttributesInfoActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("AttributesInfoActivity", stringBuilder.toString());
        if (AttributesInfoActivity.this.testTask != null && AttributesInfoActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (AttributesInfoActivity.this.loopTask != null && AttributesInfoActivity.this.loopTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("AttributesInfoActivity", "onNotifySuccess: 通知打开失败，请重新连接");
        AttributesInfoActivity.this.finish();
      }
      
      public void onNotifySuccess() {
        Log.i("AttributesInfoActivity", "onNotifySuccess: 通知打开成功");
        AttributesInfoActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (AttributesInfoActivity.this.handler != null)
          AttributesInfoActivity.this.handler.sendMessage(message); 
      }
    };
  
  private int retryTimes = 0;
  
  private BleRssiCallback rssiEvent = new BleRssiCallback() {
      final AttributesInfoActivity this$0;
      
      public void onRssiFailure(BleException param1BleException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRssiFailure: ");
        stringBuilder.append(param1BleException.getDescription());
        Log.i("AttributesInfoActivity", stringBuilder.toString());
      }
      
      public void onRssiSuccess(int param1Int) {
        AttributesInfoActivity.this.tvRssiValue.setText(String.valueOf(param1Int));
        if (param1Int > -80) {
          AttributesInfoActivity.this.tvRssiIcon.setText(AttributesInfoActivity.this.getString(2131755129));
        } else if (param1Int > -90) {
          AttributesInfoActivity.this.tvRssiIcon.setText(AttributesInfoActivity.this.getString(2131755128));
        } else if (param1Int > -100) {
          AttributesInfoActivity.this.tvRssiIcon.setText(AttributesInfoActivity.this.getString(2131755127));
        } else {
          AttributesInfoActivity.this.tvRssiIcon.setText(AttributesInfoActivity.this.getString(2131755126));
        } 
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final AttributesInfoActivity this$0;
      
      public void testFail() {
        if (AttributesInfoActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(AttributesInfoActivity.this.getString(2131755273));
          AttributesInfoActivity.this.finish();
        } else {
          ToastUtil.ShowTextLong(AttributesInfoActivity.this.getString(2131755273));
          AttributesInfoActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        AttributesInfoActivity.this.startRWThread(RD600Command.infoParamCommand, (String)null);
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        AttributesInfoActivity attributesInfoActivity = AttributesInfoActivity.this;
        attributesInfoActivity.write(attributesInfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        AttributesInfoActivity attributesInfoActivity = AttributesInfoActivity.this;
        attributesInfoActivity.write(attributesInfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  private byte[] totalFlow;
  
  @BindView(2131296700)
  TextView tvAngleValue;
  
  @BindView(2131296711)
  TextView tvEmptyHighValue;
  
  @BindView(2131296717)
  TextView tvFlowSpeedValue;
  
  @BindView(2131296721)
  TextView tvIconAngle;
  
  @BindView(2131296727)
  TextView tvIconEmptyHigh;
  
  @BindView(2131296732)
  TextView tvIconFlowSpeed;
  
  @BindView(2131296734)
  TextView tvIconInstantaneousFlow;
  
  @BindView(2131296745)
  TextView tvIconSignalFlow;
  
  @BindView(2131296746)
  TextView tvIconSignalLevel;
  
  @BindView(2131296750)
  TextView tvIconSumFlow;
  
  @BindView(2131296758)
  TextView tvIconWaterLevel;
  
  @BindView(2131296762)
  TextView tvInstantaneousFlowValue;
  
  @BindView(2131296763)
  TextView tvLastUpdateTime;
  
  @BindView(2131296765)
  TextView tvMac;
  
  @BindView(2131296766)
  TextView tvMacValue;
  
  @BindView(2131296774)
  TextView tvRssiIcon;
  
  @BindView(2131296775)
  TextView tvRssiValue;
  
  @BindView(2131296786)
  TextView tvSignalFlowValue;
  
  @BindView(2131296788)
  TextView tvSignalLevelValue;
  
  @BindView(2131296793)
  TextView tvSumFlowValue;
  
  @BindView(2131296805)
  TextView tvWaterLevelValue;
  
  private void initIcon() {
    this.tvIconAngle.setTypeface(RdDevices.iconfont);
    this.tvIconSignalFlow.setTypeface(RdDevices.iconfont);
    this.tvIconSignalLevel.setTypeface(RdDevices.iconfont);
    this.tvIconEmptyHigh.setTypeface(RdDevices.iconfont);
    this.tvIconFlowSpeed.setTypeface(RdDevices.iconfont);
    this.tvIconInstantaneousFlow.setTypeface(RdDevices.iconfont);
    this.tvIconSumFlow.setTypeface(RdDevices.iconfont);
    this.tvIconWaterLevel.setTypeface(RdDevices.iconfont);
    this.tvRssiIcon.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("AttributesInfoActivity", "startProtocolTest: 开始协议适配");
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
    byte[] arrayOfByte1;
    TextView textView1;
    String str2;
    byte[] arrayOfByte2;
    String str1;
    if (this.hud != null && this.hud.isShowing())
      this.hud.dismiss(); 
    byte[] arrayOfByte3 = paramRWData.getResultByte();
    if (arrayOfByte3 == null)
      return; 
    if (isReturnErrors(paramRWData).booleanValue()) {
      ToastUtil.ShowTextLong(getString(2131755245));
      return;
    } 
    String str3 = paramRWData.getCmd();
    String str4 = Calendar.getInstance().getTime().toString();
    TextView textView2 = this.tvLastUpdateTime;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getString(2131755261));
    stringBuilder.append(str4);
    textView2.setText(stringBuilder.toString());
    stringBuilder = null;
    int i = RD600Command.getData(arrayOfByte3);
    byte b = -1;
    switch (str3.hashCode()) {
      case 1795494400:
        if (str3.equals("800400030001dfdb"))
          b = 4; 
        break;
      case 1348064721:
        if (str3.equals("800400080001ae19"))
          b = 1; 
        break;
      case 1114092893:
        if (str3.equals("8004000100017e1b"))
          b = 3; 
        break;
      case 937055845:
        if (str3.equals("8004000c0001efd8"))
          b = 0; 
        break;
      case 668068515:
        if (str3.equals("800400060001cfda"))
          b = 8; 
        break;
      case -13332992:
        if (str3.equals("8004000400016e1a"))
          b = 6; 
        break;
      case -459238245:
        if (str3.equals("800400090001ffd9"))
          b = 2; 
        break;
      case -693331779:
        if (str3.equals("8004000200018e1b"))
          b = 5; 
        break;
      case -1140639712:
        if (str3.equals("8004000700019e1a"))
          b = 9; 
        break;
      case -1820874286:
        if (str3.equals("8004000500013fda"))
          b = 7; 
        break;
    } 
    switch (b) {
      default:
        str2 = "";
        if (stringBuilder != null)
          stringBuilder.setText(str2); 
        return;
      case 9:
        arrayOfByte1 = RD600Command.getDataArray((byte[])str2);
        if (arrayOfByte1.length == 2)
          System.arraycopy(arrayOfByte1, 0, this.totalFlow, 4, 2); 
        str2 = String.format("%.3f", new Object[] { Float.valueOf(HexUtil.toInt(this.totalFlow, 0, 6) / 1000.0F) });
        textView1 = this.tvSumFlowValue;
        break;
      case 8:
        arrayOfByte2 = RD600Command.getDataArray((byte[])str2);
        if (arrayOfByte2.length == 2)
          System.arraycopy(arrayOfByte2, 0, this.totalFlow, 2, 2); 
      case 7:
        this.totalFlow = new byte[6];
        Arrays.fill(this.totalFlow, (byte)0);
        arrayOfByte2 = RD600Command.getDataArray(arrayOfByte2);
        if (arrayOfByte2.length == 2)
          System.arraycopy(arrayOfByte2, 0, this.totalFlow, 0, 2); 
      case 6:
        textView1 = this.tvInstantaneousFlowValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 5:
        textView1 = this.tvWaterLevelValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 4:
        textView1 = this.tvEmptyHighValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 3:
        textView1 = this.tvFlowSpeedValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 2:
        textView1 = this.tvSignalLevelValue;
        str1 = String.valueOf(i);
        break;
      case 1:
        textView1 = this.tvSignalFlowValue;
        str1 = String.valueOf(i);
        break;
      case 0:
        textView1 = this.tvAngleValue;
        str1 = String.format("%.2f", new Object[] { Float.valueOf(i / 100.0F) });
        break;
    } 
    if (textView1 != null)
      textView1.setText(str1); 
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492894);
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
            final AttributesInfoActivity this$0;
            
            public void run() {
              AttributesInfoActivity attributesInfoActivity = AttributesInfoActivity.this;
              attributesInfoActivity.openNotify(attributesInfoActivity.bleDevice, AttributesInfoActivity.this.notifyEvent);
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


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/AttributesInfoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */