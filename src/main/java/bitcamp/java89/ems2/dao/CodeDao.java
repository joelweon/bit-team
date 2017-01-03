package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Code;

public interface CodeDao {
  boolean exist(int contentNo);
  ArrayList<Code> getList() throws Exception;
  void insert(Code code) throws Exception;
  Code getOne(int contentNo) throws Exception;
  void update(Code code) throws Exception;
  void delete(int contentNo) throws Exception;
}
