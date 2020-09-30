package com.equipment.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EquipmentDAO implements EquipmentDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EQUIPMENT (EQPT_NO, EQPT_VDNO, EQPT_NAME, EQPT_QTY, EQPT_PRICE, EQPT_STAT, EQPT_PIC) VALUES ('E' || LPAD(SEQ_EQPTNO.NEXTVAL, 9, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT EQPT_NO, EQPT_VDNO, EQPT_NAME, EQPT_QTY, EQPT_PRICE, EQPT_STAT, EQPT_PIC FROM EQUIPMENT";
	private static final String GET_ONE_STMT = "SELECT EQPT_NO, EQPT_VDNO, EQPT_NAME, EQPT_QTY, EQPT_PRICE, EQPT_STAT, EQPT_PIC FROM EQUIPMENT WHERE EQPTD_NO = ?";
	private static final String DELETE_EQUIPMENT = "DELETE FROM EQUIPMENT WHERE EQPT_NO = ?";	
	private static final String UPDATE = "UPDATE EQUIPMENT SET EQPT_VDNO=?, EQPT_NAME=?, EQPT_QTY=?, FOOD_PRICE=?, EQPT_STAT=?, EQPT_PIC=? WHERE EQPT_NO = ?";
		
	@Override
	public void insert(EquipmentVO equipmentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, equipmentVO.getEqptvdno());
			pstmt.setString(2, equipmentVO.getEqptname());
			pstmt.setInt(3, equipmentVO.getEqptqty());
			pstmt.setInt(4, equipmentVO.getEqptprice());
			pstmt.setInt(5, equipmentVO.getEqptstat());
			pstmt.setBytes(6, equipmentVO.getEqptpic());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}		
	}

	@Override
	public void update(EquipmentVO equipmentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equipmentVO.getEqptvdno());
			pstmt.setString(2, equipmentVO.getEqptname());
			pstmt.setInt(3, equipmentVO.getEqptqty());
			pstmt.setInt(4, equipmentVO.getEqptprice());
			pstmt.setInt(5, equipmentVO.getEqptstat());
			pstmt.setBytes(6, equipmentVO.getEqptpic());
			pstmt.setString(7, equipmentVO.getEqptno());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String eqptno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_EQUIPMENT);
			
			pstmt.setString(1, eqptno);			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public EquipmentVO findByPrimaryKey(String eqptno) {
		
		EquipmentVO equipmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, eqptno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEqptno(rs.getString("eqptno"));
				equipmentVO.setEqptvdno(rs.getString("eqptvdno"));
				equipmentVO.setEqptname(rs.getString("eqptname"));
				equipmentVO.setEqptqty(rs.getInt("eqptqty"));
				equipmentVO.setEqptprice(rs.getInt("eqptprice"));
				equipmentVO.setEqptstat(rs.getInt("eqptstat"));			
				equipmentVO.setEqptpic(rs.getBytes("eqptpic"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}	
		return equipmentVO;
	}

	@Override
	public List<EquipmentVO> getAll() {
		
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equipmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEqptno(rs.getString("eqptno"));
				equipmentVO.setEqptvdno(rs.getString("eqptvdno"));
				equipmentVO.setEqptname(rs.getString("eqptname"));
				equipmentVO.setEqptqty(rs.getInt("eqptqty"));
				equipmentVO.setEqptprice(rs.getInt("eqptprice"));
				equipmentVO.setEqptstat(rs.getInt("eqptstat"));			
				equipmentVO.setEqptpic(rs.getBytes("eqptpic"));
				
				list.add(equipmentVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
