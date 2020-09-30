package com.food.model;

import java.util.List;

public class FoodService {
	
	private FoodDAO_interface dao;

	public FoodService() {
		dao = new FoodJDBCDAO();
	}
	
	public FoodVO addFood(String foodvdno, String foodname, Integer foodprice, String foodintro, Integer foodstat, byte[] foodpic) {

		FoodVO foodVO = new FoodVO();
				
		foodVO.setFoodvdno(foodvdno);
		foodVO.setFoodname(foodname);
		foodVO.setFoodprice(foodprice);
		foodVO.setFoodintro(foodintro);
		foodVO.setFoodstat(foodstat);			
		foodVO.setFoodpic(foodpic);
		dao.insert(foodVO);

		return foodVO;
	}
	
	public FoodVO updateFood(String foodno, String foodvdno, String foodname, Integer foodprice, String foodintro, Integer foodstat, byte[] foodpic) {

		FoodVO foodVO = new FoodVO();
		
		foodVO.setFoodno(foodno);
		foodVO.setFoodvdno(foodvdno);
		foodVO.setFoodname(foodname);
		foodVO.setFoodprice(foodprice);
		foodVO.setFoodintro(foodintro);
		foodVO.setFoodstat(foodstat);			
		foodVO.setFoodpic(foodpic);
		dao.update(foodVO);

		return foodVO;
	}
	
	public void deleteFood(String foodno) {
		dao.delete(foodno);
	}
	
	public FoodVO getOneFood(String foodno) {
		return dao.findByPrimaryKey(foodno);
	}
	
	public List<FoodVO> getAll() {
		return dao.getAll();
	}
	
}
