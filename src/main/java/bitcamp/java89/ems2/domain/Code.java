package bitcamp.java89.ems2.domain;

public class Code extends Content {
  
  private static final long serialVersionUID = 1L;
  protected String code;
  protected String progLanguage;
  
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getProgLanguage() {
    return progLanguage;
  }
  public void setProgLanguage(String progLanguage) {
    this.progLanguage = progLanguage;
  }
  
  
}
