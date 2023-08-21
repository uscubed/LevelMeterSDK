package me.rocyang.bluetooth.rdradar.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.entity.RtuLog;

public class RtuCommandInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnLongClickListener {
  private static final String TAG = "RtuCommandInfoAdapter";
  
  private OnItemLongClickListener itemLongClickListener;
  
  private List<RtuLog> rtuLogs;
  
  public RtuCommandInfoAdapter(List<RtuLog> paramList) {
    this.rtuLogs = paramList;
  }
  
  public RtuLog getItem(int paramInt) {
    return (paramInt < 0 || paramInt >= this.rtuLogs.size()) ? null : this.rtuLogs.get(paramInt);
  }
  
  public int getItemCount() {
    return this.rtuLogs.size();
  }
  
  public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    if (paramViewHolder instanceof LogViewHolder) {
      paramViewHolder = paramViewHolder;
      ((LogViewHolder)paramViewHolder).itemView.setTag(Integer.valueOf(paramInt));
      RtuLog rtuLog = this.rtuLogs.get(paramInt);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onBindViewHolder: ");
      stringBuilder.append(rtuLog.toString());
      Log.i("RtuCommandInfoAdapter", stringBuilder.toString());
      ((LogViewHolder)paramViewHolder).icon.setTypeface(RdDevices.iconfont);
      ((LogViewHolder)paramViewHolder).sendIcon.setTypeface(RdDevices.iconfont);
      if (RtuLog.LogType.SEND_CMD.equals(rtuLog.getLogType())) {
        ((LogViewHolder)paramViewHolder).clReceive.setVisibility(8);
        ((LogViewHolder)paramViewHolder).clSend.setVisibility(0);
        ((LogViewHolder)paramViewHolder).sendIcon.setText(((LogViewHolder)paramViewHolder).worker);
        ((LogViewHolder)paramViewHolder).sendIcon.setTextColor(((LogViewHolder)paramViewHolder).colorSender);
        ((LogViewHolder)paramViewHolder).sendText.setText(rtuLog.getContent());
        ((LogViewHolder)paramViewHolder).sendText.setMaxWidth(RdDevices.ScreenWidth * 7 / 10);
        ((LogViewHolder)paramViewHolder).tvSendCmd.setText(rtuLog.getCmd());
      } else {
        ((LogViewHolder)paramViewHolder).clSend.setVisibility(8);
        ((LogViewHolder)paramViewHolder).clReceive.setVisibility(0);
        ((LogViewHolder)paramViewHolder).icon.setText(((LogViewHolder)paramViewHolder).blueTooth);
        ((LogViewHolder)paramViewHolder).icon.setTextColor(((LogViewHolder)paramViewHolder).colorRecver);
        ((LogViewHolder)paramViewHolder).text.setMaxWidth(RdDevices.ScreenWidth * 6 / 10);
        ((LogViewHolder)paramViewHolder).text.setText(rtuLog.getContent());
        ((LogViewHolder)paramViewHolder).tvCmd.setText(rtuLog.getCmd());
      } 
    } 
  }
  
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
    return new LogViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492911, paramViewGroup, false));
  }
  
  public boolean onLongClick(View paramView) {
    int i = ((Integer)paramView.getTag()).intValue();
    return this.itemLongClickListener.onItemLongClick(paramView, i);
  }
  
  public void setItemLongClickListener(OnItemLongClickListener paramOnItemLongClickListener) {
    this.itemLongClickListener = paramOnItemLongClickListener;
  }
  
  static class LogViewHolder extends RecyclerView.ViewHolder {
    @BindString(2131755098)
    String blueTooth;
    
    @BindView(2131296417)
    ConstraintLayout clReceive;
    
    @BindView(2131296418)
    ConstraintLayout clSend;
    
    @BindColor(2131100078)
    int colorRecver;
    
    @BindColor(2131100077)
    int colorSender;
    
    @BindView(2131296719)
    TextView icon;
    
    @BindString(2131755119)
    String received;
    
    @BindString(2131755122)
    String send;
    
    @BindView(2131296778)
    TextView sendIcon;
    
    @BindView(2131296779)
    TextView sendText;
    
    @BindView(2131296797)
    TextView text;
    
    @BindView(2131296705)
    TextView tvCmd;
    
    @BindView(2131296777)
    TextView tvSendCmd;
    
    View view;
    
    @BindString(2131755134)
    String worker;
    
    public LogViewHolder(View param1View) {
      super(param1View);
      this.view = param1View;
      ButterKnife.bind(this, param1View);
    }
  }
  
  public static interface OnItemLongClickListener {
    boolean onItemLongClick(View param1View, int param1Int);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/adapter/RtuCommandInfoAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */