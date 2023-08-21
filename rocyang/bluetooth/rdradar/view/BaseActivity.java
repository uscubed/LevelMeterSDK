package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.kaopiz.kprogresshud.KProgressHUD;
import java.math.BigDecimal;
import me.rocyang.bluetooth.rdradar.Exception.InputValueEmptyException;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.collector.ActivityCollector;
import me.rocyang.bluetooth.rdradar.contact.DeviceType;
import me.rocyang.bluetooth.rdradar.contact.RDErrors;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {
  public static final String DEVICE_DATA = "BLE_DEVICE_DATA";
  
  public static final int MSG_HUD_TESTPROTOCOL = 4096;
  
  private static final String TAG = "BaseActivity";
  
  public static final String cid = "0000fff6-0000-1000-8000-00805f9b34fb";
  
  public static final String sid = "0000fff0-0000-1000-8000-00805f9b34fb";
  
  public BleDevice bleDevice;
  
  public BleManager blueTooth;
  
  public Handler handler = new Handler() {
      final BaseActivity this$0;
      
      public void handleMessage(Message param1Message) {
        if (param1Message.what != 4096) {
          if (BaseActivity.this.hud.isShowing())
            BaseActivity.this.hud.dismiss(); 
        } else {
          if (BaseActivity.this.hud.isShowing())
            BaseActivity.this.hud.dismiss(); 
          BaseActivity.this.initHud();
          BaseActivity.this.hud.setLabel(BaseActivity.this.getString(2131755258)).show();
        } 
      }
    };
  
  public KProgressHUD hud;
  
  public static String dived(int paramInt1, int paramInt2) {
    double d = paramInt1 / paramInt2;
    paramInt1 = String.valueOf(paramInt2).length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("%.");
    stringBuilder.append(paramInt1 - 1);
    stringBuilder.append("f");
    return String.format(stringBuilder.toString(), new Object[] { Double.valueOf(d) });
  }
  
  public static String divied100(int paramInt) {
    if (paramInt >= 0 && paramInt < 10) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0.0");
      stringBuilder.append(paramInt);
      return stringBuilder.toString();
    } 
    if (paramInt >= 10 && paramInt < 100) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0.");
      stringBuilder.append(paramInt);
      return stringBuilder.toString();
    } 
    if (paramInt >= 100) {
      String str = String.valueOf(paramInt);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str.substring(0, str.length() - 2));
      stringBuilder.append(".");
      stringBuilder.append(str.substring(str.length() - 2, str.length()));
      return stringBuilder.toString();
    } 
    return "0.00";
  }
  
  public void checkInput(EditText paramEditText, String paramString) throws InputValueEmptyException {
    if (paramEditText.length() != 0 && !paramEditText.getText().toString().trim().equals(""))
      return; 
    ToastUtil.ShowTextLong(paramString);
    throw new InputValueEmptyException(paramString);
  }
  
  public void disConnect(BleDevice paramBleDevice) {
    this.blueTooth.disconnect(paramBleDevice);
  }
  
  public int getEditTextValueToInt(EditText paramEditText) {
    return Integer.valueOf(paramEditText.getText().toString()).intValue();
  }
  
  public int getEditTextValueToInt(EditText paramEditText, int paramInt) {
    return (new BigDecimal(paramEditText.getText().toString())).multiply(new BigDecimal(paramInt)).intValue();
  }
  
  public void initHud() {
    this.hud = KProgressHUD.create((Context)this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setDimAmount(0.5F).setAnimationSpeed(2);
  }
  
  public Boolean isReturnErrors(RWData paramRWData) {
    String[] arrayOfString = RDErrors.RESULT_ERRORS;
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (arrayOfString[b].equals(paramRWData.getResultStr()))
        return Boolean.valueOf(true); 
    } 
    return Boolean.valueOf(false);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (!RdDevices.isLogined.booleanValue())
      (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755405)).setMessage(getString(2131755268)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            final BaseActivity this$0;
            
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              Intent intent = new Intent((Context)BaseActivity.this, LoginActivity.class);
              BaseActivity.this.startActivity(intent);
            }
          }).create().show(); 
    ActivityCollector.add((Activity)this);
    this.blueTooth = BleManager.getInstance();
    this.bleDevice = (BleDevice)getIntent().getParcelableExtra("BLE_DEVICE_DATA");
    if (this.bleDevice == null) {
      startActivity(new Intent((Context)this, MainActivity.class));
      finish();
      return;
    } 
    initHud();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    int i = null.$SwitchMap$me$rocyang$bluetooth$rdradar$contact$DeviceType[RdDevices.deviceType.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          getMenuInflater().inflate(2131558401, paramMenu); 
        getMenuInflater().inflate(2131558403, paramMenu);
      } else {
        getMenuInflater().inflate(2131558400, paramMenu);
      } 
    } else {
      getMenuInflater().inflate(2131558402, paramMenu);
    } 
    return true;
  }
  
  protected void onDestroy() {
    ActivityCollector.remove((Activity)this);
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    int i = paramMenuItem.getItemId();
    if (i != 16908332) {
      switch (i) {
        default:
          switch (i) {
            default:
              return super.onOptionsItemSelected(paramMenuItem);
            case 2131296337:
              intent = new Intent((Context)this, SensorParamSettingActivity.class);
              intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
              startActivity(intent);
            case 2131296336:
              intent = new Intent((Context)this, AdvancedParamSettingActivity.class);
              intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
              startActivity(intent);
            case 2131296335:
              intent = new Intent((Context)this, RtuSettingActivity.class);
              intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
              startActivity(intent);
            case 2131296334:
              intent = new Intent((Context)this, Rd306ParamSettingActivity.class);
              intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
              startActivity(intent);
            case 2131296333:
              break;
          } 
          intent = new Intent((Context)this, Rd300sParamSettingActivity.class);
          intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
          startActivity(intent);
        case 2131296325:
          intent = new Intent((Context)this, DitchParamSettingActivity.class);
          intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
          startActivity(intent);
        case 2131296324:
          intent = new Intent((Context)this, DitchAttributesActivity.class);
          intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
          startActivity(intent);
        case 2131296323:
          break;
      } 
      Intent intent = new Intent((Context)this, DeviceSettingActivity.class);
      intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
      startActivity(intent);
    } 
    finish();
  }
  
  public void openNotify(BleDevice paramBleDevice, BleNotifyCallback paramBleNotifyCallback) {
    this.blueTooth.notify(paramBleDevice, "0000fff0-0000-1000-8000-00805f9b34fb", "0000fff6-0000-1000-8000-00805f9b34fb", paramBleNotifyCallback);
  }
  
  public void write(BleDevice paramBleDevice, byte[] paramArrayOfbyte) {
    writeData(paramBleDevice, paramArrayOfbyte, new BleWriteCallback() {
          final BaseActivity this$0;
          
          public void onWriteFailure(BleException param1BleException) {
            Log.i("BaseActivity", "onWriteSuccess: 发送失败：");
          }
          
          public void onWriteSuccess(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onWriteSuccess: 发送成功：");
            stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
            Log.i("BaseActivity", stringBuilder.toString());
          }
        });
  }
  
  public void writeData(BleDevice paramBleDevice, byte[] paramArrayOfbyte, BleWriteCallback paramBleWriteCallback) {
    this.blueTooth.write(paramBleDevice, "0000fff0-0000-1000-8000-00805f9b34fb", "0000fff6-0000-1000-8000-00805f9b34fb", paramArrayOfbyte, paramBleWriteCallback);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */