package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class Rd300sParamSettingActivity_ViewBinding implements Unbinder {
  private Rd300sParamSettingActivity target;
  
  private View view7f090075;
  
  private View view7f090083;
  
  private View view7f090084;
  
  private View view7f090090;
  
  private View view7f090091;
  
  private View view7f09014d;
  
  private View view7f09014e;
  
  public Rd300sParamSettingActivity_ViewBinding(Rd300sParamSettingActivity paramRd300sParamSettingActivity) {
    this(paramRd300sParamSettingActivity, paramRd300sParamSettingActivity.getWindow().getDecorView());
  }
  
  public Rd300sParamSettingActivity_ViewBinding(final Rd300sParamSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvIconWaterNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296759, "field 'tvIconWaterNoise'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296401, "field 'btnWaterNoise' and method 'onViewClicked'");
    target.btnWaterNoise = (Button)Utils.castView(view, 2131296401, "field 'btnWaterNoise'", Button.class);
    this.view7f090091 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.edtWaterNoise = (EditText)Utils.findRequiredViewAsType(paramView, 2131296486, "field 'edtWaterNoise'", EditText.class);
    target.tvIconWaterFilter = (TextView)Utils.findRequiredViewAsType(paramView, 2131296757, "field 'tvIconWaterFilter'", TextView.class);
    target.edtWaterFliter = (EditText)Utils.findRequiredViewAsType(paramView, 2131296485, "field 'edtWaterFliter'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296400, "field 'btnWaterFliter' and method 'onViewClicked'");
    target.btnWaterFliter = (Button)Utils.castView(view, 2131296400, "field 'btnWaterFliter'", Button.class);
    this.view7f090090 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconSensorHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296743, "field 'tvIconSensorHeight'", TextView.class);
    target.edtSensor = (EditText)Utils.findRequiredViewAsType(paramView, 2131296473, "field 'edtSensor'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296373, "field 'btnHeight' and method 'onViewClicked'");
    target.btnHeight = (Button)Utils.castView(view, 2131296373, "field 'btnHeight'", Button.class);
    this.view7f090075 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onViewClicked'");
    target.btnReload = (Button)Utils.castView(view, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296388, "field 'btnReset' and method 'onViewClicked'");
    target.btnReset = (Button)Utils.castView(view, 2131296388, "field 'btnReset'", Button.class);
    this.view7f090084 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconPipeType = (TextView)Utils.findRequiredViewAsType(paramView, 2131296737, "field 'tvIconPipeType'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296590, "field 'rbPipeTypeOpen' and method 'onViewClicked'");
    target.rbPipeTypeOpen = (RadioButton)Utils.castView(view, 2131296590, "field 'rbPipeTypeOpen'", RadioButton.class);
    this.view7f09014e = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296589, "field 'rbPipeTypeClose' and method 'onViewClicked'");
    target.rbPipeTypeClose = (RadioButton)Utils.castView(paramView, 2131296589, "field 'rbPipeTypeClose'", RadioButton.class);
    this.view7f09014d = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd300sParamSettingActivity_ViewBinding this$0;
          
          final Rd300sParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
  }
  
  public void unbind() {
    Rd300sParamSettingActivity rd300sParamSettingActivity = this.target;
    if (rd300sParamSettingActivity != null) {
      this.target = null;
      rd300sParamSettingActivity.toolbar = null;
      rd300sParamSettingActivity.tvIconWaterNoise = null;
      rd300sParamSettingActivity.btnWaterNoise = null;
      rd300sParamSettingActivity.edtWaterNoise = null;
      rd300sParamSettingActivity.tvIconWaterFilter = null;
      rd300sParamSettingActivity.edtWaterFliter = null;
      rd300sParamSettingActivity.btnWaterFliter = null;
      rd300sParamSettingActivity.tvIconSensorHeight = null;
      rd300sParamSettingActivity.edtSensor = null;
      rd300sParamSettingActivity.btnHeight = null;
      rd300sParamSettingActivity.btnReload = null;
      rd300sParamSettingActivity.btnReset = null;
      rd300sParamSettingActivity.tvIconPipeType = null;
      rd300sParamSettingActivity.rbPipeTypeOpen = null;
      rd300sParamSettingActivity.rbPipeTypeClose = null;
      this.view7f090091.setOnClickListener(null);
      this.view7f090091 = null;
      this.view7f090090.setOnClickListener(null);
      this.view7f090090 = null;
      this.view7f090075.setOnClickListener(null);
      this.view7f090075 = null;
      this.view7f090083.setOnClickListener(null);
      this.view7f090083 = null;
      this.view7f090084.setOnClickListener(null);
      this.view7f090084 = null;
      this.view7f09014e.setOnClickListener(null);
      this.view7f09014e = null;
      this.view7f09014d.setOnClickListener(null);
      this.view7f09014d = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd300sParamSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */