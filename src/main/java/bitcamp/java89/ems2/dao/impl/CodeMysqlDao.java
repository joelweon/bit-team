package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.CodeDao;
import bitcamp.java89.ems2.domain.Code;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.util.DataSource;

@Repository("codeDao")
public class CodeMysqlDao implements CodeDao {
  @Autowired DataSource ds;


//  public boolean exist(int memberNo) throws Exception {
//    Connection con = ds.getConnection(); 
//    try (
//      PreparedStatement stmt = con.prepareStatement(
//          "select count(*)"
//          + " from mgr left outer join memb on mgr.mrno=memb.mno"
//          + " where mrno=?"); ) {
//      
//      stmt.setInt(1, memberNo);
//      ResultSet rs = stmt.executeQuery();
//      
//      rs.next();
//      int count = rs.getInt(1);
//      rs.close();
//      
//      if (count > 0) {
//        return true;
//      } else {
//        return false;
//      }
//      
//    } finally {
//      ds.returnConnection(con);
//    }
//  } 
  
  public ArrayList<Code> getList() throws Exception {
    ArrayList<Code> list = new ArrayList<>();
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select cono, rdt, vw_cnt, pl" +
          " from code" + 
          " left outer join content on code.cdno=content.cono");
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Code code = new Code();
        code.setContentNo(rs.getInt("cono"));
        code.setRegisterDate(rs.getString("rdt"));
        code.setViewCount(rs.getInt("vw_cnt"));
        code.setProgLanguage(rs.getString("pl"));
        
        list.add(code);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

//  public boolean exist(String email) throws Exception {
//    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
//    try (
//      PreparedStatement stmt = con.prepareStatement(
//          "select count(*)"
//          + " from mgr left outer join memb on mgr.mrno=memb.mno"
//          + " where email=?"); ) {
//      
//      stmt.setString(1, email);
//      ResultSet rs = stmt.executeQuery();
//      
//      rs.next();
//      int count = rs.getInt(1);
//      rs.close();
//      
//      if (count > 0) {
//        return true;
//      } else {
//        return false;
//      }
//      
//    } finally {
//      ds.returnConnection(con);
//    }
//  }
  
  public void insert(Code code) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into code(cdno,conts,pl) values(?,?,?)"); ) {
      
      stmt.setInt(1, code.getContentNo());
      stmt.setString(2, code.getCode());
      stmt.setString(3, code.getProgLanguage());
      stmt.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  }

  public Code getOne(int contentNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select cono, rdt, conts, pl"
          + " from code"
          + " left outer join content on code.cdno=content.cono"
          + " where cono=?");) {

      stmt.setInt(1, contentNo);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Code code = new Code();
        code.setContentNo(rs.getInt("cono"));
        code.setRegisterDate(rs.getString("rdt"));
        code.setCode(rs.getString("conts"));
        code.setProgLanguage(rs.getString("pl"));
        rs.close();
        return code;
        
      } else {
        rs.close();
        return null;
      }
    } finally {
      ds.returnConnection(con);
    }
  }

  public void update(Code code) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update code set"
                + " conts=?,pl=?"
                + " where cdno=?"); ) {
      
      stmt.setString(1, code.getCode());
      stmt.setString(2, code.getProgLanguage());
      stmt.setInt(3, code.getContentNo());

      stmt.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  }


  public void delete(int contentNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from code where cdno=?"); ) {
      
      stmt.setInt(1, contentNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }

  @Override
  public boolean exist(int contentNo) {
    // TODO Auto-generated method stub
    return false;
  }

}
