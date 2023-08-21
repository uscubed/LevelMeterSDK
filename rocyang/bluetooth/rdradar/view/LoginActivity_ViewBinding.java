package me.rocyang.bluetooth.rdradar.view;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;
  
  private View view7f09006c;
  
  private View view7f09007a;
  
  private View view7f090096;
  
  public LoginActivity_ViewBinding(LoginActivity paramLoginActivity) {
    this(paramLoginActivity, paramLoginActivity.getWindow().getDecorView());
  }
  
  public LoginActivity_ViewBinding(final LoginActivity target, View paramView) {
    this.target = target;
    target.edtUsername = (EditText)Utils.findRequiredViewAsType(paramView, 2131296484, "field 'edtUsername'", EditText.class);
    target.edtPassword = (EditText)Utils.findRequiredViewAsType(paramView, 2131296469, "field 'edtPassword'", EditText.class);
    View view = Utils.findRequiredView(paramView, 2131296364, "field 'btnCancel' and method 'onBtnCancelClicked'");
    target.btnCancel = (Button)Utils.castView(view, 2131296364, "field 'btnCancel'", Button.class);
    this.view7f09006c = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final LoginActivity_ViewBinding this$0;
          
          final LoginActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnCancelClicked();
          }
        });
    view = Utils.findRequiredView(paramView, 2131296378, "field 'btnLogin' and method 'onBtnLoginClicked'");
    target.btnLogin = (Button)Utils.castView(view, 2131296378, "field 'btnLogin'", Button.class);
    this.view7f09007a = view;
    view.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final LoginActivity_ViewBinding this$0;
          
          final LoginActivity val$target;
          
          public void doClick(View param1View) {
            target.onBtnLoginClicked();
          }
        });
    paramView = Utils.findRequiredView(paramView, 2131296406, "field 'cbSave' and method 'onCbSaveClicked'");
    target.cbSave = (CheckBox)Utils.castView(paramView, 2131296406, "field 'cbSave'", CheckBox.class);
    this.view7f090096 = paramView;
    paramView.setOnClickListener((View.OnClickListener)new DebouncingOnClickListener() {
          final LoginActivity_ViewBinding this$0;
          
          final LoginActivity val$target;
          
          public void doClick(View param1View) {
            target.onCbSaveClicked();
          }
        });
  }
  
  public void unbind() {
    LoginActivity loginActivity = this.target;
    if (loginActivity != null) {
      this.target = null;
      loginActivity.edtUsername = null;
      loginActivity.edtPassword = null;
      loginActivity.btnCancel = null;
      loginActivity.btnLogin = null;
      loginActivity.cbSave = null;
      this.view7f09006c.setOnClickListener(null);
      this.view7f09006c = null;
      this.view7f09007a.setOnClickListener(null);
      this.view7f09007a = null;
      this.view7f090096.setOnClickListener(null);
      this.view7f090096 = null;
      return;
    } 
    throw new IllegalStateException("Bindings already cleared.");
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/LoginActivity_ViewBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */