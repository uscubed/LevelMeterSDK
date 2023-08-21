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
import me.rocyang.bluetooth.rdradar.contact.RD300Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class Rd306ParamSettingActivity extends BaseActivity {
  private static final String TAG = "Rd306ParamSettingAct";
  
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
  
  @BindView(2131296374)
  Button btn_houdao;
  
  @BindView(2131296402)
  Button btn_weir_type;
  
  @BindView(2131296473)
  EditText edtSensor;
  
  @BindView(2131296485)
  EditText edtWaterFliter;
  
  @BindView(2131296486)
  EditText edtWaterNoise;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final Rd306ParamSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("Rd306ParamSettingAct", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("Rd306ParamSettingAct", stringBuilder.toString());
        if (Rd306ParamSettingActivity.this.testTask != null && Rd306ParamSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (Rd306ParamSettingActivity.this.readerTask != null && Rd306ParamSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("Rd306ParamSettingAct", "onNotifySuccess: 通知打开失败");
        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
        rd306ParamSettingActivity.disConnect(rd306ParamSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("Rd306ParamSettingAct", "onNotifySuccess: 通知打开成功");
        Rd306ParamSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (Rd306ParamSettingActivity.this.handler != null)
          Rd306ParamSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  @BindView(2131296589)
  RadioButton rbPipeTypeClose;
  
  @BindView(2131296590)
  RadioButton rbPipeTypeOpen;
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final Rd306ParamSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        Rd306ParamSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong(Rd306ParamSettingActivity.this.getString(2131755228));
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (Rd306ParamSettingActivity.this.hud.isShowing())
          Rd306ParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (Rd306ParamSettingActivity.this.hud.isShowing())
          Rd306ParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
        rd306ParamSettingActivity.write(rd306ParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
        rd306ParamSettingActivity.write(rd306ParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  @BindView(2131296639)
  Spinner sp_houdao;
  
  @BindView(2131296642)
  Spinner sp_weir_type;
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final Rd306ParamSettingActivity this$0;
      
      public void testFail() {
        if (Rd306ParamSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(Rd306ParamSettingActivity.this.getString(2131755279));
          Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
          Rd306ParamSettingActivity.access$402(rd306ParamSettingActivity, rd306ParamSettingActivity.retryTimes + 1);
          Rd306ParamSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(Rd306ParamSettingActivity.this.getString(2131755273));
          Rd306ParamSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        Rd306ParamSettingActivity.this.startRWThread(RD300Command.sensorParamRead, Rd306ParamSettingActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
        rd306ParamSettingActivity.write(rd306ParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
        rd306ParamSettingActivity.write(rd306ParamSettingActivity.bleDevice, param1String.getBytes());
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
  
  @BindView(2131296718)
  TextView tv_houdao;
  
  @BindView(2131296733)
  TextView tv_icon_houdao;
  
  @BindView(2131296760)
  TextView tv_icon_weir_type;
  
  @BindView(2131296807)
  TextView tv_weir_type;
  
  private void initIcon() {
    this.tvIconWaterFilter.setTypeface(RdDevices.iconfont);
    this.tvIconWaterNoise.setTypeface(RdDevices.iconfont);
    this.tvIconSensorHeight.setTypeface(RdDevices.iconfont);
    this.tvIconPipeType.setTypeface(RdDevices.iconfont);
    this.tv_icon_weir_type.setTypeface(RdDevices.iconfont);
    this.tv_icon_houdao.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("Rd306ParamSettingAct", "startProtocolTest: 开始协议适配");
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
      case 1891009675:
        if (str.equals("8003001300016bde")) {
          j = 3;
          break;
        } 
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
          j = 4;
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
            if (j != 4) {
              String str1 = str.substring(0, 8);
              if (str.equals(HexUtil.encodeHexStr(arrayOfByte))) {
                j = 2131755398;
              } else {
                j = 2131755397;
              } 
              if ("8006000d0001c7d8".equals(str)) {
                (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755286)).setMessage(getString(2131755293)).setIcon(17301659).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                      final Rd306ParamSettingActivity this$0;
                      
                      public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                        Rd306ParamSettingActivity.this.startRWThread(RD300Command.sensorParamRead, Rd306ParamSettingActivity.this.getString(2131755280));
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
            this.sp_houdao.setSelection(i - 1);
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
    setContentView(2131492903);
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
            final Rd306ParamSettingActivity this$0;
            
            public void run() {
              Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
              rd306ParamSettingActivity.openNotify(rd306ParamSettingActivity.bleDevice, Rd306ParamSettingActivity.this.notifyEvent);
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
  
  @OnClick({2131296401, 2131296400, 2131296374, 2131296373, 2131296387, 2131296388, 2131296590, 2131296589})
  public void onViewClicked(View paramView) {
    String str1 = "";
    String str2 = str1;
    try {
      String str4;
      AlertDialog.Builder builder1;
      DialogInterface.OnClickListener onClickListener;
      String str6;
      AlertDialog.Builder builder2;
      String str5;
      int i = paramView.getId();
      int j = 2;
      switch (i) {
        default:
          str4 = "";
          j = 0;
        case 2131296590:
          str4 = "8006000f";
          j = 1;
          break;
        case 2131296589:
          str4 = "8006000f";
          break;
        case 2131296401:
          str4 = "80060004";
          str2 = str1;
          checkInput(this.edtWaterNoise, getString(2131755364));
          str2 = str1;
          str6 = getString(2131755365);
          str2 = str6;
          j = getEditTextValueToInt(this.edtWaterNoise);
          break;
        case 2131296400:
          str4 = "80060005";
          str2 = str1;
          checkInput(this.edtWaterFliter, getString(2131755364));
          str2 = str1;
          str6 = getString(2131755365);
          str2 = str6;
          j = getEditTextValueToInt(this.edtWaterFliter);
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
              final Rd306ParamSettingActivity this$0;
              
              public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                (new AlertDialog.Builder((Context)Rd306ParamSettingActivity.this)).setTitle(Rd306ParamSettingActivity.this.getString(2131755286)).setMessage(Rd306ParamSettingActivity.this.getString(2131755288)).setIcon(17301543).setPositiveButton(17039369, null).setNegativeButton(17039379, new DialogInterface.OnClickListener() {
                      final Rd306ParamSettingActivity.null this$1;
                      
                      public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                        Rd306ParamSettingActivity rd306ParamSettingActivity = Rd306ParamSettingActivity.this;
                        String str = Rd306ParamSettingActivity.this.getString(2131755290);
                        rd306ParamSettingActivity.startRWThread(new String[] { "8006000d0001c7d8" }, str);
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
        case 2131296374:
          str5 = "80060013";
          str2 = str1;
          if (this.sp_houdao.getSelectedItem() == null) {
            str2 = str1;
            ToastUtil.ShowTextLong(getString(2131755367));
            return;
          } 
          str2 = str1;
          if (this.sp_houdao.getSelectedItem().toString().equals("0.025")) {
            String str = str5;
          } else {
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.051")) {
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.076")) {
              j = 3;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.152")) {
              j = 4;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.228")) {
              j = 5;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.25")) {
              j = 6;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.30")) {
              j = 7;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.45")) {
              j = 8;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.60")) {
              j = 9;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.75")) {
              j = 10;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("0.90")) {
              j = 11;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("1.00")) {
              j = 12;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("1.20")) {
              j = 13;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("1.50")) {
              j = 14;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("1.80")) {
              j = 15;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("2.10")) {
              j = 16;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("2.40")) {
              j = 17;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("3.05")) {
              j = 18;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("3.66")) {
              j = 19;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("4.57")) {
              j = 20;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("6.10")) {
              j = 21;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("7.62")) {
              j = 22;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("9.14")) {
              j = 23;
              String str7 = str5;
              break;
            } 
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("12.19")) {
              j = 24;
              String str7 = str5;
              break;
            } 
            String str = str5;
            str2 = str1;
            if (this.sp_houdao.getSelectedItem().toString().equals("15.24")) {
              j = 25;
              str = str5;
              break;
            } 
            j = 0;
          } 
          j = 1;
          break;
        case 2131296373:
          str3 = "80060006";
          str2 = str1;
          checkInput(this.edtSensor, getString(2131755378));
          str2 = str1;
          str5 = getString(2131755379);
          str2 = str5;
          j = getEditTextValueToInt(this.edtSensor, 100);
          break;
      } 
      str2 = RD300Command.createCMDStr(str3, j);
      String str3 = getString(2131755396);
      startRWThread(new String[] { str2 }, str3);
      return;
    } catch (InputValueEmptyException inputValueEmptyException) {
    
    } catch (NumberFormatException numberFormatException) {
      ToastUtil.ShowTextLong(str2);
    } 
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd306ParamSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */