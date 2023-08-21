package me.rocyang.bluetooth.rdradar.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ValidRule {
  private int length;
  
  private int max;
  
  private int maxLength;
  
  private int min;
  
  private int minLength;
  
  private Boolean validDate;
  
  private Boolean validId;
  
  private Boolean validIp;
  
  private Boolean validLength;
  
  private Boolean validMax;
  
  private Boolean validMaxLength;
  
  private Boolean validMin;
  
  private Boolean validMinLength;
  
  public ValidRule() {
    Boolean bool = Boolean.valueOf(false);
    this.validLength = bool;
    this.validMaxLength = bool;
    this.validMinLength = bool;
    this.validMax = bool;
    this.validMin = bool;
    this.validIp = bool;
    this.validId = bool;
    this.validDate = bool;
  }
  
  public int getLength() {
    return this.length;
  }
  
  public int getMax() {
    return this.max;
  }
  
  public int getMaxLength() {
    return this.maxLength;
  }
  
  public int getMin() {
    return this.min;
  }
  
  public int getMinLength() {
    return this.minLength;
  }
  
  public Boolean getValidDate() {
    return this.validDate;
  }
  
  public Boolean getValidId() {
    return this.validId;
  }
  
  public Boolean getValidIp() {
    return this.validIp;
  }
  
  public Boolean getValidLength() {
    return this.validLength;
  }
  
  public Boolean getValidMax() {
    return this.validMax;
  }
  
  public Boolean getValidMaxLength() {
    return this.validMaxLength;
  }
  
  public Boolean getValidMin() {
    return this.validMin;
  }
  
  public Boolean getValidMinLength() {
    return this.validMinLength;
  }
  
  public void setLength(int paramInt) {
    this.length = paramInt;
    this.maxLength = paramInt;
    Boolean bool = Boolean.valueOf(true);
    this.validMaxLength = bool;
    this.validLength = bool;
  }
  
  public void setMax(int paramInt) {
    this.validMax = Boolean.valueOf(true);
    this.max = paramInt;
    setMaxLength(String.valueOf(paramInt).length());
  }
  
  public void setMaxLength(int paramInt) {
    this.maxLength = paramInt;
    this.validMaxLength = Boolean.valueOf(true);
  }
  
  public void setMin(int paramInt) {
    this.validMin = Boolean.valueOf(true);
    this.min = paramInt;
  }
  
  public void setMinLength(int paramInt) {
    this.minLength = paramInt;
    this.validMinLength = Boolean.valueOf(true);
  }
  
  public void setValidDate(Boolean paramBoolean) {
    this.validDate = paramBoolean;
    setLength(14);
  }
  
  public void setValidId(Boolean paramBoolean) {
    this.validId = paramBoolean;
    if (paramBoolean.booleanValue()) {
      setMaxLength(10);
      setMinLength(4);
    } 
  }
  
  public void setValidIp(Boolean paramBoolean) {
    this.validIp = paramBoolean;
    setMinLength(6);
  }
  
  public void setValidLength(Boolean paramBoolean) {
    this.validLength = paramBoolean;
  }
  
  public void setValidMax(Boolean paramBoolean) {
    this.validMax = paramBoolean;
  }
  
  public void setValidMaxLength(Boolean paramBoolean) {
    this.validMaxLength = paramBoolean;
  }
  
  public void setValidMin(Boolean paramBoolean) {
    this.validMin = paramBoolean;
  }
  
  public void setValidMinLength(Boolean paramBoolean) {
    this.validMinLength = paramBoolean;
  }
  
  public Boolean valid(String paramString) {
    boolean bool = this.validLength.booleanValue();
    boolean bool1 = false;
    Boolean bool2 = Boolean.valueOf(false);
    if (bool && this.length != paramString.length())
      return bool2; 
    if (this.validMaxLength.booleanValue() && paramString.length() > this.maxLength)
      return bool2; 
    if (this.validMinLength.booleanValue() && paramString.length() < this.minLength)
      return bool2; 
    if (this.validMin.booleanValue() && Integer.parseInt(paramString) < this.min)
      return bool2; 
    if (this.validMax.booleanValue() && Integer.parseInt(paramString) > this.max)
      return bool2; 
    if (this.validId.booleanValue())
      return Boolean.valueOf(true); 
    if (this.validIp.booleanValue()) {
      if (Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(paramString).matches())
        return Boolean.valueOf(true); 
      if (paramString.length() <= 12)
        bool1 = true; 
      return Boolean.valueOf(bool1);
    } 
    if (this.validDate.booleanValue()) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      try {
        simpleDateFormat.parse(paramString);
      } catch (ParseException parseException) {
        return bool2;
      } 
    } 
    return Boolean.valueOf(true);
  }
}


/* Location:              /home/brandon/levelMeter_APK/dex2jar-2.x/dex-tools/build/distributions/dex-tools-2.2-SNAPSHOT/classes-dex2jar.jar!/me/rocyang/bluetooth/rdradar/entity/ValidRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */