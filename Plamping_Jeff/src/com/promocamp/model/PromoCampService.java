package com.promocamp.model;

import java.util.List;

public class PromoCampService {
	private PromoCampDAO_interface pcDAO;

	public PromoCampService() {
		pcDAO = new PromoCampDAO();
	}

	public void insert(PromoCampVO pcVO) {
		pcDAO.insert(pcVO);
	}

	public void insert(List<PromoCampVO> pcVOList) {
		pcDAO.insert(pcVOList);
	}

	public void update(PromoCampVO pcVO) {
		pcDAO.update(pcVO);
	}

	public List<PromoCampVO> getByPc_prono(String pc_prono) {
		List<PromoCampVO> pcVOList = pcDAO.queryByPro_no(pc_prono);
		return pcVOList;
	}

	public void delete(PromoCampVO pcVO) {
		pcDAO.delete(pcVO);
	}

	public void delete(List<PromoCampVO> pcVOList) {
		pcDAO.delete(pcVOList);
	}
}
