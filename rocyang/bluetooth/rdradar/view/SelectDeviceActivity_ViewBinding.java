package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class SelectDeviceActivity_ViewBinding implements Unbinder {
  private SelectDeviceActivity target;
  
  private View view7f090060;
  
  private View view7f090061;
  
  private View view7f090062;
  
  private View view7f09006c;
  
  private View view7f090102;
  
  private View view7f090103;
  
  private View view7f090104;
  
  public SelectDeviceActivity_ViewBinding(SelectDeviceActivity paramSelectDeviceActivity) {
    this(paramSelectDeviceActivity, paramSelectDeviceActivity.getWindow().getDecorView());
  }
  
  public SelectDeviceActivity_ViewBinding(final SelectDeviceActivity target, View paramView) {
    this.target = target;
    View view = Utils.findRequiredView(paramView, 2131296354, "field 'btn600' and method 'onBtn600Clicked'");
    target.btn600 = (Button)Utils.castView(view, 2131296354, "field 'btn600'", Button.class);
    this.view7f090062 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn600Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296352, "field 'btn300' and method 'onBtn300Clicked'");
    target.btn300 = (Button)Utils.castView(view, 2131296352, "field 'btn300'", Button.class);
    this.view7f090060 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn300Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296364, "field 'btnCancel' and method 'onBtnCancelClicked'");
    target.btnCancel = (Button)Utils.castView(view, 2131296364, "field 'btnCancel'", Button.class);
    this.view7f09006c = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnCancelClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296514, "field 'img300' and method 'onBtn300Clicked'");
    target.img300 = (ImageView)Utils.castView(view, 2131296514, "field 'img300'", ImageView.class);
    this.view7f090102 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn300Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296516, "field 'img600' and method 'onBtn600Clicked'");
    target.img600 = (ImageView)Utils.castView(view, 2131296516, "field 'img600'", ImageView.class);
    this.view7f090104 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn600Clicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296515, "field 'img306' and method 'onBtn306Clicked'");
    target.img306 = (ImageView)Utils.castView(view, 2131296515, "field 'img306'", ImageView.class);
    this.view7f090103 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn306Clicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296353, "field 'btn306' and method 'onBtn306Clicked'");
    target.btn306 = (Button)Utils.castView(paramView, 2131296353, "field 'btn306'", Button.class);
    this.view7f090061 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final SelectDeviceActivity_ViewBinding this$0;
          
          final SelectDeviceActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtn306Clicked();
          }
        });
  }
  
  public void unbind() {
    SelectDeviceActivity selectDeviceActivity = this.target;
    if (selectDeviceActivity != null) {
      this.target = null;
      selectDeviceActivity.btn600 = null;
      selectDeviceActivity.btn300 = null;
      selectDeviceActivity.btnCancel = null;
      selectDeviceActivity.img300 = null;
      selectDeviceActivity.img600 = null;
      selectDeviceActivity.img306 = null;
      selectDeviceActivity.btn306 = null;
      this.view7f090062.setOnClickListener(null);
      this.view7f090062 = null;
      this.view7f090060.setOnClickListener(null);
      this.view7f090060 = null;
      this.view7f09006c.setOnClickListener(null);
      this.view7f09006c = null;
      this.view7f090102.setOnClickListener(null);
      this.view7f090102 = null;
      this.view7f090104.setOnClickListener(null);
      this.view7f090104 = null;
      this.view7f090103.setOnClickListener(null);
      this.view7f090103 = null;
      this.view7f090061.setOnClickListener(null);
      this.view7f090061 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/SelectDeviceActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */