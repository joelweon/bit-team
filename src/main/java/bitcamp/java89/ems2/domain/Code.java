package bitcamp.java89.ems2.domain;

import java.util.ArrayList;

public class Code extends Content {
  
  private static final long serialVersionUID = 1L;
  protected String code;
  protected String progLanguage;
  protected ArrayList<Tag>tagList;
  
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
  public ArrayList<Tag> getTagList() {
    return tagList;
  }
  public void setTagList(ArrayList<Tag> tagList) {
    this.tagList = tagList;
  }
  
  
}
