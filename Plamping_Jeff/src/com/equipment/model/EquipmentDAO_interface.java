package com.equipment.model;

import java.util.*;

public interface EquipmentDAO_interface {
	public void insert(EquipmentVO equipmentVO);
	public void update(EquipmentVO equipmentVO);
	public void delete(String eqptno);
	public EquipmentVO findByPrimaryKey(String eqptno);
	public List<EquipmentVO> getAll();
}
