package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.domain.Lecture;
import bitcamp.java89.ems2.domain.LectureApplication;
import bitcamp.java89.ems2.util.DataSource;

@Repository("lectureAppyDao")
public class LectureAppyMysqlDao {
  @Autowired DataSource dataSource;

  // 학생의 수강여부 확인용 
  public boolean exist(Lecture lecture) throws Exception {
    Connection con = dataSource.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select count(*) from lect_appy where sno=? and lno=?"); ) {

      stmt.setInt(1, lecture.getMemberNo());
      stmt.setInt(2, lecture.getLectureNumber());
      ResultSet resultSet = stmt.executeQuery();

      resultSet.next();
      int count = resultSet.getInt(1);
      resultSet.close();

      if (count > 0) {
        return true;
      } else {
        return false;
      }

    } finally {
      dataSource.returnConnection(con);
    }
  } 

  // 강의정보 디테일
  public LectureApplication getOne(int[] number) throws Exception {
    Connection con = dataSource.getConnection(); 
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select lno, titl, dscp, sdt, edt, qty, pric, thrs"
            + " from lect"
            + " where lno=?;");
        PreparedStatement stmt2 = con.prepareStatement(
            "select rdt, stat"
            + " from lect_appy"
            + " where lno=? and sno=?");) {
      
      stmt.setInt(1, number[0]);
      stmt2.setInt(1, number[0]);
      stmt2.setInt(2, number[1]);
      
      ResultSet resultSet = stmt.executeQuery();
      ResultSet resultSet2 = stmt2.executeQuery();

      if (resultSet.next()) {
        LectureApplication lectAppy = new LectureApplication(); 
        lectAppy.setLectureNumber(resultSet.getInt("lno"));
        lectAppy.setTitl(resultSet.getString("titl"));
        lectAppy.setExplanation(resultSet.getString("dscp"));
        lectAppy.setStartDate(resultSet.getString("sdt"));
        lectAppy.setEndDate(resultSet.getString("edt"));
        lectAppy.setPossibleNumber(resultSet.getInt("qty"));
        lectAppy.setPrice(resultSet.getInt("pric"));
        lectAppy.setAllTime(resultSet.getString("thrs"));
        
        if (resultSet2.next()) {
          lectAppy.setReportingDate(resultSet2.getString("rdt"));
          lectAppy.setStartDate(resultSet2.getString("stat"));
        }
        
        resultSet.close();
        resultSet2.close();
        return lectAppy;

      } else {
        resultSet.close();
        return null;
      }
    } finally {
      dataSource.returnConnection(con);
    }
  }

  public ArrayList<LectureApplication> getList() throws Exception {
    ArrayList<LectureApplication> list = new ArrayList<>();
    Connection con = dataSource.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select lno, titl, dscp, pric"
            + " from lect;");
        ResultSet resultSet = stmt.executeQuery(); ){

      while (resultSet.next()) {
        LectureApplication lectAppy = new LectureApplication(); 
        lectAppy.setLectureNumber(resultSet.getInt("lno"));
        lectAppy.setTitl(resultSet.getString("titl"));
        lectAppy.setExplanation(resultSet.getString("dscp"));
        lectAppy.setPrice(resultSet.getInt("pric"));

        list.add(lectAppy);
      }
    } finally {
      dataSource.returnConnection(con);
    }
    return list;
  }
  
  // 학생이 어떤 강의를 수강신청 했는지 확인
  public ArrayList<LectureApplication> getAppList(int memberNo) throws Exception {
    ArrayList<LectureApplication> list = new ArrayList<LectureApplication>();
    Connection con = dataSource.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select titl,dscp,lano from memb,stud,lect,lect_appy" //,name,stud.sno,rdt
            + " where memb.mno=stud.sno and lect.lno = lect_appy.lno" 
            + " and stud.sno = lect_appy.sno and mno=?;"); ) {
      
      /* "select titl from memb,stud,lect,lect_appy" //,name,stud.sno,rdt
            + " where memb.mno=stud.sno and lect.lno = lect_appy.lno" 
            + " and stud.sno = lect_appy.sno and where mno=?;"*/
      stmt.setInt(1, memberNo);
      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        LectureApplication lectAppy = new LectureApplication();
        
        lectAppy.setLectAppyNo(resultSet.getInt("lano"));
        lectAppy.setTitl(resultSet.getString("titl"));
        lectAppy.setExplanation(resultSet.getString("dscp"));

        list.add(lectAppy);
      }
    } finally {
      dataSource.returnConnection(con);
    }
    return list;
  }

  public void insert(LectureApplication lectAppy) throws Exception {
    Connection con = dataSource.getConnection(); 
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into lect_appy(lno, sno, rdt, stat) values(?,?,DATE_FORMAT(NOW(), '%Y-%c-%e %T'),?)"); ) {
      
      stmt.setInt(1, lectAppy.getLectureNumber()); 
      stmt.setInt(2, lectAppy.getMemberNo());
      stmt.setInt(3, lectAppy.getStatement());
          
      stmt.executeUpdate();

    } finally {
      dataSource.returnConnection(con);
    }
  }  

  public void delete(int lectureNo) throws Exception {
    Connection con = dataSource.getConnection(); 
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from lect_appy where lano=?"); ) {

      stmt.setInt(1, lectureNo);

      stmt.executeUpdate();

    } finally {
      dataSource.returnConnection(con);
    }
  }
}
