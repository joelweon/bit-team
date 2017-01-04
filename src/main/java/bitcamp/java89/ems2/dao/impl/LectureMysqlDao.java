package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.LectureDao;
import bitcamp.java89.ems2.domain.Lecture;
import bitcamp.java89.ems2.util.DataSource;

@Repository("lectureDao")
public class LectureMysqlDao implements LectureDao {
  @Autowired DataSource ds;

  @Override
  public boolean exist(int lectureNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*)"
          + " from lect"
          + " where lno=?"); ) {
      
      stmt.setInt(1, lectureNo);
      ResultSet rs = stmt.executeQuery();
      
      rs.next();
      int count = rs.getInt(1);
      rs.close();
      
      if (count > 0) {
        return true;
      } else {
        return false;
      }
      
    } finally {
      ds.returnConnection(con);
    }
  }

  @Override
  public ArrayList<Lecture> getList() throws Exception {
    ArrayList<Lecture> list = new ArrayList<>();
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select lno, titl, dscp, sdt, edt, qty, pric, thrs"
          + " from lect");
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Lecture lecture = new Lecture();
        
        lecture.setLectureNo(rs.getInt("lno"));
        lecture.setTitle(rs.getString("titl"));
        lecture.setDescription(rs.getString("dscp"));
        lecture.setStartDate(rs.getString("sdt"));
        lecture.setEndDate(rs.getString("edt"));
        lecture.setTotalStudentNumber(rs.getInt("qty"));
        lecture.setPrice(rs.getInt("pric"));
        lecture.setTotalTime(rs.getInt("thrs"));
        
        list.add(lecture);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

  @Override
  public void insert(Lecture lecture) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into lect(titl,crmno,mrno,dscp,sdt,edt,qty,pric,thrs) "
          + "values(?,?,?,?,?,?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS); ) {

      stmt.setString(1, lecture.getTitle());
      stmt.setInt(2, lecture.getClassroomNo());
      stmt.setInt(3, lecture.getManagerNo());
      stmt.setString(4, lecture.getDescription());
      stmt.setString(5, lecture.getStartDate());
      stmt.setString(6, lecture.getEndDate());
      stmt.setInt(7, lecture.getTotalStudentNumber());
      stmt.setInt(8, lecture.getPrice());
      stmt.setInt(9, lecture.getTotalTime());
      stmt.executeUpdate();
      
      ResultSet keyRS = stmt.getGeneratedKeys();
      keyRS.next();
      lecture.setLectureNo(keyRS.getInt(1));
      keyRS.close();

      insertTeacherNoList(lecture);
      
    } finally {
      ds.returnConnection(con);
    }
    
  }
  
  private void insertTeacherNoList(Lecture lecture) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into tchr_lect(lno,tno) values(?,?)"); ) {
      
      List<Integer> teacherNoList = lecture.getTeacherNoList();
      
      for (Integer teacherNo : teacherNoList) {
        if (teacherNo == null) {
          continue;
        }
        stmt.setInt(1, lecture.getLectureNo());
        stmt.setInt(2, teacherNo);
        stmt.executeUpdate();
      }
    } finally {
      ds.returnConnection(con);
    }
  }

  @Override
  public Lecture getOne(int lectureNo) throws Exception {
    Connection con = ds.getConnection(); 
    Lecture lecture = null;
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select lno, lect.crmno, mrno, titl, dscp, sdt, edt, qty, pric, thrs, memb.name, croom.name "
          + "from lect "
          + "left outer join croom on lect.crmno=croom.crmno "
          + "left outer join memb on lect.mrno=memb.mno "
          + "where lno=?");) {

      stmt.setInt(1, lectureNo);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        lecture = new Lecture();
        lecture.setLectureNo(lectureNo);
        lecture.setTitle(rs.getString("titl"));
        lecture.setClassroomNo(rs.getInt("crmno"));
        lecture.setManagerNo(rs.getInt("mrno"));
        lecture.setDescription(rs.getString("dscp"));
        lecture.setStartDate(rs.getString("sdt"));
        lecture.setEndDate(rs.getString("edt"));
        lecture.setTotalStudentNumber(rs.getInt("qty"));
        lecture.setPrice(rs.getInt("pric"));
        lecture.setTotalTime(rs.getInt("pric"));
        lecture.setManagerName(rs.getString("memb.name"));
        lecture.setClassroomName(rs.getString("croom.name"));
        rs.close();
        
        // 강사들은 나중에 하자.
        String allName = getAllTeacher(lecture);
        lecture.setTeacherAllName(allName);
        return lecture;
      } else {
        rs.close();
        return null;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
  
  private String getAllTeacher(Lecture lecture) throws Exception {
    Connection con = ds.getConnection(); 
    String allTeacherName = "";
    ArrayList<String> teacherNameList = new ArrayList<>();
    ArrayList<Integer> teacherNoList = new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select memb.name, memb.mno from tchr_lect left outer join memb on tchr_lect.tno=memb.mno where lno=?");) {

      stmt.setInt(1, lecture.getLectureNo());
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        String temp = rs.getString("memb.name");
        teacherNameList.add(temp);
        teacherNoList.add(rs.getInt("memb.mno"));
        allTeacherName += temp + ",";
      } 
      rs.close();
    } finally {
      ds.returnConnection(con);
      lecture.setTeacherNameList(teacherNameList);
      lecture.setTeacherNoList(teacherNoList);
    }
    if (allTeacherName.length() != 0) {
      allTeacherName = allTeacherName.substring(0, allTeacherName.length()-1);
    }
    return allTeacherName;
  }

  @Override
  public void update(Lecture lecture) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "update lect set"
          + " titl=?, crmno=?, mrno=?, dscp=?, sdt=?, edt=? ,qty=?, pric=?, thrs=?"
          + " where lno=?"); ) {
      
      stmt.setString(1, lecture.getTitle());
      stmt.setInt(2, lecture.getClassroomNo());
      stmt.setInt(3, lecture.getManagerNo());
      stmt.setString(4, lecture.getDescription());
      stmt.setString(5, lecture.getStartDate());
      stmt.setString(6, lecture.getEndDate());
      stmt.setInt(7, lecture.getTotalStudentNumber());
      stmt.setInt(8, lecture.getPrice());
      stmt.setInt(9, lecture.getTotalTime());
      stmt.setInt(10, lecture.getLectureNo());
      
      stmt.executeUpdate();
      
      deleteTeacherLecture(lecture.getLectureNo());
      insertTeacherNoList(lecture);
      
    } finally {
      ds.returnConnection(con);
    }
  }

  @Override
  public void delete(int lectureNo) throws Exception {
    Connection con = ds.getConnection(); 
    deleteTeacherLecture(lectureNo);
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from lect where lno=?"); ) {
      
      stmt.setInt(1, lectureNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
  private void deleteTeacherLecture(int lectureNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from tchr_lect where lno=?"); ) {
      
      stmt.setInt(1, lectureNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
}
