package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DeviceSettingActivity_ViewBinding implements Unbinder {
  private DeviceSettingActivity target;
  
  private View view7f090081;
  
  private View view7f090085;
  
  public DeviceSettingActivity_ViewBinding(DeviceSettingActivity paramDeviceSettingActivity) {
    this(paramDeviceSettingActivity, paramDeviceSettingActivity.getWindow().getDecorView());
  }
  
  public DeviceSettingActivity_ViewBinding(final DeviceSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    View view = Utils.findRequiredView(paramView, 2131296389, "field 'btnRestore' and method 'onBtnRestoreClicked'");
    target.btnRestore = (Button)Utils.castView(view, 2131296389, "field 'btnRestore'", Button.class);
    this.view7f090085 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DeviceSettingActivity_ViewBinding this$0;
          
          final DeviceSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnRestoreClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296385, "field 'btnReboot' and method 'onBtnRebootClicked'");
    target.btnReboot = (Button)Utils.castView(paramView, 2131296385, "field 'btnReboot'", Button.class);
    this.view7f090081 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DeviceSettingActivity_ViewBinding this$0;
          
          final DeviceSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnRebootClicked();
          }
        });
  }
  
  public void unbind() {
    DeviceSettingActivity deviceSettingActivity = this.target;
    if (deviceSettingActivity != null) {
      this.target = null;
      deviceSettingActivity.toolbar = null;
      deviceSettingActivity.btnRestore = null;
      deviceSettingActivity.btnReboot = null;
      this.view7f090085.setOnClickListener(null);
      this.view7f090085 = null;
      this.view7f090081.setOnClickListener(null);
      this.view7f090081 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DeviceSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */