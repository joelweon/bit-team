/* FollowMysqlDao.java */
package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.FollowDao;
import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.util.DataSource;

@Repository("followDao")
public class FollowMysqlDao implements FollowDao {
  @Autowired DataSource ds;

  // 이메일로 데이터 체크
  public boolean exist(String keyWord) throws Exception {
    Connection con = ds.getConnection();

    // 해당 이메일을 멤버테이블 내에 데이터 체크
    try (PreparedStatement stmt = con.prepareStatement("SELECT count(*) " + "FROM MEMB " + "WHERE MEMB.EMAIL=?");) {

      stmt.setString(1, keyWord);
      ResultSet rs = stmt.executeQuery();

      rs.next();
      int count = rs.getInt(1);
      rs.close();

      // 멤버테이블 내에 데이터 유무
      if (count > 0) {
        return true;
      } else {
        return false;
      }

    } finally {
      ds.returnConnection(con);
    }
  }
  
  // 이메일로 데이터 체크
  public boolean exist(int keyWord) throws Exception {
    Connection con = ds.getConnection();

    // 해당 이메일을 멤버테이블 내에 데이터 체크
    try (PreparedStatement stmt = con.prepareStatement("SELECT count(*) " + "FROM MEMB " + "WHERE MEMB.MNO=?");) {

      stmt.setInt(1, keyWord);
      ResultSet rs = stmt.executeQuery();

      rs.next();
      int count = rs.getInt(1);
      rs.close();

      // 멤버테이블 내에 데이터 유무
      if (count > 0) {
        return true;
      } else {
        return false;
      }

    } finally {
      ds.returnConnection(con);
    }
  }
  
  public Member existMember(int keyWord) throws Exception {
    Connection con = ds.getConnection();

    // 해당 이메일을 멤버테이블 내에 데이터 체크
    try (PreparedStatement stmt = con.prepareStatement("SELECT * " + "FROM MEMB " + "WHERE MEMB.MNO=?");) {

      stmt.setInt(1, keyWord);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Follow follow = new Follow();
        follow.setMemberNo(rs.getInt("MNO"));
        follow.setName(rs.getString("NAME"));
        follow.setTel(rs.getString("TEL"));
        follow.setEmail(rs.getString("EMAIL"));
        rs.close();
        return follow;

      } else {
        rs.close();
        return null;
      }

    } finally {
      ds.returnConnection(con);
    }
  }

  public boolean existFollow(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*)"
          + " from follow"
          + " where fwng_no=?"); ) {
      
      stmt.setInt(1, memberNo);
      
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
  // A'가' 팔로잉 하는 사람 목록 조회(A의 팔로잉)
  public ArrayList<Follow> getFollowingList(String keyWord) throws Exception {
    ArrayList<Follow> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (PreparedStatement stmt = con
        .prepareStatement("SELECT FO.FOWR_NO, MA.MNO, MA.NAME, MA.TEL, MA.EMAIL, FO.FWNG_NO, ME.NAME, ME.TEL, ME.EMAIL"
            + ", stud.path, mgr.path, tch_phot.path "
            + "FROM FOLLOW FO "
            + "LEFT OUTER JOIN MEMB ME ON FO.FWNG_NO = ME.MNO "
            + "LEFT OUTER JOIN MEMB MA ON FO.FOWR_NO = MA.MNO "
            
            + "left outer join stud on FO.FWNG_NO=stud.sno "
            + "left outer join mgr on FO.FWNG_NO=mgr.mrno "
            + "left outer join tch_phot on FWNG_NO=tch_phot.tpno "
            
            + "WHERE MA.EMAIL=?");) {

      stmt.setString(1, keyWord);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        Follow follow = new Follow();
        follow.setMemberNo(rs.getInt("MA.MNO"));
        follow.setName(rs.getString("MA.NAME"));
        follow.setTel(rs.getString("MA.TEL"));
        follow.setEmail(rs.getString("MA.EMAIL"));
        follow.setFollowSubject((rs.getInt("FOWR_NO")));
        follow.setFollowObject((rs.getInt("FWNG_NO")));
        follow.setFollowName(rs.getString("ME.NAME"));
        follow.setFollowTel(rs.getString("ME.TEL"));
        follow.setFollowEmail(rs.getString("ME.EMAIL"));
        if (rs.getString("mgr.path") != null) {
          follow.setFollowPhoto(rs.getString("mgr.path"));
        } else if (rs.getString("stud.path") != null) {
          follow.setFollowPhoto(rs.getString("stud.path"));
        } else if (rs.getString("tch_phot.path") != null) {
          follow.setFollowPhoto(rs.getString("tch_phot.path"));
        }
        list.add(follow);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

  // A'를' 팔로잉 하는 사람 조회(A의 팔로워)
  public ArrayList<Follow> getFollowerList(String keyWord) throws Exception {
    ArrayList<Follow> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (PreparedStatement stmt = con.prepareStatement(
        "SELECT FO.FOWR_NO, ME.NAME, ME.TEL, ME.EMAIL, FO.FWNG_NO, MA.NAME, MA.TEL, MA.EMAIL "
            + ", stud.path, mgr.path, tch_phot.path "
            + "FROM FOLLOW FO "
            + "LEFT OUTER JOIN MEMB ME ON FO.FOWR_NO = ME.MNO " 
            + "LEFT OUTER JOIN MEMB MA ON FO.FWNG_NO = MA.MNO "   
            
            + "left outer join stud on FO.FOWR_NO=stud.sno "
            + "left outer join mgr on FO.FOWR_NO=mgr.mrno "
            + "left outer join tch_phot on FOWR_NO=tch_phot.tpno "

            + "WHERE MA.EMAIL=?");) {
      
      stmt.setString(1, keyWord);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
        Follow follow = new Follow();
        follow.setName("MA.NAME");
        follow.setEmail("MA.EMAIL");
        follow.setTel("MA.TEL");
        follow.setFollowName(rs.getString("ME.NAME"));
        follow.setFollowTel(rs.getString("ME.TEL"));
        follow.setFollowEmail(rs.getString("ME.EMAIL"));
        follow.setFollowSubject((rs.getInt("FWNG_NO")));
        follow.setFollowObject((rs.getInt("FOWR_NO")));
        if (rs.getString("mgr.path") != null) {
          follow.setFollowPhoto(rs.getString("mgr.path"));
        } else if (rs.getString("stud.path") != null) {
          follow.setFollowPhoto(rs.getString("stud.path"));
        } else if (rs.getString("tch_phot.path") != null) {
          follow.setFollowPhoto(rs.getString("tch_phot.path"));
        }
        list.add(follow);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

  // 특정 사람 이메일로 조회
  public Follow getOne(String email) throws Exception {
    Connection con = ds.getConnection();
    try (PreparedStatement stmt = con
        .prepareStatement("SELECT ME.MNO, ME.NAME, ME.TEL " + "FROM MEMB ME WHERE ME.EMAIL=?");) {

      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Follow follow = new Follow();
        follow.setMemberNo(rs.getInt("MNO"));
        follow.setName(rs.getString("NAME"));
        follow.setTel(rs.getString("TEL"));
        follow.setEmail(email);
        rs.close();
        return follow;

      } else {
        rs.close();
        return null;
      }
    } finally {
      ds.returnConnection(con);
    }
  }

  // 팔로우 테이블 내 팔로잉 데이터 삽입
  public void insert(Follow follow) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into follow(fwng_no, fowr_no) values(?,?)"); ) {
      
      stmt.setInt(1, follow.getFollowSubject());
      stmt.setInt(2, follow.getFollowObject());
      stmt.executeUpdate();

    } catch (Exception e) {
      throw new Exception("이미 팔로우한 회원입니다.");
    } finally {
      ds.returnConnection(con);
    }
  } 

  // 데이터 삭제
  public void delete(Follow follow) throws Exception {
    System.out.println("dao delete value: " + follow.getFollowSubject() + ", " + follow.getFollowObject());
    Connection con = ds.getConnection();
    try (PreparedStatement stmt1 = con.prepareStatement("DELETE FROM FOLLOW WHERE FOWR_NO=? AND FWNG_NO=?");
        ) {

      stmt1.setInt(1, follow.getFollowSubject());
      stmt1.setInt(2, follow.getFollowObject());

      stmt1.executeUpdate();

    } finally {
      ds.returnConnection(con);
    }
  }


}

