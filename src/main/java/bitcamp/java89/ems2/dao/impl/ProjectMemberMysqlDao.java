package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.util.DataSource;

@Repository("projectMemberDao")
public class ProjectMemberMysqlDao implements ProjectMemberDao {
  @Autowired  DataSource ds;


  public ArrayList<ProjectMember> getList() throws Exception {
    ArrayList<ProjectMember> list = new ArrayList<>();
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.

    try (
        PreparedStatement stmt = con.prepareStatement(
            "select proj_memb.pjno, memb.mno, titl, name, rol" +
                " from proj_memb" +
                " left outer join proj on proj.pjno=proj_memb.pjno" +
            " left outer join memb on memb.mno=proj_memb.mno");

        ResultSet rs = stmt.executeQuery(); ){

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectNo(rs.getInt("pjno"));
        projectMember.setTitle(rs.getString("titl"));
        projectMember.setName(rs.getString("name"));
        projectMember.setRol(rs.getString("rol"));
        projectMember.setMemberNo(Integer.parseInt(rs.getString("memb.mno")));

        list.add(projectMember);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }


  public void insert(ProjectMember projectMember) throws Exception {
    Connection con = ds.getConnection(); 
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into proj_memb(mno,pjno,rol) values(?,?,?)"); ) {

      stmt.setInt(1, projectMember.getMemberNo());
      stmt.setInt(2, projectMember.getProjectNo());
      stmt.setString(3, projectMember.getRol());
      stmt.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  } 

  public ProjectMember getOne(int projectNo, int memberNo) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select proj_memb.pjno, titl, name, rol" +
                " from proj_memb" +
                " left outer join proj on proj.pjno=proj_memb.pjno" +
                " left outer join memb on memb.mno=proj_memb.mno"
                + " where proj_memb.pjno=? and proj_memb.mno=?");) {

      stmt.setInt(1, projectNo);
      stmt.setInt(2, memberNo);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        ProjectMember projectmember = new ProjectMember();
        projectmember.setProjectNo(projectNo);
        projectmember.setTitle(rs.getString("titl"));
        projectmember.setName(rs.getString("name"));
        projectmember.setRol(rs.getString("rol"));
        rs.close();
        return projectmember;

      } else {
        rs.close();
        return null;
      }
    } finally {
      ds.returnConnection(con);
    }
  }

  public void delete(ProjectMember projectMember) throws Exception {
    Connection con = ds.getConnection(); 
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from proj_memb where mno=? and pjno=?"); ) {

      stmt.setInt(1, projectMember.getMemberNo());
      stmt.setInt(2, projectMember.getProjectNo());

      stmt.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  }


  public void update(ProjectMember projectMember) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update proj_memb set "
            + " rol=?"
            + "where mno=? and pjno=?" ); ) {

      stmt.setString(1, projectMember.getRol());
      stmt.setInt(2, projectMember.getMemberNo());
      stmt.setInt(3, projectMember.getProjectNo());

      stmt.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  }
  
  public ArrayList<ProjectMember> getTitleList() throws Exception {
    ArrayList<ProjectMember> list = new ArrayList<>();
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "select titl from proj");

        ResultSet rs = stmt.executeQuery(); ){

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        ProjectMember projectMember = new ProjectMember();
        projectMember.setTitle(rs.getString("titl"));

        list.add(projectMember);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public ArrayList<ProjectMember> getMemberList() throws Exception{
    ArrayList<ProjectMember> list = new ArrayList<>();
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "select memb.name from memb");

        ResultSet rs = stmt.executeQuery(); ){

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(rs.getString("name"));

        list.add(projectMember);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
}



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




