package com.equipment.model;

import java.sql.*;
import java.util.*;

public class EquipmentJDBCDAO implements EquipmentDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "PLAMPING";
	String passwd = "PLAMPING";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, equipmentVO.getEqptvdno());
			pstmt.setString(2, equipmentVO.getEqptname());
			pstmt.setInt(3, equipmentVO.getEqptqty());
			pstmt.setInt(4, equipmentVO.getEqptprice());
			pstmt.setInt(5, equipmentVO.getEqptstat());
			pstmt.setBytes(6, equipmentVO.getEqptpic());

			pstmt.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equipmentVO.getEqptvdno());
			pstmt.setString(2, equipmentVO.getEqptname());
			pstmt.setInt(3, equipmentVO.getEqptqty());
			pstmt.setInt(4, equipmentVO.getEqptprice());
			pstmt.setInt(5, equipmentVO.getEqptstat());
			pstmt.setBytes(6, equipmentVO.getEqptpic());
			pstmt.setString(7, equipmentVO.getEqptno());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_EQUIPMENT);
			
			pstmt.setString(1, eqptno);			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());			
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEqptno(rs.getString("eqpt_no"));
				equipmentVO.setEqptvdno(rs.getString("eqpt_vdno"));
				equipmentVO.setEqptname(rs.getString("eqpt_name"));
				equipmentVO.setEqptqty(rs.getInt("eqpt_qty"));
				equipmentVO.setEqptprice(rs.getInt("eqpt_price"));
				equipmentVO.setEqptstat(rs.getInt("eqpt_stat"));			
				equipmentVO.setEqptpic(rs.getBytes("eqpt_pic"));
				
				list.add(equipmentVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
//	public static void main(String[] args) {
//		EquipmentJDBCDAO dao = new EquipmentJDBCDAO();
//		List<EquipmentVO> eqpts = dao.getAll();
//		for(EquipmentVO eqpt : eqpts) {
//			System.out.println(eqpt.getEqptno());
//			System.out.println(eqpt.getEqptname());
//			System.out.println(eqpt.getEqptvdno());
//			System.out.println(eqpt.getEqptprice());
//			System.out.println(eqpt.getEqptstat());
//			System.out.println("===============");
//		}
//	}
}
