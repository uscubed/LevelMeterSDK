package me.rocyang.bluetooth.rdradar.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;
  
  private View view7f090198;
  
  public MainActivity_ViewBinding(MainActivity paramMainActivity) {
    this(paramMainActivity, paramMainActivity.getWindow().getDecorView());
  }
  
  public MainActivity_ViewBinding(final MainActivity target, View paramView) {
    this.target = target;
    target.toolbar = (Toolbar)Utils.findRequiredViewAsType(paramView, 2131296689, "field 'toolbar'", Toolbar.class);
    target.tvBluetooth = (TextView)Utils.findRequiredViewAsType(paramView, 2131296702, "field 'tvBluetooth'", TextView.class);
    target.rvDevices = (RecyclerView)Utils.findRequiredViewAsType(paramView, 2131296601, "field 'rvDevices'", RecyclerView.class);
    target.tvVersion = (TextView)Utils.findRequiredViewAsType(paramView, 2131296802, "field 'tvVersion'", TextView.class);
    target.cbAutoName = (CheckBox)Utils.findRequiredViewAsType(paramView, 2131296405, "field 'cbAutoName'", CheckBox.class);
    View view = Utils.findRequiredView(paramView, 2131296664, "field 'tbScan' and method 'onViewClicked'");
    target.tbScan = (ToggleButton)Utils.castView(view, 2131296664, "field 'tbScan'", ToggleButton.class);
    this.view7f090198 = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final MainActivity_ViewBinding this$0;
          
          final MainActivity val$target;
          
          public void doClick(View param1View) {
            target.onViewClicked();
          }
        });
    Context context = paramView.getContext();
    Resources resources = context.getResources();
    target.colorAccent = ContextCompat.getColor(context, 2131099693);
    target.textColor = ContextCompat.getColor(context, 2131100084);
    target.strNotSupport = resources.getString(2131755236);
    target.strBleStatus = resources.getString(2131755198);
    target.strBleEnabled = resources.getString(2131755340);
    target.strBleDisabled = resources.getString(2131755339);
  }
  
  public void unbind() {
    MainActivity mainActivity = this.target;
    if (mainActivity != null) {
      this.target = null;
      mainActivity.toolbar = null;
      mainActivity.tvBluetooth = null;
      mainActivity.rvDevices = null;
      mainActivity.tvVersion = null;
      mainActivity.cbAutoName = null;
      mainActivity.tbScan = null;
      this.view7f090198.setOnClickListener(null);
      this.view7f090198 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/MainActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */