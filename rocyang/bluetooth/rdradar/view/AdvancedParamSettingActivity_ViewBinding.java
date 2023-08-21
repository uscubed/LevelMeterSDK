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

public class AdvancedParamSettingActivity_ViewBinding implements Unbinder {
  private AdvancedParamSettingActivity target;
  
  private View view7f09007b;
  
  private View view7f09007c;
  
  private View view7f090082;
  
  private View view7f090083;
  
  private View view7f090084;
  
  private View view7f09008c;
  
  public AdvancedParamSettingActivity_ViewBinding(AdvancedParamSettingActivity paramAdvancedParamSettingActivity) {
    this(paramAdvancedParamSettingActivity, paramAdvancedParamSettingActivity.getWindow().getDecorView());
  }
  
  public AdvancedParamSettingActivity_ViewBinding(final AdvancedParamSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvIconLowSpeed = (TextView)Utils.findRequiredViewAsType(paramView, 2131296735, "field 'tvIconLowSpeed'", TextView.class);
    target.tvLowSpeed = (TextView)Utils.findRequiredViewAsType(paramView, 2131296764, "field 'tvLowSpeed'", TextView.class);
    target.spLowSpeed = (Spinner)Utils.findRequiredViewAsType(paramView, 2131296640, "field 'spLowSpeed'", Spinner.class);
    View view = Utils.findRequiredView(paramView, 2131296379, "field 'btnLowSpeed' and method 'onViewClicked'");
    target.btnLowSpeed = (Button)Utils.castView(view, 2131296379, "field 'btnLowSpeed'", Button.class);
    this.view7f09007b = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvMeasureTimes = (TextView)Utils.findRequiredViewAsType(paramView, 2131296767, "field 'tvMeasureTimes'", TextView.class);
    target.tvIconMeasureTimes = (TextView)Utils.findRequiredViewAsType(paramView, 2131296736, "field 'tvIconMeasureTimes'", TextView.class);
    target.edtMeasureTimes = (EditText)Utils.findRequiredViewAsType(paramView, 2131296468, "field 'edtMeasureTimes'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296380, "field 'btnMeasureTimes' and method 'onViewClicked'");
    target.btnMeasureTimes = (Button)Utils.castView(view, 2131296380, "field 'btnMeasureTimes'", Button.class);
    this.view7f09007c = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvStable = (TextView)Utils.findRequiredViewAsType(paramView, 2131296790, "field 'tvStable'", TextView.class);
    target.tvIconStable = (TextView)Utils.findRequiredViewAsType(paramView, 2131296748, "field 'tvIconStable'", TextView.class);
    target.edtStable = (EditText)Utils.findRequiredViewAsType(paramView, 2131296476, "field 'edtStable'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296396, "field 'btnStable' and method 'onViewClicked'");
    target.btnStable = (Button)Utils.castView(view, 2131296396, "field 'btnStable'", Button.class);
    this.view7f09008c = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    target.tvRelative = (TextView)Utils.findRequiredViewAsType(paramView, 2131296771, "field 'tvRelative'", TextView.class);
    target.tvIconRelative = (TextView)Utils.findRequiredViewAsType(paramView, 2131296738, "field 'tvIconRelative'", TextView.class);
    target.edtRelative = (EditText)Utils.findRequiredViewAsType(paramView, 2131296470, "field 'edtRelative'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296386, "field 'btnRelative' and method 'onViewClicked'");
    target.btnRelative = (Button)Utils.castView(view, 2131296386, "field 'btnRelative'", Button.class);
    this.view7f090082 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    view = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onViewClicked'");
    target.btnReload = (Button)Utils.castView(view, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296388, "field 'btnReset' and method 'onViewClicked'");
    target.btnReset = (Button)Utils.castView(paramView, 2131296388, "field 'btnReset'", Button.class);
    this.view7f090084 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final AdvancedParamSettingActivity_ViewBinding this$0;
          
          final AdvancedParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked(param1View);
          }
        });
  }
  
  public void unbind() {
    AdvancedParamSettingActivity advancedParamSettingActivity = this.target;
    if (advancedParamSettingActivity != null) {
      this.target = null;
      advancedParamSettingActivity.toolbar = null;
      advancedParamSettingActivity.tvIconLowSpeed = null;
      advancedParamSettingActivity.tvLowSpeed = null;
      advancedParamSettingActivity.spLowSpeed = null;
      advancedParamSettingActivity.btnLowSpeed = null;
      advancedParamSettingActivity.tvMeasureTimes = null;
      advancedParamSettingActivity.tvIconMeasureTimes = null;
      advancedParamSettingActivity.edtMeasureTimes = null;
      advancedParamSettingActivity.btnMeasureTimes = null;
      advancedParamSettingActivity.tvStable = null;
      advancedParamSettingActivity.tvIconStable = null;
      advancedParamSettingActivity.edtStable = null;
      advancedParamSettingActivity.btnStable = null;
      advancedParamSettingActivity.tvRelative = null;
      advancedParamSettingActivity.tvIconRelative = null;
      advancedParamSettingActivity.edtRelative = null;
      advancedParamSettingActivity.btnRelative = null;
      advancedParamSettingActivity.btnReload = null;
      advancedParamSettingActivity.btnReset = null;
      this.view7f09007b.setOnClickListener(null);
      this.view7f09007b = null;
      this.view7f09007c.setOnClickListener(null);
      this.view7f09007c = null;
      this.view7f09008c.setOnClickListener(null);
      this.view7f09008c = null;
      this.view7f090082.setOnClickListener(null);
      this.view7f090082 = null;
      this.view7f090083.setOnClickListener(null);
      this.view7f090083 = null;
      this.view7f090084.setOnClickListener(null);
      this.view7f090084 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/AdvancedParamSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */