package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.exception.BleException;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.Exception.InputValueEmptyException;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD300Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class Rd300sParamSettingActivity extends BaseActivity {
  private static final String TAG = "Rd300sParamSettingAct";
  
  @BindView(2131296373)
  Button btnHeight;
  
  @BindView(2131296387)
  Button btnReload;
  
  @BindView(2131296388)
  Button btnReset;
  
  @BindView(2131296400)
  Button btnWaterFliter;
  
  @BindView(2131296401)
  Button btnWaterNoise;
  
  @BindView(2131296473)
  EditText edtSensor;
  
  @BindView(2131296485)
  EditText edtWaterFliter;
  
  @BindView(2131296486)
  EditText edtWaterNoise;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final Rd300sParamSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("Rd300sParamSettingAct", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("Rd300sParamSettingAct", stringBuilder.toString());
        if (Rd300sParamSettingActivity.this.testTask != null && Rd300sParamSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (Rd300sParamSettingActivity.this.readerTask != null && Rd300sParamSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("Rd300sParamSettingAct", "onNotifySuccess: 通知打开失败");
        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
        rd300sParamSettingActivity.disConnect(rd300sParamSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("Rd300sParamSettingAct", "onNotifySuccess: 通知打开成功");
        Rd300sParamSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (Rd300sParamSettingActivity.this.handler != null)
          Rd300sParamSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  @BindView(2131296589)
  RadioButton rbPipeTypeClose;
  
  @BindView(2131296590)
  RadioButton rbPipeTypeOpen;
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final Rd300sParamSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        Rd300sParamSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong(Rd300sParamSettingActivity.this.getString(2131755228));
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (Rd300sParamSettingActivity.this.hud.isShowing())
          Rd300sParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (Rd300sParamSettingActivity.this.hud.isShowing())
          Rd300sParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
        rd300sParamSettingActivity.write(rd300sParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
        rd300sParamSettingActivity.write(rd300sParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final Rd300sParamSettingActivity this$0;
      
      public void testFail() {
        if (Rd300sParamSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(Rd300sParamSettingActivity.this.getString(2131755279));
          Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
          Rd300sParamSettingActivity.access$402(rd300sParamSettingActivity, rd300sParamSettingActivity.retryTimes + 1);
          Rd300sParamSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(Rd300sParamSettingActivity.this.getString(2131755273));
          Rd300sParamSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        Rd300sParamSettingActivity.this.startRWThread(RD300Command.sensorParamRead, Rd300sParamSettingActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
        rd300sParamSettingActivity.write(rd300sParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
        rd300sParamSettingActivity.write(rd300sParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296737)
  TextView tvIconPipeType;
  
  @BindView(2131296743)
  TextView tvIconSensorHeight;
  
  @BindView(2131296757)
  TextView tvIconWaterFilter;
  
  @BindView(2131296759)
  TextView tvIconWaterNoise;
  
  private void initIcon() {
    this.tvIconWaterFilter.setTypeface(RdDevices.iconfont);
    this.tvIconWaterNoise.setTypeface(RdDevices.iconfont);
    this.tvIconSensorHeight.setTypeface(RdDevices.iconfont);
    this.tvIconPipeType.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("Rd300sParamSettingAct", "startProtocolTest: 开始协议适配");
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
    this.readerTask = new DataReaderAsyncTask(this.rwListener);
    this.readerTask.execute((Object[])paramArrayOfString);
  }
  
  private void stopProtocol() {
    ProtocolTestAsyncTask protocolTestAsyncTask = this.testTask;
    if (protocolTestAsyncTask != null && protocolTestAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.testTask.cancel(true); 
    DataReaderAsyncTask dataReaderAsyncTask = this.readerTask;
    if (dataReaderAsyncTask != null && dataReaderAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.readerTask.cancel(true); 
    RdDevices.recvQueue.clear();
  }
  
  public void dealData(RWData paramRWData) {
    byte[] arrayOfByte = paramRWData.getResultByte();
    if (arrayOfByte == null)
      return; 
    if (isReturnErrors(paramRWData).booleanValue()) {
      ToastUtil.ShowTextLong(getString(2131755245));
      return;
    } 
    String str = paramRWData.getCmd();
    int i = RD300Command.getData(arrayOfByte);
    int j = str.hashCode();
    byte b = -1;
    boolean bool = false;
    switch (j) {
      default:
        j = -1;
        break;
      case 960154332:
        if (str.equals("8003000600017a1a")) {
          j = 2;
          break;
        } 
      case 281439099:
        if (str.equals("800300040001dbda")) {
          j = 0;
          break;
        } 
      case 102937209:
        if (str.equals("8003000f0001aa18")) {
          j = 3;
          break;
        } 
      case -1527328710:
        if (str.equals("8003000500018a1a")) {
          j = 1;
          break;
        } 
    } 
    if (j != 0) {
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            String str1 = str.substring(0, 8);
            if (str.equals(HexUtil.encodeHexStr(arrayOfByte))) {
              j = 2131755398;
            } else {
              j = 2131755397;
            } 
            if ("8006000d0001c7d8".equals(str)) {
              (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755286)).setMessage(getString(2131755293)).setIcon(17301659).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    final Rd300sParamSettingActivity this$0;
                    
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                      Rd300sParamSettingActivity.this.startRWThread(RD300Command.sensorParamRead, Rd300sParamSettingActivity.this.getString(2131755280));
                    }
                  }).show();
              return;
            } 
            ToastUtil.ShowTextLong(getString(j));
            switch (str1.hashCode()) {
              default:
                j = b;
                break;
              case -110818300:
                j = b;
                if (str1.equals("80060006"))
                  j = 2; 
                break;
              case -110818301:
                j = b;
                if (str1.equals("80060005"))
                  j = 1; 
                break;
              case -110818302:
                j = b;
                if (str1.equals("80060004"))
                  j = 0; 
                break;
            } 
            if (j != 0) {
              if (j != 1) {
                if (j == 2) {
                  EditText editText = this.edtSensor;
                  editText.setHint(editText.getText().toString());
                } 
              } else {
                EditText editText = this.edtWaterFliter;
                editText.setHint(editText.getText().toString());
              } 
            } else {
              EditText editText = this.edtWaterNoise;
              editText.setHint(editText.getText().toString());
            } 
          } else {
            RadioButton radioButton = this.rbPipeTypeOpen;
            if (1 == i) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            radioButton.setChecked(bool1);
            radioButton = this.rbPipeTypeClose;
            boolean bool1 = bool;
            if (2 == i)
              bool1 = true; 
            radioButton.setChecked(bool1);
          } 
        } else {
          this.edtSensor.setText(divied100(i));
          this.edtSensor.setHint(divied100(i));
        } 
      } else {
        this.edtWaterFliter.setText(String.valueOf(i));
        this.edtWaterFliter.setHint(String.valueOf(i));
      } 
    } else {
      this.edtWaterNoise.setText(String.valueOf(i));
      this.edtWaterNoise.setHint(String.valueOf(i));
    } 
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492901);
    ButterKnife.bind((Activity)this);
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    initIcon();
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
            final Rd300sParamSettingActivity this$0;
            
            public void run() {
              Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
              rd300sParamSettingActivity.openNotify(rd300sParamSettingActivity.bleDevice, Rd300sParamSettingActivity.this.notifyEvent);
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
  
  @OnClick({2131296401, 2131296400, 2131296373, 2131296387, 2131296388, 2131296590, 2131296589})
  public void onViewClicked(View paramView) {
    String str1 = "";
    String str2 = str1;
    try {
      String str4;
      AlertDialog.Builder builder1;
      DialogInterface.OnClickListener onClickListener;
      AlertDialog.Builder builder2;
      int i = paramView.getId();
      switch (i) {
        default:
          str4 = "";
          i = 0;
          break;
        case 2131296590:
          i = 1;
          str4 = "8006000f";
          break;
        case 2131296589:
          i = 2;
          str4 = "8006000f";
          break;
        case 2131296401:
          str4 = "80060004";
          str2 = str1;
          checkInput(this.edtWaterNoise, getString(2131755364));
          str2 = str1;
          str1 = getString(2131755365);
          str2 = str1;
          i = getEditTextValueToInt(this.edtWaterNoise);
          break;
        case 2131296400:
          str4 = "80060005";
          str2 = str1;
          checkInput(this.edtWaterFliter, getString(2131755364));
          str2 = str1;
          str1 = getString(2131755365);
          str2 = str1;
          i = getEditTextValueToInt(this.edtWaterFliter);
          break;
        case 2131296388:
          str2 = str1;
          builder1 = new AlertDialog.Builder();
          str2 = str1;
          this((Context)this);
          str2 = str1;
          builder2 = builder1.setTitle(getString(2131755286)).setMessage(getString(2131755292)).setIcon(17301543);
          str2 = str1;
          onClickListener = new DialogInterface.OnClickListener() {
              final Rd300sParamSettingActivity this$0;
              
              public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                (new AlertDialog.Builder((Context)Rd300sParamSettingActivity.this)).setTitle(Rd300sParamSettingActivity.this.getString(2131755286)).setMessage(Rd300sParamSettingActivity.this.getString(2131755288)).setIcon(17301543).setPositiveButton(17039369, null).setNegativeButton(17039379, new DialogInterface.OnClickListener() {
                      final Rd300sParamSettingActivity.null this$1;
                      
                      public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                        Rd300sParamSettingActivity rd300sParamSettingActivity = Rd300sParamSettingActivity.this;
                        String str = Rd300sParamSettingActivity.this.getString(2131755290);
                        rd300sParamSettingActivity.startRWThread(new String[] { "8006000d0001c7d8" }, str);
                      }
                    }).show();
              }
            };
          str2 = str1;
          super(this);
          str2 = str1;
          builder2.setPositiveButton(17039379, onClickListener).setNegativeButton(17039369, null).show();
          return;
        case 2131296387:
          str2 = str1;
          startRWThread(RD300Command.sensorParamRead, getString(2131755280));
          return;
        case 2131296373:
          str3 = "80060006";
          str2 = str1;
          checkInput(this.edtSensor, getString(2131755378));
          str2 = str1;
          str1 = getString(2131755379);
          str2 = str1;
          i = getEditTextValueToInt(this.edtSensor, 100);
          break;
      } 
      str2 = RD300Command.createCMDStr(str3, i);
      String str3 = getString(2131755396);
      startRWThread(new String[] { str2 }, str3);
      return;
    } catch (InputValueEmptyException inputValueEmptyException) {
    
    } catch (NumberFormatException numberFormatException) {
      ToastUtil.ShowTextLong(str2);
    } 
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd300sParamSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */