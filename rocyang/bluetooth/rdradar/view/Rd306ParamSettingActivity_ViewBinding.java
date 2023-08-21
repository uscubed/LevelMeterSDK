package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class Rd306ParamSettingActivity_ViewBinding implements Unbinder {
  private Rd306ParamSettingActivity target;
  
  private View view7f090075;
  
  private View view7f090076;
  
  private View view7f090083;
  
  private View view7f090084;
  
  private View view7f090090;
  
  private View view7f090091;
  
  private View view7f09014d;
  
  private View view7f09014e;
  
  public Rd306ParamSettingActivity_ViewBinding(Rd306ParamSettingActivity paramRd306ParamSettingActivity) {
    this(paramRd306ParamSettingActivity, paramRd306ParamSettingActivity.getWindow().getDecorView());
  }
  
  public Rd306ParamSettingActivity_ViewBinding(final Rd306ParamSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvIconWaterNoise = (TextView)Utils.findRequiredViewAsType(paramView, 2131296759, "field 'tvIconWaterNoise'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296401, "field 'btnWaterNoise' and method 'onViewClicked'");
    target.btnWaterNoise = (Button)Utils.castView(view, 2131296401, "field 'btnWaterNoise'", Button.class);
    this.view7f090091 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
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
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
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
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onViewClicked'");
    target.btnReload = (Button)Utils.castView(view, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296388, "field 'btnReset' and method 'onViewClicked'");
    target.btnReset = (Button)Utils.castView(view, 2131296388, "field 'btnReset'", Button.class);
    this.view7f090084 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvIconPipeType = (TextView)Utils.findRequiredViewAsType(paramView, 2131296737, "field 'tvIconPipeType'", TextView.class);
    view = Utils.findRequiredView(paramView, 2131296590, "field 'rbPipeTypeOpen' and method 'onViewClicked'");
    target.rbPipeTypeOpen = (RadioButton)Utils.castView(view, 2131296590, "field 'rbPipeTypeOpen'", RadioButton.class);
    this.view7f09014e = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296589, "field 'rbPipeTypeClose' and method 'onViewClicked'");
    target.rbPipeTypeClose = (RadioButton)Utils.castView(view, 2131296589, "field 'rbPipeTypeClose'", RadioButton.class);
    this.view7f09014d = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tv_weir_type = (TextView)Utils.findRequiredViewAsType(paramView, 2131296807, "field 'tv_weir_type'", TextView.class);
    target.tv_icon_weir_type = (TextView)Utils.findRequiredViewAsType(paramView, 2131296760, "field 'tv_icon_weir_type'", TextView.class);
    target.sp_weir_type = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296642, "field 'sp_weir_type'", Spinner.class);
    target.btn_weir_type = (Button)Utils.findRequiredViewAsType(paramView, 2131296402, "field 'btn_weir_type'", Button.class);
    target.tv_houdao = (TextView)Utils.findRequiredViewAsType(paramView, 2131296718, "field 'tv_houdao'", TextView.class);
    target.tv_icon_houdao = (TextView)Utils.findRequiredViewAsType(paramView, 2131296733, "field 'tv_icon_houdao'", TextView.class);
    target.sp_houdao = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296639, "field 'sp_houdao'", Spinner.class);
    paramView = Utils.findRequiredView(paramView, 2131296374, "field 'btn_houdao' and method 'onViewClicked'");
    target.btn_houdao = (Button)Utils.castView(paramView, 2131296374, "field 'btn_houdao'", Button.class);
    this.view7f090076 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final Rd306ParamSettingActivity_ViewBinding this$0;
          
          final Rd306ParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
  }
  
  public void unbind() {
    Rd306ParamSettingActivity rd306ParamSettingActivity = this.target;
    if (rd306ParamSettingActivity != null) {
      this.target = null;
      rd306ParamSettingActivity.toolbar = null;
      rd306ParamSettingActivity.tvIconWaterNoise = null;
      rd306ParamSettingActivity.btnWaterNoise = null;
      rd306ParamSettingActivity.edtWaterNoise = null;
      rd306ParamSettingActivity.tvIconWaterFilter = null;
      rd306ParamSettingActivity.edtWaterFliter = null;
      rd306ParamSettingActivity.btnWaterFliter = null;
      rd306ParamSettingActivity.tvIconSensorHeight = null;
      rd306ParamSettingActivity.edtSensor = null;
      rd306ParamSettingActivity.btnHeight = null;
      rd306ParamSettingActivity.btnReload = null;
      rd306ParamSettingActivity.btnReset = null;
      rd306ParamSettingActivity.tvIconPipeType = null;
      rd306ParamSettingActivity.rbPipeTypeOpen = null;
      rd306ParamSettingActivity.rbPipeTypeClose = null;
      rd306ParamSettingActivity.tv_weir_type = null;
      rd306ParamSettingActivity.tv_icon_weir_type = null;
      rd306ParamSettingActivity.sp_weir_type = null;
      rd306ParamSettingActivity.btn_weir_type = null;
      rd306ParamSettingActivity.tv_houdao = null;
      rd306ParamSettingActivity.tv_icon_houdao = null;
      rd306ParamSettingActivity.sp_houdao = null;
      rd306ParamSettingActivity.btn_houdao = null;
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
      this.view7f090076.setOnClickListener(null);
      this.view7f090076 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/Rd306ParamSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */