package com.promotion.model;

import java.util.List;

public interface PromotionDAO_interface {
	
	public void insert(PromotionVO proVO);
	
	public void update(PromotionVO proVO);
	
	public PromotionVO queryByNo(String pro_no);
	
	public List<PromotionVO> queryAll();
	
	public void delete (PromotionVO proVO);
	
	public void delete (List<PromotionVO> proVOList);
}