package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class RtuSettingActivity_ViewBinding implements Unbinder {
  private RtuSettingActivity target;
  
  private View view7f09006e;
  
  private View view7f09007f;
  
  private View view7f090089;
  
  public RtuSettingActivity_ViewBinding(RtuSettingActivity paramRtuSettingActivity) {
    this(paramRtuSettingActivity, paramRtuSettingActivity.getWindow().getDecorView());
  }
  
  public RtuSettingActivity_ViewBinding(final RtuSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.rvResult = (RecyclerView)Utils.findRequiredViewAsType(paramView, 2131296602, "field 'rvResult'", RecyclerView.class);
    View view = Utils.findRequiredView(paramView, 2131296383, "field 'btnQuery' and method 'onBtnQueryClicked'");
    target.btnQuery = (Button)Utils.castView(view, 2131296383, "field 'btnQuery'", Button.class);
    this.view7f09007f = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final RtuSettingActivity_ViewBinding this$0;
          
          final RtuSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnQueryClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296393, "field 'btnSetting' and method 'onBtnSettingClicked'");
    target.btnSetting = (Button)Utils.castView(view, 2131296393, "field 'btnSetting'", Button.class);
    this.view7f090089 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final RtuSettingActivity_ViewBinding this$0;
          
          final RtuSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnSettingClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296366, "field 'btnClear' and method 'onBtnClearClicked'");
    target.btnClear = (Button)Utils.castView(paramView, 2131296366, "field 'btnClear'", Button.class);
    this.view7f09006e = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final RtuSettingActivity_ViewBinding this$0;
          
          final RtuSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnClearClicked();
          }
        });
  }
  
  public void unbind() {
    RtuSettingActivity rtuSettingActivity = this.target;
    if (rtuSettingActivity != null) {
      this.target = null;
      rtuSettingActivity.toolbar = null;
      rtuSettingActivity.rvResult = null;
      rtuSettingActivity.btnQuery = null;
      rtuSettingActivity.btnSetting = null;
      rtuSettingActivity.btnClear = null;
      this.view7f09007f.setOnClickListener(null);
      this.view7f09007f = null;
      this.view7f090089.setOnClickListener(null);
      this.view7f090089 = null;
      this.view7f09006e.setOnClickListener(null);
      this.view7f09006e = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/RtuSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */