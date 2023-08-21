package me.rocyang.bluetooth.rdradar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class RdDevicesInfoAdapter$DeviceViewHolder_ViewBinding implements Unbinder {
  private RdDevicesInfoAdapter.DeviceViewHolder target;
  
  public RdDevicesInfoAdapter$DeviceViewHolder_ViewBinding(RdDevicesInfoAdapter.DeviceViewHolder paramDeviceViewHolder, View paramView) {
    this.target = paramDeviceViewHolder;
    paramDeviceViewHolder.tvDevice = (TextView)Utils.findRequiredViewAsType(paramView, 2131296706, "field 'tvDevice'", TextView.class);
    paramDeviceViewHolder.tvDeviceName = (TextView)Utils.findRequiredViewAsType(paramView, 2131296707, "field 'tvDeviceName'", TextView.class);
    paramDeviceViewHolder.tvMac = (TextView)Utils.findRequiredViewAsType(paramView, 2131296765, "field 'tvMac'", TextView.class);
    paramDeviceViewHolder.tvRssi = (TextView)Utils.findRequiredViewAsType(paramView, 2131296773, "field 'tvRssi'", TextView.class);
    paramDeviceViewHolder.tvIconRssi = (TextView)Utils.findRequiredViewAsType(paramView, 2131296741, "field 'tvIconRssi'", TextView.class);
    Context context = paramView.getContext();
    Resources resources = context.getResources();
    paramDeviceViewHolder.blueTooth = ContextCompat.getColor(context, 2131099694);
    paramDeviceViewHolder.strMac = resources.getString(2131755234);
    paramDeviceViewHolder.strRssi = resources.getString(2131755238);
    paramDeviceViewHolder.signal_4 = resources.getString(2131755129);
    paramDeviceViewHolder.signal_3 = resources.getString(2131755128);
    paramDeviceViewHolder.signal_2 = resources.getString(2131755127);
    paramDeviceViewHolder.signal_1 = resources.getString(2131755126);
  }
  
  public void unbind() {
    RdDevicesInfoAdapter.DeviceViewHolder deviceViewHolder = this.target;
    if (deviceViewHolder != null) {
      this.target = null;
      deviceViewHolder.tvDevice = null;
      deviceViewHolder.tvDeviceName = null;
      deviceViewHolder.tvMac = null;
      deviceViewHolder.tvRssi = null;
      deviceViewHolder.tvIconRssi = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/adapter/RdDevicesInfoAdapter$DeviceViewHolder_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */