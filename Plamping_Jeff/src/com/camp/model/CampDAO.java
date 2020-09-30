package com.camp.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CampDAO implements CampDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, campVO.getCampvdno());
			pstmt.setString(2, campVO.getCampname());
			pstmt.setString(3, campVO.getCampctno());
			pstmt.setInt(4, campVO.getCampqty());
			pstmt.setInt(5, campVO.getCampprice());
			pstmt.setInt(6, campVO.getCampstat());
			pstmt.setBytes(7, campVO.getCamppic());

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
	public void update(CampVO campVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_CAMP);
			
			pstmt.setString(1, campno);			
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
	public CampVO findByPrimaryKey(String campno) {
		
		CampVO campVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				
				list.add(campVO);
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
