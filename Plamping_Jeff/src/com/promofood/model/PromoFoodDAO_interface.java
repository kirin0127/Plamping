package com.promofood.model;

import java.util.List;

public interface PromoFoodDAO_interface {
	public void insert(PromoFoodVO pfVO);

	public void insert(List<PromoFoodVO> pfVOList);

	public void update(PromoFoodVO pfVO);

	public List<PromoFoodVO> queryByPro_no(String pf_prono);

	public void delete(PromoFoodVO pfVO);

	public void delete(List<PromoFoodVO> pfVOList);
}
