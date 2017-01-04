package bitcamp.java89.ems2.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecture implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected ArrayList<Integer> teacherNoList; //강사 일련번호 리스트.
  protected ArrayList<String> teacherNameList;
  protected int lectureNo; // 강의 일련번호
  protected int classroomNo;  //강의실 일련번호
  protected int managerNo;  //매니저 일련번호
  protected int totalStudentNumber; // 수강가능인원
  protected int price; // 수업료
  protected int totalTime; // 총시간
  protected String title; // 제목
  protected String description; // 설명 
  protected String startDate; // 시작일
  protected String endDate; // 종료일
  protected String managerName;
  protected String classroomName;
  protected String teacherAllName;
  
  
  public String getTeacherAllName() {
    return teacherAllName;
  }
  public void setTeacherAllName(String teacherAllName) {
    this.teacherAllName = teacherAllName;
  }
  public ArrayList<Integer> getTeacherNoList() {
    return teacherNoList;
  }
  public void setTeacherNoList(ArrayList<Integer> teacherNoList) {
    this.teacherNoList = teacherNoList;
  }
  public ArrayList<String> getTeacherNameList() {
    return teacherNameList;
  }
  public void setTeacherNameList(ArrayList<String> teacherNameList) {
    this.teacherNameList = teacherNameList;
  }
  public int getLectureNo() {
    return lectureNo;
  }
  public void setLectureNo(int lectureNo) {
    this.lectureNo = lectureNo;
  }
  public int getClassroomNo() {
    return classroomNo;
  }
  public void setClassroomNo(int classroomNo) {
    this.classroomNo = classroomNo;
  }
  public int getManagerNo() {
    return managerNo;
  }
  public void setManagerNo(int managerNo) {
    this.managerNo = managerNo;
  }
  public int getTotalStudentNumber() {
    return totalStudentNumber;
  }
  public void setTotalStudentNumber(int totalStudentNumber) {
    this.totalStudentNumber = totalStudentNumber;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getTotalTime() {
    return totalTime;
  }
  public void setTotalTime(int totalTime) {
    this.totalTime = totalTime;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
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
  public String getManagerName() {
    return managerName;
  }
  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }
  public String getClassroomName() {
    return classroomName;
  }
  public void setClassroomName(String classroomName) {
    this.classroomName = classroomName;
  }
 
}
