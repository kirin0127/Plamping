package com.promofood.model;

import java.util.List;

public class PromoFoodService {
	private PromoFoodDAO_interface pfDAO;

	public PromoFoodService() {
		pfDAO = new PromoFoodDAO();
	}

	public void insert(PromoFoodVO pfVO) {
		pfDAO.insert(pfVO);
	}

	public void insert(List<PromoFoodVO> pfVOList) {
		pfDAO.insert(pfVOList);
	}

	public void update(PromoFoodVO pfVO) {
		pfDAO.update(pfVO);
	}

	public List<PromoFoodVO> getByPe_prono(String pe_prono) {
		List<PromoFoodVO> pfVOList = pfDAO.queryByPro_no(pe_prono);
		return pfVOList;
	}

	public void delete(PromoFoodVO pfVO) {
		pfDAO.delete(pfVO);
	}

	public void delete(List<PromoFoodVO> pfVOList) {
		pfDAO.delete(pfVOList);
	}
}
