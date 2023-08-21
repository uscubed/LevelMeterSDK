package me.rocyang.bluetooth.rdradar.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AdapterSettingActivity extends AppCompatActivity {
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492892);
    setSupportActionBar((Toolbar)findViewById(2131296689));
    ((FloatingActionButton)findViewById(2131296493)).setOnClickListener(new View.OnClickListener() {
          final AdapterSettingActivity this$0;
          
          public void onClick(View param1View) {
            Snackbar.make(param1View, "Replace with your own action", 0).setAction("Action", null).show();
          }
        });
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/AdapterSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */