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
import android.widget.Spinner;
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
import me.rocyang.bluetooth.rdradar.contact.RD600Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class AdvancedParamSettingActivity extends BaseActivity {
  private static final String TAG = "AdvanceParamSettingAct";
  
  @BindView(2131296379)
  Button btnLowSpeed;
  
  @BindView(2131296380)
  Button btnMeasureTimes;
  
  @BindView(2131296386)
  Button btnRelative;
  
  @BindView(2131296387)
  Button btnReload;
  
  @BindView(2131296388)
  Button btnReset;
  
  @BindView(2131296396)
  Button btnStable;
  
  @BindView(2131296468)
  EditText edtMeasureTimes;
  
  @BindView(2131296470)
  EditText edtRelative;
  
  @BindView(2131296476)
  EditText edtStable;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final AdvancedParamSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("AdvanceParamSettingAct", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("AdvanceParamSettingAct", stringBuilder.toString());
        if (AdvancedParamSettingActivity.this.testTask != null && AdvancedParamSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (AdvancedParamSettingActivity.this.readerTask != null && AdvancedParamSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("AdvanceParamSettingAct", "onNotifySuccess: 通知打开失败");
        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
        advancedParamSettingActivity.disConnect(advancedParamSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("AdvanceParamSettingAct", "onNotifySuccess: 通知打开成功");
        AdvancedParamSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (AdvancedParamSettingActivity.this.handler != null)
          AdvancedParamSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final AdvancedParamSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        AdvancedParamSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (AdvancedParamSettingActivity.this.hud.isShowing())
          AdvancedParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (AdvancedParamSettingActivity.this.hud.isShowing())
          AdvancedParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
        advancedParamSettingActivity.write(advancedParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
        advancedParamSettingActivity.write(advancedParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  @BindView(2131296640)
  Spinner spLowSpeed;
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final AdvancedParamSettingActivity this$0;
      
      public void testFail() {
        if (AdvancedParamSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(AdvancedParamSettingActivity.this.getString(2131755279));
          AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
          AdvancedParamSettingActivity.access$402(advancedParamSettingActivity, advancedParamSettingActivity.retryTimes + 1);
          AdvancedParamSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(AdvancedParamSettingActivity.this.getString(2131755273));
          AdvancedParamSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        AdvancedParamSettingActivity.this.startRWThread(RD600Command.advancedParamRead, AdvancedParamSettingActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
        advancedParamSettingActivity.write(advancedParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
        advancedParamSettingActivity.write(advancedParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296735)
  TextView tvIconLowSpeed;
  
  @BindView(2131296736)
  TextView tvIconMeasureTimes;
  
  @BindView(2131296738)
  TextView tvIconRelative;
  
  @BindView(2131296748)
  TextView tvIconStable;
  
  @BindView(2131296764)
  TextView tvLowSpeed;
  
  @BindView(2131296767)
  TextView tvMeasureTimes;
  
  @BindView(2131296771)
  TextView tvRelative;
  
  @BindView(2131296790)
  TextView tvStable;
  
  private void initIcon() {
    this.tvIconLowSpeed.setTypeface(RdDevices.iconfont);
    this.tvIconMeasureTimes.setTypeface(RdDevices.iconfont);
    this.tvIconStable.setTypeface(RdDevices.iconfont);
    this.tvIconRelative.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("AdvanceParamSettingAct", "startProtocolTest: 开始协议适配");
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
    int i = RD600Command.getData(arrayOfByte);
    int j = str.hashCode();
    byte b = -1;
    switch (j) {
      default:
        j = -1;
        break;
      case 1497980074:
        if (str.equals("8003003300016a14")) {
          j = 3;
          break;
        } 
      case 819264841:
        if (str.equals("800300310001cbd4")) {
          j = 1;
          break;
        } 
      case -290087265:
        if (str.equals("8003002f0001abd2")) {
          j = 2;
          break;
        } 
      case -1669501756:
        if (str.equals("8003003000019a14")) {
          j = 0;
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
            if ("80060025000147d0".equals(str)) {
              (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755286)).setMessage(getString(2131755293)).setIcon(17301659).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    final AdvancedParamSettingActivity this$0;
                    
                    public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                      AdvancedParamSettingActivity.this.startRWThread(RD600Command.sensorParamRead, AdvancedParamSettingActivity.this.getString(2131755280));
                    }
                  }).show();
              return;
            } 
            ToastUtil.ShowTextLong(getString(j));
            j = str1.hashCode();
            if (j != -110818212) {
              if (j != -110818210) {
                if (j != -110818190) {
                  j = b;
                } else {
                  j = b;
                  if (str1.equals("8006002f"))
                    j = 1; 
                } 
              } else {
                j = b;
                if (str1.equals("80060033"))
                  j = 2; 
              } 
            } else {
              j = b;
              if (str1.equals("80060031"))
                j = 0; 
            } 
            if (j != 0) {
              if (j != 1) {
                if (j == 2) {
                  EditText editText = this.edtRelative;
                  editText.setHint(editText.getText().toString());
                } 
              } else {
                EditText editText = this.edtStable;
                editText.setHint(editText.getText().toString());
              } 
            } else {
              EditText editText = this.edtMeasureTimes;
              editText.setHint(editText.getText().toString());
            } 
          } else {
            this.edtRelative.setText(String.valueOf(divied100(i)));
            this.edtRelative.setHint(String.valueOf(divied100(i)));
          } 
        } else {
          this.edtStable.setText(String.valueOf(i));
          this.edtStable.setHint(String.valueOf(i));
        } 
      } else {
        this.edtMeasureTimes.setText(String.valueOf(i));
        this.edtMeasureTimes.setHint(String.valueOf(i));
      } 
    } else {
      this.spLowSpeed.setSelection(i);
    } 
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492893);
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
            final AdvancedParamSettingActivity this$0;
            
            public void run() {
              AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
              advancedParamSettingActivity.openNotify(advancedParamSettingActivity.bleDevice, AdvancedParamSettingActivity.this.notifyEvent);
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
  
  @OnClick({2131296379, 2131296380, 2131296396, 2131296386, 2131296387, 2131296388})
  public void onViewClicked(View paramView) {
    String str1 = "";
    String str2 = str1;
    try {
      String str4;
      AlertDialog.Builder builder1;
      DialogInterface.OnClickListener onClickListener;
      int i;
      String str6;
      AlertDialog.Builder builder2;
      String str5;
      switch (paramView.getId()) {
        default:
          str4 = "";
          i = 0;
          break;
        case 2131296396:
          str4 = "8006002f";
          str2 = str1;
          checkInput(this.edtStable, getString(2131755368));
          str2 = str1;
          str6 = getString(2131755247);
          str2 = str6;
          i = getEditTextValueToInt(this.edtStable);
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
              final AdvancedParamSettingActivity this$0;
              
              public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                (new AlertDialog.Builder((Context)AdvancedParamSettingActivity.this)).setTitle(AdvancedParamSettingActivity.this.getString(2131755286)).setMessage(AdvancedParamSettingActivity.this.getString(2131755288)).setIcon(17301543).setPositiveButton(17039369, null).setNegativeButton(17039379, new DialogInterface.OnClickListener() {
                      final AdvancedParamSettingActivity.null this$1;
                      
                      public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                        AdvancedParamSettingActivity advancedParamSettingActivity = AdvancedParamSettingActivity.this;
                        String str = AdvancedParamSettingActivity.this.getString(2131755290);
                        advancedParamSettingActivity.startRWThread(new String[] { "80060025000147d0" }, str);
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
          startRWThread(RD600Command.advancedParamRead, getString(2131755280));
          return;
        case 2131296386:
          str3 = "80060033";
          str2 = str1;
          checkInput(this.edtRelative, getString(2131755368));
          str2 = str1;
          str5 = getString(2131755246);
          str2 = str5;
          i = getEditTextValueToInt(this.edtRelative, 100);
          break;
        case 2131296380:
          str3 = "80060031";
          str2 = str1;
          checkInput(this.edtMeasureTimes, getString(2131755368));
          str2 = str1;
          str5 = getString(2131755247);
          str2 = str5;
          i = getEditTextValueToInt(this.edtMeasureTimes);
          break;
        case 2131296379:
          str5 = "80060030";
          str2 = str1;
          if (this.spLowSpeed.getSelectedItem() == null) {
            str2 = str1;
            ToastUtil.ShowTextLong(getString(2131755362));
            return;
          } 
          str2 = str1;
          if (this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755216))) {
            str3 = str5;
          } else {
            str2 = str1;
            if (this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755250))) {
              i = 1;
              str3 = str5;
              break;
            } 
            str2 = str1;
            if (this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755251))) {
              i = 2;
              str3 = str5;
              break;
            } 
            str2 = str1;
            if (this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755252))) {
              i = 3;
              str3 = str5;
              break;
            } 
            str2 = str1;
            if (this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755253))) {
              i = 4;
              str3 = str5;
              break;
            } 
            str2 = str1;
            boolean bool = this.spLowSpeed.getSelectedItem().toString().equals(getString(2131755254));
            str3 = str5;
            if (bool) {
              i = 5;
              str3 = str5;
              break;
            } 
          } 
          i = 0;
          break;
      } 
      String str3 = RD600Command.createCMDStr(str3, i);
      str2 = getString(2131755396);
      startRWThread(new String[] { str3 }, str2);
      return;
    } catch (InputValueEmptyException inputValueEmptyException) {
    
    } catch (NumberFormatException numberFormatException) {
      ToastUtil.ShowTextLong(str2);
    } 
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/AdvancedParamSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */