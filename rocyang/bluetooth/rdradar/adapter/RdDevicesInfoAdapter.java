package me.rocyang.bluetooth.rdradar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.clj.fastble.data.BleDevice;
import java.util.List;
import me.rocyang.bluetooth.rdradar.RdDevices;

public class RdDevicesInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
  private static final String TAG = "RdDevicesInfoAdapter";
  
  public static final int VIEW_TYPE_EMPTY = 0;
  
  public static final int VIEW_TYPE_ITEM = 1;
  
  private List<BleDevice> mDevices;
  
  private OnItemClickListener mOnItemClickListener = null;
  
  public RdDevicesInfoAdapter(List<BleDevice> paramList) {
    this.mDevices = paramList;
  }
  
  public BleDevice getDevice(int paramInt) {
    BleDevice bleDevice;
    if (this.mDevices.size() <= paramInt) {
      bleDevice = null;
    } else {
      bleDevice = this.mDevices.get(paramInt);
    } 
    return bleDevice;
  }
  
  public int getItemCount() {
    int i;
    if (this.mDevices.size() == 0) {
      i = 1;
    } else {
      i = this.mDevices.size();
    } 
    return i;
  }
  
  public int getItemViewType(int paramInt) {
    if (this.mDevices.size() == 0) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    return paramInt;
  }
  
  public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    String str;
    if (paramViewHolder instanceof DeviceViewHolder) {
      BleDevice bleDevice = this.mDevices.get(paramInt);
      DeviceViewHolder deviceViewHolder = (DeviceViewHolder)paramViewHolder;
      deviceViewHolder.itemView.setTag(Integer.valueOf(paramInt));
      TextView textView = deviceViewHolder.tvDeviceName;
      if (bleDevice.getName() == null || bleDevice.getName().trim().isEmpty()) {
        str = "无名称";
      } else {
        str = bleDevice.getName().trim();
      } 
      textView.setText(str);
      deviceViewHolder.tvDevice.setTypeface(RdDevices.iconfont);
      deviceViewHolder.tvIconRssi.setTypeface(RdDevices.iconfont);
      deviceViewHolder.tvDevice.setTextColor(deviceViewHolder.blueTooth);
      str = bleDevice.getMac();
      deviceViewHolder.tvMac.setText(str);
      paramInt = bleDevice.getRssi();
      deviceViewHolder.tvRssi.setText(String.valueOf(paramInt));
      paramInt = bleDevice.getRssi();
      if (paramInt > -80) {
        deviceViewHolder.tvIconRssi.setText(deviceViewHolder.signal_4);
      } else if (paramInt > -90) {
        deviceViewHolder.tvIconRssi.setText(deviceViewHolder.signal_3);
      } else if (paramInt > -100) {
        deviceViewHolder.tvIconRssi.setText(deviceViewHolder.signal_2);
      } else {
        deviceViewHolder.tvIconRssi.setText(deviceViewHolder.signal_1);
      } 
    } else {
      ((TextView)((RecyclerView.ViewHolder)str).itemView.findViewById(2131296726)).setTypeface(RdDevices.iconfont);
    } 
  }
  
  public void onClick(View paramView) {
    OnItemClickListener onItemClickListener = this.mOnItemClickListener;
    if (onItemClickListener != null)
      onItemClickListener.onItemClick(paramView, ((Integer)paramView.getTag()).intValue()); 
  }
  
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
    if (paramInt == 0)
      return new RecyclerView.ViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492909, paramViewGroup, false)) {
          final RdDevicesInfoAdapter this$0;
        }; 
    View view = LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492910, paramViewGroup, false);
    view.setOnClickListener(this);
    return new DeviceViewHolder(view);
  }
  
  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
    this.mOnItemClickListener = paramOnItemClickListener;
  }
  
  static class DeviceViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ViewHolder";
    
    @BindColor(2131099694)
    int blueTooth;
    
    View deviceView;
    
    @BindString(2131755126)
    String signal_1;
    
    @BindString(2131755127)
    String signal_2;
    
    @BindString(2131755128)
    String signal_3;
    
    @BindString(2131755129)
    String signal_4;
    
    @BindString(2131755234)
    String strMac;
    
    @BindString(2131755238)
    String strRssi;
    
    @BindView(2131296706)
    TextView tvDevice;
    
    @BindView(2131296707)
    TextView tvDeviceName;
    
    @BindView(2131296741)
    TextView tvIconRssi;
    
    @BindView(2131296765)
    TextView tvMac;
    
    @BindView(2131296773)
    TextView tvRssi;
    
    public DeviceViewHolder(View param1View) {
      super(param1View);
      this.deviceView = param1View;
      ButterKnife.bind(this, param1View);
    }
  }
  
  public static interface OnItemClickListener {
    void onItemClick(View param1View, int param1Int);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/adapter/RdDevicesInfoAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */