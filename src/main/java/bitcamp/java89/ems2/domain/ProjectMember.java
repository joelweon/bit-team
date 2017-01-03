package bitcamp.java89.ems2.domain;

public class ProjectMember extends Project {
  private static final long serialVersionUID = 1L;
  
  protected String rol;
  protected int memberNo; 
  
  public String getRol() {
    return rol;
  }
  
  public void setRol(String rol) {
    this.rol = rol;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }
  
  
}
