package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.exception.BleException;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.Exception.InputValueEmptyException;
import me.rocyang.bluetooth.rdradar.Exception.InputValueZeroException;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD600Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class DitchParamSettingActivity extends BaseActivity {
  private static final String TAG = "DitchParamSettingAct";
  
  @BindView(2131296387)
  Button btnReload;
  
  @BindView(2131296390)
  Button btnSave;
  
  @BindView(2131296416)
  ConstraintLayout clAction;
  
  @BindView(2131296419)
  ConstraintLayout clTypeRound;
  
  @BindView(2131296420)
  ConstraintLayout clTypeSelect;
  
  @BindView(2131296421)
  ConstraintLayout clTypeTrapezoid;
  
  @BindView(2131296422)
  ConstraintLayout clTypeU;
  
  @BindBitmap(2131230833)
  Bitmap ditchR;
  
  @BindBitmap(2131230834)
  Bitmap ditchT;
  
  @BindBitmap(2131230835)
  Bitmap ditchU;
  
  @BindView(2131296471)
  EditText edtRoundR;
  
  @BindView(2131296472)
  EditText edtSectorHeight;
  
  @BindView(2131296473)
  EditText edtSensor;
  
  @BindView(2131296478)
  EditText edtTBottom;
  
  @BindView(2131296479)
  EditText edtTHeight;
  
  @BindView(2131296480)
  EditText edtTTop;
  
  @BindView(2131296481)
  EditText edtUBottom;
  
  @BindView(2131296482)
  EditText edtUHeight;
  
  @BindView(2131296483)
  EditText edtUTop;
  
  @BindView(2131296517)
  ImageView imgDitch;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final DitchParamSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("DitchParamSettingAct", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("DitchParamSettingAct", stringBuilder.toString());
        if (DitchParamSettingActivity.this.testTask != null && DitchParamSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (DitchParamSettingActivity.this.readerTask != null && DitchParamSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("DitchParamSettingAct", "onNotifySuccess: 通知打开失败");
        DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
        ditchParamSettingActivity.disConnect(ditchParamSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("DitchParamSettingAct", "onNotifySuccess: 通知打开成功");
        DitchParamSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (DitchParamSettingActivity.this.handler != null)
          DitchParamSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  @BindView(2131296585)
  RadioButton rbDitchTypeRound;
  
  @BindView(2131296586)
  RadioButton rbDitchTypeTrapezoid;
  
  @BindView(2131296587)
  RadioButton rbDitchTypeU;
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  @BindView(2131296595)
  RadioGroup rgDitchType;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final DitchParamSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        DitchParamSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (DitchParamSettingActivity.this.hud.isShowing())
          DitchParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (DitchParamSettingActivity.this.hud.isShowing())
          DitchParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
        ditchParamSettingActivity.write(ditchParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
        ditchParamSettingActivity.write(ditchParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final DitchParamSettingActivity this$0;
      
      public void testFail() {
        if (DitchParamSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(DitchParamSettingActivity.this.getString(2131755279));
          DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
          DitchParamSettingActivity.access$402(ditchParamSettingActivity, ditchParamSettingActivity.retryTimes + 1);
          DitchParamSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(DitchParamSettingActivity.this.getString(2131755273));
          DitchParamSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        DitchParamSettingActivity.this.startRWThread(RD600Command.ditchParamRead, DitchParamSettingActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
        ditchParamSettingActivity.write(ditchParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
        ditchParamSettingActivity.write(ditchParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296709)
  TextView tvDitchType;
  
  @BindView(2131296725)
  TextView tvIconDitchType;
  
  @BindView(2131296740)
  TextView tvIconRoundR;
  
  @BindView(2131296742)
  TextView tvIconSectorHeight;
  
  @BindView(2131296743)
  TextView tvIconSensorHeight;
  
  @BindView(2131296751)
  TextView tvIconTBottom;
  
  @BindView(2131296752)
  TextView tvIconTHight;
  
  @BindView(2131296753)
  TextView tvIconTTop;
  
  @BindView(2131296754)
  TextView tvIconUBottom;
  
  @BindView(2131296755)
  TextView tvIconUHight;
  
  @BindView(2131296756)
  TextView tvIconUTop;
  
  @BindView(2131296772)
  TextView tvRoundR;
  
  @BindView(2131296776)
  TextView tvSectorHeight;
  
  @BindView(2131296780)
  TextView tvSensorHeight;
  
  @BindView(2131296794)
  TextView tvTBottom;
  
  @BindView(2131296795)
  TextView tvTHeight;
  
  @BindView(2131296796)
  TextView tvTTop;
  
  @BindView(2131296799)
  TextView tvUBottom;
  
  @BindView(2131296800)
  TextView tvUHight;
  
  @BindView(2131296801)
  TextView tvUTop;
  
  private Boolean writeSuccess = Boolean.valueOf(true);
  
  private void initIcon() {
    this.tvIconDitchType.setTypeface(RdDevices.iconfont);
    this.tvIconRoundR.setTypeface(RdDevices.iconfont);
    this.tvIconSectorHeight.setTypeface(RdDevices.iconfont);
    this.tvIconSensorHeight.setTypeface(RdDevices.iconfont);
    this.tvIconTBottom.setTypeface(RdDevices.iconfont);
    this.tvIconTHight.setTypeface(RdDevices.iconfont);
    this.tvIconTTop.setTypeface(RdDevices.iconfont);
    this.tvIconUBottom.setTypeface(RdDevices.iconfont);
    this.tvIconUHight.setTypeface(RdDevices.iconfont);
    this.tvIconUTop.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("DitchParamSettingAct", "startProtocolTest: 开始协议适配");
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
  
  public String[] createSaveCommands() throws InputValueEmptyException, InputValueZeroException {
    int i = this.rgDitchType.getCheckedRadioButtonId();
    checkInput(this.edtSensor, getString(2131755378));
    try {
      String str1 = getString(2131755379);
      String str2 = str1;
      try {
        int k;
        int m;
        int n;
        int j = getEditTextValueToInt(this.edtSensor, 100);
        switch (i) {
          default:
            return null;
          case 2131296587:
            str2 = str1;
            checkInput(this.edtSectorHeight, getString(2131755378));
            str2 = str1;
            str1 = getString(2131755379);
            str2 = str1;
            k = getEditTextValueToInt(this.edtSectorHeight, 100);
            str2 = str1;
            checkInput(this.edtUBottom, getString(2131755354));
            str2 = str1;
            str1 = getString(2131755355);
            str2 = str1;
            m = getEditTextValueToInt(this.edtUBottom, 100);
            str2 = str1;
            checkInput(this.edtUTop, getString(2131755358));
            str2 = str1;
            str1 = getString(2131755359);
            str2 = str1;
            n = getEditTextValueToInt(this.edtUTop, 100);
            str2 = str1;
            checkInput(this.edtUHeight, getString(2131755356));
            str2 = str1;
            str1 = getString(2131755351);
            str2 = str1;
            i = getEditTextValueToInt(this.edtUHeight, 100);
            str2 = str1;
            return new String[] { RD600Command.createCMDStr("8006000f", 3), RD600Command.createCMDStr("80060011", k), RD600Command.createCMDStr("80060012", m), RD600Command.createCMDStr("80060013", n), RD600Command.createCMDStr("80060014", i), RD600Command.createCMDStr("80060010", j) };
          case 2131296586:
            str2 = str1;
            checkInput(this.edtTBottom, getString(2131755348));
            str2 = str1;
            str1 = getString(2131755349);
            str2 = str1;
            i = getEditTextValueToInt(this.edtTBottom, 100);
            str2 = str1;
            checkInput(this.edtTTop, getString(2131755352));
            str2 = str1;
            str1 = getString(2131755353);
            str2 = str1;
            k = getEditTextValueToInt(this.edtTTop, 100);
            str2 = str1;
            checkInput(this.edtTHeight, getString(2131755350));
            str2 = str1;
            str1 = getString(2131755351);
            str2 = str1;
            m = getEditTextValueToInt(this.edtTHeight, 100);
            str2 = str1;
            return new String[] { RD600Command.createCMDStr("8006000f", 2), RD600Command.createCMDStr("80060012", i), RD600Command.createCMDStr("80060013", k), RD600Command.createCMDStr("80060014", m), RD600Command.createCMDStr("80060010", j) };
          case 2131296585:
            break;
        } 
        str2 = str1;
        checkInput(this.edtRoundR, getString(2131755346));
        str2 = str1;
        str1 = getString(2131755347);
        str2 = str1;
        i = getEditTextValueToInt(this.edtRoundR, 100);
        str2 = str1;
        String str3 = RD600Command.createCMDStr("8006000f", 1);
        str2 = str1;
        String str4 = RD600Command.createCMDStr("80060011", i);
        str2 = str1;
        str1 = RD600Command.createCMDStr("80060010", j);
        return new String[] { str3, str4, str1 };
      } catch (NumberFormatException numberFormatException) {}
      ToastUtil.ShowTextLong(str2);
      return null;
    } catch (InputValueEmptyException inputValueEmptyException) {
      return null;
    } catch (NumberFormatException numberFormatException) {
      String str = "";
      ToastUtil.ShowTextLong(str);
      return null;
    } 
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
    int i = str.hashCode();
    byte b = -1;
    switch (i) {
      default:
        i = -1;
        break;
      case 1891009675:
        if (str.equals("8003001300016bde")) {
          i = 4;
          break;
        } 
      case 1212289358:
        if (str.equals("800300110001ca1e")) {
          i = 1;
          break;
        } 
      case 102937209:
        if (str.equals("8003000f0001aa18")) {
          i = 0;
          break;
        } 
      case 84923057:
        if (str.equals("800300140001da1f")) {
          i = 5;
          break;
        } 
      case -596595073:
        if (str.equals("8003001200013a1e")) {
          i = 3;
          break;
        } 
      case -1276472155:
        if (str.equals("8003001000019bde")) {
          i = 2;
          break;
        } 
    } 
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i != 5) {
                EditText editText;
                String str1 = str.substring(0, 8);
                i = str1.hashCode();
                if (i != -110818252) {
                  switch (i) {
                    default:
                      i = b;
                      break;
                    case -110818271:
                      i = b;
                      if (str1.equals("80060014"))
                        i = 4; 
                      break;
                    case -110818272:
                      i = b;
                      if (str1.equals("80060013"))
                        i = 3; 
                      break;
                    case -110818273:
                      i = b;
                      if (str1.equals("80060012"))
                        i = 2; 
                      break;
                    case -110818274:
                      i = b;
                      if (str1.equals("80060011"))
                        i = 1; 
                      break;
                    case -110818275:
                      i = b;
                      if (str1.equals("80060010"))
                        i = 5; 
                      break;
                  } 
                } else {
                  i = b;
                  if (str1.equals("8006000f"))
                    i = 0; 
                } 
                if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4) {
                  if (i == 5) {
                    this.hud.dismiss();
                    boolean bool = this.writeSuccess.booleanValue();
                    this.writeSuccess = Boolean.valueOf(str.equals(HexUtil.encodeHexStr(arrayOfByte)) & bool);
                    if (this.writeSuccess.booleanValue()) {
                      i = 2131755398;
                    } else {
                      i = 2131755397;
                    } 
                    ToastUtil.ShowTextLong(getString(i));
                    if (this.writeSuccess.booleanValue()) {
                      editText = this.edtUHeight;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtUTop;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtUBottom;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtTBottom;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtTTop;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtTHeight;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtRoundR;
                      editText.setHint(editText.getText().toString());
                      editText = this.edtSectorHeight;
                      editText.setHint(editText.getText().toString());
                      this.edtSensor.setHint(this.edtSectorHeight.getText().toString());
                    } 
                  } 
                } else {
                  boolean bool = this.writeSuccess.booleanValue();
                  this.writeSuccess = Boolean.valueOf(editText.equals(HexUtil.encodeHexStr(arrayOfByte)) & bool);
                } 
              } else {
                i = RD600Command.getData(arrayOfByte);
                this.edtTHeight.setHint(divied100(i));
                this.edtTHeight.setText(divied100(i));
                this.edtUHeight.setHint(divied100(i));
                this.edtUHeight.setText(divied100(i));
              } 
            } else {
              i = RD600Command.getData(arrayOfByte);
              this.edtTTop.setHint(divied100(i));
              this.edtTTop.setText(divied100(i));
              this.edtUTop.setHint(divied100(i));
              this.edtUTop.setText(divied100(i));
            } 
          } else {
            i = RD600Command.getData(arrayOfByte);
            this.edtTBottom.setHint(divied100(i));
            this.edtTBottom.setText(divied100(i));
            this.edtUBottom.setHint(divied100(i));
            this.edtUBottom.setText(divied100(i));
          } 
        } else {
          i = RD600Command.getData(arrayOfByte);
          this.edtSensor.setText(divied100(i));
          this.edtSensor.setHint(divied100(i));
        } 
      } else {
        i = RD600Command.getData(arrayOfByte);
        this.edtRoundR.setText(divied100(i));
        this.edtRoundR.setHint(divied100(i));
        this.edtSectorHeight.setText(divied100(i));
        this.edtSectorHeight.setHint(divied100(i));
      } 
    } else {
      i = RD600Command.getData(arrayOfByte);
      if (i != 1) {
        if (i != 2) {
          this.rbDitchTypeU.callOnClick();
          this.rbDitchTypeU.setChecked(true);
        } else {
          this.rbDitchTypeTrapezoid.callOnClick();
          this.rbDitchTypeTrapezoid.setChecked(true);
        } 
      } else {
        this.rbDitchTypeRound.callOnClick();
        this.rbDitchTypeRound.setChecked(true);
      } 
    } 
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  @OnClick({2131296387})
  public void onBtnReloadClicked() {
    startRWThread(RD600Command.ditchParamRead, getString(2131755280));
  }
  
  @OnClick({2131296390})
  public void onBtnSaveClicked() {
    try {
      String[] arrayOfString = createSaveCommands();
      this.writeSuccess = Boolean.valueOf(true);
      startRWThread(arrayOfString, getString(2131755396));
    } catch (Exception exception) {}
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492897);
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
  
  @OnClick({2131296585})
  public void onRbDitchTypeRoundClicked() {
    this.clTypeRound.setVisibility(0);
    this.clTypeTrapezoid.setVisibility(8);
    this.clTypeU.setVisibility(8);
    this.imgDitch.setImageBitmap(this.ditchR);
  }
  
  @OnClick({2131296586})
  public void onRbDitchTypeTrapezoidClicked() {
    this.clTypeRound.setVisibility(8);
    this.clTypeTrapezoid.setVisibility(0);
    this.clTypeU.setVisibility(8);
    this.imgDitch.setImageBitmap(this.ditchT);
  }
  
  @OnClick({2131296587})
  public void onRbDitchTypeUClicked() {
    this.clTypeRound.setVisibility(8);
    this.clTypeTrapezoid.setVisibility(8);
    this.clTypeU.setVisibility(0);
    this.imgDitch.setImageBitmap(this.ditchU);
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
            final DitchParamSettingActivity this$0;
            
            public void run() {
              DitchParamSettingActivity ditchParamSettingActivity = DitchParamSettingActivity.this;
              ditchParamSettingActivity.openNotify(ditchParamSettingActivity.bleDevice, DitchParamSettingActivity.this.notifyEvent);
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


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DitchParamSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */