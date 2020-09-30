package com.camp.model;

import java.util.List;

public class CampService {
	
	private CampDAO_interface dao;
	
	public CampService() {
		dao = new CampDAO();
	}
	
	public CampVO addCamp(String campvdno, String campname, String campctno, Integer campqty, Integer campprice, Integer campstat, byte[] camppic) {

		CampVO campVO = new CampVO();
		
		campVO.setCampvdno(campvdno);
		campVO.setCampname(campname);
		campVO.setCampctno(campctno);
		campVO.setCampqty(campqty);
		campVO.setCampprice(campprice);
		campVO.setCampstat(campstat);
		campVO.setCamppic(camppic);
		dao.insert(campVO);

		return campVO;
	}
	
	public CampVO updateCamp(String campno, String campvdno, String campname, String campctno, Integer campqty, Integer campprice, Integer campstat, byte[] camppic) {

		CampVO campVO = new CampVO();
		
		campVO.setCampno(campno);
		campVO.setCampvdno(campvdno);
		campVO.setCampname(campname);
		campVO.setCampctno(campctno);
		campVO.setCampqty(campqty);
		campVO.setCampprice(campprice);
		campVO.setCampstat(campstat);
		campVO.setCamppic(camppic);
		dao.update(campVO);

		return campVO;
	}
	
	public void deleteCamp(String campno) {
		dao.delete(campno);
	}
	
	public CampVO getOneCamp(String campno) {
		return dao.findByPrimaryKey(campno);
	}
	
	public List<CampVO> getAll() {
		return dao.getAll();
	}
		
}
