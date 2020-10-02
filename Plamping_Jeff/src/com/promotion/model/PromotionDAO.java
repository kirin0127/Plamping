package com.promotion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OraclePreparedStatement;

public class PromotionDAO implements PromotionDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "PLAMPING";
	private static final String PASSWORD = "PLAMPING";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private static final String SQL_INSERT = "INSERT INTO PROMOTION(PRO_NO,PRO_NAME,PRO_START,PRO_END,PRO_VDNO,PRO_STAT) VALUES('P' || LPAD(SEQ_PRONO.NEXTVAL, 9, '0'),?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE PROMOTION SET PRO_NAME=?, PRO_START=?, PRO_END=?, PRO_VDNO=?, PRO_STAT=? WHERE PRO_NO=?";
	private static final String SQL_QUERYNO = "SELECT * FROM PROMOTION WHERE PRO_NO=?";
	private static final String SQL_QUERYALL = "SELECT * FROM PROMOTION";
	private static final String SQL_DELETE = "DELETE FROM PROMOTION WHERE PRO_NO=?";

	@Override
	public String insert(PromotionVO proVO) {
		String pro_no = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			String[] seqNo = { "PRO_NO" };
			pstmt = con.prepareStatement(SQL_INSERT, seqNo);
			pstmt.setString(1, proVO.getPro_name());
			pstmt.setDate(2, proVO.getPro_start());
			pstmt.setDate(3, proVO.getPro_end());
			pstmt.setString(4, proVO.getPro_vdno());
			pstmt.setInt(5, proVO.getPro_stat());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			// get generated seq pro_no
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				pro_no = rs.getString(1);
				System.out.println("In pstmt.getGeneratedKeys() : " + pro_no);
			}else {
				System.out.println("There's no generated pro_no.");
			}
			con.commit();
			System.out.printf("Promotion: \"%s\" Insert Successfully.%n", proVO.getPro_name());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promotion: \"%s\" Insert Failed.%n", proVO.getPro_name());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("tail of the insert DAO method : " + pro_no);
		return pro_no;
	}

	@Override
	public void update(PromotionVO proVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(6, proVO.getPro_no());
			pstmt.setString(1, proVO.getPro_name());
			pstmt.setDate(2, proVO.getPro_start());
			pstmt.setDate(3, proVO.getPro_end());
			pstmt.setString(4, proVO.getPro_vdno());
			pstmt.setInt(5, proVO.getPro_stat());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
			System.out.printf("Promotion: \"%s\" Update Successfully.%n", proVO.getPro_name());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promotion: \"%s\" Update Failed.%n", proVO.getPro_name());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public PromotionVO queryByNo(String PRO_NO) {
		PromotionVO proVO = new PromotionVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_QUERYNO);
			pstmt.setString(1, PRO_NO);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				proVO.setPro_name(rs.getString("PRO_NAME"));
				proVO.setPro_start(rs.getDate("PRO_START"));
				proVO.setPro_end(rs.getDate("PRO_END"));
				proVO.setPro_vdno(rs.getNString("PRO_VDNO"));
				proVO.setPro_stat(rs.getInt("PRO_STAT"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return proVO;
	}

	@Override
	public List<PromotionVO> queryAll() {
		List<PromotionVO> proVOList = new ArrayList<PromotionVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_QUERYALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PromotionVO proVO = new PromotionVO();
				proVO.setPro_no(rs.getString("PRO_NO"));
				proVO.setPro_name(rs.getString("PRO_NAME"));
				proVO.setPro_start(rs.getDate("PRO_START"));
				proVO.setPro_end(rs.getDate("PRO_END"));
				proVO.setPro_vdno(rs.getNString("PRO_VDNO"));
				proVO.setPro_stat(rs.getInt("PRO_STAT"));
				proVOList.add(proVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return proVOList;
	}

	@Override
	public void delete(PromotionVO proVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setString(1, proVO.getPro_no());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
			System.out.printf("Promotion: \"%s\" Delete Successfully.%n", proVO.getPro_name());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promotion: \"%s\" Delete Failed.%n", proVO.getPro_name());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(List<PromotionVO> proVOList) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_DELETE);
			con.setAutoCommit(false);
			((OraclePreparedStatement) pstmt).setExecuteBatch(proVOList.size());
			for (PromotionVO proVOi : proVOList) {
				pstmt.setString(1, proVOi.getPro_no());
				pstmt.executeUpdate();
				System.out.printf("Promotion: Deleting \"%s\"...%n", proVOi.getPro_no());
			}
			((OraclePreparedStatement) pstmt).sendBatch();
			con.commit();
			System.out.printf("Promo_Food: Delete total %d rows Successfully.%n",
					((OraclePreparedStatement) pstmt).getExecuteBatch());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Food: Delete total %d rows Failed.%n", proVOList.size());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String pro_no = "P000000001";
		String pro_name = "JavaDAO第一波促銷";
		long start = new java.util.Date().getTime();
		java.sql.Date pro_start = new java.sql.Date(start);
		long end = new java.util.Date().getTime() + (10 * 24 * 60 * 60 * 1000);
		java.sql.Date pro_end = new java.sql.Date(end);
		String pro_vdno = "V000000001";
		int pro_stat = 0;
		PromotionVO proVO = new PromotionVO(pro_no, pro_name, pro_start, pro_end, pro_vdno, pro_stat);
		PromotionDAO proDAO = new PromotionDAO();
		// Test Promotion Insert DAO
		proDAO.insert(proVO);
//		// Test Promotion Update DAO
//		proDAO.update(proVO);
//		// Test Promotion Query By PRO_NO
//		PromotionVO testQueryNo = proDAO.queryByNo(pro_no);
//		System.out.printf("%s PRO_NAME is : %s%n", pro_no, testQueryNo.getPro_name());
//		System.out.printf("%s PRO_START is : %s%n", pro_no, testQueryNo.getPro_start());
//		System.out.printf("%s PRO_END is : %s%n", pro_no, testQueryNo.getPro_end(), pro_no);
//		System.out.printf("%s PRO_VDNO is : %s%n", pro_no, testQueryNo.getPro_vdno(), pro_no);
//		System.out.printf("%s PRO_STAT is : %d%n", pro_no, testQueryNo.getPro_stat(), pro_no);
//		// Test Promotion Query All
//		List<PromotionVO> proVOList = proDAO.queryAll();
//		for (PromotionVO proVOi : proVOList) {
//			System.out.println("===============================");
//			System.out.println("PRO_NO : " + proVOi.getPro_no());
//			System.out.println("PRO_NAME : " + proVOi.getPro_name());
//			System.out.println("PRO_START : " + proVOi.getPro_start());
//			System.out.println("PRO_END : " + proVOi.getPro_end());
//			System.out.println("PRO_VDNO : " + proVOi.getPro_vdno());
//			System.out.println("PRO_STAT : " + proVOi.getPro_stat());
//		}
//		// Test Promotion Delete DAO
//		proDAO.delete(proVO);
//		// Test Promotion List Delete DAO
//		List<PromotionVO> proVOList = proDAO.queryAll();
//		proVOList.remove(0);
//		proDAO.delete(proVOList);
	}
}