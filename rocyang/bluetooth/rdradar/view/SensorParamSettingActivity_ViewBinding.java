package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SensorParamSettingActivity_ViewBinding implements Unbinder {
  private SensorParamSettingActivity target;
  
  private View view7f090063;
  
  private View view7f090065;
  
  private View view7f090069;
  
  private View view7f09006b;
  
  private View view7f090070;
  
  private View view7f090071;
  
  private View view7f090072;
  
  private View view7f090073;
  
  private View view7f090080;
  
  private View view7f090083;
  
  private View view7f090084;
  
  private View view7f09008b;
  
  private View view7f09008d;
  
  private View view7f090090;
  
  private View view7f090091;
  
  public SensorParamSettingActivity_ViewBinding(SensorParamSettingActivity paramSensorParamSettingActivity) {
    this(paramSensorParamSettingActivity, paramSensorParamSettingActivity.getWindow().getDecorView());
  }
  
  public SensorParamSettingActivity_ViewBinding(final SensorParamSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvIconFlowGear = (TextView)Utils.findRequiredViewAsType(paramView, 2131296730, "field 'tvIconFlowGear'", TextView.class);
    target.tvFlowGear = (TextView)Utils.findRequiredViewAsType(paramView, 2131296714, "field 'tvFlowGear'", TextView.class);
    target.spFlowGear = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296638, "field 'spFlowGear'", Spinner.class);
    View view = Utils.findRequiredView(paramView, 2131296370, "field 'btnFlowGear' and method 'onViewClicked'");
    target.btnFlowGear = (Button)Utils.castView(view, 2131296370, "field 'btnFlowGear'", Button.class);
    this.view7f090072 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvFlowDirection = (TextView)Utils.findRequiredViewAsType(paramView, 2131296712, "field 'tvFlowDirection'", TextView.class);
    target.tvIconFlowDirection = (TextView)Utils.findRequiredViewAsType(paramView, 2131296728, "field 'tvIconFlowDirection'", TextView.class);
    target.spFlowDirection = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296637, "field 'spFlowDirection'", Spinner.class);
    view = Utils.findRequiredView(paramView, 2131296368, "field 'btnFlowDirection' and method 'onViewClicked'");
    target.btnFlowDirection = (Button)Utils.castView(view, 2131296368, "field 'btnFlowDirection'", Button.class);
    this.view7f090070 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconFlowNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296731, "field 'tvIconFlowNoise'", TextView.class);
    target.tvFlowNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296715, "field 'tvFlowNoise'", TextView.class);
    target.edtFlowNoise = (EditText)Utils.findRequiredViewAsType(paramView, 2131296462, "field 'edtFlowNoise'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296371, "field 'btnFlowNoise' and method 'onViewClicked'");
    target.btnFlowNoise = (Button)Utils.castView(view, 2131296371, "field 'btnFlowNoise'", Button.class);
    this.view7f090073 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconFlowFilter = (TextView)Utils.findRequiredViewAsType(paramView, 2131296729, "field 'tvIconFlowFilter'", TextView.class);
    target.tvFlowFilter = (TextView)Utils.findRequiredViewAsType(paramView, 2131296713, "field 'tvFlowFilter'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296369, "field 'btnFlowFilter' and method 'onViewClicked'");
    target.btnFlowFilter = (Button)Utils.castView(view, 2131296369, "field 'btnFlowFilter'", Button.class);
    this.view7f090071 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.edtFlowFilter = (EditText)Utils.findRequiredViewAsType(paramView, 2131296461, "field 'edtFlowFilter'", EditText.class);
    target.tvIconWaterNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296759, "field 'tvIconWaterNoise'", TextView.class);
    target.tvWaterNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296806, "field 'tvWaterNoise'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296401, "field 'btnWaterNoise' and method 'onViewClicked'");
    target.btnWaterNoise = (Button)Utils.castView(view, 2131296401, "field 'btnWaterNoise'", Button.class);
    this.view7f090091 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.edtWaterNoise = (EditText)Utils.findRequiredViewAsType(paramView, 2131296486, "field 'edtWaterNoise'", EditText.class);
    target.tvIconWaterFilter = (TextView)Utils.findRequiredViewAsType(paramView, 2131296757, "field 'tvIconWaterFilter'", TextView.class);
    target.tvWaterFliter = (TextView)Utils.findRequiredViewAsType(paramView, 2131296803, "field 'tvWaterFliter'", TextView.class);
    target.edtWaterFliter = (EditText)Utils.findRequiredViewAsType(paramView, 2131296485, "field 'edtWaterFliter'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296400, "field 'btnWaterFliter' and method 'onViewClicked'");
    target.btnWaterFliter = (Button)Utils.castView(view, 2131296400, "field 'btnWaterFliter'", Button.class);
    this.view7f090090 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconAngle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296721, "field 'tvIconAngle'", TextView.class);
    target.tvAngle = (TextView)Utils.findRequiredViewAsType(paramView, 2131296699, "field 'tvAngle'", TextView.class);
    target.edtAngle = (EditText)Utils.findRequiredViewAsType(paramView, 2131296458, "field 'edtAngle'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296357, "field 'btnAngle' and method 'onViewClicked'");
    target.btnAngle = (Button)Utils.castView(view, 2131296357, "field 'btnAngle'", Button.class);
    this.view7f090065 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconRiany = (TextView)Utils.findRequiredViewAsType(paramView, 2131296739, "field 'tvIconRiany'", TextView.class);
    target.tvRainy = (TextView)Utils.findRequiredViewAsType(paramView, 2131296770, "field 'tvRainy'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296384, "field 'btnRainy' and method 'onViewClicked'");
    target.btnRainy = (Button)Utils.castView(view, 2131296384, "field 'btnRainy'", Button.class);
    this.view7f090080 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.spRainy = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296641, "field 'spRainy'", Spinner.class);
    target.tvIconSleep = (TextView)Utils.findRequiredViewAsType(paramView, 2131296747, "field 'tvIconSleep'", TextView.class);
    target.tvSleep = (TextView)Utils.findRequiredViewAsType(paramView, 2131296789, "field 'tvSleep'", TextView.class);
    target.edtSleep = (EditText)Utils.findRequiredViewAsType(paramView, 2131296475, "field 'edtSleep'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296395, "field 'btnSleep' and method 'onViewClicked'");
    target.btnSleep = (Button)Utils.castView(view, 2131296395, "field 'btnSleep'", Button.class);
    this.view7f09008b = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconStillWater = (TextView)Utils.findRequiredViewAsType(paramView, 2131296749, "field 'tvIconStillWater'", TextView.class);
    target.tvStillWater = (TextView)Utils.findRequiredViewAsType(paramView, 2131296791, "field 'tvStillWater'", TextView.class);
    target.edtStillWater = (EditText)Utils.findRequiredViewAsType(paramView, 2131296477, "field 'edtStillWater'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296397, "field 'btnStillWater' and method 'onViewClicked'");
    target.btnStillWater = (Button)Utils.castView(view, 2131296397, "field 'btnStillWater'", Button.class);
    this.view7f09008d = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onViewClicked'");
    target.btnReload = (Button)Utils.castView(view, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.edtA = (EditText)Utils.findRequiredViewAsType(paramView, 2131296457, "field 'edtA'", EditText.class);
    target.tvIconA = (TextView)Utils.findRequiredViewAsType(paramView, 2131296720, "field 'tvIconA'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296355, "field 'btnA' and method 'onViewClicked'");
    target.btnA = (Button)Utils.castView(view, 2131296355, "field 'btnA'", Button.class);
    this.view7f090063 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconB = (TextView)Utils.findRequiredViewAsType(paramView, 2131296722, "field 'tvIconB'", TextView.class);
    target.edtB = (EditText)Utils.findRequiredViewAsType(paramView, 2131296459, "field 'edtB'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296361, "field 'btnB' and method 'onViewClicked'");
    target.btnB = (Button)Utils.castView(view, 2131296361, "field 'btnB'", Button.class);
    this.view7f090069 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconC = (TextView)Utils.findRequiredViewAsType(paramView, 2131296723, "field 'tvIconC'", TextView.class);
    target.edtC = (EditText)Utils.findRequiredViewAsType(paramView, 2131296460, "field 'edtC'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296363, "field 'btnC' and method 'onViewClicked'");
    target.btnC = (Button)Utils.castView(view, 2131296363, "field 'btnC'", Button.class);
    this.view7f09006b = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296388, "field 'btnReset' and method 'onViewClicked'");
    target.btnReset = (Button)Utils.castView(paramView, 2131296388, "field 'btnReset'", Button.class);
    this.view7f090084 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SensorParamSettingActivity_ViewBinding this$0;
          
          final SensorParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
  }
  
  public void unbind() {
    SensorParamSettingActivity sensorParamSettingActivity = this.target;
    if (sensorParamSettingActivity != null) {
      this.target = null;
      sensorParamSettingActivity.toolbar = null;
      sensorParamSettingActivity.tvIconFlowGear = null;
      sensorParamSettingActivity.tvFlowGear = null;
      sensorParamSettingActivity.spFlowGear = null;
      sensorParamSettingActivity.btnFlowGear = null;
      sensorParamSettingActivity.tvFlowDirection = null;
      sensorParamSettingActivity.tvIconFlowDirection = null;
      sensorParamSettingActivity.spFlowDirection = null;
      sensorParamSettingActivity.btnFlowDirection = null;
      sensorParamSettingActivity.tvIconFlowNoise = null;
      sensorParamSettingActivity.tvFlowNoise = null;
      sensorParamSettingActivity.edtFlowNoise = null;
      sensorParamSettingActivity.btnFlowNoise = null;
      sensorParamSettingActivity.tvIconFlowFilter = null;
      sensorParamSettingActivity.tvFlowFilter = null;
      sensorParamSettingActivity.btnFlowFilter = null;
      sensorParamSettingActivity.edtFlowFilter = null;
      sensorParamSettingActivity.tvIconWaterNoise = null;
      sensorParamSettingActivity.tvWaterNoise = null;
      sensorParamSettingActivity.btnWaterNoise = null;
      sensorParamSettingActivity.edtWaterNoise = null;
      sensorParamSettingActivity.tvIconWaterFilter = null;
      sensorParamSettingActivity.tvWaterFliter = null;
      sensorParamSettingActivity.edtWaterFliter = null;
      sensorParamSettingActivity.btnWaterFliter = null;
      sensorParamSettingActivity.tvIconAngle = null;
      sensorParamSettingActivity.tvAngle = null;
      sensorParamSettingActivity.edtAngle = null;
      sensorParamSettingActivity.btnAngle = null;
      sensorParamSettingActivity.tvIconRiany = null;
      sensorParamSettingActivity.tvRainy = null;
      sensorParamSettingActivity.btnRainy = null;
      sensorParamSettingActivity.spRainy = null;
      sensorParamSettingActivity.tvIconSleep = null;
      sensorParamSettingActivity.tvSleep = null;
      sensorParamSettingActivity.edtSleep = null;
      sensorParamSettingActivity.btnSleep = null;
      sensorParamSettingActivity.tvIconStillWater = null;
      sensorParamSettingActivity.tvStillWater = null;
      sensorParamSettingActivity.edtStillWater = null;
      sensorParamSettingActivity.btnStillWater = null;
      sensorParamSettingActivity.btnReload = null;
      sensorParamSettingActivity.edtA = null;
      sensorParamSettingActivity.tvIconA = null;
      sensorParamSettingActivity.btnA = null;
      sensorParamSettingActivity.tvIconB = null;
      sensorParamSettingActivity.edtB = null;
      sensorParamSettingActivity.btnB = null;
      sensorParamSettingActivity.tvIconC = null;
      sensorParamSettingActivity.edtC = null;
      sensorParamSettingActivity.btnC = null;
      sensorParamSettingActivity.btnReset = null;
      this.view7f090072.setOnClickListener(null);
      this.view7f090072 = null;
      this.view7f090070.setOnClickListener(null);
      this.view7f090070 = null;
      this.view7f090073.setOnClickListener(null);
      this.view7f090073 = null;
      this.view7f090071.setOnClickListener(null);
      this.view7f090071 = null;
      this.view7f090091.setOnClickListener(null);
      this.view7f090091 = null;
      this.view7f090090.setOnClickListener(null);
      this.view7f090090 = null;
      this.view7f090065.setOnClickListener(null);
      this.view7f090065 = null;
      this.view7f090080.setOnClickListener(null);
      this.view7f090080 = null;
      this.view7f09008b.setOnClickListener(null);
      this.view7f09008b = null;
      this.view7f09008d.setOnClickListener(null);
      this.view7f09008d = null;
      this.view7f090083.setOnClickListener(null);
      this.view7f090083 = null;
      this.view7f090063.setOnClickListener(null);
      this.view7f090063 = null;
      this.view7f090069.setOnClickListener(null);
      this.view7f090069 = null;
      this.view7f09006b.setOnClickListener(null);
      this.view7f09006b = null;
      this.view7f090084.setOnClickListener(null);
      this.view7f090084 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/SensorParamSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */