package me.rocyang.bluetooth.rdradar.view;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class DitchParamSettingActivity_ViewBinding implements Unbinder {
  private DitchParamSettingActivity target;
  
  private View view7f090083;
  
  private View view7f090086;
  
  private View view7f090149;
  
  private View view7f09014a;
  
  private View view7f09014b;
  
  public DitchParamSettingActivity_ViewBinding(DitchParamSettingActivity paramDitchParamSettingActivity) {
    this(paramDitchParamSettingActivity, paramDitchParamSettingActivity.getWindow().getDecorView());
  }
  
  public DitchParamSettingActivity_ViewBinding(final DitchParamSettingActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvIconDitchType = (TextView)Utils.findRequiredViewAsType(paramView, 2131296725, "field 'tvIconDitchType'", TextView.class);
    target.tvDitchType = (TextView)Utils.findRequiredViewAsType(paramView, 2131296709, "field 'tvDitchType'", TextView.class);
    View view = Utils.findRequiredView(paramView, 2131296585, "field 'rbDitchTypeRound' and method 'onRbDitchTypeRoundClicked'");
    target.rbDitchTypeRound = (RadioButton)Utils.castView(view, 2131296585, "field 'rbDitchTypeRound'", RadioButton.class);
    this.view7f090149 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchParamSettingActivity_ViewBinding this$0;
          
          final DitchParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbDitchTypeRoundClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296586, "field 'rbDitchTypeTrapezoid' and method 'onRbDitchTypeTrapezoidClicked'");
    target.rbDitchTypeTrapezoid = (RadioButton)Utils.castView(view, 2131296586, "field 'rbDitchTypeTrapezoid'", RadioButton.class);
    this.view7f09014a = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchParamSettingActivity_ViewBinding this$0;
          
          final DitchParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbDitchTypeTrapezoidClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296587, "field 'rbDitchTypeU' and method 'onRbDitchTypeUClicked'");
    target.rbDitchTypeU = (RadioButton)Utils.castView(view, 2131296587, "field 'rbDitchTypeU'", RadioButton.class);
    this.view7f09014b = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchParamSettingActivity_ViewBinding this$0;
          
          final DitchParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onRbDitchTypeUClicked();
          }
        });
    target.rgDitchType = (RadioGroup)Utils.findRequiredViewAsType(paramView, 2131296595, "field 'rgDitchType'", RadioGroup.class);
    target.imgDitch = (ImageView)Utils.findRequiredViewAsType(paramView, 2131296517, "field 'imgDitch'", ImageView.class);
    target.tvIconSensorHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296743, "field 'tvIconSensorHeight'", TextView.class);
    target.tvSensorHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296780, "field 'tvSensorHeight'", TextView.class);
    target.edtSensor = (EditText)Utils.findRequiredViewAsType(paramView, 2131296473, "field 'edtSensor'", EditText.class);
    target.clTypeSelect = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296420, "field 'clTypeSelect'", ConstraintLayout.class);
    target.tvSectorHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296776, "field 'tvSectorHeight'", TextView.class);
    target.tvIconSectorHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296742, "field 'tvIconSectorHeight'", TextView.class);
    target.edtSectorHeight = (EditText)Utils.findRequiredViewAsType(paramView, 2131296472, "field 'edtSectorHeight'", EditText.class);
    target.tvUBottom = (TextView)Utils.findRequiredViewAsType(paramView, 2131296799, "field 'tvUBottom'", TextView.class);
    target.tvIconUBottom = (TextView)Utils.findRequiredViewAsType(paramView, 2131296754, "field 'tvIconUBottom'", TextView.class);
    target.edtUBottom = (EditText)Utils.findRequiredViewAsType(paramView, 2131296481, "field 'edtUBottom'", EditText.class);
    target.tvUTop = (TextView)Utils.findRequiredViewAsType(paramView, 2131296801, "field 'tvUTop'", TextView.class);
    target.tvIconUTop = (TextView)Utils.findRequiredViewAsType(paramView, 2131296756, "field 'tvIconUTop'", TextView.class);
    target.edtUTop = (EditText)Utils.findRequiredViewAsType(paramView, 2131296483, "field 'edtUTop'", EditText.class);
    target.tvUHight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296800, "field 'tvUHight'", TextView.class);
    target.tvIconUHight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296755, "field 'tvIconUHight'", TextView.class);
    target.edtUHeight = (EditText)Utils.findRequiredViewAsType(paramView, 2131296482, "field 'edtUHeight'", EditText.class);
    target.clTypeU = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296422, "field 'clTypeU'", ConstraintLayout.class);
    target.tvRoundR = (TextView)Utils.findRequiredViewAsType(paramView, 2131296772, "field 'tvRoundR'", TextView.class);
    target.tvIconRoundR = (TextView)Utils.findRequiredViewAsType(paramView, 2131296740, "field 'tvIconRoundR'", TextView.class);
    target.edtRoundR = (EditText)Utils.findRequiredViewAsType(paramView, 2131296471, "field 'edtRoundR'", EditText.class);
    target.clTypeRound = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296419, "field 'clTypeRound'", ConstraintLayout.class);
    target.tvTBottom = (TextView)Utils.findRequiredViewAsType(paramView, 2131296794, "field 'tvTBottom'", TextView.class);
    target.tvIconTBottom = (TextView)Utils.findRequiredViewAsType(paramView, 2131296751, "field 'tvIconTBottom'", TextView.class);
    target.edtTBottom = (EditText)Utils.findRequiredViewAsType(paramView, 2131296478, "field 'edtTBottom'", EditText.class);
    target.tvTTop = (TextView)Utils.findRequiredViewAsType(paramView, 2131296796, "field 'tvTTop'", TextView.class);
    target.tvIconTTop = (TextView)Utils.findRequiredViewAsType(paramView, 2131296753, "field 'tvIconTTop'", TextView.class);
    target.edtTTop = (EditText)Utils.findRequiredViewAsType(paramView, 2131296480, "field 'edtTTop'", EditText.class);
    target.tvTHeight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296795, "field 'tvTHeight'", TextView.class);
    target.tvIconTHight = (TextView)Utils.findRequiredViewAsType(paramView, 2131296752, "field 'tvIconTHight'", TextView.class);
    target.edtTHeight = (EditText)Utils.findRequiredViewAsType(paramView, 2131296479, "field 'edtTHeight'", EditText.class);
    target.clTypeTrapezoid = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296421, "field 'clTypeTrapezoid'", ConstraintLayout.class);
    view = Utils.findRequiredView(paramView, 2131296390, "field 'btnSave' and method 'onBtnSaveClicked'");
    target.btnSave = (Button)Utils.castView(view, 2131296390, "field 'btnSave'", Button.class);
    this.view7f090086 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchParamSettingActivity_ViewBinding this$0;
          
          final DitchParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnSaveClicked();
          }
        });
    target.clAction = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296416, "field 'clAction'", ConstraintLayout.class);
    view = Utils.findRequiredView(paramView, 2131296387, "field 'btnReload' and method 'onBtnReloadClicked'");
    target.btnReload = (Button)Utils.castView(view, 2131296387, "field 'btnReload'", Button.class);
    this.view7f090083 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final DitchParamSettingActivity_ViewBinding this$0;
          
          final DitchParamSettingActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnReloadClicked();
          }
        });
    Resources resources = paramView.getContext().getResources();
    target.ditchR = BitmapFactory.decodeResource(resources, 2131230833);
    target.ditchT = BitmapFactory.decodeResource(resources, 2131230834);
    target.ditchU = BitmapFactory.decodeResource(resources, 2131230835);
  }
  
  public void unbind() {
    DitchParamSettingActivity ditchParamSettingActivity = this.target;
    if (ditchParamSettingActivity != null) {
      this.target = null;
      ditchParamSettingActivity.toolbar = null;
      ditchParamSettingActivity.tvIconDitchType = null;
      ditchParamSettingActivity.tvDitchType = null;
      ditchParamSettingActivity.rbDitchTypeRound = null;
      ditchParamSettingActivity.rbDitchTypeTrapezoid = null;
      ditchParamSettingActivity.rbDitchTypeU = null;
      ditchParamSettingActivity.rgDitchType = null;
      ditchParamSettingActivity.imgDitch = null;
      ditchParamSettingActivity.tvIconSensorHeight = null;
      ditchParamSettingActivity.tvSensorHeight = null;
      ditchParamSettingActivity.edtSensor = null;
      ditchParamSettingActivity.clTypeSelect = null;
      ditchParamSettingActivity.tvSectorHeight = null;
      ditchParamSettingActivity.tvIconSectorHeight = null;
      ditchParamSettingActivity.edtSectorHeight = null;
      ditchParamSettingActivity.tvUBottom = null;
      ditchParamSettingActivity.tvIconUBottom = null;
      ditchParamSettingActivity.edtUBottom = null;
      ditchParamSettingActivity.tvUTop = null;
      ditchParamSettingActivity.tvIconUTop = null;
      ditchParamSettingActivity.edtUTop = null;
      ditchParamSettingActivity.tvUHight = null;
      ditchParamSettingActivity.tvIconUHight = null;
      ditchParamSettingActivity.edtUHeight = null;
      ditchParamSettingActivity.clTypeU = null;
      ditchParamSettingActivity.tvRoundR = null;
      ditchParamSettingActivity.tvIconRoundR = null;
      ditchParamSettingActivity.edtRoundR = null;
      ditchParamSettingActivity.clTypeRound = null;
      ditchParamSettingActivity.tvTBottom = null;
      ditchParamSettingActivity.tvIconTBottom = null;
      ditchParamSettingActivity.edtTBottom = null;
      ditchParamSettingActivity.tvTTop = null;
      ditchParamSettingActivity.tvIconTTop = null;
      ditchParamSettingActivity.edtTTop = null;
      ditchParamSettingActivity.tvTHeight = null;
      ditchParamSettingActivity.tvIconTHight = null;
      ditchParamSettingActivity.edtTHeight = null;
      ditchParamSettingActivity.clTypeTrapezoid = null;
      ditchParamSettingActivity.btnSave = null;
      ditchParamSettingActivity.clAction = null;
      ditchParamSettingActivity.btnReload = null;
      this.view7f090149.setOnClickListener(null);
      this.view7f090149 = null;
      this.view7f09014a.setOnClickListener(null);
      this.view7f09014a = null;
      this.view7f09014b.setOnClickListener(null);
      this.view7f09014b = null;
      this.view7f090086.setOnClickListener(null);
      this.view7f090086 = null;
      this.view7f090083.setOnClickListener(null);
      this.view7f090083 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/DitchParamSettingActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */