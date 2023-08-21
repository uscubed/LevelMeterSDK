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

public class DitchAttributesActivity_ViewBinding implements Unbinder {
  private DitchAttributesActivity target;
  
  private View view7f090083;
  
  private View view7f090086;
  
  private View view7f09014c;
  
  private View view7f09014f;
  
  private View view7f090150;
  
  private View view7f090151;
  
  private View view7f090152;
  
  public DitchAttributesActivity_ViewBinding(DitchAttributesActivity paramDitchAttributesActivity) {
    this(paramDitchAttributesActivity, paramDitchAttributesActivity.getWindow().getDecorView());
  }
  
  public DitchAttributesActivity_ViewBinding(final DitchAttributesActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvDitchClass = (TextView)Utils.findRequiredViewAsType(paramView, 2131296708, "field 'tvDitchClass'", TextView.class);
    target.tvIconDitchClass = (TextView)Utils.findRequiredViewAsType(paramView, 2131296724, "field 'tvIconDitchClass'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296592, "field 'rbStraight' and method 'onRbStraightClicked'");
    target.rbStraight = (RadioButton)Utils.castView(view, 2131296592, "field 'rbStraight'", RadioButton.class);
    this.view7f090150 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbStraightClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296588, "field 'rbNormal' and method 'onRbNormalClicked'");
    target.rbNormal = (RadioButton)Utils.castView(view, 2131296588, "field 'rbNormal'", RadioButton.class);
    this.view7f09014c = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbNormalClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296594, "field 'rbTurbulentFlow' and method 'onRbTurbulentFlowClicked'");
    target.rbTurbulentFlow = (RadioButton)Utils.castView(view, 2131296594, "field 'rbTurbulentFlow'", RadioButton.class);
    this.view7f090152 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbTurbulentFlowClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296593, "field 'rbTilt' and method 'onRbTiltClicked'");
    target.rbTilt = (RadioButton)Utils.castView(view, 2131296593, "field 'rbTilt'", RadioButton.class);
    this.view7f090151 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbTiltClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296591, "field 'rbSplash' and method 'onRbSplashClicked'");
    target.rbSplash = (RadioButton)Utils.castView(view, 2131296591, "field 'rbSplash'", RadioButton.class);
    this.view7f09014f = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbSplashClicked();
          }
        });
    target.tvIconShore = (TextView)Utils.findRequiredViewAsType(paramView, 2131296744, "field 'tvIconShore'", TextView.class);
    target.tvShore = (TextView)Utils.findRequiredViewAsType(paramView, 2131296784, "field 'tvShore'", TextView.class);
    target.edtShoreCoeffcient = (EditText)Utils.findRequiredViewAsType(paramView, 2131296474, "field 'edtShoreCoeffcient'", EditText.class);
    view = Utils.findRequiredView(paramView, 2131296390, "field 'btnSave' and method 'onBtnSaveClicked'");
    target.btnSave = (Button)Utils.castView(view, 2131296390, "field 'btnSave'", Button.class);
    this.view7f090086 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnSaveClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onBtnReloadClicked'");
    target.btnReload = (Button)Utils.castView(paramView, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchAttributesActivity_ViewBinding this$0;
          
          final DitchAttributesActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnReloadClicked();
          }
        });
  }
  
  public void unbind() {
    DitchAttributesActivity ditchAttributesActivity = this.target;
    if (ditchAttributesActivity != null) {
      this.target = null;
      ditchAttributesActivity.toolbar = null;
      ditchAttributesActivity.tvDitchClass = null;
      ditchAttributesActivity.tvIconDitchClass = null;
      ditchAttributesActivity.rbStraight = null;
      ditchAttributesActivity.rbNormal = null;
      ditchAttributesActivity.rbTurbulentFlow = null;
      ditchAttributesActivity.rbTilt = null;
      ditchAttributesActivity.rbSplash = null;
      ditchAttributesActivity.tvIconShore = null;
      ditchAttributesActivity.tvShore = null;
      ditchAttributesActivity.edtShoreCoeffcient = null;
      ditchAttributesActivity.btnSave = null;
      ditchAttributesActivity.btnReload = null;
      this.view7f090150.setOnClickListener(null);
      this.view7f090150 = null;
      this.view7f09014c.setOnClickListener(null);
      this.view7f09014c = null;
      this.view7f090152.setOnClickListener(null);
      this.view7f090152 = null;
      this.view7f090151.setOnClickListener(null);
      this.view7f090151 = null;
      this.view7f09014f.setOnClickListener(null);
      this.view7f09014f = null;
      this.view7f090086.setOnClickListener(null);
      this.view7f090086 = null;
      this.view7f090083.setOnClickListener(null);
      this.view7f090083 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DitchAttributesActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */