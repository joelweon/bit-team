package bitcamp.java89.ems2.domain;

public class Lecture extends Member {
  private static final long serialVersionUID = 1L;
  protected int lectureNumber; // 강의 일련번호
  protected int possibleNumber; // 수강가능인원
  protected int price; // 수업료
  protected String title; // 제목
  protected String explanation; // 설명 
  protected String startDate; // 시작일
  protected String endDate; // 종료일
  protected String allTime; // 총시간
  
  public int getLectureNumber() {
    return lectureNumber;
  }
  public void setLectureNumber(int lectureNumber) {
    this.lectureNumber = lectureNumber;
  }
  public int getPossibleNumber() {
    return possibleNumber;
  }
  public void setPossibleNumber(int possibleNumber) {
    this.possibleNumber = possibleNumber;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getTitl() {
    return title;
  }
  public void setTitl(String title) {
    this.title = title;
  }
  public String getExplanation() {
    return explanation;
  }
  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getAllTime() {
    return allTime;
  }
  public void setAllTime(String allTime) {
    this.allTime = allTime;
  }
}
