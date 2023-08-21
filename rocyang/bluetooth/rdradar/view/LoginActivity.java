package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.rocyang.bluetooth.rdradar.RdDevices;

public class LoginActivity extends AppCompatActivity {
  @BindView(2131296364)
  Button btnCancel;
  
  @BindView(2131296378)
  Button btnLogin;
  
  @BindView(2131296406)
  CheckBox cbSave;
  
  @BindView(2131296469)
  EditText edtPassword;
  
  @BindView(2131296484)
  EditText edtUsername;
  
  private void loadSP() {
    SharedPreferences sharedPreferences = getSharedPreferences("savefile", 0);
    this.cbSave.setChecked(sharedPreferences.getBoolean("autosave", false));
    String str2 = sharedPreferences.getString("username", "");
    String str1 = sharedPreferences.getString("password", "");
    this.edtUsername.setText(str2);
    this.edtPassword.setText(str1);
  }
  
  @OnClick({2131296364})
  public void onBtnCancelClicked() {
    finish();
  }
  
  @OnClick({2131296378})
  public void onBtnLoginClicked() {
    String str1 = this.edtUsername.getText().toString();
    String str2 = this.edtPassword.getText().toString();
    boolean bool = "CJ001".equals(str1.trim());
    Boolean bool1 = Boolean.valueOf(true);
    if (bool && "20190920".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else if ("SZHJ".equals(str1.trim()) && "326326".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else if ("qsy2020".equals(str1.trim()) && "20200319".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else if ("SXSR".equals(str1.trim()) && "20200409".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else if ("ZGLH".equals(str1.trim()) && "20200413".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else if ("GZZLY".equals(str1.trim()) && "20200420".equals(str2.trim())) {
      RdDevices.isLogined = bool1;
    } else {
      RdDevices.isLogined = Boolean.valueOf(false);
    } 
    if (RdDevices.isLogined.booleanValue()) {
      if (this.cbSave.isChecked()) {
        SharedPreferences.Editor editor = getSharedPreferences("savefile", 0).edit();
        editor.putString("username", str1);
        editor.putString("password", str2);
        editor.putBoolean("autosave", this.cbSave.isChecked());
        editor.apply();
        editor.commit();
      } 
      this.edtPassword.setText("");
      this.edtUsername.setText("");
      startActivity(new Intent((Context)this, MainActivity.class));
    } else {
      (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755405)).setMessage(getString(2131755262)).setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            final LoginActivity this$0;
            
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              LoginActivity.this.edtPassword.setText("");
              LoginActivity.this.edtPassword.clearFocus();
              LoginActivity.this.edtUsername.setText("");
              LoginActivity.this.edtUsername.setFocusable(true);
              LoginActivity.this.cbSave.setChecked(false);
            }
          }).create().show();
    } 
  }
  
  @OnClick({2131296406})
  public void onCbSaveClicked() {
    if (!this.cbSave.isChecked()) {
      SharedPreferences.Editor editor = getSharedPreferences("savefile", 0).edit();
      editor.putString("username", "");
      editor.putString("password", "");
      editor.putBoolean("autosave", this.cbSave.isChecked());
      editor.apply();
      editor.commit();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492898);
    setSupportActionBar((Toolbar)findViewById(2131296689));
    ButterKnife.bind((Activity)this);
  }
  
  protected void onResume() {
    super.onResume();
    this.edtUsername.setFocusable(true);
    RdDevices.isLogined = Boolean.valueOf(false);
    loadSP();
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/LoginActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */