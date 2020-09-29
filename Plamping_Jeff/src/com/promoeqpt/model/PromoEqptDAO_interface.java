package com.promoeqpt.model;

import java.util.List;

public interface PromoEqptDAO_interface {
	public void insert(PromoEqptVO peVO);

	public void insert(List<PromoEqptVO> peVOList);

	public void update(PromoEqptVO peVO);

	public List<PromoEqptVO> queryByPro_no(String pe_prono);

	public void delete(PromoEqptVO peVO);

	public void delete(List<PromoEqptVO> peVOList);
}
