package me.rocyang.bluetooth.rdradar.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.exception.BleException;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.rocyang.bluetooth.rdradar.RdDevices;
import me.rocyang.bluetooth.rdradar.adapter.RtuCommandInfoAdapter;
import me.rocyang.bluetooth.rdradar.contact.ControlCommand;
import me.rocyang.bluetooth.rdradar.contact.RTUCmdType;
import me.rocyang.bluetooth.rdradar.contact.RTUCommand;
import me.rocyang.bluetooth.rdradar.entity.RWData;
import me.rocyang.bluetooth.rdradar.entity.RtuLog;
import me.rocyang.bluetooth.rdradar.entity.ValidRule;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestAsyncTask;
import me.rocyang.bluetooth.rdradar.thread.ProtocolTestHandleListener;
import me.rocyang.bluetooth.rdradar.thread.RWHandleListener;
import me.rocyang.bluetooth.rdradar.thread.TextReaderAsyncTask;
import me.rocyang.bluetooth.rdradar.utils.HexUtil;
import me.rocyang.bluetooth.rdradar.utils.ToastUtil;

public class RtuSettingActivity extends BaseActivity {
  private static final String TAG = "RtuSettingActivity";
  
  public View.OnClickListener actionMenuListener = new View.OnClickListener() {
      final RtuSettingActivity this$0;
      
      public void onClick(View param1View) {
        // Byte code:
        //   0: aload_0
        //   1: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   4: ldc 2131755272
        //   6: invokevirtual getString : (I)Ljava/lang/String;
        //   9: astore_2
        //   10: new me/rocyang/bluetooth/rdradar/entity/ValidRule
        //   13: dup
        //   14: invokespecial <init> : ()V
        //   17: astore_3
        //   18: aload_1
        //   19: invokevirtual getId : ()I
        //   22: istore #4
        //   24: ldc ''
        //   26: astore #5
        //   28: iconst_1
        //   29: invokestatic valueOf : (Z)Ljava/lang/Boolean;
        //   32: astore #6
        //   34: iload #4
        //   36: lookupswitch default -> 160, 2131296358 -> 1108, 2131296359 -> 1039, 2131296360 -> 970, 2131296362 -> 882, 2131296364 -> 160, 2131296365 -> 797, 2131296367 -> 722, 2131296375 -> 647, 2131296376 -> 572, 2131296377 -> 497, 2131296381 -> 417, 2131296382 -> 337, 2131296391 -> 256, 2131296392 -> 175
        //   160: aconst_null
        //   161: astore #7
        //   163: ldc ''
        //   165: astore #6
        //   167: aload_2
        //   168: astore_1
        //   169: aload #6
        //   171: astore_2
        //   172: goto -> 1184
        //   175: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   178: dup
        //   179: ldc 'setsei'
        //   181: iconst_1
        //   182: invokespecial <init> : (Ljava/lang/String;I)V
        //   185: astore #5
        //   187: new java/lang/StringBuilder
        //   190: dup
        //   191: invokespecial <init> : ()V
        //   194: astore_1
        //   195: aload_1
        //   196: aload_2
        //   197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: pop
        //   201: aload_1
        //   202: aload_0
        //   203: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   206: ldc 2131755332
        //   208: invokevirtual getString : (I)Ljava/lang/String;
        //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: pop
        //   215: aload_1
        //   216: invokevirtual toString : ()Ljava/lang/String;
        //   219: astore_1
        //   220: aload_0
        //   221: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   224: ldc 2131755333
        //   226: invokevirtual getString : (I)Ljava/lang/String;
        //   229: astore_2
        //   230: aload_0
        //   231: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   234: ldc 2131755332
        //   236: invokevirtual getString : (I)Ljava/lang/String;
        //   239: astore #7
        //   241: aload_3
        //   242: sipush #255
        //   245: invokevirtual setMax : (I)V
        //   248: aload_3
        //   249: iconst_2
        //   250: invokevirtual setMin : (I)V
        //   253: goto -> 875
        //   256: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   259: dup
        //   260: ldc 'setsai'
        //   262: iconst_1
        //   263: invokespecial <init> : (Ljava/lang/String;I)V
        //   266: astore #5
        //   268: new java/lang/StringBuilder
        //   271: dup
        //   272: invokespecial <init> : ()V
        //   275: astore_1
        //   276: aload_1
        //   277: aload_2
        //   278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: pop
        //   282: aload_1
        //   283: aload_0
        //   284: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   287: ldc 2131755330
        //   289: invokevirtual getString : (I)Ljava/lang/String;
        //   292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: pop
        //   296: aload_1
        //   297: invokevirtual toString : ()Ljava/lang/String;
        //   300: astore_1
        //   301: aload_0
        //   302: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   305: ldc 2131755331
        //   307: invokevirtual getString : (I)Ljava/lang/String;
        //   310: astore_2
        //   311: aload_0
        //   312: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   315: ldc 2131755330
        //   317: invokevirtual getString : (I)Ljava/lang/String;
        //   320: astore #7
        //   322: aload_3
        //   323: sipush #255
        //   326: invokevirtual setMax : (I)V
        //   329: aload_3
        //   330: iconst_1
        //   331: invokevirtual setMin : (I)V
        //   334: goto -> 875
        //   337: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   340: dup
        //   341: ldc 'setport2'
        //   343: iconst_1
        //   344: invokespecial <init> : (Ljava/lang/String;I)V
        //   347: astore #5
        //   349: new java/lang/StringBuilder
        //   352: dup
        //   353: invokespecial <init> : ()V
        //   356: astore_1
        //   357: aload_1
        //   358: aload_2
        //   359: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: pop
        //   363: aload_1
        //   364: aload_0
        //   365: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   368: ldc 2131755328
        //   370: invokevirtual getString : (I)Ljava/lang/String;
        //   373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: pop
        //   377: aload_1
        //   378: invokevirtual toString : ()Ljava/lang/String;
        //   381: astore_1
        //   382: aload_0
        //   383: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   386: ldc 2131755329
        //   388: invokevirtual getString : (I)Ljava/lang/String;
        //   391: astore_2
        //   392: aload_0
        //   393: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   396: ldc 2131755328
        //   398: invokevirtual getString : (I)Ljava/lang/String;
        //   401: astore #7
        //   403: aload_3
        //   404: ldc 65534
        //   406: invokevirtual setMax : (I)V
        //   409: aload_3
        //   410: iconst_1
        //   411: invokevirtual setMin : (I)V
        //   414: goto -> 875
        //   417: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   420: dup
        //   421: ldc 'setport1'
        //   423: iconst_1
        //   424: invokespecial <init> : (Ljava/lang/String;I)V
        //   427: astore #5
        //   429: new java/lang/StringBuilder
        //   432: dup
        //   433: invokespecial <init> : ()V
        //   436: astore_1
        //   437: aload_1
        //   438: aload_2
        //   439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: pop
        //   443: aload_1
        //   444: aload_0
        //   445: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   448: ldc 2131755327
        //   450: invokevirtual getString : (I)Ljava/lang/String;
        //   453: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   456: pop
        //   457: aload_1
        //   458: invokevirtual toString : ()Ljava/lang/String;
        //   461: astore_1
        //   462: aload_0
        //   463: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   466: ldc 2131755329
        //   468: invokevirtual getString : (I)Ljava/lang/String;
        //   471: astore_2
        //   472: aload_0
        //   473: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   476: ldc 2131755327
        //   478: invokevirtual getString : (I)Ljava/lang/String;
        //   481: astore #7
        //   483: aload_3
        //   484: ldc 65534
        //   486: invokevirtual setMax : (I)V
        //   489: aload_3
        //   490: iconst_1
        //   491: invokevirtual setMin : (I)V
        //   494: goto -> 875
        //   497: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   500: dup
        //   501: ldc 'setceip2'
        //   503: iconst_1
        //   504: invokespecial <init> : (Ljava/lang/String;I)V
        //   507: astore #5
        //   509: new java/lang/StringBuilder
        //   512: dup
        //   513: invokespecial <init> : ()V
        //   516: astore_1
        //   517: aload_1
        //   518: aload_2
        //   519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: pop
        //   523: aload_1
        //   524: aload_0
        //   525: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   528: ldc 2131755319
        //   530: invokevirtual getString : (I)Ljava/lang/String;
        //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   536: pop
        //   537: aload_1
        //   538: invokevirtual toString : ()Ljava/lang/String;
        //   541: astore_1
        //   542: aload_0
        //   543: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   546: ldc 2131755320
        //   548: invokevirtual getString : (I)Ljava/lang/String;
        //   551: astore_2
        //   552: aload_0
        //   553: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   556: ldc 2131755319
        //   558: invokevirtual getString : (I)Ljava/lang/String;
        //   561: astore #7
        //   563: aload_3
        //   564: aload #6
        //   566: invokevirtual setValidIp : (Ljava/lang/Boolean;)V
        //   569: goto -> 875
        //   572: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   575: dup
        //   576: ldc 'setceip1'
        //   578: iconst_1
        //   579: invokespecial <init> : (Ljava/lang/String;I)V
        //   582: astore #5
        //   584: new java/lang/StringBuilder
        //   587: dup
        //   588: invokespecial <init> : ()V
        //   591: astore_1
        //   592: aload_1
        //   593: aload_2
        //   594: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   597: pop
        //   598: aload_1
        //   599: aload_0
        //   600: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   603: ldc 2131755318
        //   605: invokevirtual getString : (I)Ljava/lang/String;
        //   608: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   611: pop
        //   612: aload_1
        //   613: invokevirtual toString : ()Ljava/lang/String;
        //   616: astore_1
        //   617: aload_0
        //   618: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   621: ldc 2131755320
        //   623: invokevirtual getString : (I)Ljava/lang/String;
        //   626: astore_2
        //   627: aload_0
        //   628: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   631: ldc 2131755318
        //   633: invokevirtual getString : (I)Ljava/lang/String;
        //   636: astore #7
        //   638: aload_3
        //   639: aload #6
        //   641: invokevirtual setValidIp : (Ljava/lang/Boolean;)V
        //   644: goto -> 875
        //   647: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   650: dup
        //   651: ldc 'setid'
        //   653: iconst_1
        //   654: invokespecial <init> : (Ljava/lang/String;I)V
        //   657: astore #5
        //   659: new java/lang/StringBuilder
        //   662: dup
        //   663: invokespecial <init> : ()V
        //   666: astore_1
        //   667: aload_1
        //   668: aload_2
        //   669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   672: pop
        //   673: aload_1
        //   674: aload_0
        //   675: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   678: ldc 2131755325
        //   680: invokevirtual getString : (I)Ljava/lang/String;
        //   683: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: pop
        //   687: aload_1
        //   688: invokevirtual toString : ()Ljava/lang/String;
        //   691: astore_1
        //   692: aload_0
        //   693: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   696: ldc 2131755326
        //   698: invokevirtual getString : (I)Ljava/lang/String;
        //   701: astore_2
        //   702: aload_0
        //   703: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   706: ldc 2131755325
        //   708: invokevirtual getString : (I)Ljava/lang/String;
        //   711: astore #7
        //   713: aload_3
        //   714: aload #6
        //   716: invokevirtual setValidId : (Ljava/lang/Boolean;)V
        //   719: goto -> 875
        //   722: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   725: dup
        //   726: ldc 'setdate'
        //   728: iconst_1
        //   729: invokespecial <init> : (Ljava/lang/String;I)V
        //   732: astore #5
        //   734: new java/lang/StringBuilder
        //   737: dup
        //   738: invokespecial <init> : ()V
        //   741: astore_1
        //   742: aload_1
        //   743: aload_2
        //   744: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   747: pop
        //   748: aload_1
        //   749: aload_0
        //   750: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   753: ldc 2131755323
        //   755: invokevirtual getString : (I)Ljava/lang/String;
        //   758: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   761: pop
        //   762: aload_1
        //   763: invokevirtual toString : ()Ljava/lang/String;
        //   766: astore_1
        //   767: aload_0
        //   768: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   771: ldc 2131755324
        //   773: invokevirtual getString : (I)Ljava/lang/String;
        //   776: astore_2
        //   777: aload_0
        //   778: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   781: ldc 2131755323
        //   783: invokevirtual getString : (I)Ljava/lang/String;
        //   786: astore #7
        //   788: aload_3
        //   789: aload #6
        //   791: invokevirtual setValidDate : (Ljava/lang/Boolean;)V
        //   794: goto -> 875
        //   797: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   800: dup
        //   801: ldc 'setcw'
        //   803: iconst_1
        //   804: invokespecial <init> : (Ljava/lang/String;I)V
        //   807: astore #5
        //   809: new java/lang/StringBuilder
        //   812: dup
        //   813: invokespecial <init> : ()V
        //   816: astore_1
        //   817: aload_1
        //   818: aload_2
        //   819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   822: pop
        //   823: aload_1
        //   824: aload_0
        //   825: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   828: ldc 2131755321
        //   830: invokevirtual getString : (I)Ljava/lang/String;
        //   833: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   836: pop
        //   837: aload_1
        //   838: invokevirtual toString : ()Ljava/lang/String;
        //   841: astore_1
        //   842: aload_0
        //   843: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   846: ldc 2131755322
        //   848: invokevirtual getString : (I)Ljava/lang/String;
        //   851: astore_2
        //   852: aload_0
        //   853: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   856: ldc 2131755321
        //   858: invokevirtual getString : (I)Ljava/lang/String;
        //   861: astore #7
        //   863: aload_3
        //   864: sipush #3600
        //   867: invokevirtual setMax : (I)V
        //   870: aload_3
        //   871: iconst_0
        //   872: invokevirtual setMin : (I)V
        //   875: aload #7
        //   877: astore #6
        //   879: goto -> 959
        //   882: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   885: dup
        //   886: ldc 'setble'
        //   888: iconst_1
        //   889: invokespecial <init> : (Ljava/lang/String;I)V
        //   892: astore #5
        //   894: new java/lang/StringBuilder
        //   897: dup
        //   898: invokespecial <init> : ()V
        //   901: astore_1
        //   902: aload_1
        //   903: aload_2
        //   904: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   907: pop
        //   908: aload_1
        //   909: aload_0
        //   910: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   913: ldc 2131755316
        //   915: invokevirtual getString : (I)Ljava/lang/String;
        //   918: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   921: pop
        //   922: aload_1
        //   923: invokevirtual toString : ()Ljava/lang/String;
        //   926: astore_1
        //   927: aload_0
        //   928: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   931: ldc 2131755316
        //   933: invokevirtual getString : (I)Ljava/lang/String;
        //   936: astore #6
        //   938: aload_0
        //   939: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   942: ldc 2131755317
        //   944: invokevirtual getString : (I)Ljava/lang/String;
        //   947: astore_2
        //   948: aload_3
        //   949: bipush #12
        //   951: invokevirtual setMaxLength : (I)V
        //   954: aload_3
        //   955: iconst_5
        //   956: invokevirtual setMinLength : (I)V
        //   959: aload #5
        //   961: astore #7
        //   963: aload #6
        //   965: astore #5
        //   967: goto -> 1184
        //   970: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   973: dup
        //   974: ldc 'setana'
        //   976: iconst_1
        //   977: invokespecial <init> : (Ljava/lang/String;I)V
        //   980: astore #7
        //   982: new java/lang/StringBuilder
        //   985: dup
        //   986: invokespecial <init> : ()V
        //   989: astore_1
        //   990: aload_1
        //   991: aload_2
        //   992: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   995: pop
        //   996: aload_1
        //   997: aload_0
        //   998: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1001: ldc 2131755195
        //   1003: invokevirtual getString : (I)Ljava/lang/String;
        //   1006: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1009: pop
        //   1010: aload_1
        //   1011: invokevirtual toString : ()Ljava/lang/String;
        //   1014: astore_1
        //   1015: aload_0
        //   1016: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1019: ldc 2131755195
        //   1021: invokevirtual getString : (I)Ljava/lang/String;
        //   1024: astore_2
        //   1025: aload_3
        //   1026: bipush #24
        //   1028: invokevirtual setMaxLength : (I)V
        //   1031: aload_3
        //   1032: iconst_0
        //   1033: invokevirtual setMinLength : (I)V
        //   1036: goto -> 1174
        //   1039: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   1042: dup
        //   1043: ldc 'setapw'
        //   1045: iconst_1
        //   1046: invokespecial <init> : (Ljava/lang/String;I)V
        //   1049: astore #7
        //   1051: new java/lang/StringBuilder
        //   1054: dup
        //   1055: invokespecial <init> : ()V
        //   1058: astore_1
        //   1059: aload_1
        //   1060: aload_2
        //   1061: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1064: pop
        //   1065: aload_1
        //   1066: aload_0
        //   1067: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1070: ldc 2131755194
        //   1072: invokevirtual getString : (I)Ljava/lang/String;
        //   1075: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1078: pop
        //   1079: aload_1
        //   1080: invokevirtual toString : ()Ljava/lang/String;
        //   1083: astore_1
        //   1084: aload_0
        //   1085: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1088: ldc 2131755194
        //   1090: invokevirtual getString : (I)Ljava/lang/String;
        //   1093: astore_2
        //   1094: aload_3
        //   1095: bipush #24
        //   1097: invokevirtual setMaxLength : (I)V
        //   1100: aload_3
        //   1101: iconst_0
        //   1102: invokevirtual setMinLength : (I)V
        //   1105: goto -> 1174
        //   1108: new me/rocyang/bluetooth/rdradar/entity/RWData
        //   1111: dup
        //   1112: ldc 'setapn'
        //   1114: iconst_1
        //   1115: invokespecial <init> : (Ljava/lang/String;I)V
        //   1118: astore #7
        //   1120: new java/lang/StringBuilder
        //   1123: dup
        //   1124: invokespecial <init> : ()V
        //   1127: astore_1
        //   1128: aload_1
        //   1129: aload_2
        //   1130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1133: pop
        //   1134: aload_1
        //   1135: aload_0
        //   1136: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1139: ldc 2131755193
        //   1141: invokevirtual getString : (I)Ljava/lang/String;
        //   1144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1147: pop
        //   1148: aload_1
        //   1149: invokevirtual toString : ()Ljava/lang/String;
        //   1152: astore_1
        //   1153: aload_0
        //   1154: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1157: ldc 2131755193
        //   1159: invokevirtual getString : (I)Ljava/lang/String;
        //   1162: astore_2
        //   1163: aload_3
        //   1164: bipush #24
        //   1166: invokevirtual setMaxLength : (I)V
        //   1169: aload_3
        //   1170: iconst_0
        //   1171: invokevirtual setMinLength : (I)V
        //   1174: ldc ''
        //   1176: astore #6
        //   1178: aload_2
        //   1179: astore #5
        //   1181: aload #6
        //   1183: astore_2
        //   1184: aload_0
        //   1185: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1188: getfield bottomMenuDialog : Lcom/google/android/material/bottomsheet/BottomSheetDialog;
        //   1191: invokevirtual dismiss : ()V
        //   1194: aload #7
        //   1196: ifnonnull -> 1200
        //   1199: return
        //   1200: ldc 'setble'
        //   1202: aload #7
        //   1204: invokevirtual getCmd : ()Ljava/lang/String;
        //   1207: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1210: ifeq -> 1464
        //   1213: new java/lang/StringBuilder
        //   1216: dup
        //   1217: invokespecial <init> : ()V
        //   1220: astore #6
        //   1222: aload #6
        //   1224: aload #7
        //   1226: invokevirtual getCmd : ()Ljava/lang/String;
        //   1229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1232: pop
        //   1233: aload #6
        //   1235: ldc ' bad'
        //   1237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1240: pop
        //   1241: aload #6
        //   1243: invokevirtual toString : ()Ljava/lang/String;
        //   1246: astore #6
        //   1248: new java/lang/StringBuilder
        //   1251: dup
        //   1252: invokespecial <init> : ()V
        //   1255: astore #8
        //   1257: aload #8
        //   1259: aload #7
        //   1261: invokevirtual getCmd : ()Ljava/lang/String;
        //   1264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1267: pop
        //   1268: aload #8
        //   1270: ldc ' ok'
        //   1272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1275: pop
        //   1276: aload #8
        //   1278: invokevirtual toString : ()Ljava/lang/String;
        //   1281: astore #8
        //   1283: new java/lang/StringBuilder
        //   1286: dup
        //   1287: invokespecial <init> : ()V
        //   1290: astore #9
        //   1292: aload #9
        //   1294: aload #7
        //   1296: invokevirtual getCmd : ()Ljava/lang/String;
        //   1299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1302: pop
        //   1303: aload #9
        //   1305: ldc ' ok@@'
        //   1307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1310: pop
        //   1311: aload #9
        //   1313: invokevirtual toString : ()Ljava/lang/String;
        //   1316: astore #9
        //   1318: new java/lang/StringBuilder
        //   1321: dup
        //   1322: invokespecial <init> : ()V
        //   1325: astore #10
        //   1327: aload #10
        //   1329: aload #7
        //   1331: invokevirtual getCmd : ()Ljava/lang/String;
        //   1334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1337: pop
        //   1338: aload #10
        //   1340: ldc ' bad@@'
        //   1342: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1345: pop
        //   1346: aload #10
        //   1348: invokevirtual toString : ()Ljava/lang/String;
        //   1351: astore #10
        //   1353: new java/lang/StringBuilder
        //   1356: dup
        //   1357: invokespecial <init> : ()V
        //   1360: astore #11
        //   1362: aload #11
        //   1364: aload #7
        //   1366: invokevirtual getCmd : ()Ljava/lang/String;
        //   1369: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1372: pop
        //   1373: aload #11
        //   1375: getstatic me/rocyang/bluetooth/rdradar/contact/ControlCommand.SET_SUCCESS_OLD_VERSION_POSTFIX : Ljava/lang/String;
        //   1378: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1381: pop
        //   1382: aload #11
        //   1384: invokevirtual toString : ()Ljava/lang/String;
        //   1387: astore #11
        //   1389: new java/lang/StringBuilder
        //   1392: dup
        //   1393: invokespecial <init> : ()V
        //   1396: astore #12
        //   1398: aload #12
        //   1400: aload #7
        //   1402: invokevirtual getCmd : ()Ljava/lang/String;
        //   1405: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1408: pop
        //   1409: aload #12
        //   1411: getstatic me/rocyang/bluetooth/rdradar/contact/ControlCommand.SET_FAIL_OLD_VERSION_POSTFIX : Ljava/lang/String;
        //   1414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1417: pop
        //   1418: aload #7
        //   1420: bipush #6
        //   1422: anewarray java/lang/String
        //   1425: dup
        //   1426: iconst_0
        //   1427: aload #6
        //   1429: aastore
        //   1430: dup
        //   1431: iconst_1
        //   1432: aload #8
        //   1434: aastore
        //   1435: dup
        //   1436: iconst_2
        //   1437: aload #9
        //   1439: aastore
        //   1440: dup
        //   1441: iconst_3
        //   1442: aload #10
        //   1444: aastore
        //   1445: dup
        //   1446: iconst_4
        //   1447: aload #11
        //   1449: aastore
        //   1450: dup
        //   1451: iconst_5
        //   1452: aload #12
        //   1454: invokevirtual toString : ()Ljava/lang/String;
        //   1457: aastore
        //   1458: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1461: goto -> 1751
        //   1464: ldc 'setceip1'
        //   1466: aload #7
        //   1468: invokevirtual getCmd : ()Ljava/lang/String;
        //   1471: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1474: ifeq -> 1494
        //   1477: aload #7
        //   1479: iconst_1
        //   1480: anewarray java/lang/String
        //   1483: dup
        //   1484: iconst_0
        //   1485: ldc 'setceip [1] ok@@'
        //   1487: aastore
        //   1488: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1491: goto -> 1751
        //   1494: ldc 'setceip2'
        //   1496: aload #7
        //   1498: invokevirtual getCmd : ()Ljava/lang/String;
        //   1501: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1504: ifeq -> 1524
        //   1507: aload #7
        //   1509: iconst_1
        //   1510: anewarray java/lang/String
        //   1513: dup
        //   1514: iconst_0
        //   1515: ldc 'setceip [2] ok@@'
        //   1517: aastore
        //   1518: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1521: goto -> 1751
        //   1524: ldc 'setport1'
        //   1526: aload #7
        //   1528: invokevirtual getCmd : ()Ljava/lang/String;
        //   1531: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1534: ifeq -> 1554
        //   1537: aload #7
        //   1539: iconst_1
        //   1540: anewarray java/lang/String
        //   1543: dup
        //   1544: iconst_0
        //   1545: ldc 'setport [1] ok@@'
        //   1547: aastore
        //   1548: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1551: goto -> 1751
        //   1554: ldc 'setport2'
        //   1556: aload #7
        //   1558: invokevirtual getCmd : ()Ljava/lang/String;
        //   1561: invokevirtual equals : (Ljava/lang/Object;)Z
        //   1564: ifeq -> 1584
        //   1567: aload #7
        //   1569: iconst_1
        //   1570: anewarray java/lang/String
        //   1573: dup
        //   1574: iconst_0
        //   1575: ldc 'setport [2] ok@@'
        //   1577: aastore
        //   1578: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1581: goto -> 1751
        //   1584: new java/lang/StringBuilder
        //   1587: dup
        //   1588: invokespecial <init> : ()V
        //   1591: astore #6
        //   1593: aload #6
        //   1595: aload #7
        //   1597: invokevirtual getCmd : ()Ljava/lang/String;
        //   1600: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1603: pop
        //   1604: aload #6
        //   1606: ldc ' ok@@'
        //   1608: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1611: pop
        //   1612: aload #6
        //   1614: invokevirtual toString : ()Ljava/lang/String;
        //   1617: astore #6
        //   1619: new java/lang/StringBuilder
        //   1622: dup
        //   1623: invokespecial <init> : ()V
        //   1626: astore #8
        //   1628: aload #8
        //   1630: aload #7
        //   1632: invokevirtual getCmd : ()Ljava/lang/String;
        //   1635: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1638: pop
        //   1639: aload #8
        //   1641: ldc ' bad@@'
        //   1643: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1646: pop
        //   1647: aload #8
        //   1649: invokevirtual toString : ()Ljava/lang/String;
        //   1652: astore #8
        //   1654: new java/lang/StringBuilder
        //   1657: dup
        //   1658: invokespecial <init> : ()V
        //   1661: astore #9
        //   1663: aload #9
        //   1665: aload #7
        //   1667: invokevirtual getCmd : ()Ljava/lang/String;
        //   1670: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1673: pop
        //   1674: aload #9
        //   1676: getstatic me/rocyang/bluetooth/rdradar/contact/ControlCommand.SET_SUCCESS_OLD_VERSION_POSTFIX : Ljava/lang/String;
        //   1679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1682: pop
        //   1683: aload #9
        //   1685: invokevirtual toString : ()Ljava/lang/String;
        //   1688: astore #9
        //   1690: new java/lang/StringBuilder
        //   1693: dup
        //   1694: invokespecial <init> : ()V
        //   1697: astore #10
        //   1699: aload #10
        //   1701: aload #7
        //   1703: invokevirtual getCmd : ()Ljava/lang/String;
        //   1706: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1709: pop
        //   1710: aload #10
        //   1712: getstatic me/rocyang/bluetooth/rdradar/contact/ControlCommand.SET_FAIL_OLD_VERSION_POSTFIX : Ljava/lang/String;
        //   1715: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   1718: pop
        //   1719: aload #7
        //   1721: iconst_4
        //   1722: anewarray java/lang/String
        //   1725: dup
        //   1726: iconst_0
        //   1727: aload #6
        //   1729: aastore
        //   1730: dup
        //   1731: iconst_1
        //   1732: aload #8
        //   1734: aastore
        //   1735: dup
        //   1736: iconst_2
        //   1737: aload #9
        //   1739: aastore
        //   1740: dup
        //   1741: iconst_3
        //   1742: aload #10
        //   1744: invokevirtual toString : ()Ljava/lang/String;
        //   1747: aastore
        //   1748: invokevirtual setEndFlag : ([Ljava/lang/String;)V
        //   1751: aload_0
        //   1752: getfield this$0 : Lme/rocyang/bluetooth/rdradar/view/RtuSettingActivity;
        //   1755: aload_1
        //   1756: aload_2
        //   1757: aload #5
        //   1759: aload #7
        //   1761: aload_3
        //   1762: invokevirtual createInputDialog : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lme/rocyang/bluetooth/rdradar/entity/RWData;Lme/rocyang/bluetooth/rdradar/entity/ValidRule;)Landroid/app/AlertDialog;
        //   1765: invokevirtual show : ()V
        //   1768: return
      }
    };
  
  ActionMenuViewHolder actionMenuViewHolder;
  
  BottomSheetDialog bottomMenuDialog;
  
  @BindView(2131296366)
  Button btnClear;
  
  @BindView(2131296383)
  Button btnQuery;
  
  @BindView(2131296393)
  Button btnSetting;
  
  RtuCommandInfoAdapter commandInfoAdapter;
  
  List<EditText> ipInputs = new ArrayList<EditText>();
  
  View.OnKeyListener ipKeyListener = new View.OnKeyListener() {
      final RtuSettingActivity this$0;
      
      public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
        if (param1KeyEvent.getKeyCode() == 67) {
          EditText editText = (EditText)param1View;
          if (editText.length() == 0) {
            int i = RtuSettingActivity.this.ipInputs.indexOf(editText);
            if (i < 0)
              return true; 
            if (i > 0)
              ((EditText)RtuSettingActivity.this.ipInputs.get(i - 1)).requestFocus(); 
          } 
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onKey: ");
        stringBuilder.append(param1KeyEvent.toString());
        Log.i("RtuSettingActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onKey: i=");
        stringBuilder.append(param1Int);
        Log.i("RtuSettingActivity", stringBuilder.toString());
        return false;
      }
    };
  
  private RtuCommandInfoAdapter.OnItemLongClickListener logItemLongClickListener = new RtuCommandInfoAdapter.OnItemLongClickListener() {
      final RtuSettingActivity this$0;
      
      public boolean onItemLongClick(View param1View, int param1Int) {
        RtuLog rtuLog = RtuSettingActivity.this.commandInfoAdapter.getItem(param1Int);
        if (rtuLog == null)
          return false; 
        if (rtuLog.getCmd().isEmpty())
          return false; 
        ToastUtil.ShowTextLong(rtuLog.getCmd());
        return true;
      }
    };
  
  private BleNotifyCallback notifyEvent = new BleNotifyCallback() {
      final RtuSettingActivity this$0;
      
      public void onCharacteristicChanged(byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: String:");
        stringBuilder.append(new String(param1ArrayOfbyte));
        Log.i("RtuSettingActivity", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("onCharacteristicChanged: HEX:");
        stringBuilder.append(HexUtil.encodeHexStr(param1ArrayOfbyte));
        Log.i("RtuSettingActivity", stringBuilder.toString());
        if (RtuSettingActivity.this.testTask != null && RtuSettingActivity.this.testTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
          RdDevices.recvQueue.offer(param1ArrayOfbyte);
          return;
        } 
        if (RtuSettingActivity.this.readerTask != null && RtuSettingActivity.this.readerTask.getStatus().equals(AsyncTask.Status.RUNNING))
          RdDevices.recvQueue.offer(param1ArrayOfbyte); 
      }
      
      public void onNotifyFailure(BleException param1BleException) {
        Log.i("RtuSettingActivity", "onNotifySuccess: 通知打开失败");
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.disConnect(rtuSettingActivity.bleDevice);
      }
      
      public void onNotifySuccess() {
        Log.i("RtuSettingActivity", "onNotifySuccess: 通知打开成功");
        RtuSettingActivity.this.startProtocolTest();
        Message message = new Message();
        message.what = 4096;
        if (RtuSettingActivity.this.handler != null)
          RtuSettingActivity.this.handler.sendMessage(message); 
      }
    };
  
  public View.OnClickListener queryMenuListener = new View.OnClickListener() {
      final RtuSettingActivity this$0;
      
      public void onClick(View param1View) {
        RWData rWData;
        int i = param1View.getId();
        View view = null;
        String str1 = "";
        param1View = view;
        String str2 = str1;
        switch (i) {
          default:
            param1View = view;
            str2 = str1;
            break;
          case 2131296399:
            rWData = new RWData("vol", 1);
            str2 = RtuSettingActivity.this.getString(2131755392);
            break;
          case 2131296398:
            rWData = new RWData("temp", 1);
            str2 = RtuSettingActivity.this.getString(2131755342);
            break;
          case 2131296394:
            rWData = new RWData("csq", 1);
            str2 = RtuSettingActivity.this.getString(2131755225);
            break;
          case 2131296392:
            rWData = new RWData("catsei", 1);
            str2 = RtuSettingActivity.this.getString(2131755210);
            break;
          case 2131296391:
            rWData = new RWData("catsai", 1);
            str2 = RtuSettingActivity.this.getString(2131755209);
            break;
          case 2131296381:
            rWData = new RWData("catport", 1);
            str2 = RtuSettingActivity.this.getString(2131755208);
            break;
          case 2131296376:
            rWData = new RWData("catip", 1);
            str2 = RtuSettingActivity.this.getString(2131755207);
            break;
          case 2131296375:
            rWData = new RWData("catid", 1);
            str2 = RtuSettingActivity.this.getString(2131755206);
            break;
          case 2131296372:
            rWData = new RWData("gprs", 1);
            str2 = RtuSettingActivity.this.getString(2131755255);
            break;
          case 2131296367:
            rWData = new RWData("date", 1);
            str2 = RtuSettingActivity.this.getString(2131755229);
            break;
          case 2131296365:
            rWData = new RWData("catcw", 1);
            str2 = RtuSettingActivity.this.getString(2131755203);
            break;
          case 2131296360:
            rWData = new RWData("catana", 1);
            str2 = RtuSettingActivity.this.getString(2131755195);
            break;
          case 2131296359:
            rWData = new RWData("catapw", 1);
            str2 = RtuSettingActivity.this.getString(2131755194);
            break;
          case 2131296358:
            rWData = new RWData("catapn", 1);
            str2 = RtuSettingActivity.this.getString(2131755193);
            break;
          case 2131296356:
            rWData = new RWData("catep", 1);
            str2 = RtuSettingActivity.this.getString(2131755204);
            break;
          case 2131296364:
            break;
        } 
        RtuSettingActivity.this.bottomMenuDialog.dismiss();
        if (rWData == null)
          return; 
        RtuLog rtuLog = new RtuLog();
        rtuLog.setLogType(RtuLog.LogType.SEND_CMD);
        rtuLog.setContent(String.format(RtuSettingActivity.this.getString(2131755277), new Object[] { str2 }));
        rtuLog.setCmd(rWData.getCmd());
        RtuSettingActivity.this.addLog(rtuLog);
        if ("catep".equals(rWData.getCmd())) {
          rWData.setEndFlag(new String[] { "############" });
        } else if ("catip".equals(rWData.getCmd()) || "catport".equals(rWData.getCmd())) {
          rWData.setEndFlag(new String[] { "@@" });
        } else {
          rWData.setEndFlag(new String[] { "@@", ControlCommand.QUERY_OLD_VERSION_POSTFIX_STRING });
        } 
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.startRWThread(String.format(rtuSettingActivity.getString(2131755276), new Object[] { str2 }), new RWData[] { rWData });
      }
    };
  
  QueryMenuViewHolder queryMenuViewHolder;
  
  private TextReaderAsyncTask readerTask;
  
  private int retryTimes = 0;
  
  List<RtuLog> rtuLogs = new ArrayList<RtuLog>();
  
  @BindView(2131296602)
  RecyclerView rvResult;
  
  private RWHandleListener rwListener = new RWHandleListener() {
      final RtuSettingActivity this$0;
      
      public void commandResultEvent(RWData param1RWData) {
        RtuSettingActivity.this.dealData(param1RWData);
      }
      
      public void readDateOverTimeEvent() {
        ToastUtil.ShowTextLong(RtuSettingActivity.this.getString(2131755306));
      }
      
      public void readRssi() {}
      
      public void threadForceStopEvent() {
        if (RtuSettingActivity.this.hud != null && RtuSettingActivity.this.hud.isShowing())
          RtuSettingActivity.this.hud.dismiss(); 
      }
      
      public void threadStopEvent() {
        if (RtuSettingActivity.this.hud != null && RtuSettingActivity.this.hud.isShowing())
          RtuSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.write(rtuSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("writeTextCmdEvent: ");
        stringBuilder.append(param1String);
        Log.i("RtuSettingActivity", stringBuilder.toString());
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.write(rtuSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestHandleListener testHandleListener = new ProtocolTestHandleListener() {
      final RtuSettingActivity this$0;
      
      public void testFail() {
        if (RtuSettingActivity.this.retryTimes < 3) {
          ToastUtil.ShowTextLong(RtuSettingActivity.this.getString(2131755279));
          RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
          RtuSettingActivity.access$302(rtuSettingActivity, rtuSettingActivity.retryTimes + 1);
          RtuSettingActivity.this.startProtocolTest();
        } else {
          ToastUtil.ShowTextLong(RtuSettingActivity.this.getString(2131755273));
          RtuSettingActivity.this.finish();
        } 
      }
      
      public void testSuccess() {
        if (RtuSettingActivity.this.hud != null && RtuSettingActivity.this.hud.isShowing())
          RtuSettingActivity.this.hud.dismiss(); 
      }
      
      public void writeCommCmdEvent(byte[] param1ArrayOfbyte) {
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.write(rtuSettingActivity.bleDevice, param1ArrayOfbyte);
      }
      
      public void writeTextCmdEvent(String param1String) {
        RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
        rtuSettingActivity.write(rtuSettingActivity.bleDevice, param1String.getBytes());
      }
    };
  
  private ProtocolTestAsyncTask testTask;
  
  @BindView(2131296689)
  Toolbar toolbar;
  
  private void dealData(RWData paramRWData) {
    // Byte code:
    //   0: aload_0
    //   1: getfield hud : Lcom/kaopiz/kprogresshud/KProgressHUD;
    //   4: ifnull -> 24
    //   7: aload_0
    //   8: getfield hud : Lcom/kaopiz/kprogresshud/KProgressHUD;
    //   11: invokevirtual isShowing : ()Z
    //   14: ifeq -> 24
    //   17: aload_0
    //   18: getfield hud : Lcom/kaopiz/kprogresshud/KProgressHUD;
    //   21: invokevirtual dismiss : ()V
    //   24: new me/rocyang/bluetooth/rdradar/entity/RtuLog
    //   27: dup
    //   28: invokespecial <init> : ()V
    //   31: astore_2
    //   32: aload_2
    //   33: getstatic me/rocyang/bluetooth/rdradar/entity/RtuLog$LogType.RECEIVE_RESULT : Lme/rocyang/bluetooth/rdradar/entity/RtuLog$LogType;
    //   36: invokevirtual setLogType : (Lme/rocyang/bluetooth/rdradar/entity/RtuLog$LogType;)V
    //   39: aload_1
    //   40: invokevirtual getResultStr : ()Ljava/lang/String;
    //   43: astore_3
    //   44: aload_3
    //   45: ifnonnull -> 49
    //   48: return
    //   49: getstatic me/rocyang/bluetooth/rdradar/contact/RTUCommand.QUERY_PARAM : Lme/rocyang/bluetooth/rdradar/contact/RTUCommand;
    //   52: invokevirtual getCmd : ()Ljava/lang/String;
    //   55: aload_1
    //   56: invokevirtual getCmd : ()Ljava/lang/String;
    //   59: invokevirtual equals : (Ljava/lang/Object;)Z
    //   62: ifeq -> 175
    //   65: aload_2
    //   66: aload_3
    //   67: ldc 'Station ID'
    //   69: aload_0
    //   70: ldc 2131755338
    //   72: invokevirtual getString : (I)Ljava/lang/String;
    //   75: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   78: ldc 'Equipment parameter'
    //   80: aload_0
    //   81: ldc 2131755244
    //   83: invokevirtual getString : (I)Ljava/lang/String;
    //   86: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   89: ldc 'APN name'
    //   91: aload_0
    //   92: ldc 2131755193
    //   94: invokevirtual getString : (I)Ljava/lang/String;
    //   97: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   100: ldc 'APN password'
    //   102: aload_0
    //   103: ldc 2131755194
    //   105: invokevirtual getString : (I)Ljava/lang/String;
    //   108: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   111: ldc 'Save Interval'
    //   113: aload_0
    //   114: ldc 2131755305
    //   116: invokevirtual getString : (I)Ljava/lang/String;
    //   119: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   122: ldc 'Send interval'
    //   124: aload_0
    //   125: ldc 2131755313
    //   127: invokevirtual getString : (I)Ljava/lang/String;
    //   130: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   133: ldc 'Cent station ip'
    //   135: aload_0
    //   136: ldc 2131755213
    //   138: invokevirtual getString : (I)Ljava/lang/String;
    //   141: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   144: ldc 'Port'
    //   146: aload_0
    //   147: ldc 2131755214
    //   149: invokevirtual getString : (I)Ljava/lang/String;
    //   152: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   155: ldc 'Connect type'
    //   157: aload_0
    //   158: ldc 2131755223
    //   160: invokevirtual getString : (I)Ljava/lang/String;
    //   163: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   166: invokevirtual setContent : (Ljava/lang/String;)V
    //   169: aload_0
    //   170: aload_2
    //   171: invokevirtual addLog : (Lme/rocyang/bluetooth/rdradar/entity/RtuLog;)V
    //   174: return
    //   175: ldc ''
    //   177: astore #4
    //   179: aload_3
    //   180: ldc '@@'
    //   182: ldc ''
    //   184: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   187: new java/lang/String
    //   190: dup
    //   191: getstatic me/rocyang/bluetooth/rdradar/contact/ControlCommand.QUERY_OLD_VERSION_POSTFIX : [B
    //   194: invokespecial <init> : ([B)V
    //   197: ldc ''
    //   199: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   202: astore_3
    //   203: aload_3
    //   204: ldc_w 'setport'
    //   207: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   210: ifne -> 225
    //   213: aload_3
    //   214: astore_1
    //   215: aload_3
    //   216: ldc_w 'setceip'
    //   219: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   222: ifeq -> 245
    //   225: aload_3
    //   226: ldc_w ' [1]'
    //   229: ldc_w '1'
    //   232: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   235: ldc_w ' [2]'
    //   238: ldc_w '2'
    //   241: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   244: astore_1
    //   245: aload_2
    //   246: aload_1
    //   247: invokevirtual setCmd : (Ljava/lang/String;)V
    //   250: aload_1
    //   251: invokestatic startWith : (Ljava/lang/String;)Lme/rocyang/bluetooth/rdradar/contact/RTUCommand;
    //   254: astore_3
    //   255: aload_3
    //   256: ifnonnull -> 260
    //   259: return
    //   260: aload_1
    //   261: aload_3
    //   262: invokevirtual getCmd : ()Ljava/lang/String;
    //   265: ldc ''
    //   267: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   270: invokevirtual trim : ()Ljava/lang/String;
    //   273: astore_1
    //   274: getstatic me/rocyang/bluetooth/rdradar/view/RtuSettingActivity$11.$SwitchMap$me$rocyang$bluetooth$rdradar$contact$RTUCmdType : [I
    //   277: aload_3
    //   278: invokevirtual getRTUCmdType : ()Lme/rocyang/bluetooth/rdradar/contact/RTUCmdType;
    //   281: invokevirtual ordinal : ()I
    //   284: iaload
    //   285: istore #5
    //   287: iload #5
    //   289: iconst_1
    //   290: if_icmpeq -> 723
    //   293: iload #5
    //   295: iconst_2
    //   296: if_icmpeq -> 313
    //   299: aload_2
    //   300: aload_0
    //   301: ldc_w 2131755245
    //   304: invokevirtual getString : (I)Ljava/lang/String;
    //   307: invokevirtual setContent : (Ljava/lang/String;)V
    //   310: goto -> 800
    //   313: aload_3
    //   314: invokevirtual getDivNum : ()I
    //   317: ifle -> 335
    //   320: aload_1
    //   321: invokestatic parseInt : (Ljava/lang/String;)I
    //   324: aload_3
    //   325: invokevirtual getDivNum : ()I
    //   328: invokestatic dived : (II)Ljava/lang/String;
    //   331: astore_1
    //   332: goto -> 335
    //   335: aload_1
    //   336: invokevirtual isEmpty : ()Z
    //   339: ifeq -> 374
    //   342: aload_2
    //   343: aload_0
    //   344: ldc_w 2131755295
    //   347: invokevirtual getString : (I)Ljava/lang/String;
    //   350: iconst_1
    //   351: anewarray java/lang/Object
    //   354: dup
    //   355: iconst_0
    //   356: aload_0
    //   357: aload_3
    //   358: invokevirtual getStr : ()I
    //   361: invokevirtual getString : (I)Ljava/lang/String;
    //   364: aastore
    //   365: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   368: invokevirtual setContent : (Ljava/lang/String;)V
    //   371: goto -> 800
    //   374: getstatic me/rocyang/bluetooth/rdradar/view/RtuSettingActivity$11.$SwitchMap$me$rocyang$bluetooth$rdradar$contact$RTUCommand : [I
    //   377: aload_3
    //   378: invokevirtual ordinal : ()I
    //   381: iaload
    //   382: istore #5
    //   384: iload #5
    //   386: iconst_1
    //   387: if_icmpeq -> 666
    //   390: iload #5
    //   392: iconst_2
    //   393: if_icmpeq -> 609
    //   396: iload #5
    //   398: iconst_3
    //   399: if_icmpeq -> 531
    //   402: iload #5
    //   404: iconst_4
    //   405: if_icmpeq -> 495
    //   408: aload_0
    //   409: ldc_w 2131755278
    //   412: invokevirtual getString : (I)Ljava/lang/String;
    //   415: astore #4
    //   417: aload_0
    //   418: aload_3
    //   419: invokevirtual getStr : ()I
    //   422: invokevirtual getString : (I)Ljava/lang/String;
    //   425: astore #6
    //   427: new java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial <init> : ()V
    //   434: astore #7
    //   436: aload #7
    //   438: aload_1
    //   439: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: pop
    //   443: aload #7
    //   445: ldc_w ' '
    //   448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: pop
    //   452: aload #7
    //   454: aload_0
    //   455: aload_3
    //   456: invokevirtual getUnit : ()I
    //   459: invokevirtual getString : (I)Ljava/lang/String;
    //   462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: pop
    //   466: aload_2
    //   467: aload #4
    //   469: iconst_2
    //   470: anewarray java/lang/Object
    //   473: dup
    //   474: iconst_0
    //   475: aload #6
    //   477: aastore
    //   478: dup
    //   479: iconst_1
    //   480: aload #7
    //   482: invokevirtual toString : ()Ljava/lang/String;
    //   485: aastore
    //   486: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   489: invokevirtual setContent : (Ljava/lang/String;)V
    //   492: goto -> 800
    //   495: aload_2
    //   496: aload_0
    //   497: ldc_w 2131755278
    //   500: invokevirtual getString : (I)Ljava/lang/String;
    //   503: iconst_2
    //   504: anewarray java/lang/Object
    //   507: dup
    //   508: iconst_0
    //   509: aload_0
    //   510: aload_3
    //   511: invokevirtual getStr : ()I
    //   514: invokevirtual getString : (I)Ljava/lang/String;
    //   517: aastore
    //   518: dup
    //   519: iconst_1
    //   520: aload_1
    //   521: aastore
    //   522: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   525: invokevirtual setContent : (Ljava/lang/String;)V
    //   528: goto -> 800
    //   531: aload_2
    //   532: aload_0
    //   533: ldc_w 2131755278
    //   536: invokevirtual getString : (I)Ljava/lang/String;
    //   539: iconst_2
    //   540: anewarray java/lang/Object
    //   543: dup
    //   544: iconst_0
    //   545: aload_0
    //   546: aload_3
    //   547: invokevirtual getStr : ()I
    //   550: invokevirtual getString : (I)Ljava/lang/String;
    //   553: aastore
    //   554: dup
    //   555: iconst_1
    //   556: aload_1
    //   557: invokevirtual toUpperCase : ()Ljava/lang/String;
    //   560: ldc_w 'D'
    //   563: aload_0
    //   564: ldc_w 2131755230
    //   567: invokevirtual getString : (I)Ljava/lang/String;
    //   570: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   573: ldc_w 'H'
    //   576: aload_0
    //   577: ldc_w 2131755256
    //   580: invokevirtual getString : (I)Ljava/lang/String;
    //   583: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   586: ldc_w 'M'
    //   589: aload_0
    //   590: ldc_w 2131755266
    //   593: invokevirtual getString : (I)Ljava/lang/String;
    //   596: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   599: aastore
    //   600: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   603: invokevirtual setContent : (Ljava/lang/String;)V
    //   606: goto -> 800
    //   609: aload_1
    //   610: invokestatic parseInt : (Ljava/lang/String;)I
    //   613: invokestatic getQueryTaskValue : (I)Ljava/lang/String;
    //   616: pop
    //   617: goto -> 630
    //   620: astore #4
    //   622: aload_0
    //   623: ldc_w 2131755343
    //   626: invokevirtual getString : (I)Ljava/lang/String;
    //   629: pop
    //   630: aload_2
    //   631: aload_0
    //   632: ldc_w 2131755278
    //   635: invokevirtual getString : (I)Ljava/lang/String;
    //   638: iconst_2
    //   639: anewarray java/lang/Object
    //   642: dup
    //   643: iconst_0
    //   644: aload_0
    //   645: aload_3
    //   646: invokevirtual getStr : ()I
    //   649: invokevirtual getString : (I)Ljava/lang/String;
    //   652: aastore
    //   653: dup
    //   654: iconst_1
    //   655: aload_1
    //   656: aastore
    //   657: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   660: invokevirtual setContent : (Ljava/lang/String;)V
    //   663: goto -> 800
    //   666: aload_1
    //   667: invokestatic parseInt : (Ljava/lang/String;)I
    //   670: invokestatic getQueryGprsValue : (I)Ljava/lang/String;
    //   673: pop
    //   674: goto -> 687
    //   677: astore #4
    //   679: aload_0
    //   680: ldc_w 2131755343
    //   683: invokevirtual getString : (I)Ljava/lang/String;
    //   686: pop
    //   687: aload_2
    //   688: aload_0
    //   689: ldc_w 2131755278
    //   692: invokevirtual getString : (I)Ljava/lang/String;
    //   695: iconst_2
    //   696: anewarray java/lang/Object
    //   699: dup
    //   700: iconst_0
    //   701: aload_0
    //   702: aload_3
    //   703: invokevirtual getStr : ()I
    //   706: invokevirtual getString : (I)Ljava/lang/String;
    //   709: aastore
    //   710: dup
    //   711: iconst_1
    //   712: aload_1
    //   713: aastore
    //   714: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   717: invokevirtual setContent : (Ljava/lang/String;)V
    //   720: goto -> 800
    //   723: ldc_w 'ok'
    //   726: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   729: aload_1
    //   730: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   733: invokevirtual equals : (Ljava/lang/Object;)Z
    //   736: ifeq -> 771
    //   739: aload_2
    //   740: aload_0
    //   741: ldc_w 2131755299
    //   744: invokevirtual getString : (I)Ljava/lang/String;
    //   747: iconst_1
    //   748: anewarray java/lang/Object
    //   751: dup
    //   752: iconst_0
    //   753: aload_0
    //   754: aload_3
    //   755: invokevirtual getStr : ()I
    //   758: invokevirtual getString : (I)Ljava/lang/String;
    //   761: aastore
    //   762: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   765: invokevirtual setContent : (Ljava/lang/String;)V
    //   768: goto -> 800
    //   771: aload_2
    //   772: aload_0
    //   773: ldc_w 2131755298
    //   776: invokevirtual getString : (I)Ljava/lang/String;
    //   779: iconst_1
    //   780: anewarray java/lang/Object
    //   783: dup
    //   784: iconst_0
    //   785: aload_0
    //   786: aload_3
    //   787: invokevirtual getStr : ()I
    //   790: invokevirtual getString : (I)Ljava/lang/String;
    //   793: aastore
    //   794: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   797: invokevirtual setContent : (Ljava/lang/String;)V
    //   800: aload_0
    //   801: aload_2
    //   802: invokevirtual addLog : (Lme/rocyang/bluetooth/rdradar/entity/RtuLog;)V
    //   805: return
    //   806: astore_1
    //   807: aload #4
    //   809: astore_1
    //   810: goto -> 335
    // Exception table:
    //   from	to	target	type
    //   320	332	806	java/lang/NumberFormatException
    //   609	617	620	java/lang/NumberFormatException
    //   666	674	677	java/lang/NumberFormatException
  }
  
  private void startProtocolTest() {
    Log.i("RtuSettingActivity", "startProtocolTest: 开始协议适配");
    this.testTask = new ProtocolTestAsyncTask(this.testHandleListener);
    this.testTask.execute((Object[])new Void[0]);
  }
  
  private void startRWThread(String paramString, RWData... paramVarArgs) {
    if (paramVarArgs == null)
      return; 
    if (this.hud != null && this.hud.isShowing())
      this.hud.dismiss(); 
    if (paramString != null) {
      initHud();
      this.hud.setLabel(paramString).setDetailsLabel(this.bleDevice.getName()).show();
    } 
    RdDevices.recvQueue.clear();
    this.readerTask = new TextReaderAsyncTask(this.rwListener);
    this.readerTask.execute((Object[])paramVarArgs);
  }
  
  private void stopProtocol() {
    ProtocolTestAsyncTask protocolTestAsyncTask = this.testTask;
    if (protocolTestAsyncTask != null && protocolTestAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.testTask.cancel(true); 
    TextReaderAsyncTask textReaderAsyncTask = this.readerTask;
    if (textReaderAsyncTask != null && textReaderAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING))
      this.readerTask.cancel(true); 
    RdDevices.recvQueue.clear();
  }
  
  public void addLog(RtuLog paramRtuLog) {
    this.rtuLogs.add(paramRtuLog);
    this.commandInfoAdapter.notifyDataSetChanged();
    this.rvResult.scrollToPosition(this.commandInfoAdapter.getItemCount() - 1);
  }
  
  public AlertDialog createAlert(String paramString1, String paramString2) {
    return (new AlertDialog.Builder((Context)this)).setTitle(paramString1).setMessage(paramString2).setIcon(17301543).setPositiveButton(17039379, null).create();
  }
  
  public AlertDialog createConfirmCommandAlert(final String msg, final String value, final RWData cmd) {
    return (new AlertDialog.Builder((Context)this)).setTitle(getString(2131755217)).setMessage(String.format(getString(2131755275), new Object[] { msg, value })).setIcon(17301659).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
          final RtuSettingActivity this$0;
          
          final RWData val$cmd;
          
          final String val$msg;
          
          final String val$value;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            String str1;
            RtuLog rtuLog = new RtuLog();
            rtuLog.setLogType(RtuLog.LogType.SEND_CMD);
            RTUCommand rTUCommand = RTUCommand.getByCmd(cmd.getCmd());
            String str2 = RtuSettingActivity.this.getString(2131755315);
            String str3 = msg;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(value);
            if (rTUCommand == null) {
              str1 = "";
            } else {
              str1 = RtuSettingActivity.this.getString(str1.getUnit());
            } 
            stringBuilder2.append(str1);
            rtuLog.setContent(String.format(str2, new Object[] { str3, stringBuilder2.toString() }));
            RWData rWData = cmd;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(cmd.getCmd());
            stringBuilder1.append(" ");
            stringBuilder1.append(value);
            rWData.setCmd(stringBuilder1.toString());
            rtuLog.setCmd(cmd.getCmd());
            RtuSettingActivity.this.addLog(rtuLog);
            RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
            rtuSettingActivity.startRWThread(String.format(rtuSettingActivity.getString(2131755335), new Object[] { this.val$msg, this.val$value }), new RWData[] { this.val$cmd });
          }
        }).setNegativeButton(17039369, null).create();
  }
  
  public AlertDialog createInputDialog(String paramString1, final String msg, final String hint, final RWData cmd, final ValidRule vr) {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
    View view = getLayoutInflater().inflate(2131492942, null);
    ((TextView)view.findViewById(2131296768)).setText(msg);
    final EditText edtInput = (EditText)view.findViewById(2131296463);
    ((LinearLayout)view.findViewById(2131296528)).setVisibility(8);
    if ("setble".equals(cmd.getCmd()) || "setceip1".equals(cmd.getCmd()) || "setceip2".equals(cmd.getCmd()) || "setapn".equals(cmd.getCmd()) || "setana".equals(cmd.getCmd()) || "setapw".equals(cmd.getCmd())) {
      editText.setInputType(1);
    } else {
      editText.setKeyListener((KeyListener)DigitsKeyListener.getInstance("0123456789"));
      editText.setInputType(2);
    } 
    if (vr.getValidMaxLength().booleanValue())
      editText.setFilters(new InputFilter[] { (InputFilter)new InputFilter.LengthFilter(vr.getMaxLength()) }); 
    if ("setdate".equals(cmd.getCmd()))
      editText.setText((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date(System.currentTimeMillis()))); 
    builder.setView(view).setTitle(paramString1).setCancelable(false).setIcon(17301547).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
          final RtuSettingActivity this$0;
          
          final RWData val$cmd;
          
          final EditText val$edtInput;
          
          final String val$hint;
          
          final String val$msg;
          
          final ValidRule val$vr;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            RtuSettingActivity rtuSettingActivity;
            String str = edtInput.getText().toString().trim();
            if (str.isEmpty() || !vr.valid(str).booleanValue()) {
              rtuSettingActivity = RtuSettingActivity.this;
              rtuSettingActivity.createAlert(rtuSettingActivity.getString(2131755259), msg).show();
              return;
            } 
            RtuSettingActivity.this.createConfirmCommandAlert(hint, (String)rtuSettingActivity, cmd).show();
          }
        }).setNegativeButton(17039369, null);
    return builder.create();
  }
  
  public String getInputIp() {
    if (this.ipInputs.size() < 4)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(((EditText)this.ipInputs.get(0)).getText().toString());
    stringBuffer.append(getString(2131755242));
    stringBuffer.append(((EditText)this.ipInputs.get(1)).getText().toString());
    stringBuffer.append(getString(2131755242));
    stringBuffer.append(((EditText)this.ipInputs.get(2)).getText().toString());
    stringBuffer.append(getString(2131755242));
    stringBuffer.append(((EditText)this.ipInputs.get(3)).getText().toString());
    return stringBuffer.toString();
  }
  
  public void onBackPressed() {
    stopProtocol();
    finish();
    super.onBackPressed();
  }
  
  @OnClick({2131296366})
  public void onBtnClearClicked() {
    this.rtuLogs.clear();
    this.commandInfoAdapter.notifyDataSetChanged();
  }
  
  @OnClick({2131296383})
  public void onBtnQueryClicked() {
    this.bottomMenuDialog = new BottomSheetDialog((Context)this);
    View view1 = getLayoutInflater().inflate(2131492905, null);
    this.bottomMenuDialog.setContentView(view1);
    View view2 = (View)view1.getParent();
    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view2);
    view1.measure(0, 0);
    bottomSheetBehavior.setPeekHeight(view1.getMeasuredHeight());
    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)view2.getLayoutParams();
    layoutParams.gravity = 49;
    view2.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    this.queryMenuViewHolder = new QueryMenuViewHolder(view1, this.queryMenuListener);
    this.bottomMenuDialog.show();
  }
  
  @OnClick({2131296393})
  public void onBtnSettingClicked() {
    this.bottomMenuDialog = new BottomSheetDialog((Context)this);
    View view1 = getLayoutInflater().inflate(2131492904, null);
    this.bottomMenuDialog.setContentView(view1);
    View view2 = (View)view1.getParent();
    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view2);
    view1.measure(0, 0);
    bottomSheetBehavior.setPeekHeight(view1.getMeasuredHeight());
    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)view2.getLayoutParams();
    layoutParams.gravity = 49;
    view2.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    this.actionMenuViewHolder = new ActionMenuViewHolder(view1, this.actionMenuListener);
    this.bottomMenuDialog.show();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492906);
    ButterKnife.bind((Activity)this);
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context)this);
    this.rvResult.setLayoutManager((RecyclerView.LayoutManager)linearLayoutManager);
    this.rtuLogs.add(new RtuLog(RtuLog.LogType.SEND_CMD, getString(2131755303), ""));
    this.rtuLogs.add(new RtuLog(RtuLog.LogType.RECEIVE_RESULT, getString(2131755301), ""));
    this.rtuLogs.add(new RtuLog(RtuLog.LogType.SEND_CMD, getString(2131755304), ""));
    this.rtuLogs.add(new RtuLog(RtuLog.LogType.RECEIVE_RESULT, getString(2131755302), ""));
    this.commandInfoAdapter = new RtuCommandInfoAdapter(this.rtuLogs);
    this.commandInfoAdapter.setItemLongClickListener(this.logItemLongClickListener);
    this.rvResult.setAdapter((RecyclerView.Adapter)this.commandInfoAdapter);
  }
  
  protected void onPause() {
    stopProtocol();
    super.onPause();
    finish();
  }
  
  protected void onResume() {
    super.onResume();
    if (this.bleDevice != null) {
      if (this.blueTooth.isConnected(this.bleDevice)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.bleDevice.getName().trim());
        stringBuilder.append(getString(2131755058));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        initHud();
        this.hud.setLabel(getString(2131755257)).show();
        TimerTask timerTask = new TimerTask() {
            final RtuSettingActivity this$0;
            
            public void run() {
              RtuSettingActivity rtuSettingActivity = RtuSettingActivity.this;
              rtuSettingActivity.openNotify(rtuSettingActivity.bleDevice, RtuSettingActivity.this.notifyEvent);
            }
          };
        (new Timer()).schedule(timerTask, 2000L);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.bleDevice.getName().trim());
        stringBuilder.append(getString(2131755057));
        ToastUtil.ShowTextLong(stringBuilder.toString());
        finish();
      } 
    } else {
      finish();
    } 
  }
  
  static class ActionMenuViewHolder {
    @BindView(2131296364)
    Button btnCancel;
    
    @BindView(2131296391)
    Button btnSaveInterval;
    
    @BindView(2131296362)
    Button btnSetBlueName;
    
    @BindView(2131296365)
    Button btnSetCatWait;
    
    @BindView(2131296367)
    Button btnSetDate;
    
    @BindView(2131296375)
    Button btnSetId;
    
    @BindView(2131296376)
    Button btnSetIp;
    
    @BindView(2131296381)
    Button btnSetPort;
    
    @BindView(2131296392)
    Button btnSetSendInterval;
    
    @BindView(2131296358)
    Button btn_apn;
    
    @BindView(2131296359)
    Button btn_apn_password;
    
    @BindView(2131296360)
    Button btn_apn_user_name;
    
    @BindView(2131296377)
    Button btn_ip2;
    
    @BindView(2131296382)
    Button btn_port2;
    
    View.OnClickListener clickListener;
    
    ActionMenuViewHolder(View param1View, View.OnClickListener param1OnClickListener) {
      ButterKnife.bind(this, param1View);
      this.clickListener = param1OnClickListener;
    }
    
    @OnClick({2131296362, 2131296375, 2131296376, 2131296381, 2131296391, 2131296392, 2131296365, 2131296367, 2131296364, 2131296382, 2131296377, 2131296358, 2131296360, 2131296359})
    public void onViewClicked(View param1View) {
      this.clickListener.onClick(param1View);
    }
  }
  
  static class QueryMenuViewHolder {
    @BindView(2131296356)
    Button btnAllParam;
    
    @BindView(2131296358)
    Button btnApn;
    
    @BindView(2131296360)
    Button btnApnUserName;
    
    @BindView(2131296364)
    Button btnCancel;
    
    @BindView(2131296365)
    Button btnCatWait;
    
    @BindView(2131296367)
    Button btnDate;
    
    @BindView(2131296372)
    Button btnGrps;
    
    @BindView(2131296375)
    Button btnId;
    
    @BindView(2131296376)
    Button btnIp;
    
    @BindView(2131296359)
    Button btnPassword;
    
    @BindView(2131296381)
    Button btnPort;
    
    @BindView(2131296391)
    Button btnSaveInterval;
    
    @BindView(2131296392)
    Button btnSendInterval;
    
    @BindView(2131296394)
    Button btnSingle;
    
    @BindView(2131296398)
    Button btnTemp;
    
    @BindView(2131296399)
    Button btnVol;
    
    View.OnClickListener clickListener;
    
    QueryMenuViewHolder(View param1View, View.OnClickListener param1OnClickListener) {
      ButterKnife.bind(this, param1View);
      this.clickListener = param1OnClickListener;
    }
    
    @OnClick({2131296394, 2131296372, 2131296358, 2131296398, 2131296399, 2131296360, 2131296359, 2131296375, 2131296391, 2131296392, 2131296365, 2131296376, 2131296381, 2131296367, 2131296356, 2131296364})
    public void onViewClicked(View param1View) {
      this.clickListener.onClick(param1View);
    }
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/view/RtuSettingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */