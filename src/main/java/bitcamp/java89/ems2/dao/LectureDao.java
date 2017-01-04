package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Lecture;

public interface LectureDao {
  boolean exist(int lectureNo) throws Exception;
  ArrayList<Lecture> getList() throws Exception;
  void insert(Lecture lecture) throws Exception;
  Lecture getOne(int lectureNo) throws Exception;
  void update(Lecture lecture) throws Exception;
  void delete(int lectureNo) throws Exception;
}

