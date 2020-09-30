package com.food.model;

import java.util.*;

public interface FoodDAO_interface {
	public void insert(FoodVO foodVO);
	public void update(FoodVO foodVO);
	public void delete(String foodno);
	public FoodVO findByPrimaryKey(String foodno);
	public List<FoodVO> getAll();
}
