package com.food.model;

public class FoodVO implements java.io.Serializable {
	private String foodno;
	private String foodvdno;
	private String foodname;
	private Integer foodprice;
	private String foodintro;
	private Integer foodstat;
	private byte[] foodpic;
	
	public FoodVO() {
		super();
	}
	
	public FoodVO(String foodno, String foodvdno, String foodname, Integer foodprice, String foodintro,
			Integer foodstat, byte[] foodpic) {
		super();
		this.foodno = foodno;
		this.foodvdno = foodvdno;
		this.foodname = foodname;
		this.foodprice = foodprice;
		this.foodintro = foodintro;
		this.foodstat = foodstat;
		this.foodpic = foodpic;
	}

	public String getFoodno() {
		return foodno;
	}

	public void setFoodno(String foodno) {
		this.foodno = foodno;
	}

	public String getFoodvdno() {
		return foodvdno;
	}

	public void setFoodvdno(String foodvdno) {
		this.foodvdno = foodvdno;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public Integer getFoodprice() {
		return foodprice;
	}

	public void setFoodprice(Integer foodprice) {
		this.foodprice = foodprice;
	}

	public String getFoodintro() {
		return foodintro;
	}

	public void setFoodintro(String foodintro) {
		this.foodintro = foodintro;
	}

	public Integer getFoodstat() {
		return foodstat;
	}

	public void setFoodstat(Integer foodstat) {
		this.foodstat = foodstat;
	}

	public byte[] getFoodpic() {
		return foodpic;
	}

	public void setFoodpic(byte[] foodpic) {
		this.foodpic = foodpic;
	}
	
}
