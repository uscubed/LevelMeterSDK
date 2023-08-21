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
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.RD600Command;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.thread.DataReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class SensorParamSettingActivity extends BaseActivity {
  private static final String TAG = "SensorParamSettingAct";
  
  @BindView(2131296355)
  Button btnA;
  
  @BindView(2131296357)
  Button btnAngle;
  
  @BindView(2131296361)
  Button btnB;
  
  @BindView(2131296363)
  Button btnC;
  
  @BindView(2131296368)
  Button btnFlowDirection;
  
  @BindView(2131296369)
  Button btnFlowFilter;
  
  @BindView(2131296370)
  Button btnFlowGear;
  
  @BindView(2131296371)
  Button btnFlowNoise;
  
  @BindView(2131296384)
  Button btnRainy;
  
  @BindView(2131296387)
  Button btnReload;
  
  @BindView(2131296388)
  Button btnReset;
  
  @BindView(2131296395)
  Button btnSleep;
  
  @BindView(2131296397)
  Button btnStillWater;
  
  @BindView(2131296400)
  Button btnWaterFliter;
  
  @BindView(2131296401)
  Button btnWaterNoise;
  
  @BindView(2131296457)
  EditText edtA;
  
  @BindView(2131296458)
  EditText edtAngle;
  
  @BindView(2131296459)
  EditText edtB;
  
  @BindView(2131296460)
  EditText edtC;
  
  @BindView(2131296461)
  EditText edtFlowFilter;
  
  @BindView(2131296462)
  EditText edtFlowNoise;
  
  @BindView(2131296475)
  EditText edtSleep;
  
  @BindView(2131296477)
  EditText edtStillWater;
  
  @BindView(2131296485)
  EditText edtWaterFliter;
  
  @BindView(2131296486)
  EditText edtWaterNoise;
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final SensorParamSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("SensorParamSettingAct", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: ");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("SensorParamSettingAct", stringBuilder.toString());
        if (SensorParamSettingActivity.this.testTask != null && SensorParamSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (SensorParamSettingActivity.this.readerTask != null && SensorParamSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("SensorParamSettingAct", "onNotifySuccess: 通知打开失败");
        SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
        sensorParamSettingActivity.disConnect(sensorParamSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("SensorParamSettingAct", "onNotifySuccess: 通知打开成功");
        SensorParamSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (SensorParamSettingActivity.this.handler != null)
          SensorParamSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  private DataReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final SensorParamSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        SensorParamSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong("设备返回数据超时，请尝试重新连接！");
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (SensorParamSettingActivity.this.hud.isShowing())
          SensorParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (SensorParamSettingActivity.this.hud.isShowing())
          SensorParamSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
        sensorParamSettingActivity.write(sensorParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
        sensorParamSettingActivity.write(sensorParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  @BindView(2131296637)
  Spinner spFlowDirection;
  
  @BindView(2131296638)
  Spinner spFlowGear;
  
  @BindView(2131296641)
  Spinner spRainy;
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final SensorParamSettingActivity this$0;
      
      public void testFail() {
        if (SensorParamSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(SensorParamSettingActivity.this.getString(2131755279));
          SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
          SensorParamSettingActivity.access$402(sensorParamSettingActivity, sensorParamSettingActivity.retryTimes + 1);
          SensorParamSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(SensorParamSettingActivity.this.getString(2131755273));
          SensorParamSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        SensorParamSettingActivity.this.startRWThread(RD600Command.sensorParamRead, SensorParamSettingActivity.this.getString(2131755280));
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
        sensorParamSettingActivity.write(sensorParamSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
        sensorParamSettingActivity.write(sensorParamSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296699)
  TextView tvAngle;
  
  @BindView(2131296712)
  TextView tvFlowDirection;
  
  @BindView(2131296713)
  TextView tvFlowFilter;
  
  @BindView(2131296714)
  TextView tvFlowGear;
  
  @BindView(2131296715)
  TextView tvFlowNoise;
  
  @BindView(2131296720)
  TextView tvIconA;
  
  @BindView(2131296721)
  TextView tvIconAngle;
  
  @BindView(2131296722)
  TextView tvIconB;
  
  @BindView(2131296723)
  TextView tvIconC;
  
  @BindView(2131296728)
  TextView tvIconFlowDirection;
  
  @BindView(2131296729)
  TextView tvIconFlowFilter;
  
  @BindView(2131296730)
  TextView tvIconFlowGear;
  
  @BindView(2131296731)
  TextView tvIconFlowNoise;
  
  @BindView(2131296739)
  TextView tvIconRiany;
  
  @BindView(2131296747)
  TextView tvIconSleep;
  
  @BindView(2131296749)
  TextView tvIconStillWater;
  
  @BindView(2131296757)
  TextView tvIconWaterFilter;
  
  @BindView(2131296759)
  TextView tvIconWaterNoise;
  
  @BindView(2131296770)
  TextView tvRainy;
  
  @BindView(2131296789)
  TextView tvSleep;
  
  @BindView(2131296791)
  TextView tvStillWater;
  
  @BindView(2131296803)
  TextView tvWaterFliter;
  
  @BindView(2131296806)
  TextView tvWaterNoise;
  
  private void initIcon() {
    this.tvIconAngle.setTypeface(RdDevices.iconfont);
    this.tvIconFlowDirection.setTypeface(RdDevices.iconfont);
    this.tvIconFlowFilter.setTypeface(RdDevices.iconfont);
    this.tvIconFlowGear.setTypeface(RdDevices.iconfont);
    this.tvIconAngle.setTypeface(RdDevices.iconfont);
    this.tvIconFlowNoise.setTypeface(RdDevices.iconfont);
    this.tvIconRiany.setTypeface(RdDevices.iconfont);
    this.tvIconSleep.setTypeface(RdDevices.iconfont);
    this.tvIconWaterFilter.setTypeface(RdDevices.iconfont);
    this.tvIconWaterNoise.setTypeface(RdDevices.iconfont);
    this.tvIconStillWater.setTypeface(RdDevices.iconfont);
    this.tvIconA.setTypeface(RdDevices.iconfont);
    this.tvIconB.setTypeface(RdDevices.iconfont);
    this.tvIconC.setTypeface(RdDevices.iconfont);
  }
  
  private void startProtocolTest() {
    Log.i("SensorParamSettingAct", "startProtocolTest: 开始协议适配");
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
    //   18: ifeq -> 32
    //   21: aload_0
    //   22: ldc_w 2131755245
    //   25: invokevirtual getString : (I)Ljava/lang/String;
    //   28: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   31: return
    //   32: aload_1
    //   33: invokevirtual getCmd : ()Ljava/lang/String;
    //   36: astore_3
    //   37: aload_2
    //   38: invokestatic getData : ([B)I
    //   41: istore #4
    //   43: aload_3
    //   44: invokevirtual hashCode : ()I
    //   47: istore #5
    //   49: iconst_5
    //   50: istore #6
    //   52: iload #5
    //   54: lookupswitch default -> 168, -1650087382 -> 369, -1472985712 -> 352, -1455001304 -> 335, -1257057824 -> 319, -1043962586 -> 303, -578578378 -> 287, -560355648 -> 270, -93573710 -> 253, 157280250 -> 236, 1229022498 -> 220, 1443520453 -> 204, 1714027166 -> 187, 1910543169 -> 171
    //   168: goto -> 386
    //   171: aload_3
    //   172: ldc_w '8003000e0001fbd8'
    //   175: invokevirtual equals : (Ljava/lang/Object;)Z
    //   178: ifeq -> 386
    //   181: iconst_5
    //   182: istore #5
    //   184: goto -> 389
    //   187: aload_3
    //   188: ldc_w '8003001e0001fa1d'
    //   191: invokevirtual equals : (Ljava/lang/Object;)Z
    //   194: ifeq -> 386
    //   197: bipush #6
    //   199: istore #5
    //   201: goto -> 389
    //   204: aload_3
    //   205: ldc_w '8003001800011a1c'
    //   208: invokevirtual equals : (Ljava/lang/Object;)Z
    //   211: ifeq -> 386
    //   214: iconst_1
    //   215: istore #5
    //   217: goto -> 389
    //   220: aload_3
    //   221: ldc_w '8003000c00015a18'
    //   224: invokevirtual equals : (Ljava/lang/Object;)Z
    //   227: ifeq -> 386
    //   230: iconst_3
    //   231: istore #5
    //   233: goto -> 389
    //   236: aload_3
    //   237: ldc_w '8003002a0001bbd3'
    //   240: invokevirtual equals : (Ljava/lang/Object;)Z
    //   243: ifeq -> 386
    //   246: bipush #11
    //   248: istore #5
    //   250: goto -> 389
    //   253: aload_3
    //   254: ldc_w '8003001f0001abdd'
    //   257: invokevirtual equals : (Ljava/lang/Object;)Z
    //   260: ifeq -> 386
    //   263: bipush #7
    //   265: istore #5
    //   267: goto -> 389
    //   270: aload_3
    //   271: ldc_w '8003002900014bd3'
    //   274: invokevirtual equals : (Ljava/lang/Object;)Z
    //   277: ifeq -> 386
    //   280: bipush #10
    //   282: istore #5
    //   284: goto -> 389
    //   287: aload_3
    //   288: ldc_w '8003000d00010bd8'
    //   291: invokevirtual equals : (Ljava/lang/Object;)Z
    //   294: ifeq -> 386
    //   297: iconst_4
    //   298: istore #5
    //   300: goto -> 389
    //   303: aload_3
    //   304: ldc_w '8003001700012a1f'
    //   307: invokevirtual equals : (Ljava/lang/Object;)Z
    //   310: ifeq -> 386
    //   313: iconst_0
    //   314: istore #5
    //   316: goto -> 389
    //   319: aload_3
    //   320: ldc_w '8003000b0001ebd9'
    //   323: invokevirtual equals : (Ljava/lang/Object;)Z
    //   326: ifeq -> 386
    //   329: iconst_2
    //   330: istore #5
    //   332: goto -> 389
    //   335: aload_3
    //   336: ldc_w '8003002C00015bd2'
    //   339: invokevirtual equals : (Ljava/lang/Object;)Z
    //   342: ifeq -> 386
    //   345: bipush #9
    //   347: istore #5
    //   349: goto -> 389
    //   352: aload_3
    //   353: ldc_w '8003002000019bd1'
    //   356: invokevirtual equals : (Ljava/lang/Object;)Z
    //   359: ifeq -> 386
    //   362: bipush #8
    //   364: istore #5
    //   366: goto -> 389
    //   369: aload_3
    //   370: ldc_w '8003002b0001ea13'
    //   373: invokevirtual equals : (Ljava/lang/Object;)Z
    //   376: ifeq -> 386
    //   379: bipush #12
    //   381: istore #5
    //   383: goto -> 389
    //   386: iconst_m1
    //   387: istore #5
    //   389: iload #5
    //   391: tableswitch default -> 456, 0 -> 781, 1 -> 767, 2 -> 740, 3 -> 713, 4 -> 686, 5 -> 659, 6 -> 632, 7 -> 618, 8 -> 591, 9 -> 564, 10 -> 537, 11 -> 510, 12 -> 483
    //   456: aload_3
    //   457: iconst_0
    //   458: bipush #8
    //   460: invokevirtual substring : (II)Ljava/lang/String;
    //   463: astore_1
    //   464: aload_3
    //   465: aload_2
    //   466: invokestatic encodeHexStr : ([B)Ljava/lang/String;
    //   469: invokevirtual equals : (Ljava/lang/Object;)Z
    //   472: ifeq -> 795
    //   475: ldc_w 2131755398
    //   478: istore #5
    //   480: goto -> 800
    //   483: aload_0
    //   484: getfield edtC : Landroid/widget/EditText;
    //   487: iload #4
    //   489: invokestatic divied100 : (I)Ljava/lang/String;
    //   492: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   495: aload_0
    //   496: getfield edtC : Landroid/widget/EditText;
    //   499: iload #4
    //   501: invokestatic divied100 : (I)Ljava/lang/String;
    //   504: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   507: goto -> 1233
    //   510: aload_0
    //   511: getfield edtB : Landroid/widget/EditText;
    //   514: iload #4
    //   516: invokestatic divied100 : (I)Ljava/lang/String;
    //   519: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   522: aload_0
    //   523: getfield edtB : Landroid/widget/EditText;
    //   526: iload #4
    //   528: invokestatic divied100 : (I)Ljava/lang/String;
    //   531: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   534: goto -> 1233
    //   537: aload_0
    //   538: getfield edtA : Landroid/widget/EditText;
    //   541: iload #4
    //   543: invokestatic divied100 : (I)Ljava/lang/String;
    //   546: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   549: aload_0
    //   550: getfield edtA : Landroid/widget/EditText;
    //   553: iload #4
    //   555: invokestatic divied100 : (I)Ljava/lang/String;
    //   558: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   561: goto -> 1233
    //   564: aload_0
    //   565: getfield edtStillWater : Landroid/widget/EditText;
    //   568: iload #4
    //   570: invokestatic divied100 : (I)Ljava/lang/String;
    //   573: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   576: aload_0
    //   577: getfield edtStillWater : Landroid/widget/EditText;
    //   580: iload #4
    //   582: invokestatic divied100 : (I)Ljava/lang/String;
    //   585: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   588: goto -> 1233
    //   591: aload_0
    //   592: getfield edtSleep : Landroid/widget/EditText;
    //   595: iload #4
    //   597: invokestatic valueOf : (I)Ljava/lang/String;
    //   600: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   603: aload_0
    //   604: getfield edtSleep : Landroid/widget/EditText;
    //   607: iload #4
    //   609: invokestatic valueOf : (I)Ljava/lang/String;
    //   612: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   615: goto -> 1233
    //   618: aload_0
    //   619: getfield spRainy : Landroid/widget/Spinner;
    //   622: iload #4
    //   624: iconst_1
    //   625: isub
    //   626: invokevirtual setSelection : (I)V
    //   629: goto -> 1233
    //   632: aload_0
    //   633: getfield edtAngle : Landroid/widget/EditText;
    //   636: iload #4
    //   638: invokestatic valueOf : (I)Ljava/lang/String;
    //   641: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   644: aload_0
    //   645: getfield edtAngle : Landroid/widget/EditText;
    //   648: iload #4
    //   650: invokestatic valueOf : (I)Ljava/lang/String;
    //   653: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   656: goto -> 1233
    //   659: aload_0
    //   660: getfield edtWaterFliter : Landroid/widget/EditText;
    //   663: iload #4
    //   665: invokestatic valueOf : (I)Ljava/lang/String;
    //   668: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   671: aload_0
    //   672: getfield edtWaterFliter : Landroid/widget/EditText;
    //   675: iload #4
    //   677: invokestatic valueOf : (I)Ljava/lang/String;
    //   680: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   683: goto -> 1233
    //   686: aload_0
    //   687: getfield edtFlowFilter : Landroid/widget/EditText;
    //   690: iload #4
    //   692: invokestatic valueOf : (I)Ljava/lang/String;
    //   695: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   698: aload_0
    //   699: getfield edtFlowFilter : Landroid/widget/EditText;
    //   702: iload #4
    //   704: invokestatic valueOf : (I)Ljava/lang/String;
    //   707: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   710: goto -> 1233
    //   713: aload_0
    //   714: getfield edtWaterNoise : Landroid/widget/EditText;
    //   717: iload #4
    //   719: invokestatic valueOf : (I)Ljava/lang/String;
    //   722: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   725: aload_0
    //   726: getfield edtWaterNoise : Landroid/widget/EditText;
    //   729: iload #4
    //   731: invokestatic valueOf : (I)Ljava/lang/String;
    //   734: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   737: goto -> 1233
    //   740: aload_0
    //   741: getfield edtFlowNoise : Landroid/widget/EditText;
    //   744: iload #4
    //   746: invokestatic valueOf : (I)Ljava/lang/String;
    //   749: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   752: aload_0
    //   753: getfield edtFlowNoise : Landroid/widget/EditText;
    //   756: iload #4
    //   758: invokestatic valueOf : (I)Ljava/lang/String;
    //   761: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   764: goto -> 1233
    //   767: aload_0
    //   768: getfield spFlowDirection : Landroid/widget/Spinner;
    //   771: iload #4
    //   773: iconst_1
    //   774: isub
    //   775: invokevirtual setSelection : (I)V
    //   778: goto -> 1233
    //   781: aload_0
    //   782: getfield spFlowGear : Landroid/widget/Spinner;
    //   785: iload #4
    //   787: iconst_1
    //   788: isub
    //   789: invokevirtual setSelection : (I)V
    //   792: goto -> 1233
    //   795: ldc_w 2131755397
    //   798: istore #5
    //   800: ldc_w '80060025000147d0'
    //   803: aload_3
    //   804: invokevirtual equals : (Ljava/lang/Object;)Z
    //   807: ifeq -> 863
    //   810: new androidx/appcompat/app/AlertDialog$Builder
    //   813: dup
    //   814: aload_0
    //   815: invokespecial <init> : (Landroid/content/Context;)V
    //   818: aload_0
    //   819: ldc_w 2131755286
    //   822: invokevirtual getString : (I)Ljava/lang/String;
    //   825: invokevirtual setTitle : (Ljava/lang/CharSequence;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   828: aload_0
    //   829: ldc_w 2131755293
    //   832: invokevirtual getString : (I)Ljava/lang/String;
    //   835: invokevirtual setMessage : (Ljava/lang/CharSequence;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   838: ldc_w 17301659
    //   841: invokevirtual setIcon : (I)Landroidx/appcompat/app/AlertDialog$Builder;
    //   844: ldc_w 17039370
    //   847: new me/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity$5
    //   850: dup
    //   851: aload_0
    //   852: invokespecial <init> : (Lme/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity;)V
    //   855: invokevirtual setPositiveButton : (ILandroid/content/DialogInterface$OnClickListener;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   858: invokevirtual show : ()Landroidx/appcompat/app/AlertDialog;
    //   861: pop
    //   862: return
    //   863: aload_0
    //   864: iload #5
    //   866: invokevirtual getString : (I)Ljava/lang/String;
    //   869: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   872: aload_1
    //   873: invokevirtual hashCode : ()I
    //   876: istore #5
    //   878: iload #5
    //   880: ldc_w -110818244
    //   883: if_icmpeq -> 1036
    //   886: iload #5
    //   888: ldc_w -110818222
    //   891: if_icmpeq -> 1020
    //   894: iload #5
    //   896: ldc_w -110818193
    //   899: if_icmpeq -> 1003
    //   902: iload #5
    //   904: tableswitch default -> 936, -110818256 -> 987, -110818255 -> 971, -110818254 -> 955, -110818253 -> 939
    //   936: goto -> 1053
    //   939: aload_1
    //   940: ldc_w '8006000e'
    //   943: invokevirtual equals : (Ljava/lang/Object;)Z
    //   946: ifeq -> 1053
    //   949: iconst_3
    //   950: istore #5
    //   952: goto -> 1056
    //   955: aload_1
    //   956: ldc_w '8006000d'
    //   959: invokevirtual equals : (Ljava/lang/Object;)Z
    //   962: ifeq -> 1053
    //   965: iconst_2
    //   966: istore #5
    //   968: goto -> 1056
    //   971: aload_1
    //   972: ldc_w '8006000c'
    //   975: invokevirtual equals : (Ljava/lang/Object;)Z
    //   978: ifeq -> 1053
    //   981: iconst_1
    //   982: istore #5
    //   984: goto -> 1056
    //   987: aload_1
    //   988: ldc_w '8006000b'
    //   991: invokevirtual equals : (Ljava/lang/Object;)Z
    //   994: ifeq -> 1053
    //   997: iconst_0
    //   998: istore #5
    //   1000: goto -> 1056
    //   1003: aload_1
    //   1004: ldc_w '8006002c'
    //   1007: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1010: ifeq -> 1053
    //   1013: bipush #6
    //   1015: istore #5
    //   1017: goto -> 1056
    //   1020: aload_1
    //   1021: ldc_w '8006001e'
    //   1024: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1027: ifeq -> 1053
    //   1030: iconst_4
    //   1031: istore #5
    //   1033: goto -> 1056
    //   1036: aload_1
    //   1037: ldc_w '80060020'
    //   1040: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1043: ifeq -> 1053
    //   1046: iload #6
    //   1048: istore #5
    //   1050: goto -> 1056
    //   1053: iconst_m1
    //   1054: istore #5
    //   1056: iload #5
    //   1058: tableswitch default -> 1100, 0 -> 1217, 1 -> 1198, 2 -> 1179, 3 -> 1160, 4 -> 1141, 5 -> 1122, 6 -> 1103
    //   1100: goto -> 1233
    //   1103: aload_0
    //   1104: getfield edtStillWater : Landroid/widget/EditText;
    //   1107: astore_1
    //   1108: aload_1
    //   1109: aload_1
    //   1110: invokevirtual getText : ()Landroid/text/Editable;
    //   1113: invokevirtual toString : ()Ljava/lang/String;
    //   1116: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1119: goto -> 1233
    //   1122: aload_0
    //   1123: getfield edtSleep : Landroid/widget/EditText;
    //   1126: astore_1
    //   1127: aload_1
    //   1128: aload_1
    //   1129: invokevirtual getText : ()Landroid/text/Editable;
    //   1132: invokevirtual toString : ()Ljava/lang/String;
    //   1135: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1138: goto -> 1233
    //   1141: aload_0
    //   1142: getfield edtAngle : Landroid/widget/EditText;
    //   1145: astore_1
    //   1146: aload_1
    //   1147: aload_1
    //   1148: invokevirtual getText : ()Landroid/text/Editable;
    //   1151: invokevirtual toString : ()Ljava/lang/String;
    //   1154: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1157: goto -> 1233
    //   1160: aload_0
    //   1161: getfield edtWaterFliter : Landroid/widget/EditText;
    //   1164: astore_1
    //   1165: aload_1
    //   1166: aload_1
    //   1167: invokevirtual getText : ()Landroid/text/Editable;
    //   1170: invokevirtual toString : ()Ljava/lang/String;
    //   1173: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1176: goto -> 1233
    //   1179: aload_0
    //   1180: getfield edtFlowFilter : Landroid/widget/EditText;
    //   1183: astore_1
    //   1184: aload_1
    //   1185: aload_1
    //   1186: invokevirtual getText : ()Landroid/text/Editable;
    //   1189: invokevirtual toString : ()Ljava/lang/String;
    //   1192: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1195: goto -> 1233
    //   1198: aload_0
    //   1199: getfield edtWaterNoise : Landroid/widget/EditText;
    //   1202: astore_1
    //   1203: aload_1
    //   1204: aload_1
    //   1205: invokevirtual getText : ()Landroid/text/Editable;
    //   1208: invokevirtual toString : ()Ljava/lang/String;
    //   1211: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1214: goto -> 1233
    //   1217: aload_0
    //   1218: getfield edtFlowNoise : Landroid/widget/EditText;
    //   1221: astore_1
    //   1222: aload_1
    //   1223: aload_1
    //   1224: invokevirtual getText : ()Landroid/text/Editable;
    //   1227: invokevirtual toString : ()Ljava/lang/String;
    //   1230: invokevirtual setHint : (Ljava/lang/CharSequence;)V
    //   1233: return
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492908);
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
            final SensorParamSettingActivity this$0;
            
            public void run() {
              SensorParamSettingActivity sensorParamSettingActivity = SensorParamSettingActivity.this;
              sensorParamSettingActivity.openNotify(sensorParamSettingActivity.bleDevice, SensorParamSettingActivity.this.notifyEvent);
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
  
  @OnClick({2131296370, 2131296368, 2131296371, 2131296369, 2131296401, 2131296400, 2131296357, 2131296384, 2131296395, 2131296397, 2131296387, 2131296355, 2131296361, 2131296363, 2131296388})
  public void onViewClicked(View paramView) {
    // Byte code:
    //   0: ldc_w ''
    //   3: astore_2
    //   4: aload_2
    //   5: astore_3
    //   6: aload_1
    //   7: invokevirtual getId : ()I
    //   10: istore #4
    //   12: iconst_3
    //   13: istore #5
    //   15: iload #4
    //   17: lookupswitch default -> 148, 2131296355 -> 1047, 2131296357 -> 999, 2131296361 -> 949, 2131296363 -> 899, 2131296368 -> 767, 2131296369 -> 719, 2131296370 -> 593, 2131296371 -> 545, 2131296384 -> 450, 2131296387 -> 433, 2131296388 -> 352, 2131296395 -> 304, 2131296397 -> 254, 2131296400 -> 206, 2131296401 -> 158
    //   148: ldc_w ''
    //   151: astore_1
    //   152: iconst_0
    //   153: istore #5
    //   155: goto -> 1094
    //   158: ldc_w '8006000c'
    //   161: astore_1
    //   162: aload_2
    //   163: astore_3
    //   164: aload_0
    //   165: aload_0
    //   166: getfield edtWaterNoise : Landroid/widget/EditText;
    //   169: aload_0
    //   170: ldc_w 2131755364
    //   173: invokevirtual getString : (I)Ljava/lang/String;
    //   176: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   179: aload_2
    //   180: astore_3
    //   181: aload_0
    //   182: ldc_w 2131755365
    //   185: invokevirtual getString : (I)Ljava/lang/String;
    //   188: astore #6
    //   190: aload #6
    //   192: astore_3
    //   193: aload_0
    //   194: aload_0
    //   195: getfield edtWaterNoise : Landroid/widget/EditText;
    //   198: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   201: istore #5
    //   203: goto -> 1094
    //   206: ldc_w '8006000e'
    //   209: astore_1
    //   210: aload_2
    //   211: astore_3
    //   212: aload_0
    //   213: aload_0
    //   214: getfield edtWaterFliter : Landroid/widget/EditText;
    //   217: aload_0
    //   218: ldc_w 2131755364
    //   221: invokevirtual getString : (I)Ljava/lang/String;
    //   224: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   227: aload_2
    //   228: astore_3
    //   229: aload_0
    //   230: ldc_w 2131755365
    //   233: invokevirtual getString : (I)Ljava/lang/String;
    //   236: astore #6
    //   238: aload #6
    //   240: astore_3
    //   241: aload_0
    //   242: aload_0
    //   243: getfield edtWaterFliter : Landroid/widget/EditText;
    //   246: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   249: istore #5
    //   251: goto -> 1094
    //   254: ldc_w '8006002c'
    //   257: astore_1
    //   258: aload_2
    //   259: astore_3
    //   260: aload_0
    //   261: aload_0
    //   262: getfield edtStillWater : Landroid/widget/EditText;
    //   265: aload_0
    //   266: ldc_w 2131755386
    //   269: invokevirtual getString : (I)Ljava/lang/String;
    //   272: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   275: aload_2
    //   276: astore_3
    //   277: aload_0
    //   278: ldc_w 2131755368
    //   281: invokevirtual getString : (I)Ljava/lang/String;
    //   284: astore #6
    //   286: aload #6
    //   288: astore_3
    //   289: aload_0
    //   290: aload_0
    //   291: getfield edtStillWater : Landroid/widget/EditText;
    //   294: bipush #100
    //   296: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;I)I
    //   299: istore #5
    //   301: goto -> 1094
    //   304: ldc_w '80060020'
    //   307: astore_1
    //   308: aload_2
    //   309: astore_3
    //   310: aload_0
    //   311: aload_0
    //   312: getfield edtSleep : Landroid/widget/EditText;
    //   315: aload_0
    //   316: ldc_w 2131755384
    //   319: invokevirtual getString : (I)Ljava/lang/String;
    //   322: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   325: aload_2
    //   326: astore_3
    //   327: aload_0
    //   328: ldc_w 2131755385
    //   331: invokevirtual getString : (I)Ljava/lang/String;
    //   334: astore #6
    //   336: aload #6
    //   338: astore_3
    //   339: aload_0
    //   340: aload_0
    //   341: getfield edtSleep : Landroid/widget/EditText;
    //   344: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   347: istore #5
    //   349: goto -> 1094
    //   352: aload_2
    //   353: astore_3
    //   354: new androidx/appcompat/app/AlertDialog$Builder
    //   357: astore_1
    //   358: aload_2
    //   359: astore_3
    //   360: aload_1
    //   361: aload_0
    //   362: invokespecial <init> : (Landroid/content/Context;)V
    //   365: aload_2
    //   366: astore_3
    //   367: aload_1
    //   368: aload_0
    //   369: ldc_w 2131755286
    //   372: invokevirtual getString : (I)Ljava/lang/String;
    //   375: invokevirtual setTitle : (Ljava/lang/CharSequence;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   378: aload_0
    //   379: ldc_w 2131755292
    //   382: invokevirtual getString : (I)Ljava/lang/String;
    //   385: invokevirtual setMessage : (Ljava/lang/CharSequence;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   388: ldc_w 17301543
    //   391: invokevirtual setIcon : (I)Landroidx/appcompat/app/AlertDialog$Builder;
    //   394: astore_1
    //   395: aload_2
    //   396: astore_3
    //   397: new me/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity$1
    //   400: astore #6
    //   402: aload_2
    //   403: astore_3
    //   404: aload #6
    //   406: aload_0
    //   407: invokespecial <init> : (Lme/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity;)V
    //   410: aload_2
    //   411: astore_3
    //   412: aload_1
    //   413: ldc_w 17039379
    //   416: aload #6
    //   418: invokevirtual setPositiveButton : (ILandroid/content/DialogInterface$OnClickListener;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   421: ldc_w 17039369
    //   424: aconst_null
    //   425: invokevirtual setNegativeButton : (ILandroid/content/DialogInterface$OnClickListener;)Landroidx/appcompat/app/AlertDialog$Builder;
    //   428: invokevirtual show : ()Landroidx/appcompat/app/AlertDialog;
    //   431: pop
    //   432: return
    //   433: aload_2
    //   434: astore_3
    //   435: aload_0
    //   436: getstatic me/rocyang/bluetooth/rdradar/contact/RD600Command.sensorParamRead : [Ljava/lang/String;
    //   439: aload_0
    //   440: ldc_w 2131755280
    //   443: invokevirtual getString : (I)Ljava/lang/String;
    //   446: invokespecial startRWThread : ([Ljava/lang/String;Ljava/lang/String;)V
    //   449: return
    //   450: ldc_w '8006001f'
    //   453: astore #6
    //   455: aload_2
    //   456: astore_3
    //   457: aload_0
    //   458: getfield spRainy : Landroid/widget/Spinner;
    //   461: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   464: ifnonnull -> 480
    //   467: aload_2
    //   468: astore_3
    //   469: aload_0
    //   470: ldc_w 2131755375
    //   473: invokevirtual getString : (I)Ljava/lang/String;
    //   476: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   479: return
    //   480: aload_2
    //   481: astore_3
    //   482: aload_0
    //   483: getfield spRainy : Landroid/widget/Spinner;
    //   486: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   489: invokevirtual toString : ()Ljava/lang/String;
    //   492: aload_0
    //   493: ldc_w 2131755270
    //   496: invokevirtual getString : (I)Ljava/lang/String;
    //   499: invokevirtual equals : (Ljava/lang/Object;)Z
    //   502: ifeq -> 511
    //   505: aload #6
    //   507: astore_1
    //   508: goto -> 825
    //   511: aload #6
    //   513: astore_1
    //   514: aload_2
    //   515: astore_3
    //   516: aload_0
    //   517: getfield spRainy : Landroid/widget/Spinner;
    //   520: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   523: invokevirtual toString : ()Ljava/lang/String;
    //   526: aload_0
    //   527: ldc_w 2131755216
    //   530: invokevirtual getString : (I)Ljava/lang/String;
    //   533: invokevirtual equals : (Ljava/lang/Object;)Z
    //   536: ifeq -> 152
    //   539: aload #6
    //   541: astore_1
    //   542: goto -> 859
    //   545: ldc_w '8006000b'
    //   548: astore_1
    //   549: aload_2
    //   550: astore_3
    //   551: aload_0
    //   552: aload_0
    //   553: getfield edtFlowNoise : Landroid/widget/EditText;
    //   556: aload_0
    //   557: ldc_w 2131755364
    //   560: invokevirtual getString : (I)Ljava/lang/String;
    //   563: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   566: aload_2
    //   567: astore_3
    //   568: aload_0
    //   569: ldc_w 2131755365
    //   572: invokevirtual getString : (I)Ljava/lang/String;
    //   575: astore #6
    //   577: aload #6
    //   579: astore_3
    //   580: aload_0
    //   581: aload_0
    //   582: getfield edtFlowNoise : Landroid/widget/EditText;
    //   585: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   588: istore #5
    //   590: goto -> 1094
    //   593: ldc_w '80060017'
    //   596: astore #6
    //   598: aload_2
    //   599: astore_3
    //   600: aload_0
    //   601: getfield spFlowGear : Landroid/widget/Spinner;
    //   604: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   607: ifnonnull -> 623
    //   610: aload_2
    //   611: astore_3
    //   612: aload_0
    //   613: ldc_w 2131755362
    //   616: invokevirtual getString : (I)Ljava/lang/String;
    //   619: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   622: return
    //   623: aload_2
    //   624: astore_3
    //   625: aload_0
    //   626: getfield spFlowGear : Landroid/widget/Spinner;
    //   629: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   632: invokevirtual toString : ()Ljava/lang/String;
    //   635: aload_0
    //   636: ldc_w 2131755250
    //   639: invokevirtual getString : (I)Ljava/lang/String;
    //   642: invokevirtual equals : (Ljava/lang/Object;)Z
    //   645: ifeq -> 654
    //   648: aload #6
    //   650: astore_1
    //   651: goto -> 825
    //   654: aload_2
    //   655: astore_3
    //   656: aload_0
    //   657: getfield spFlowGear : Landroid/widget/Spinner;
    //   660: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   663: invokevirtual toString : ()Ljava/lang/String;
    //   666: aload_0
    //   667: ldc_w 2131755251
    //   670: invokevirtual getString : (I)Ljava/lang/String;
    //   673: invokevirtual equals : (Ljava/lang/Object;)Z
    //   676: ifeq -> 685
    //   679: aload #6
    //   681: astore_1
    //   682: goto -> 859
    //   685: aload #6
    //   687: astore_1
    //   688: aload_2
    //   689: astore_3
    //   690: aload_0
    //   691: getfield spFlowGear : Landroid/widget/Spinner;
    //   694: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   697: invokevirtual toString : ()Ljava/lang/String;
    //   700: aload_0
    //   701: ldc_w 2131755252
    //   704: invokevirtual getString : (I)Ljava/lang/String;
    //   707: invokevirtual equals : (Ljava/lang/Object;)Z
    //   710: ifeq -> 152
    //   713: aload #6
    //   715: astore_1
    //   716: goto -> 1094
    //   719: ldc_w '8006000d'
    //   722: astore_1
    //   723: aload_2
    //   724: astore_3
    //   725: aload_0
    //   726: aload_0
    //   727: getfield edtFlowFilter : Landroid/widget/EditText;
    //   730: aload_0
    //   731: ldc_w 2131755364
    //   734: invokevirtual getString : (I)Ljava/lang/String;
    //   737: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   740: aload_2
    //   741: astore_3
    //   742: aload_0
    //   743: ldc_w 2131755365
    //   746: invokevirtual getString : (I)Ljava/lang/String;
    //   749: astore #6
    //   751: aload #6
    //   753: astore_3
    //   754: aload_0
    //   755: aload_0
    //   756: getfield edtFlowFilter : Landroid/widget/EditText;
    //   759: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   762: istore #5
    //   764: goto -> 1094
    //   767: ldc_w '80060018'
    //   770: astore #6
    //   772: aload_2
    //   773: astore_3
    //   774: aload_0
    //   775: getfield spFlowDirection : Landroid/widget/Spinner;
    //   778: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   781: ifnonnull -> 797
    //   784: aload_2
    //   785: astore_3
    //   786: aload_0
    //   787: ldc_w 2131755366
    //   790: invokevirtual getString : (I)Ljava/lang/String;
    //   793: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   796: return
    //   797: aload_2
    //   798: astore_3
    //   799: aload_0
    //   800: getfield spFlowDirection : Landroid/widget/Spinner;
    //   803: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   806: invokevirtual toString : ()Ljava/lang/String;
    //   809: aload_0
    //   810: ldc_w 2131755079
    //   813: invokevirtual getString : (I)Ljava/lang/String;
    //   816: invokevirtual equals : (Ljava/lang/Object;)Z
    //   819: ifeq -> 831
    //   822: aload #6
    //   824: astore_1
    //   825: iconst_1
    //   826: istore #5
    //   828: goto -> 1094
    //   831: aload_2
    //   832: astore_3
    //   833: aload_0
    //   834: getfield spFlowDirection : Landroid/widget/Spinner;
    //   837: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   840: invokevirtual toString : ()Ljava/lang/String;
    //   843: aload_0
    //   844: ldc_w 2131755061
    //   847: invokevirtual getString : (I)Ljava/lang/String;
    //   850: invokevirtual equals : (Ljava/lang/Object;)Z
    //   853: ifeq -> 865
    //   856: aload #6
    //   858: astore_1
    //   859: iconst_2
    //   860: istore #5
    //   862: goto -> 1094
    //   865: aload #6
    //   867: astore_1
    //   868: aload_2
    //   869: astore_3
    //   870: aload_0
    //   871: getfield spFlowDirection : Landroid/widget/Spinner;
    //   874: invokevirtual getSelectedItem : ()Ljava/lang/Object;
    //   877: invokevirtual toString : ()Ljava/lang/String;
    //   880: aload_0
    //   881: ldc_w 2131755040
    //   884: invokevirtual getString : (I)Ljava/lang/String;
    //   887: invokevirtual equals : (Ljava/lang/Object;)Z
    //   890: ifeq -> 152
    //   893: aload #6
    //   895: astore_1
    //   896: goto -> 1094
    //   899: ldc_w '8006002b'
    //   902: astore_1
    //   903: aload_2
    //   904: astore_3
    //   905: aload_0
    //   906: aload_0
    //   907: getfield edtC : Landroid/widget/EditText;
    //   910: aload_0
    //   911: ldc_w 2131755373
    //   914: invokevirtual getString : (I)Ljava/lang/String;
    //   917: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   920: aload_2
    //   921: astore_3
    //   922: aload_0
    //   923: ldc_w 2131755374
    //   926: invokevirtual getString : (I)Ljava/lang/String;
    //   929: astore #6
    //   931: aload #6
    //   933: astore_3
    //   934: aload_0
    //   935: aload_0
    //   936: getfield edtC : Landroid/widget/EditText;
    //   939: bipush #100
    //   941: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;I)I
    //   944: istore #5
    //   946: goto -> 1094
    //   949: ldc_w '8006002a'
    //   952: astore_1
    //   953: aload_2
    //   954: astore_3
    //   955: aload_0
    //   956: aload_0
    //   957: getfield edtB : Landroid/widget/EditText;
    //   960: aload_0
    //   961: ldc_w 2131755371
    //   964: invokevirtual getString : (I)Ljava/lang/String;
    //   967: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   970: aload_2
    //   971: astore_3
    //   972: aload_0
    //   973: ldc_w 2131755372
    //   976: invokevirtual getString : (I)Ljava/lang/String;
    //   979: astore #6
    //   981: aload #6
    //   983: astore_3
    //   984: aload_0
    //   985: aload_0
    //   986: getfield edtB : Landroid/widget/EditText;
    //   989: bipush #100
    //   991: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;I)I
    //   994: istore #5
    //   996: goto -> 1094
    //   999: ldc_w '8006001e'
    //   1002: astore_1
    //   1003: aload_2
    //   1004: astore_3
    //   1005: aload_0
    //   1006: aload_0
    //   1007: getfield edtAngle : Landroid/widget/EditText;
    //   1010: aload_0
    //   1011: ldc_w 2131755364
    //   1014: invokevirtual getString : (I)Ljava/lang/String;
    //   1017: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   1020: aload_2
    //   1021: astore_3
    //   1022: aload_0
    //   1023: ldc_w 2131755365
    //   1026: invokevirtual getString : (I)Ljava/lang/String;
    //   1029: astore #6
    //   1031: aload #6
    //   1033: astore_3
    //   1034: aload_0
    //   1035: aload_0
    //   1036: getfield edtAngle : Landroid/widget/EditText;
    //   1039: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;)I
    //   1042: istore #5
    //   1044: goto -> 1094
    //   1047: ldc_w '80060029'
    //   1050: astore_1
    //   1051: aload_2
    //   1052: astore_3
    //   1053: aload_0
    //   1054: aload_0
    //   1055: getfield edtA : Landroid/widget/EditText;
    //   1058: aload_0
    //   1059: ldc_w 2131755369
    //   1062: invokevirtual getString : (I)Ljava/lang/String;
    //   1065: invokevirtual checkInput : (Landroid/widget/EditText;Ljava/lang/String;)V
    //   1068: aload_2
    //   1069: astore_3
    //   1070: aload_0
    //   1071: ldc_w 2131755370
    //   1074: invokevirtual getString : (I)Ljava/lang/String;
    //   1077: astore #6
    //   1079: aload #6
    //   1081: astore_3
    //   1082: aload_0
    //   1083: aload_0
    //   1084: getfield edtA : Landroid/widget/EditText;
    //   1087: bipush #100
    //   1089: invokevirtual getEditTextValueToInt : (Landroid/widget/EditText;I)I
    //   1092: istore #5
    //   1094: aload_1
    //   1095: iload #5
    //   1097: invokestatic createCMDStr : (Ljava/lang/String;I)Ljava/lang/String;
    //   1100: astore_1
    //   1101: aload_0
    //   1102: ldc_w 2131755396
    //   1105: invokevirtual getString : (I)Ljava/lang/String;
    //   1108: astore_3
    //   1109: aload_0
    //   1110: iconst_1
    //   1111: anewarray java/lang/String
    //   1114: dup
    //   1115: iconst_0
    //   1116: aload_1
    //   1117: aastore
    //   1118: aload_3
    //   1119: invokespecial startRWThread : ([Ljava/lang/String;Ljava/lang/String;)V
    //   1122: return
    //   1123: astore_1
    //   1124: aload_3
    //   1125: invokestatic ShowTextLong : (Ljava/lang/String;)V
    //   1128: return
    //   1129: astore_1
    //   1130: goto -> 1128
    // Exception table:
    //   from	to	target	type
    //   6	12	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   6	12	1123	java/lang/NumberFormatException
    //   164	179	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   164	179	1123	java/lang/NumberFormatException
    //   181	190	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   181	190	1123	java/lang/NumberFormatException
    //   193	203	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   193	203	1123	java/lang/NumberFormatException
    //   212	227	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   212	227	1123	java/lang/NumberFormatException
    //   229	238	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   229	238	1123	java/lang/NumberFormatException
    //   241	251	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   241	251	1123	java/lang/NumberFormatException
    //   260	275	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   260	275	1123	java/lang/NumberFormatException
    //   277	286	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   277	286	1123	java/lang/NumberFormatException
    //   289	301	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   289	301	1123	java/lang/NumberFormatException
    //   310	325	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   310	325	1123	java/lang/NumberFormatException
    //   327	336	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   327	336	1123	java/lang/NumberFormatException
    //   339	349	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   339	349	1123	java/lang/NumberFormatException
    //   354	358	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   354	358	1123	java/lang/NumberFormatException
    //   360	365	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   360	365	1123	java/lang/NumberFormatException
    //   367	395	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   367	395	1123	java/lang/NumberFormatException
    //   397	402	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   397	402	1123	java/lang/NumberFormatException
    //   404	410	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   404	410	1123	java/lang/NumberFormatException
    //   412	432	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   412	432	1123	java/lang/NumberFormatException
    //   435	449	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   435	449	1123	java/lang/NumberFormatException
    //   457	467	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   457	467	1123	java/lang/NumberFormatException
    //   469	479	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   469	479	1123	java/lang/NumberFormatException
    //   482	505	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   482	505	1123	java/lang/NumberFormatException
    //   516	539	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   516	539	1123	java/lang/NumberFormatException
    //   551	566	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   551	566	1123	java/lang/NumberFormatException
    //   568	577	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   568	577	1123	java/lang/NumberFormatException
    //   580	590	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   580	590	1123	java/lang/NumberFormatException
    //   600	610	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   600	610	1123	java/lang/NumberFormatException
    //   612	622	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   612	622	1123	java/lang/NumberFormatException
    //   625	648	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   625	648	1123	java/lang/NumberFormatException
    //   656	679	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   656	679	1123	java/lang/NumberFormatException
    //   690	713	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   690	713	1123	java/lang/NumberFormatException
    //   725	740	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   725	740	1123	java/lang/NumberFormatException
    //   742	751	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   742	751	1123	java/lang/NumberFormatException
    //   754	764	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   754	764	1123	java/lang/NumberFormatException
    //   774	784	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   774	784	1123	java/lang/NumberFormatException
    //   786	796	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   786	796	1123	java/lang/NumberFormatException
    //   799	822	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   799	822	1123	java/lang/NumberFormatException
    //   833	856	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   833	856	1123	java/lang/NumberFormatException
    //   870	893	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   870	893	1123	java/lang/NumberFormatException
    //   905	920	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   905	920	1123	java/lang/NumberFormatException
    //   922	931	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   922	931	1123	java/lang/NumberFormatException
    //   934	946	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   934	946	1123	java/lang/NumberFormatException
    //   955	970	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   955	970	1123	java/lang/NumberFormatException
    //   972	981	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   972	981	1123	java/lang/NumberFormatException
    //   984	996	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   984	996	1123	java/lang/NumberFormatException
    //   1005	1020	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1005	1020	1123	java/lang/NumberFormatException
    //   1022	1031	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1022	1031	1123	java/lang/NumberFormatException
    //   1034	1044	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1034	1044	1123	java/lang/NumberFormatException
    //   1053	1068	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1053	1068	1123	java/lang/NumberFormatException
    //   1070	1079	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1070	1079	1123	java/lang/NumberFormatException
    //   1082	1094	1129	me/rocyang/bluetooth/rdradar/Exception/InputValueEmptyException
    //   1082	1094	1123	java/lang/NumberFormatException
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */