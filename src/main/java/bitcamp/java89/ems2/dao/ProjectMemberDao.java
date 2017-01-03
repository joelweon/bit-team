package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.ProjectMember;

public interface ProjectMemberDao {
  ArrayList<ProjectMember> getList() throws Exception;
  ArrayList<ProjectMember> getTitleList() throws Exception;
  ArrayList<ProjectMember> getMemberList() throws Exception;
  void insert(ProjectMember projectMember) throws Exception;
  ProjectMember getOne(int projectNo, int memberNo) throws Exception;
  void delete(ProjectMember projectMember) throws Exception;
  void update(ProjectMember projectMember) throws Exception;

}
