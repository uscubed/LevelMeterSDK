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
import me.rocyang.bluetooth.rdradar.contact.RD300Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.LoopReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class Rd306InfoActivity extends BaseActivity {
  private static final String TAG = "Rd300InfoActivity";
  
  private RWHandleListener handleListener = new RWHandleListener() {
      final Rd306InfoActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        Rd306InfoActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {
        Rd306InfoActivity.this.blueTooth.readRssi(Rd306InfoActivity.this.bleDevice, Rd306InfoActivity.this.rssiEvent);
      }
      
      public void threadForceStopEvent() {
        if (Rd306InfoActivity.this.hud.isShowing())
          Rd306InfoActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (Rd306InfoActivity.this.hud.isShowing())
          Rd306InfoActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
        rd306InfoActivity.write(rd306InfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
        rd306InfoActivity.write(rd306InfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private byte[] instFlow;
  
  private LoopReaderAsyncTask loopTask;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final Rd306InfoActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("Rd300InfoActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("Rd300InfoActivity", stringBuilder.toString());
        if (Rd306InfoActivity.this.testTask != null && Rd306InfoActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (Rd306InfoActivity.this.loopTask != null && Rd306InfoActivity.this.loopTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("Rd300InfoActivity", "onNotifySuccess: 通知打开失败，请重新连接");
        Rd306InfoActivity.this.finish();
      }
      
      public void onNotifySuccess() {
        Log.i("Rd300InfoActivity", "onNotifySuccess: 通知打开成功");
        Rd306InfoActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (Rd306InfoActivity.this.handler != null)
          Rd306InfoActivity.this.handler.sendMessage(message); 
      }
    };
  
  private int retryTimes = 0;
  
  private BleRssiCallback rssiEvent = new BleRssiCallback() {
      final Rd306InfoActivity this$0;
      
      public void onRssiFailure(BleException param1BleException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRssiFailure: ");
        stringBuilder.append(param1BleException.getDescription());
        Log.i("Rd300InfoActivity", stringBuilder.toString());
      }
      
      public void onRssiSuccess(int param1Int) {
        Rd306InfoActivity.this.tvRssiValue.setText(String.valueOf(param1Int));
        if (param1Int > -80) {
          Rd306InfoActivity.this.tvRssiIcon.setText(Rd306InfoActivity.this.getString(2131755129));
        } else if (param1Int > -90) {
          Rd306InfoActivity.this.tvRssiIcon.setText(Rd306InfoActivity.this.getString(2131755128));
        } else if (param1Int > -100) {
          Rd306InfoActivity.this.tvRssiIcon.setText(Rd306InfoActivity.this.getString(2131755127));
        } else {
          Rd306InfoActivity.this.tvRssiIcon.setText(Rd306InfoActivity.this.getString(2131755126));
        } 
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final Rd306InfoActivity this$0;
      
      public void testFail() {
        if (Rd306InfoActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(Rd306InfoActivity.this.getString(2131755279));
          Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
          Rd306InfoActivity.access$402(rd306InfoActivity, rd306InfoActivity.retryTimes + 1);
          Rd306InfoActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(Rd306InfoActivity.this.getString(2131755273));
          Rd306InfoActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        Rd306InfoActivity.this.startRWThread(RD300Command.infoParamCommand, (String)null);
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
        rd306InfoActivity.write(rd306InfoActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
        rd306InfoActivity.write(rd306InfoActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  private byte[] totalFlow;
  
  @BindView(2131296711)
  TextView tvEmptyHighValue;
  
  @BindView(2131296727)
  TextView tvIconEmptyHigh;
  
  @BindView(2131296734)
  TextView tvIconInstantaneousFlow;
  
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
  
  @BindView(2131296766)
  TextView tvMacValue;
  
  @BindView(2131296774)
  TextView tvRssiIcon;
  
  @BindView(2131296775)
  TextView tvRssiValue;
  
  @BindView(2131296788)
  TextView tvSignalLevelValue;
  
  @BindView(2131296793)
  TextView tvSumFlowValue;
  
  @BindView(2131296805)
  TextView tvWaterLevelValue;
  
  private void initIcon() {
    this.tvIconSignalLevel.setTypeface(RdDevices.iconfont);
    this.tvIconEmptyHigh.setTypeface(RdDevices.iconfont);
    this.tvIconWaterLevel.setTypeface(RdDevices.iconfont);
    this.tvRssiIcon.setTypeface(RdDevices.iconfont);
    this.tvIconInstantaneousFlow.setTypeface(RdDevices.iconfont);
    this.tvIconSumFlow.setTypeface(RdDevices.iconfont);
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
    byte[] arrayOfByte2;
    TextView textView2;
    byte[] arrayOfByte1;
    TextView textView1;
    String str3;
    byte[] arrayOfByte4;
    String str2;
    byte[] arrayOfByte3;
    String str1;
    byte[] arrayOfByte5 = paramRWData.getResultByte();
    if (arrayOfByte5 == null)
      return; 
    if (isReturnErrors(paramRWData).booleanValue()) {
      ToastUtil.ShowTextLong(getString(2131755245));
      return;
    } 
    String str4 = paramRWData.getCmd();
    String str5 = Calendar.getInstance().getTime().toString();
    TextView textView3 = this.tvLastUpdateTime;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getString(2131755261));
    stringBuilder.append(str5);
    textView3.setText(stringBuilder.toString());
    textView3 = null;
    int i = RD300Command.getData(arrayOfByte5);
    stringBuilder = new StringBuilder();
    stringBuilder.append("cmd: ");
    stringBuilder.append(str4);
    Log.i("Rd300InfoActivity", stringBuilder.toString());
    byte b = -1;
    switch (str4.hashCode()) {
      case 1795494400:
        if (str4.equals("800400030001dfdb"))
          b = 0; 
        break;
      case 1348064721:
        if (str4.equals("800400080001ae19"))
          b = 4; 
        break;
      case 1114092893:
        if (str4.equals("8004000100017e1b"))
          b = 2; 
        break;
      case 255418553:
        if (str4.equals("8004000a00010fd9"))
          b = 6; 
        break;
      case -459238245:
        if (str4.equals("800400090001ffd9"))
          b = 5; 
        break;
      case -693331779:
        if (str4.equals("8004000200018e1b"))
          b = 1; 
        break;
      case -1140639712:
        if (str4.equals("8004000700019e1a"))
          b = 3; 
        break;
      case -1551889497:
        if (str4.equals("8004000b00015e19"))
          b = 7; 
        break;
    } 
    switch (b) {
      default:
        str3 = "";
        if (textView3 != null)
          textView3.setText(str3); 
        return;
      case 7:
        arrayOfByte2 = RD300Command.getDataArray((byte[])str3);
        if (arrayOfByte2.length == 2)
          System.arraycopy(arrayOfByte2, 0, this.totalFlow, 4, 2); 
        str3 = String.format("%.3f", new Object[] { Float.valueOf(HexUtil.toInt(this.totalFlow, 0, 6) / 1000.0F) });
        textView2 = this.tvSumFlowValue;
        break;
      case 6:
        arrayOfByte4 = RD300Command.getDataArray((byte[])str3);
        if (arrayOfByte4.length == 2)
          System.arraycopy(arrayOfByte4, 0, this.totalFlow, 2, 2); 
      case 5:
        this.totalFlow = new byte[6];
        Arrays.fill(this.totalFlow, (byte)0);
        arrayOfByte4 = RD300Command.getDataArray(arrayOfByte4);
        if (arrayOfByte4.length == 2)
          System.arraycopy(arrayOfByte4, 0, this.totalFlow, 0, 2); 
      case 4:
        arrayOfByte1 = RD300Command.getDataArray(arrayOfByte4);
        if (arrayOfByte1.length == 2)
          System.arraycopy(arrayOfByte1, 0, this.instFlow, 2, 2); 
        str2 = String.format("%.3f", new Object[] { Float.valueOf(HexUtil.toInt(this.instFlow, 0, 4) / 1000.0F) });
        textView1 = this.tvInstantaneousFlowValue;
        break;
      case 3:
        this.instFlow = new byte[6];
        Arrays.fill(this.instFlow, (byte)0);
        arrayOfByte3 = RD300Command.getDataArray((byte[])str2);
        if (arrayOfByte3.length == 2)
          System.arraycopy(arrayOfByte3, 0, this.instFlow, 0, 2); 
      case 2:
        textView1 = this.tvWaterLevelValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 1:
        textView1 = this.tvEmptyHighValue;
        str1 = String.format("%.3f", new Object[] { Float.valueOf(i / 1000.0F) });
        break;
      case 0:
        textView1 = this.tvSignalLevelValue;
        str1 = String.valueOf(i);
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
    setContentView(2131492902);
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
            final Rd306InfoActivity this$0;
            
            public void run() {
              Rd306InfoActivity rd306InfoActivity = Rd306InfoActivity.this;
              rd306InfoActivity.openNotify(rd306InfoActivity.bleDevice, Rd306InfoActivity.this.notifyEvent);
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


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd306InfoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */