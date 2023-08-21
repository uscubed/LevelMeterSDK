package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class AttributesInfoActivity_ViewBinding implements Unbinder {
  private AttributesInfoActivity target;
  
  public AttributesInfoActivity_ViewBinding(AttributesInfoActivity paramAttributesInfoActivity) {
    this(paramAttributesInfoActivity, paramAttributesInfoActivity.getWindow().getDecorView());
  }
  
  public AttributesInfoActivity_ViewBinding(AttributesInfoActivity paramAttributesInfoActivity, View paramView) {
    this.target = paramAttributesInfoActivity;
    paramAttributesInfoActivity.tvMac = (TextView)Utils.findRequiredViewAsType(paramView, 2131296765, "field 'tvMac'", TextView.class);
    paramAttributesInfoActivity.tvIconAngle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296721, "field 'tvIconAngle'", TextView.class);
    paramAttributesInfoActivity.tvAngleValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296700, "field 'tvAngleValue'", TextView.class);
    paramAttributesInfoActivity.tvIconSignalFlow = (TextView)Utils.findRequiredViewAsType(paramView, 2131296745, "field 'tvIconSignalFlow'", TextView.class);
    paramAttributesInfoActivity.tvSignalFlowValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296786, "field 'tvSignalFlowValue'", TextView.class);
    paramAttributesInfoActivity.tvIconSignalLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296746, "field 'tvIconSignalLevel'", TextView.class);
    paramAttributesInfoActivity.tvSignalLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296788, "field 'tvSignalLevelValue'", TextView.class);
    paramAttributesInfoActivity.tvIconFlowSpeed = (TextView)Utils.findRequiredViewAsType(paramView, 2131296732, "field 'tvIconFlowSpeed'", TextView.class);
    paramAttributesInfoActivity.tvFlowSpeedValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296717, "field 'tvFlowSpeedValue'", TextView.class);
    paramAttributesInfoActivity.tvIconEmptyHigh = (TextView)Utils.findRequiredViewAsType(paramView, 2131296727, "field 'tvIconEmptyHigh'", TextView.class);
    paramAttributesInfoActivity.tvEmptyHighValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296711, "field 'tvEmptyHighValue'", TextView.class);
    paramAttributesInfoActivity.tvIconWaterLevel = (TextView)Utils.findRequiredViewAsType(paramView, 2131296758, "field 'tvIconWaterLevel'", TextView.class);
    paramAttributesInfoActivity.tvWaterLevelValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296805, "field 'tvWaterLevelValue'", TextView.class);
    paramAttributesInfoActivity.tvIconInstantaneousFlow = (TextView)Utils.findRequiredViewAsType(paramView, 2131296734, "field 'tvIconInstantaneousFlow'", TextView.class);
    paramAttributesInfoActivity.tvInstantaneousFlowValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296762, "field 'tvInstantaneousFlowValue'", TextView.class);
    paramAttributesInfoActivity.tvIconSumFlow = (TextView)Utils.findRequiredViewAsType(paramView, 2131296750, "field 'tvIconSumFlow'", TextView.class);
    paramAttributesInfoActivity.tvSumFlowValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296793, "field 'tvSumFlowValue'", TextView.class);
    paramAttributesInfoActivity.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    paramAttributesInfoActivity.tvMacValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296766, "field 'tvMacValue'", TextView.class);
    paramAttributesInfoActivity.tvLastUpdateTime = (TextView)Utils.findRequiredViewAsType(paramView, 2131296763, "field 'tvLastUpdateTime'", TextView.class);
    paramAttributesInfoActivity.tvRssiValue = (TextView)Utils.findRequiredViewAsType(paramView, 2131296775, "field 'tvRssiValue'", TextView.class);
    paramAttributesInfoActivity.tvRssiIcon = (TextView)Utils.findRequiredViewAsType(paramView, 2131296774, "field 'tvRssiIcon'", TextView.class);
  }
  
  public void unbind() {
    AttributesInfoActivity attributesInfoActivity = this.target;
    if (attributesInfoActivity != null) {
      this.target = null;
      attributesInfoActivity.tvMac = null;
      attributesInfoActivity.tvIconAngle = null;
      attributesInfoActivity.tvAngleValue = null;
      attributesInfoActivity.tvIconSignalFlow = null;
      attributesInfoActivity.tvSignalFlowValue = null;
      attributesInfoActivity.tvIconSignalLevel = null;
      attributesInfoActivity.tvSignalLevelValue = null;
      attributesInfoActivity.tvIconFlowSpeed = null;
      attributesInfoActivity.tvFlowSpeedValue = null;
      attributesInfoActivity.tvIconEmptyHigh = null;
      attributesInfoActivity.tvEmptyHighValue = null;
      attributesInfoActivity.tvIconWaterLevel = null;
      attributesInfoActivity.tvWaterLevelValue = null;
      attributesInfoActivity.tvIconInstantaneousFlow = null;
      attributesInfoActivity.tvInstantaneousFlowValue = null;
      attributesInfoActivity.tvIconSumFlow = null;
      attributesInfoActivity.tvSumFlowValue = null;
      attributesInfoActivity.toolbar = null;
      attributesInfoActivity.tvMacValue = null;
      attributesInfoActivity.tvLastUpdateTime = null;
      attributesInfoActivity.tvRssiValue = null;
      attributesInfoActivity.tvRssiIcon = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/AttributesInfoActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */