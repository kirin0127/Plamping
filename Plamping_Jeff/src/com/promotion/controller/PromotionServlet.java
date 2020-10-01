package com.promotion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camp.model.*;
import com.equipment.model.*;
import com.food.model.*;

@WebServlet("/PromotionServlet")
@MultipartConfig()
public class PromotionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		// promoCreate ajax call to update origin price.
		if("ajax_getPrice".equals(action)) {
			// Create camp, eqpt, food service instance
			CampService campSvc = new CampService();
			EquipmentService eqptSvc = new EquipmentService();
			FoodService foodSvc = new FoodService();
			// Get parameters from promoCreate.jsp ajax call
			String vd_no = req.getParameter("vd_no");
			String item_type = req.getParameter("item_type");
			String item_no = req.getParameter("item_no");
			if("pc_campno".equals(item_type)) {
				List<CampVO> campList = campSvc.getAll();
				for(CampVO camp : campList) {
					if(vd_no.equals(camp.getCampno())) {
						if(item_no.equals(camp.getCampvdno())) {
							out.print(camp.getCampprice());
							System.out.println(vd_no);
							System.out.println(item_type);
							System.out.println(item_no);
							System.out.println(camp.getCampprice());
						}
					}
				}
			}else if("pc_eqptno".equals(item_type)) {
				List<EquipmentVO> eqptList = eqptSvc.getAll();
				for(EquipmentVO eqpt : eqptList) {
					if(vd_no.equals(eqpt.getEqptno())) {
						if(item_no.equals(eqpt.getEqptvdno())) {
							out.print(eqpt.getEqptprice());
							System.out.println(vd_no);
							System.out.println(item_type);
							System.out.println(item_no);
							System.out.println(eqpt.getEqptprice());
						}
					}
				}
			}else if("pc_foodno".equals(item_type)) {
				List<FoodVO> foodList = foodSvc.getAll();
				for(FoodVO food : foodList) {
					if(vd_no.equals(food.getFoodno())) {
						if(item_no.equals(food.getFoodvdno())) {
							out.print(food.getFoodprice());
							System.out.println(vd_no);
							System.out.println(item_type);
							System.out.println(item_no);
							System.out.println(food.getFoodprice());
						}
					}
				}
			}
		}
		// Other action

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
