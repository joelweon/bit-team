package bitcamp.java89.ems2.domain;

public class LectureApplication extends Lecture {
  private static final long serialVersionUID = 1L;
  protected String reportingDate; //신청일
  protected int lectAppyNo; 
  protected int statement; // 상태
  
  public int getLectAppyNo() {
    return lectAppyNo;
  }
  public void setLectAppyNo(int lectAppyNo) {
    this.lectAppyNo = lectAppyNo;
  }
  public String getReportingDate() {
    return reportingDate;
  }
  public void setReportingDate(String reportingDate) {
    this.reportingDate = reportingDate;
  }
  public int getStatement() {
    return statement;
  }
  public void setStatement(int statement) {
    this.statement = statement;
  }
}
