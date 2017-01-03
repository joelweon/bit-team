package bitcamp.java89.ems2.domain;

public class Follow extends Member {
  private static final long serialVersionUID = 1L;

  protected int followSubject; /// 해당 주체
  protected int followObject; //// 객체
  protected int followMno; /////// 팔로워 일련번호
  protected String followName; /// 팔로워 이름
  protected String followEmail; // 팔로워 이메일
  protected String followTel; //// 팔로워 전화번호
  protected String followPhoto;

  public String getFollowPhoto() {
		return followPhoto;
	}

	public void setFollowPhoto(String followPhoto) {
		this.followPhoto = followPhoto;
	}

	public int getFollowSubject() {
    return followSubject;
  }

  public void setFollowSubject(int followSubject) {
    this.followSubject = followSubject;
  }

  public int getFollowObject() {
    return followObject;
  }

  public void setFollowObject(int followObject) {
    this.followObject = followObject;
  }
  public int getFollowMno() {
    return followMno;
  }

  public void setFollowMno(int followMno) {
    this.followMno = followMno;
  }

  public String getFollowName() {
    return followName;
  }

  public void setFollowName(String followName) {
    this.followName = followName;
  }

  public String getFollowEmail() {
    return followEmail;
  }

  public void setFollowEmail(String followEmail) {
    this.followEmail = followEmail;
  }

  public String getFollowTel() {
    return followTel;
  }

  public void setFollowTel(String followTel) {
    this.followTel = followTel;
  }

}
