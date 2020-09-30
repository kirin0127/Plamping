package com.camp.model;

import java.sql.*;
import java.util.*;

public class CampJDBCDAO implements CampDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "PLAMPING";
	String passwd = "PLAMPING";

	private static final String INSERT_STMT = "INSERT INTO CAMP (CAMP_NO, CAMP_VDNO, CAMP_NAME, CAMP_CTNO, CAMP_QTY, CAMP_PRICE, CAMP_STAT, CAMP_PIC) VALUES ('C' || LPAD(SEQ_CAMPNO.NEXTVAL, 9, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT CAMP_NO, CAMP_VDNO, CAMP_NAME, CAMP_CTNO, CAMP_QTY, CAMP_PRICE, CAMP_STAT, CAMP_PIC FROM CAMP";
	private static final String GET_ONE_STMT = "SELECT CAMP_NO, CAMP_VDNO, CAMP_NAME, CAMP_CTNO, CAMP_QTY, CAMP_PRICE, CAMP_STAT, CAMP_PIC FROM CAMP WHERE CAMP_NO = ?";
	private static final String DELETE_CAMP = "DELETE FROM CAMP WHERE CAMP_NO = ?";	
	private static final String UPDATE = "UPDATE CAMP SET CAMP_VDNO=?, CAMP_NAME=?, CAMP_CTNO=?, CAMP_QTY=?, CAMP_PRICE=?, CAMP_STAT=?, CAMP_PIC=? WHERE CAMP_NO = ?";

	@Override
	public void insert(CampVO campVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, campVO.getCampvdno());
			pstmt.setString(2, campVO.getCampname());
			pstmt.setString(3, campVO.getCampctno());
			pstmt.setInt(4, campVO.getCampqty());
			pstmt.setInt(5, campVO.getCampprice());
			pstmt.setInt(6, campVO.getCampstat());
			pstmt.setBytes(7, campVO.getCamppic());

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
	public void update(CampVO campVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, campVO.getCampvdno());
			pstmt.setString(2, campVO.getCampname());
			pstmt.setString(3, campVO.getCampctno());
			pstmt.setInt(4, campVO.getCampqty());
			pstmt.setInt(5, campVO.getCampprice());
			pstmt.setInt(6, campVO.getCampstat());
			pstmt.setBytes(7, campVO.getCamppic());
			pstmt.setString(8, campVO.getCampno());

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
	public void delete(String campno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_CAMP);
			
			pstmt.setString(1, campno);
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
	public CampVO findByPrimaryKey(String campno) {
		
		CampVO campVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, campno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCampno(rs.getString("campno"));
				campVO.setCampvdno(rs.getString("campvdno"));
				campVO.setCampname(rs.getString("campname"));
				campVO.setCampctno(rs.getString("campctno"));
				campVO.setCampqty(rs.getInt("campqty"));
				campVO.setCampprice(rs.getInt("campprice"));
				campVO.setCampstat(rs.getInt("campstat"));			
				campVO.setCamppic(rs.getBytes("camppic"));
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
		return campVO;
	}

	@Override
	public List<CampVO> getAll() {
		
		List<CampVO> list = new ArrayList<CampVO>();
		CampVO campVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCampno(rs.getString("camp_no"));
				campVO.setCampvdno(rs.getString("camp_vdno"));
				campVO.setCampname(rs.getString("camp_name"));
				campVO.setCampctno(rs.getString("camp_ctno"));
				campVO.setCampqty(rs.getInt("camp_qty"));
				campVO.setCampprice(rs.getInt("camp_price"));
				campVO.setCampstat(rs.getInt("camp_stat"));			
				campVO.setCamppic(rs.getBytes("camp_pic"));
				
				list.add(campVO);
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
//		CampJDBCDAO dao = new CampJDBCDAO();
//		List<CampVO> camps = dao.getAll();
//		for(CampVO camp : camps) {
//			System.out.println(camp.getCampno());
//			System.out.println(camp.getCampname());
//			System.out.println(camp.getCampvdno());
//			System.out.println(camp.getCampprice());
//			System.out.println(camp.getCampstat());
//			System.out.println("====================");
//		}
//	}

}
