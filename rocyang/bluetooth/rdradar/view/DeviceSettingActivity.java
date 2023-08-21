package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.exception.BleException;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.ControlCommand;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.thread.TextReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class DeviceSettingActivity extends BaseActivity {
  private static final String TAG = "DeviceSettingActivity";
  
  @BindView(2131296385)
  Button btnReboot;
  
  @BindView(2131296389)
  Button btnRestore;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final DeviceSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("DeviceSettingActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("DeviceSettingActivity", stringBuilder.toString());
        if (DeviceSettingActivity.this.testTask != null && DeviceSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (DeviceSettingActivity.this.readerTask != null && DeviceSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("DeviceSettingActivity", "onNotifySuccess: 通知打开失败");
        DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
        deviceSettingActivity.disConnect(deviceSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("DeviceSettingActivity", "onNotifySuccess: 通知打开成功");
        DeviceSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (DeviceSettingActivity.this.handler != null)
          DeviceSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  private TextReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final DeviceSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        DeviceSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (DeviceSettingActivity.this.hud.isShowing())
          DeviceSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (DeviceSettingActivity.this.hud.isShowing())
          DeviceSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
        deviceSettingActivity.write(deviceSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
        deviceSettingActivity.write(deviceSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final DeviceSettingActivity this$0;
      
      public void testFail() {
        if (DeviceSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(DeviceSettingActivity.this.getString(2131755279));
          DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
          DeviceSettingActivity.access$402(deviceSettingActivity, deviceSettingActivity.retryTimes + 1);
          DeviceSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(DeviceSettingActivity.this.getString(2131755273));
          DeviceSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        if (DeviceSettingActivity.this.hud.isShowing())
          DeviceSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
        deviceSettingActivity.write(deviceSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
        deviceSettingActivity.write(deviceSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  private void dealData(RWData paramRWData) {
    String str1;
    if (paramRWData == null)
      return; 
    String str2 = paramRWData.getCmd();
    byte b = -1;
    int i = str2.hashCode();
    if (i != -934938715) {
      if (i == 1097519758 && str2.equals("restore"))
        b = 1; 
    } else if (str2.equals("reboot")) {
      b = 0;
    } 
    if (b != 0) {
      if (b != 1) {
        str1 = "";
      } else if (str1.getResultStr().contains("restore ok")) {
        str1 = getString(2131755293);
      } else {
        str1 = getString(2131755289);
      } 
    } else if (str1.getResultStr().contains("reboot ok")) {
      str1 = getString(2131755285);
    } else {
      str1 = getString(2131755282);
    } 
    ToastUtil.ShowTextLong(str1);
  }
  
  private void startProtocolTest() {
    Log.i("DeviceSettingActivity", "startProtocolTest: 开始协议适配");
    this.testTask = new ProtocolTestAsyncTask(this.testHandleListener);
    this.testTask.execute((Object[])new Void[0]);
  }
  
  private void startRWThread(RWData[] paramArrayOfRWData, String paramString) {
    if (paramArrayOfRWData == null)
      return; 
    if (this.hud.isShowing())
      this.hud.dismiss(); 
    if (paramString != null) {
      initHud();
      this.hud.setLabel(paramString).setDetailsLabel(this.bleDevice.getName()).show();
    } 
    this.readerTask = new TextReaderAsyncTask(this.rwListener);
    this.readerTask.execute((Object[])paramArrayOfRWData);
  }
  
  private void stopProtocol() {
    ProtocolTestAsyncTask protocolTestAsyncTask = this.testTask;
    if (protocolTestAsyncTask != null && protocolTestAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.testTask.cancel(true); 
    TextReaderAsyncTask textReaderAsyncTask = this.readerTask;
    if (textReaderAsyncTask != null && textReaderAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.readerTask.cancel(true); 
    RdDevices.recvQueue.clear();
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  @OnClick({2131296385})
  public void onBtnRebootClicked() {
    (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755281)).setMessage(getString(2131755284)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
          final DeviceSettingActivity this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            RWData rWData = new RWData("reboot", 1);
            rWData.setEndFlag(ControlCommand.REBOOT_END);
            rWData.setWaitSeconds(30);
            DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
            String str = deviceSettingActivity.getString(2131755283);
            deviceSettingActivity.startRWThread(new RWData[] { rWData }, str);
          }
        }).setNegativeButton(17039369, null).show();
  }
  
  @OnClick({2131296389})
  public void onBtnRestoreClicked() {
    (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755287)).setMessage(getString(2131755291)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
          final DeviceSettingActivity this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            (new AlertDialog.Builder((Context)DeviceSettingActivity.this)).setTitle(DeviceSettingActivity.this.getString(2131755287)).setMessage(DeviceSettingActivity.this.getString(2131755288)).setIcon(17301543).setPositiveButton(17039369, null).setNegativeButton(17039379, new DialogInterface.OnClickListener() {
                  final DeviceSettingActivity.null this$1;
                  
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    RWData rWData = new RWData("restore", 1);
                    rWData.setEndFlag(ControlCommand.RESET_END);
                    rWData.setWaitSeconds(30);
                    DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
                    String str = DeviceSettingActivity.this.getString(2131755290);
                    deviceSettingActivity.startRWThread(new RWData[] { rWData }, str);
                  }
                }).show();
          }
        }).setNegativeButton(17039369, null).show();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492895);
    ButterKnife.bind((Activity)this);
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
  
  protected void onPause() {
    stopProtocol();
    super.onPause();
    finish();
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
        TimerTask timerTask = new TimerTask() {
            final DeviceSettingActivity this$0;
            
            public void run() {
              DeviceSettingActivity deviceSettingActivity = DeviceSettingActivity.this;
              deviceSettingActivity.openNotify(deviceSettingActivity.bleDevice, DeviceSettingActivity.this.notifyEvent);
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


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DeviceSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */