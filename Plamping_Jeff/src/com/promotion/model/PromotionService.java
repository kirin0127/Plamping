package com.promotion.model;

import java.util.List;

public class PromotionService {
	private PromotionDAO_interface proDAO;

	public PromotionService() {
		proDAO = new PromotionDAO();
	}

	public String insert(PromotionVO proVO) {
		String pro_no = proDAO.insert(proVO);
		return pro_no;
	}

	public void update(PromotionVO proVO) {
		proDAO.update(proVO);
	}

	public PromotionVO getOnePro(String pro_no) {
		PromotionVO proVO = proDAO.queryByNo(pro_no);
		return proVO;
	}

	public List<PromotionVO> getAllPro() {
		List<PromotionVO> proVOList = proDAO.queryAll();
		return proVOList;
	}

	public void delete(PromotionVO proVO) {
		proDAO.delete(proVO);
	}

	public void delete(List<PromotionVO> proVOList) {
		proDAO.delete(proVOList);
	}
}