package com.equipment.model;

import java.util.List;

public class EquipmentService {

	private EquipmentDAO_interface dao;
	
	public EquipmentService() {
		dao = new EquipmentDAO();
	}
	
	public EquipmentVO addEquipment(String eqptvdno, String eqptname, Integer eqptqty, Integer eqptprice, Integer eqptstat, byte[] eqptpic) {

		EquipmentVO equipmentVO = new EquipmentVO();
		
		equipmentVO.setEqptvdno(eqptvdno);
		equipmentVO.setEqptname(eqptname);
		equipmentVO.setEqptqty(eqptqty);
		equipmentVO.setEqptprice(eqptprice);
		equipmentVO.setEqptstat(eqptstat);			
		equipmentVO.setEqptpic(eqptpic);
		dao.insert(equipmentVO);
		
		return equipmentVO;
	}
	
	public EquipmentVO updateEquipment(String eqptno, String eqptvdno, String eqptname, Integer eqptqty, Integer eqptprice,	Integer eqptstat, byte[] eqptpic) {

		EquipmentVO equipmentVO = new EquipmentVO();
		
		equipmentVO.setEqptno(eqptno);
		equipmentVO.setEqptvdno(eqptvdno);
		equipmentVO.setEqptname(eqptname);
		equipmentVO.setEqptqty(eqptqty);
		equipmentVO.setEqptprice(eqptprice);
		equipmentVO.setEqptstat(eqptstat);			
		equipmentVO.setEqptpic(eqptpic);
		dao.update(equipmentVO);

		return equipmentVO;
	}
	
	public void deleteEquipment(String eqptno) {
		dao.delete(eqptno);
	}
	
	public EquipmentVO getOneEquipment(String eqptno) {
		return dao.findByPrimaryKey(eqptno);
	}
	
	public List<EquipmentVO> getAll() {
		return dao.getAll();
	}
	
}
