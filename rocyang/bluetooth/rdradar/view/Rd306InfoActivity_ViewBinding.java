package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class Rd306InfoActivity_ViewBinding implements Unbinder {
  private Rd306InfoActivity target;
  
  public Rd306InfoActivity_ViewBinding(Rd306InfoActivity paramRd306InfoActivity) {
    this(paramRd306InfoActivity, paramRd306InfoActivity.getWindow().getDecorView());
  }
  
  public Rd306InfoActivity_ViewBinding(Rd306InfoActivity paramRd306InfoActivity, View paramView) {
    this.target = paramRd306InfoActivity;
    paramRd306InfoActivity.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    paramRd306InfoActivity.tvMacValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296766, "field 'tvMacValue'", TextView.class);
    paramRd306InfoActivity.tvIconSignalLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296746, "field 'tvIconSignalLevel'", TextView.class);
    paramRd306InfoActivity.tvSignalLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296788, "field 'tvSignalLevelValue'", TextView.class);
    paramRd306InfoActivity.tvIconEmptyHigh = (TextView)Utils.findRequiredViewAsType(paramView, 2131296727, "field 'tvIconEmptyHigh'", TextView.class);
    paramRd306InfoActivity.tvEmptyHighValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296711, "field 'tvEmptyHighValue'", TextView.class);
    paramRd306InfoActivity.tvIconWaterLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296758, "field 'tvIconWaterLevel'", TextView.class);
    paramRd306InfoActivity.tvWaterLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296805, "field 'tvWaterLevelValue'", TextView.class);
    paramRd306InfoActivity.tvIconInstantaneousFlow = (TextView)Utils.findRequiredViewAsType(paramView, 2131296734, "field 'tvIconInstantaneousFlow'", TextView.class);
    paramRd306InfoActivity.tvInstantaneousFlowValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296762, "field 'tvInstantaneousFlowValue'", TextView.class);
    paramRd306InfoActivity.tvIconSumFlow = (TextView)Utils.findRequiredViewAsType(paramView, 2131296750, "field 'tvIconSumFlow'", TextView.class);
    paramRd306InfoActivity.tvSumFlowValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296793, "field 'tvSumFlowValue'", TextView.class);
    paramRd306InfoActivity.tvLastUpdateTime = (TextView)Utils.findRequiredViewAsType(paramView, 2131296763, "field 'tvLastUpdateTime'", TextView.class);
    paramRd306InfoActivity.tvRssiValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296775, "field 'tvRssiValue'", TextView.class);
    paramRd306InfoActivity.tvRssiIcon = (TextView)Utils.findRequiredViewAsType(paramView, 2131296774, "field 'tvRssiIcon'", TextView.class);
  }
  
  public void unbind() {
    Rd306InfoActivity rd306InfoActivity = this.target;
    if (rd306InfoActivity != null) {
      this.target = null;
      rd306InfoActivity.toolbar = null;
      rd306InfoActivity.tvMacValue = null;
      rd306InfoActivity.tvIconSignalLevel = null;
      rd306InfoActivity.tvSignalLevelValue = null;
      rd306InfoActivity.tvIconEmptyHigh = null;
      rd306InfoActivity.tvEmptyHighValue = null;
      rd306InfoActivity.tvIconWaterLevel = null;
      rd306InfoActivity.tvWaterLevelValue = null;
      rd306InfoActivity.tvIconInstantaneousFlow = null;
      rd306InfoActivity.tvInstantaneousFlowValue = null;
      rd306InfoActivity.tvIconSumFlow = null;
      rd306InfoActivity.tvSumFlowValue = null;
      rd306InfoActivity.tvLastUpdateTime = null;
      rd306InfoActivity.tvRssiValue = null;
      rd306InfoActivity.tvRssiIcon = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd306InfoActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */