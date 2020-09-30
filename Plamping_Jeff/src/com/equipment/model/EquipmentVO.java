package com.equipment.model;

public class EquipmentVO implements java.io.Serializable {
	private String eqptno;
	private String eqptvdno;
	private String eqptname;
	private Integer eqptqty;
	private Integer eqptprice;
	private Integer eqptstat;
	private byte[] eqptpic;
	
	public EquipmentVO() {
		super();
	}
	
	public EquipmentVO(String eqptno, String eqptvdno, String eqptname, Integer eqptqty, Integer eqptprice,
			Integer eqptstat, byte[] eqptpic) {
		super();
		this.eqptno = eqptno;
		this.eqptvdno = eqptvdno;
		this.eqptname = eqptname;
		this.eqptqty = eqptqty;
		this.eqptprice = eqptprice;
		this.eqptstat = eqptstat;
		this.eqptpic = eqptpic;
	}

	public String getEqptno() {
		return eqptno;
	}

	public void setEqptno(String eqptno) {
		this.eqptno = eqptno;
	}

	public String getEqptvdno() {
		return eqptvdno;
	}

	public void setEqptvdno(String eqptvdno) {
		this.eqptvdno = eqptvdno;
	}

	public String getEqptname() {
		return eqptname;
	}

	public void setEqptname(String eqptname) {
		this.eqptname = eqptname;
	}

	public Integer getEqptqty() {
		return eqptqty;
	}

	public void setEqptqty(Integer eqptqty) {
		this.eqptqty = eqptqty;
	}

	public Integer getEqptprice() {
		return eqptprice;
	}

	public void setEqptprice(Integer eqptprice) {
		this.eqptprice = eqptprice;
	}

	public Integer getEqptstat() {
		return eqptstat;
	}

	public void setEqptstat(Integer eqptstat) {
		this.eqptstat = eqptstat;
	}

	public byte[] getEqptpic() {
		return eqptpic;
	}

	public void setEqptpic(byte[] eqptpic) {
		this.eqptpic = eqptpic;
	}
	
}
