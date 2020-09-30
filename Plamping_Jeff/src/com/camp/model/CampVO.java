package com.camp.model;

public class CampVO implements java.io.Serializable {
	private String campno;
	private String campvdno;
	private String campname;
	private String campctno;
	private Integer campqty;
	private Integer campprice;
	private Integer campstat;
	private byte[] camppic;
	
	public CampVO() {
		super();
	}
	
	public CampVO(String campno, String campvdno, String campname, String campctno, Integer campqty, Integer campprice,	Integer campstat, byte[] camppic) {
		super();
		this.campno = campno;
		this.campvdno = campvdno;
		this.campname = campname;
		this.campctno = campctno;
		this.campqty = campqty;
		this.campprice = campprice;
		this.campstat = campstat;
		this.camppic = camppic;
	}

	public String getCampno() {
		return campno;
	}

	public void setCampno(String campno) {
		this.campno = campno;
	}

	public String getCampvdno() {
		return campvdno;
	}

	public void setCampvdno(String campvdno) {
		this.campvdno = campvdno;
	}

	public String getCampname() {
		return campname;
	}

	public void setCampname(String campname) {
		this.campname = campname;
	}

	public String getCampctno() {
		return campctno;
	}

	public void setCampctno(String campctno) {
		this.campctno = campctno;
	}

	public Integer getCampqty() {
		return campqty;
	}

	public void setCampqty(Integer campqty) {
		this.campqty = campqty;
	}

	public Integer getCampprice() {
		return campprice;
	}

	public void setCampprice(Integer campprice) {
		this.campprice = campprice;
	}

	public Integer getCampstat() {
		return campstat;
	}

	public void setCampstat(Integer campstat) {
		this.campstat = campstat;
	}

	public byte[] getCamppic() {
		return camppic;
	}

	public void setCamppic(byte[] camppic) {
		this.camppic = camppic;
	}
	
}
