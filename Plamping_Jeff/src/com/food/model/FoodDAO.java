package com.food.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FoodDAO implements FoodDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FOOD (FOOD_NO, FOOD_VDNO, FOOD_NAME, FOOD_PRICE, FOOD_INTRO, FOOD_STAT, FOOD_PIC) VALUES ('F' || LPAD(SEQ_FOODNO.NEXTVAL, 9, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT FOOD_NO, FOOD_VDNO, FOOD_NAME, FOOD_PRICE, FOOD_INTRO, FOOD_STAT, FOOD_PIC FROM FOOD";
	private static final String GET_ONE_STMT = "SELECT FOOD_NO, FOOD_VDNO, FOOD_NAME, FOOD_PRICE, FOOD_INTRO, FOOD_STAT, FOOD_PIC FROM FOOD WHERE FOOD_NO = ?";
	private static final String DELETE_FOOD = "DELETE FROM FOOD WHERE FOOD_NO = ?";	
	private static final String UPDATE = "UPDATE FOOD SET FOOD_VDNO=?, FOOD_NAME=?, FOOD_PRICE=?, FOOD_INTRO=?, FOOD_STAT=?, FOOD_PIC=? WHERE FOOD_NO = ?";
	
	@Override
	public void insert(FoodVO foodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodVO.getFoodvdno());
			pstmt.setString(2, foodVO.getFoodname());
			pstmt.setInt(3, foodVO.getFoodprice());
			pstmt.setString(4, foodVO.getFoodintro());
			pstmt.setInt(5, foodVO.getFoodstat());
			pstmt.setBytes(6, foodVO.getFoodpic());

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
	public void update(FoodVO foodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodVO.getFoodvdno());
			pstmt.setString(2, foodVO.getFoodname());
			pstmt.setInt(3, foodVO.getFoodprice());
			pstmt.setString(4, foodVO.getFoodintro());
			pstmt.setInt(5, foodVO.getFoodstat());
			pstmt.setBytes(6, foodVO.getFoodpic());
			pstmt.setString(7, foodVO.getFoodno());

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
	public void delete(String foodno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FOOD);
			
			pstmt.setString(1, foodno);			
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
	public FoodVO findByPrimaryKey(String foodno) {

		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, foodno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFoodno(rs.getString("foodno"));
				foodVO.setFoodvdno(rs.getString("foodvdno"));
				foodVO.setFoodname(rs.getString("foodname"));
				foodVO.setFoodprice(rs.getInt("foodprice"));
				foodVO.setFoodintro(rs.getString("foodintro"));
				foodVO.setFoodstat(rs.getInt("foodstat"));			
				foodVO.setFoodpic(rs.getBytes("foodpic"));
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
		return foodVO;
	}

	@Override
	public List<FoodVO> getAll() {
		
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFoodno(rs.getString("foodno"));
				foodVO.setFoodvdno(rs.getString("foodvdno"));
				foodVO.setFoodname(rs.getString("foodname"));
				foodVO.setFoodprice(rs.getInt("foodprice"));
				foodVO.setFoodintro(rs.getString("foodintro"));
				foodVO.setFoodstat(rs.getInt("foodstat"));			
				foodVO.setFoodpic(rs.getBytes("foodpic"));
				
				list.add(foodVO);
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
