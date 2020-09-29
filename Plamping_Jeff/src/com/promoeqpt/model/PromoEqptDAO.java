package com.promoeqpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OraclePreparedStatement;

public class PromoEqptDAO implements PromoEqptDAO_interface {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "PLAMPING";
	private static final String PASSWORD = "PLAMPING";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private static final String SQL_INSERT = "INSERT INTO PROMO_EQPT(PE_PRONO, PE_EQPTNO, PE_PRICE) VALUES(?,?,?)";
	private static final String SQL_UPDATE = "UPDATE PROMO_EQPT SET PE_PRICE=? WHERE (PE_PRONO=? AND PE_EQPTNO=?)";
	private static final String SQL_QUERYBYPRONO = "SELECT * FROM PROMO_EQPT WHERE PE_PRONO=?";
	private static final String SQL_DELETE = "DELETE FROM PROMO_EQPT WHERE (PE_PRONO=? AND PE_EQPTNO=?)";

	@Override
	public void insert(PromoEqptVO peVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_INSERT);
			pstmt.setString(1, peVO.getPe_prono());
			pstmt.setString(2, peVO.getPe_eqptno());
			pstmt.setInt(3, peVO.getPe_price());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
			System.out.printf("Promo_Eqpt: \"%s\" - \"%s\" Insert Successfully.%n", peVO.getPe_prono(),
					peVO.getPe_eqptno());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Eqpt: \"%s\" - \"%s\" Insert Failed.%n", peVO.getPe_prono(),
						peVO.getPe_eqptno());
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
	public void insert(List<PromoEqptVO> peVOList) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_INSERT);
			con.setAutoCommit(false);
			((OraclePreparedStatement) pstmt).setExecuteBatch(peVOList.size());
			for (PromoEqptVO peVOi : peVOList) {
				pstmt.setString(1, peVOi.getPe_prono());
				pstmt.setString(2, peVOi.getPe_eqptno());
				pstmt.setInt(3, peVOi.getPe_price());
				pstmt.executeUpdate();
				System.out.printf("Promo_Eqpt: Inserting \"%s\"-\"%s\"...%n", peVOi.getPe_prono(),
						peVOi.getPe_eqptno());
			}
			((OraclePreparedStatement) pstmt).sendBatch();
			con.commit();
			System.out.printf("Promo_Eqpt: Insert total %d rows Successfully.%n",
					((OraclePreparedStatement) pstmt).getExecuteBatch());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Eqpt: Insert total %d rows Failed.%n", peVOList.size());
				;
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
	public void update(PromoEqptVO peVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, peVO.getPe_price());
			pstmt.setString(2, peVO.getPe_prono());
			pstmt.setString(3, peVO.getPe_eqptno());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
			System.out.printf("Promo_Eqpt: \"%s\"-\"%s\" Update Successfully.%n", peVO.getPe_prono(),
					peVO.getPe_eqptno());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Eqpt: \"%s\"-\"%s\" Update Failed.%n", peVO.getPe_prono(),
						peVO.getPe_eqptno());
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.printf("Promo_Eqpt: \"%s\"-\"%s\" Update Rollback Failed.%n", peVO.getPe_prono(),
						peVO.getPe_eqptno());
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
	public List<PromoEqptVO> queryByPro_no(String pe_prono) {
		List<PromoEqptVO> peVOList = new ArrayList<PromoEqptVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_QUERYBYPRONO);
			pstmt.setString(1, pe_prono);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PromoEqptVO peVO = new PromoEqptVO();
				peVO.setPe_prono(rs.getString("PE_PRONO"));
				peVO.setPe_eqptno(rs.getString("PE_EQPTNO"));
				peVO.setPe_price(rs.getInt("PE_PRICE"));
				peVOList.add(peVO);
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
		return peVOList;
	}

	@Override
	public void delete(PromoEqptVO peVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setString(1, peVO.getPe_prono());
			pstmt.setString(2, peVO.getPe_eqptno());
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
			System.out.printf("Promo_Eqpt: \"%s\" - \"%s\" Delete Successfully.%n", peVO.getPe_prono(),
					peVO.getPe_eqptno());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Eqpt: \"%s\" - \"%s\" Delete Failed.%n", peVO.getPe_prono(),
						peVO.getPe_eqptno());
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.printf("Promo_Eqpt: \"%s\" - \"%s\" Delete Rollback Failed.%n", peVO.getPe_prono(),
						peVO.getPe_eqptno());
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
	public void delete(List<PromoEqptVO> peVOList) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connetion Successfully.");
			pstmt = con.prepareStatement(SQL_DELETE);
			con.setAutoCommit(false);
			((OraclePreparedStatement) pstmt).setExecuteBatch(peVOList.size());
			for (PromoEqptVO peVOi : peVOList) {
				pstmt.setString(1, peVOi.getPe_prono());
				pstmt.setString(2, peVOi.getPe_eqptno());
				pstmt.executeUpdate();
				System.out.printf("Promo_Eqpt: Deleting \"%s\" - \"%s\"...%n", peVOi.getPe_prono(),
						peVOi.getPe_eqptno());
			}
			((OraclePreparedStatement) pstmt).sendBatch();
			con.commit();
			System.out.printf("Promo_Eqpt: Delete total %d rows Successfully.%n",
					((OraclePreparedStatement) pstmt).getExecuteBatch());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.printf("Promo_Eqpt: Delete total %d rows Failed.%n", peVOList.size());
				;
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
		String pe_prono = "P000000008";
		String pe_epqtno = "E000000001";
		int pe_price = 1000;
		PromoEqptVO peVO = new PromoEqptVO(pe_prono, pe_epqtno, pe_price);
		PromoEqptDAO peDAO = new PromoEqptDAO();
//		// Test Promo_Eqpt Insert DAO
//		peDAO.insert(peVO);
//		// Test Promo_Eqpt List Insert DAO
//		List<PromoEqptVO> peVOList = new ArrayList<>();
//		String[] eqptnoList = {"E000000005", "E000000006", "E000000007"};
//		for(int i = 0; i < eqptnoList.length; i++) {
//			PromoEqptVO peVOi = new PromoEqptVO(pe_prono, eqptnoList[i], pe_price);
//			peVOList.add(peVOi);
//		}
//		peDAO.insert(peVOList);
//		// Test Promo_Eqpt Update DAO
//		peDAO.update(peVO);
		// Test Promo_Eqpt Query By PRO_NO
		List<PromoEqptVO> peVOList = peDAO.queryByPro_no(pe_prono);
		for (PromoEqptVO peVOi : peVOList) {
			System.out.println("===============================");
			System.out.println("PE_PRONO : " + peVOi.getPe_prono());
			System.out.println("PE_CAMPNO : " + peVOi.getPe_eqptno());
			System.out.println("PE_PRICE : " + peVOi.getPe_price());
		}
//		// Test Promo_Eqpt Delete DAO
//		peDAO.delete(peVO);
//		// Test Promo_Eqpt List Delete DAO
//		List<PromoEqptVO> peVOList = peDAO.queryByPro_no(pe_prono);
//		peVOList.remove(0);
//		peDAO.delete(peVOList);
	}
}
