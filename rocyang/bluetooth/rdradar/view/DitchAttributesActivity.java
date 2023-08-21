package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.exception.BleException;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD600Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class DitchAttributesActivity extends BaseActivity {
  private static final String TAG = "DitchAttributesActivity";
  
  @BindView(2131296387)
  Button btnReload;
  
  @BindView(2131296390)
  Button btnSave;
  
  @BindView(2131296474)
  EditText edtShoreCoeffcient;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final DitchAttributesActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("DitchAttributesActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("DitchAttributesActivity", stringBuilder.toString());
        if (DitchAttributesActivity.this.testTask != null && DitchAttributesActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (DitchAttributesActivity.this.readerTask != null && DitchAttributesActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("DitchAttributesActivity", "onNotifySuccess: 通知打开失败");
        DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
        ditchAttributesActivity.disConnect(ditchAttributesActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("DitchAttributesActivity", "onNotifySuccess: 通知打开成功");
        DitchAttributesActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (DitchAttributesActivity.this.handler != null)
          DitchAttributesActivity.this.handler.sendMessage(message); 
      }
    };
  
  @BindView(2131296588)
  RadioButton rbNormal;
  
  @BindView(2131296591)
  RadioButton rbSplash;
  
  @BindView(2131296592)
  RadioButton rbStraight;
  
  @BindView(2131296593)
  RadioButton rbTilt;
  
  @BindView(2131296594)
  RadioButton rbTurbulentFlow;
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final DitchAttributesActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        DitchAttributesActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (DitchAttributesActivity.this.hud.isShowing())
          DitchAttributesActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (DitchAttributesActivity.this.hud.isShowing())
          DitchAttributesActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
        ditchAttributesActivity.write(ditchAttributesActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
        ditchAttributesActivity.write(ditchAttributesActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final DitchAttributesActivity this$0;
      
      public void testFail() {
        if (DitchAttributesActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(DitchAttributesActivity.this.getString(2131755279));
          DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
          DitchAttributesActivity.access$402(ditchAttributesActivity, ditchAttributesActivity.retryTimes + 1);
          DitchAttributesActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(DitchAttributesActivity.this.getString(2131755273));
          DitchAttributesActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        DitchAttributesActivity.this.startRWThread(RD600Command.ditchAttriRead, DitchAttributesActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
        ditchAttributesActivity.write(ditchAttributesActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
        ditchAttributesActivity.write(ditchAttributesActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296708)
  TextView tvDitchClass;
  
  @BindView(2131296724)
  TextView tvIconDitchClass;
  
  @BindView(2131296744)
  TextView tvIconShore;
  
  @BindView(2131296784)
  TextView tvShore;
  
  private void initIcon() {
    this.tvIconShore.setTypeface(RdDevices.iconfont);
    this.tvIconDitchClass.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("DitchAttributesActivity", "startProtocolTest: 开始协议适配");
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
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getResultByte : ()[B
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull -> 10
    //   9: return
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual isReturnErrors : (Lme/rocyang/bluetooth/rdradar/entity/RWData;)Ljava/lang/Boolean;
    //   15: invokevirtual booleanValue : ()Z
    //   18: ifeq -> 31
    //   21: aload_0
    //   22: ldc 2131755245
    //   24: invokevirtual getString : (I)Ljava/lang/String;
    //   27: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   30: return
    //   31: aload_1
    //   32: invokevirtual getCmd : ()Ljava/lang/String;
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual hashCode : ()I
    //   40: istore_3
    //   41: iconst_m1
    //   42: istore #4
    //   44: iload_3
    //   45: ldc -1723839668
    //   47: if_icmpeq -> 73
    //   50: iload_3
    //   51: ldc 763643374
    //   53: if_icmpeq -> 59
    //   56: goto -> 88
    //   59: aload_1
    //   60: ldc '8003001600017bdf'
    //   62: invokevirtual equals : (Ljava/lang/Object;)Z
    //   65: ifeq -> 88
    //   68: iconst_0
    //   69: istore_3
    //   70: goto -> 90
    //   73: aload_1
    //   74: ldc_w '8003001500018bdf'
    //   77: invokevirtual equals : (Ljava/lang/Object;)Z
    //   80: ifeq -> 88
    //   83: iconst_1
    //   84: istore_3
    //   85: goto -> 90
    //   88: iconst_m1
    //   89: istore_3
    //   90: iload_3
    //   91: ifeq -> 277
    //   94: iload_3
    //   95: iconst_1
    //   96: if_icmpeq -> 247
    //   99: aload_1
    //   100: iconst_0
    //   101: bipush #8
    //   103: invokevirtual substring : (II)Ljava/lang/String;
    //   106: astore #5
    //   108: aload_1
    //   109: aload_2
    //   110: invokestatic encodeHexStr : ([B)Ljava/lang/String;
    //   113: invokevirtual equals : (Ljava/lang/Object;)Z
    //   116: ifeq -> 126
    //   119: ldc_w 2131755398
    //   122: istore_3
    //   123: goto -> 130
    //   126: ldc_w 2131755397
    //   129: istore_3
    //   130: aload_0
    //   131: iload_3
    //   132: invokevirtual getString : (I)Ljava/lang/String;
    //   135: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   138: aload #5
    //   140: invokevirtual hashCode : ()I
    //   143: tableswitch default -> 164, -110818270 -> 189, -110818269 -> 170
    //   164: iload #4
    //   166: istore_3
    //   167: goto -> 205
    //   170: iload #4
    //   172: istore_3
    //   173: aload #5
    //   175: ldc_w '80060016'
    //   178: invokevirtual equals : (Ljava/lang/Object;)Z
    //   181: ifeq -> 205
    //   184: iconst_0
    //   185: istore_3
    //   186: goto -> 205
    //   189: iload #4
    //   191: istore_3
    //   192: aload #5
    //   194: ldc_w '80060015'
    //   197: invokevirtual equals : (Ljava/lang/Object;)Z
    //   200: ifeq -> 205
    //   203: iconst_1
    //   204: istore_3
    //   205: iload_3
    //   206: ifeq -> 362
    //   209: iload_3
    //   210: iconst_1
    //   211: if_icmpeq -> 217
    //   214: goto -> 362
    //   217: aload_1
    //   218: aload_2
    //   219: invokestatic encodeHexStr : ([B)Ljava/lang/String;
    //   222: invokevirtual equals : (Ljava/lang/Object;)Z
    //   225: ifeq -> 362
    //   228: aload_0
    //   229: getfield edtShoreCoeffcient : Landroid/widget/EditText;
    //   232: astore_1
    //   233: aload_1
    //   234: aload_1
    //   235: invokevirtual getText : ()Landroid/text/Editable;
    //   238: invokevirtual toString : ()Ljava/lang/String;
    //   241: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   244: goto -> 362
    //   247: aload_2
    //   248: invokestatic getData : ([B)I
    //   251: istore_3
    //   252: aload_0
    //   253: getfield edtShoreCoeffcient : Landroid/widget/EditText;
    //   256: iload_3
    //   257: invokestatic valueOf : (I)Ljava/lang/String;
    //   260: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   263: aload_0
    //   264: getfield edtShoreCoeffcient : Landroid/widget/EditText;
    //   267: iload_3
    //   268: invokestatic valueOf : (I)Ljava/lang/String;
    //   271: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   274: goto -> 362
    //   277: aload_2
    //   278: invokestatic getData : ([B)I
    //   281: istore_3
    //   282: iload_3
    //   283: iconst_1
    //   284: if_icmpeq -> 354
    //   287: iload_3
    //   288: iconst_2
    //   289: if_icmpeq -> 343
    //   292: iload_3
    //   293: iconst_3
    //   294: if_icmpeq -> 332
    //   297: iload_3
    //   298: iconst_4
    //   299: if_icmpeq -> 321
    //   302: iload_3
    //   303: iconst_5
    //   304: if_icmpeq -> 310
    //   307: goto -> 362
    //   310: aload_0
    //   311: getfield rbSplash : Landroid/widget/RadioButton;
    //   314: iconst_1
    //   315: invokevirtual setChecked : (Z)V
    //   318: goto -> 362
    //   321: aload_0
    //   322: getfield rbTilt : Landroid/widget/RadioButton;
    //   325: iconst_1
    //   326: invokevirtual setChecked : (Z)V
    //   329: goto -> 362
    //   332: aload_0
    //   333: getfield rbTurbulentFlow : Landroid/widget/RadioButton;
    //   336: iconst_1
    //   337: invokevirtual setChecked : (Z)V
    //   340: goto -> 362
    //   343: aload_0
    //   344: getfield rbNormal : Landroid/widget/RadioButton;
    //   347: iconst_1
    //   348: invokevirtual setChecked : (Z)V
    //   351: goto -> 362
    //   354: aload_0
    //   355: getfield rbStraight : Landroid/widget/RadioButton;
    //   358: iconst_1
    //   359: invokevirtual setChecked : (Z)V
    //   362: return
  }
  
  public void onBackPressed() {
    stopProtocol();
    super.onBackPressed();
  }
  
  @OnClick({2131296387})
  public void onBtnReloadClicked() {
    startRWThread(RD600Command.ditchAttriRead, getString(2131755280));
  }
  
  @OnClick({2131296390})
  public void onBtnSaveClicked() {
    try {
      checkInput(this.edtShoreCoeffcient, getString(2131755382));
      int i = getEditTextValueToInt(this.edtShoreCoeffcient);
      String str1 = RD600Command.createCMDStr("80060015", i);
      String str2 = getString(2131755396);
      startRWThread(new String[] { str1 }, str2);
      return;
    } catch (Exception exception) {
      ToastUtil.ShowTextLong(getString(2131755383));
      return;
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492896);
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
  
  @OnClick({2131296588})
  public void onRbNormalClicked() {
    String str1 = RD600Command.createCMDStr("80060016", 2);
    String str2 = getString(2131755396);
    startRWThread(new String[] { str1 }, str2);
  }
  
  @OnClick({2131296591})
  public void onRbSplashClicked() {
    String str1 = RD600Command.createCMDStr("80060016", 5);
    String str2 = getString(2131755396);
    startRWThread(new String[] { str1 }, str2);
  }
  
  @OnClick({2131296592})
  public void onRbStraightClicked() {
    String str1 = RD600Command.createCMDStr("80060016", 1);
    String str2 = getString(2131755396);
    startRWThread(new String[] { str1 }, str2);
  }
  
  @OnClick({2131296593})
  public void onRbTiltClicked() {
    String str1 = RD600Command.createCMDStr("80060016", 4);
    String str2 = getString(2131755396);
    startRWThread(new String[] { str1 }, str2);
  }
  
  @OnClick({2131296594})
  public void onRbTurbulentFlowClicked() {
    String str1 = RD600Command.createCMDStr("80060016", 3);
    String str2 = getString(2131755396);
    startRWThread(new String[] { str1 }, str2);
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
            final DitchAttributesActivity this$0;
            
            public void run() {
              DitchAttributesActivity ditchAttributesActivity = DitchAttributesActivity.this;
              ditchAttributesActivity.openNotify(ditchAttributesActivity.bleDevice, DitchAttributesActivity.this.notifyEvent);
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


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DitchAttributesActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */