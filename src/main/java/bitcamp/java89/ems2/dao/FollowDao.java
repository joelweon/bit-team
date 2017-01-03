package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;

public interface FollowDao {
  boolean exist(String email) throws Exception;
  public boolean exist(int keyWord) throws Exception;
  public Member existMember(int keyWord) throws Exception;
  public boolean existFollow(int keyWord) throws Exception;
  ArrayList<Follow> getFollowingList(String keyWord) throws Exception;
  ArrayList<Follow> getFollowerList(String keyWord) throws Exception;
  Follow getOne(String email) throws Exception;
  void insert(Follow follow) throws Exception;
  void delete(Follow follow) throws Exception;
}