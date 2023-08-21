package me.rocyang.bluetooth.rdradar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class RtuCommandInfoAdapter$LogViewHolder_ViewBinding implements Unbinder {
  private RtuCommandInfoAdapter.LogViewHolder target;
  
  public RtuCommandInfoAdapter$LogViewHolder_ViewBinding(RtuCommandInfoAdapter.LogViewHolder paramLogViewHolder, View paramView) {
    this.target = paramLogViewHolder;
    paramLogViewHolder.icon = (TextView)Utils.findRequiredViewAsType(paramView, 2131296719, "field 'icon'", TextView.class);
    paramLogViewHolder.text = (TextView)Utils.findRequiredViewAsType(paramView, 2131296797, "field 'text'", TextView.class);
    paramLogViewHolder.sendIcon = (TextView)Utils.findRequiredViewAsType(paramView, 2131296778, "field 'sendIcon'", TextView.class);
    paramLogViewHolder.sendText = (TextView)Utils.findRequiredViewAsType(paramView, 2131296779, "field 'sendText'", TextView.class);
    paramLogViewHolder.tvCmd = (TextView)Utils.findRequiredViewAsType(paramView, 2131296705, "field 'tvCmd'", TextView.class);
    paramLogViewHolder.tvSendCmd = (TextView)Utils.findRequiredViewAsType(paramView, 2131296777, "field 'tvSendCmd'", TextView.class);
    paramLogViewHolder.clSend = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296418, "field 'clSend'", ConstraintLayout.class);
    paramLogViewHolder.clReceive = (ConstraintLayout)Utils.findRequiredViewAsType(paramView, 2131296417, "field 'clReceive'", ConstraintLayout.class);
    Context context = paramView.getContext();
    Resources resources = context.getResources();
    paramLogViewHolder.colorSender = ContextCompat.getColor(context, 2131100077);
    paramLogViewHolder.colorRecver = ContextCompat.getColor(context, 2131100078);
    paramLogViewHolder.send = resources.getString(2131755122);
    paramLogViewHolder.received = resources.getString(2131755119);
    paramLogViewHolder.blueTooth = resources.getString(2131755098);
    paramLogViewHolder.worker = resources.getString(2131755134);
  }
  
  public void unbind() {
    RtuCommandInfoAdapter.LogViewHolder logViewHolder = this.target;
    if (logViewHolder != null) {
      this.target = null;
      logViewHolder.icon = null;
      logViewHolder.text = null;
      logViewHolder.sendIcon = null;
      logViewHolder.sendText = null;
      logViewHolder.tvCmd = null;
      logViewHolder.tvSendCmd = null;
      logViewHolder.clSend = null;
      logViewHolder.clReceive = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/adapter/RtuCommandInfoAdapter$LogViewHolder_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */