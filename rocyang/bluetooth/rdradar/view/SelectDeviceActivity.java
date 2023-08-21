package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.contact.DeviceType;

public class SelectDeviceActivity extends BaseActivity {
  @BindView(2131296352)
  Button btn300;
  
  @BindView(2131296353)
  Button btn306;
  
  @BindView(2131296354)
  Button btn600;
  
  @BindView(2131296364)
  Button btnCancel;
  
  @BindView(2131296514)
  ImageView img300;
  
  @BindView(2131296515)
  ImageView img306;
  
  @BindView(2131296516)
  ImageView img600;
  
  @OnClick({2131296352, 2131296514})
  public void onBtn300Clicked() {
    RdDevices.deviceType = DeviceType.RD300s;
    Intent intent = new Intent((Context)this, Rd300InfoActivity.class);
    intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
    startActivity(intent);
  }
  
  @OnClick({2131296353, 2131296515})
  public void onBtn306Clicked() {
    RdDevices.deviceType = DeviceType.RD306;
    Intent intent = new Intent((Context)this, Rd306InfoActivity.class);
    intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
    startActivity(intent);
  }
  
  @OnClick({2131296354, 2131296516})
  public void onBtn600Clicked() {
    RdDevices.deviceType = DeviceType.RD600s;
    Intent intent = new Intent((Context)this, AttributesInfoActivity.class);
    intent.putExtra("BLE_DEVICE_DATA", (Parcelable)this.bleDevice);
    startActivity(intent);
  }
  
  @OnClick({2131296364})
  public void onBtnCancelClicked() {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492907);
    ButterKnife.bind((Activity)this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131558403, paramMenu);
    return true;
  }
  
  protected void onDestroy() {
    disConnect(this.bleDevice);
    super.onDestroy();
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/SelectDeviceActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */