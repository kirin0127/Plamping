package com.camp.model;

import java.util.*;

public interface CampDAO_interface {
	public void insert(CampVO campVO);
	public void update(CampVO campVO);
	public void delete(String campno);
	public CampVO findByPrimaryKey(String campno);
	public List<CampVO> getAll();
}
