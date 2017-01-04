package bitcamp.java89.ems2.domain;

public class Todo extends ProjectMember {
  private static final long serialVersionUID = 1L;
 

  protected int sequence; // 순서
  protected String tdContents; // 내용
  protected String state; // 상태
  protected String stateDate; // 상태설정일
  
 
  public int getSequence() {
    return sequence;
  }
  public void setSequence(int sequence) {
    this.sequence = sequence;
  }
  public String getTdContents() {
    return tdContents;
  }
  public void setTdContents(String tdContents) {
    this.tdContents = tdContents;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getStateDate() {
    return stateDate;
  }
  public void setStateDate(String stateDate) {
    this.stateDate = stateDate;
  }
  
 
 
 
}
