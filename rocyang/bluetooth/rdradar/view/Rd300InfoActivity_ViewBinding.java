package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class Rd300InfoActivity_ViewBinding implements Unbinder {
  private Rd300InfoActivity target;
  
  public Rd300InfoActivity_ViewBinding(Rd300InfoActivity paramRd300InfoActivity) {
    this(paramRd300InfoActivity, paramRd300InfoActivity.getWindow().getDecorView());
  }
  
  public Rd300InfoActivity_ViewBinding(Rd300InfoActivity paramRd300InfoActivity, View paramView) {
    this.target = paramRd300InfoActivity;
    paramRd300InfoActivity.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    paramRd300InfoActivity.tvMacValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296766, "field 'tvMacValue'", TextView.class);
    paramRd300InfoActivity.tvIconSignalLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296746, "field 'tvIconSignalLevel'", TextView.class);
    paramRd300InfoActivity.tvSignalLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296788, "field 'tvSignalLevelValue'", TextView.class);
    paramRd300InfoActivity.tvIconEmptyHigh = (TextView)Utils.findRequiredViewAsType(paramView, 2131296727, "field 'tvIconEmptyHigh'", TextView.class);
    paramRd300InfoActivity.tvEmptyHighValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296711, "field 'tvEmptyHighValue'", TextView.class);
    paramRd300InfoActivity.tvIconWaterLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296758, "field 'tvIconWaterLevel'", TextView.class);
    paramRd300InfoActivity.tvWaterLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296805, "field 'tvWaterLevelValue'", TextView.class);
    paramRd300InfoActivity.tvLastUpdateTime = (TextView)Utils.findRequiredViewAsType(paramView, 2131296763, "field 'tvLastUpdateTime'", TextView.class);
    paramRd300InfoActivity.tvRssiValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296775, "field 'tvRssiValue'", TextView.class);
    paramRd300InfoActivity.tvRssiIcon = (TextView)Utils.findRequiredViewAsType(paramView, 2131296774, "field 'tvRssiIcon'", TextView.class);
  }
  
  public void unbind() {
    Rd300InfoActivity rd300InfoActivity = this.target;
    if (rd300InfoActivity != null) {
      this.target = null;
      rd300InfoActivity.toolbar = null;
      rd300InfoActivity.tvMacValue = null;
      rd300InfoActivity.tvIconSignalLevel = null;
      rd300InfoActivity.tvSignalLevelValue = null;
      rd300InfoActivity.tvIconEmptyHigh = null;
      rd300InfoActivity.tvEmptyHighValue = null;
      rd300InfoActivity.tvIconWaterLevel = null;
      rd300InfoActivity.tvWaterLevelValue = null;
      rd300InfoActivity.tvLastUpdateTime = null;
      rd300InfoActivity.tvRssiValue = null;
      rd300InfoActivity.tvRssiIcon = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd300InfoActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */