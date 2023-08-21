package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.kaopiz.kprogresshud.KProgressHUD;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.adapter.RdDevicesInfoAdapter;
import me.rocyang.bluetooth.rdradar.collector.ActivityCollector;
import me.rocyang.bluetooth.rdradar.contact.DeviceType;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
  private static final int REQUEST_CODE_OPEN_GPS = 1;
  
  private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
  
  public View.OnClickListener actionListenner = new View.OnClickListener() {
      final MainActivity this$0;
      
      public void onClick(View param1View) {}
    };
  
  @BindView(2131296405)
  CheckBox cbAutoName;
  
  @BindColor(2131099693)
  int colorAccent;
  
  private BleGattCallback connectEvent = new BleGattCallback() {
      final MainActivity this$0;
      
      public void onConnectFail(BleDevice param1BleDevice, BleException param1BleException) {
        String str;
        if (MainActivity.this.hud.isShowing())
          MainActivity.this.hud.dismiss(); 
        if (MainActivity.this.device.getName() == null || MainActivity.this.device.getName().trim().isEmpty()) {
          str = "无名称";
        } else {
          str = MainActivity.this.device.getName().trim();
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(MainActivity.this.getString(2131755057));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        MainActivity.this.clearDevices();
        MainActivity.this.tbScan.setChecked(true);
        MainActivity.this.checkPermissions();
      }
      
      public void onConnectSuccess(final BleDevice bleDevice, BluetoothGatt param1BluetoothGatt, int param1Int) {
        final String name;
        if (MainActivity.this.hud.isShowing())
          MainActivity.this.hud.dismiss(); 
        MainActivity.this.addDevice(bleDevice);
        if (bleDevice.getName() == null || bleDevice.getName().trim().isEmpty()) {
          str = "无名称";
        } else {
          str = bleDevice.getName().trim();
        } 
        (new AlertDialog.Builder((Context)MainActivity.this)).setTitle(MainActivity.this.getString(2131755219)).setMessage(MainActivity.this.getString(2131755218)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
              final MainActivity.null this$1;
              
              final BleDevice val$bleDevice;
              
              final String val$name;
              
              public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                if (MainActivity.this.cbAutoName.isChecked()) {
                  String str = name;
                  if (str != null && str.trim().length() > 3) {
                    if ("600".equals(name.substring(0, 3))) {
                      RdDevices.deviceType = DeviceType.RD600s;
                      Intent intent1 = new Intent((Context)MainActivity.this, AttributesInfoActivity.class);
                      intent1.putExtra("BLE_DEVICE_DATA", (Parcelable)bleDevice);
                      MainActivity.this.startActivity(intent1);
                      return;
                    } 
                    if ("300".equals(name.substring(0, 3))) {
                      RdDevices.deviceType = DeviceType.RD300s;
                      Intent intent1 = new Intent((Context)MainActivity.this, Rd300InfoActivity.class);
                      intent1.putExtra("BLE_DEVICE_DATA", (Parcelable)bleDevice);
                      MainActivity.this.startActivity(intent1);
                      return;
                    } 
                  } 
                } 
                Intent intent = new Intent((Context)MainActivity.this, SelectDeviceActivity.class);
                intent.putExtra("BLE_DEVICE_DATA", (Parcelable)bleDevice);
                MainActivity.this.startActivity(intent);
              }
            }).setNegativeButton(17039369, new DialogInterface.OnClickListener() {
              final MainActivity.null this$1;
              
              final BleDevice val$bleDevice;
              
              public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                if (MainActivity.this.hud.isShowing())
                  MainActivity.this.hud.dismiss(); 
                BleManager.getInstance().disconnect(bleDevice);
              }
            }).show();
      }
      
      public void onDisConnected(boolean param1Boolean, BleDevice param1BleDevice, BluetoothGatt param1BluetoothGatt, int param1Int) {
        String str;
        if (param1BleDevice.getName() == null || param1BleDevice.getName().trim().isEmpty()) {
          str = "无名称";
        } else {
          str = str.getName().trim();
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onDisConnected: 设备");
        stringBuilder.append(str);
        stringBuilder.append(MainActivity.this.getString(2131755064));
        Log.i("Main", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(MainActivity.this.getString(2131755064));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        param1BluetoothGatt.close();
        ActivityCollector.finishConnected();
      }
      
      public void onStartConnect() {}
    };
  
  private BleDevice device;
  
  private List<BleDevice> devices = new ArrayList<BleDevice>();
  
  private KProgressHUD hud;
  
  private MultiplePulse multiplePulse;
  
  private RdDevicesInfoAdapter.OnItemClickListener rdDeviceItemClickListener = new RdDevicesInfoAdapter.OnItemClickListener() {
      final MainActivity this$0;
      
      public void onItemClick(View param1View, int param1Int) {
        BleDevice bleDevice = MainActivity.this.rdDevicesInfoAdapter.getDevice(param1Int);
        if (bleDevice == null)
          return; 
        MainActivity.this.onConnect(bleDevice);
      }
    };
  
  private RdDevicesInfoAdapter rdDevicesInfoAdapter;
  
  @BindView(2131296601)
  RecyclerView rvDevices;
  
  @BindString(2131755339)
  String strBleDisabled;
  
  @BindString(2131755340)
  String strBleEnabled;
  
  @BindString(2131755198)
  String strBleStatus;
  
  @BindString(2131755236)
  String strNotSupport;
  
  @BindView(2131296664)
  ToggleButton tbScan;
  
  @BindColor(2131100084)
  int textColor;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  @BindView(2131296702)
  TextView tvBluetooth;
  
  @BindView(2131296802)
  TextView tvVersion;
  
  private boolean checkGPSIsOpen() {
    LocationManager locationManager = (LocationManager)getSystemService("location");
    return (locationManager == null) ? false : locationManager.isProviderEnabled("gps");
  }
  
  private void checkPermissions() {
    if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
      ToastUtil.ShowTextLong(getString(2131755169));
      return;
    } 
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "android.permission.ACCESS_FINE_LOCATION";
    ArrayList<String> arrayList = new ArrayList();
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = arrayOfString[b];
      if (ContextCompat.checkSelfPermission((Context)this, str) == 0) {
        onPermissionGranted(str);
      } else {
        arrayList.add(str);
      } 
    } 
    if (!arrayList.isEmpty())
      ActivityCompat.requestPermissions((Activity)this, arrayList.<String>toArray(new String[arrayList.size()]), 2); 
  }
  
  private void onPermissionGranted(String paramString) {
    byte b;
    if (paramString.hashCode() == -1888586689 && paramString.equals("android.permission.ACCESS_FINE_LOCATION")) {
      b = 0;
    } else {
      b = -1;
    } 
    if (b == 0)
      if (Build.VERSION.SDK_INT >= 23 && !checkGPSIsOpen()) {
        (new AlertDialog.Builder((Context)this)).setTitle(2131755161).setMessage(2131755094).setNegativeButton(2131755042, new DialogInterface.OnClickListener() {
              final MainActivity this$0;
              
              public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                MainActivity.this.finish();
              }
            }).setPositiveButton(2131755182, new DialogInterface.OnClickListener() {
              final MainActivity this$0;
              
              public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                MainActivity.this.startActivityForResult(intent, 1);
              }
            }).setCancelable(false).show();
      } else {
        setScanRule();
        startScan();
      }  
  }
  
  private void setScanRule() {
    UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");
    BleScanRuleConfig bleScanRuleConfig = (new BleScanRuleConfig.Builder()).setDeviceName(true, new String[] { "" }).setDeviceMac("").setAutoConnect(false).setScanTimeOut(10000L).build();
    BleManager.getInstance().initScanRule(bleScanRuleConfig);
  }
  
  private void startScan() {
    BleManager.getInstance().scan(new BleScanCallback() {
          final MainActivity this$0;
          
          public void onLeScan(BleDevice param1BleDevice) {
            super.onLeScan(param1BleDevice);
          }
          
          public void onScanFinished(List<BleDevice> param1List) {
            MainActivity.this.multiplePulse.stop();
            MainActivity.this.multiplePulse.setVisible(false, false);
            MainActivity.this.tbScan.setCompoundDrawables(null, null, null, null);
            if (MainActivity.this.tbScan.isChecked())
              MainActivity.this.tbScan.setChecked(false); 
            if (MainActivity.this.devices.size() == 0)
              ToastUtil.ShowTextLong(MainActivity.this.getString(2131755309)); 
          }
          
          public void onScanStarted(boolean param1Boolean) {
            ActivityCollector.finishConnected();
            MainActivity.this.clearDevices();
            MainActivity.access$002(MainActivity.this, new MultiplePulse());
            MainActivity.this.multiplePulse.setBounds(0, 0, 100, 100);
            MainActivity.this.multiplePulse.setColor(MainActivity.this.getColor(2131099693));
            MainActivity.this.tbScan.setCompoundDrawables(null, (Drawable)MainActivity.this.multiplePulse, null, null);
            MainActivity.this.multiplePulse.start();
          }
          
          public void onScanning(BleDevice param1BleDevice) {
            MainActivity.this.addDevice(param1BleDevice);
          }
        });
  }
  
  public void addDevice(BleDevice paramBleDevice) {
    if (paramBleDevice.getName() != null && !paramBleDevice.getName().trim().isEmpty()) {
      int i = removeDevice(paramBleDevice);
      if (i != -1) {
        this.devices.add(i, paramBleDevice);
      } else {
        this.devices.add(paramBleDevice);
      } 
      this.rdDevicesInfoAdapter.notifyDataSetChanged();
    } 
  }
  
  public void clearDevices() {
    if (this.devices.size() == 0)
      return; 
    for (BleDevice bleDevice : this.devices) {
      if (BleManager.getInstance().isConnected(bleDevice))
        BleManager.getInstance().disconnect(bleDevice); 
    } 
    this.devices.clear();
    this.rdDevicesInfoAdapter.notifyDataSetChanged();
  }
  
  public void disconnectDevices() {
    this.devices.size();
    for (BleDevice bleDevice : this.devices) {
      if (BleManager.getInstance().isConnected(bleDevice))
        BleManager.getInstance().disconnect(bleDevice); 
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1 && checkGPSIsOpen()) {
      setScanRule();
      startScan();
    } 
  }
  
  public void onConnect(final BleDevice bleDevice) {
    String str;
    KProgressHUD kProgressHUD = KProgressHUD.create((Context)this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setAnimationSpeed(1).setCancellable(false).setLabel(getString(2131755059));
    if (bleDevice.getName() == null || bleDevice.getName().trim().isEmpty()) {
      str = "无名称";
    } else {
      str = bleDevice.getName().trim();
    } 
    this.hud = kProgressHUD.setDetailsLabel(str).show();
    BleManager.getInstance().cancelScan();
    this.device = bleDevice;
    TimerTask timerTask = new TimerTask() {
        final MainActivity this$0;
        
        final BleDevice val$bleDevice;
        
        public void run() {
          BleManager.getInstance().connect(bleDevice, MainActivity.this.connectEvent);
        }
      };
    (new Timer()).schedule(timerTask, 1000L);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492899);
    setSupportActionBar(this.toolbar);
    ButterKnife.bind((Activity)this);
    if (!RdDevices.isLogined.booleanValue())
      (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755405)).setMessage(getString(2131755268)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            final MainActivity this$0;
            
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              Intent intent = new Intent((Context)MainActivity.this, LoginActivity.class);
              MainActivity.this.startActivity(intent);
            }
          }).create().show(); 
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context)this);
    this.tvVersion.setText(RdDevices.packageName((Context)this));
    this.rvDevices.setLayoutManager((RecyclerView.LayoutManager)linearLayoutManager);
    this.rdDevicesInfoAdapter = new RdDevicesInfoAdapter(this.devices);
    this.rdDevicesInfoAdapter.setOnItemClickListener(this.rdDeviceItemClickListener);
    this.rvDevices.setAdapter((RecyclerView.Adapter)this.rdDevicesInfoAdapter);
    BleManager.getInstance().enableLog(false).setReConnectCount(1, 5000L).setConnectOverTime(20000L).setOperateTimeout(5000);
    if (!BleManager.getInstance().isSupportBle()) {
      this.tvBluetooth.setTextColor(this.textColor);
      this.tvBluetooth.setText(this.strNotSupport);
    } else {
      this.tvBluetooth.setText(this.strBleStatus);
      if (BleManager.getInstance().isBlueEnable()) {
        this.tvBluetooth.append(this.strBleEnabled);
      } else {
        this.tvBluetooth.setTextColor(this.colorAccent);
        this.tvBluetooth.append(this.strBleDisabled);
        ToastUtil.ShowTextLong(getString(2131755169));
      } 
    } 
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131558403, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    paramMenuItem.getItemId();
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause() {
    KProgressHUD kProgressHUD = this.hud;
    if (kProgressHUD != null && kProgressHUD.isShowing())
      this.hud.dismiss(); 
    if (BleManager.getInstance().getScanSate().equals(BleScanState.STATE_SCANNING))
      BleManager.getInstance().cancelScan(); 
    super.onPause();
  }
  
  public final void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfint);
    if (paramInt == 2 && paramArrayOfint.length > 0)
      for (paramInt = 0; paramInt < paramArrayOfint.length; paramInt++) {
        if (paramArrayOfint[paramInt] == 0)
          onPermissionGranted(paramArrayOfString[paramInt]); 
      }  
  }
  
  protected void onResume() {
    super.onResume();
    if (!BleManager.getInstance().isSupportBle()) {
      this.tvBluetooth.setText(this.strNotSupport);
    } else {
      this.tvBluetooth.setTextColor(this.textColor);
      this.tvBluetooth.setText(this.strBleStatus);
      if (BleManager.getInstance().isBlueEnable()) {
        this.tvBluetooth.append(this.strBleEnabled);
      } else {
        this.tvBluetooth.setTextColor(this.colorAccent);
        this.tvBluetooth.append(this.strBleDisabled);
        ToastUtil.ShowTextLong(getString(2131755169));
      } 
    } 
    disconnectDevices();
  }
  
  @OnClick({2131296664})
  public void onViewClicked() {
    if (this.tbScan.isChecked()) {
      checkPermissions();
    } else if (BleManager.getInstance().getScanSate() == BleScanState.STATE_SCANNING) {
      BleManager.getInstance().cancelScan();
    } 
  }
  
  public int removeDevice(BleDevice paramBleDevice) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iload_2
    //   3: aload_0
    //   4: getfield devices : Ljava/util/List;
    //   7: invokeinterface size : ()I
    //   12: if_icmpge -> 50
    //   15: aload_0
    //   16: getfield devices : Ljava/util/List;
    //   19: iload_2
    //   20: invokeinterface get : (I)Ljava/lang/Object;
    //   25: checkcast com/clj/fastble/data/BleDevice
    //   28: invokevirtual getKey : ()Ljava/lang/String;
    //   31: aload_1
    //   32: invokevirtual getKey : ()Ljava/lang/String;
    //   35: invokevirtual equals : (Ljava/lang/Object;)Z
    //   38: ifeq -> 44
    //   41: goto -> 52
    //   44: iinc #2, 1
    //   47: goto -> 2
    //   50: iconst_m1
    //   51: istore_2
    //   52: iload_2
    //   53: iconst_m1
    //   54: if_icmpeq -> 68
    //   57: aload_0
    //   58: getfield devices : Ljava/util/List;
    //   61: iload_2
    //   62: invokeinterface remove : (I)Ljava/lang/Object;
    //   67: pop
    //   68: iload_2
    //   69: ireturn
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */