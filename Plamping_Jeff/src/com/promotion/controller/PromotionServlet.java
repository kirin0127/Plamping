package com.promotion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camp.model.*;
import com.equipment.model.*;
import com.food.model.*;
import com.google.gson.*;
import com.promotion.model.*;
import com.promocamp.model.*;
import com.promoeqpt.model.*;
import com.promofood.model.*;


@WebServlet("/PromotionServlet")
@MultipartConfig()
public class PromotionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String vd_no = req.getParameter("vd_no");
		System.out.println(action);
		System.out.println(vd_no);
		// promoCreate ajax call to update origin price.
		if("ajax_getPrice".equals(action)) {
			// Create camp, eqpt, food service instance
			CampService campSvc = new CampService();
			EquipmentService eqptSvc = new EquipmentService();
			FoodService foodSvc = new FoodService();
			// Get parameters from promoCreate.jsp ajax call
			String item_type = req.getParameter("item_type");
			String item_no = req.getParameter("item_no");
			if("pc_campno".equals(item_type)) {
				List<CampVO> campList = campSvc.getAll();
				for(CampVO camp : campList) {
					if(vd_no.equals(camp.getCampvdno())) {
						if(item_no.equals(camp.getCampno())) {
							out.print(camp.getCampprice());
						}
					}
				}
			}else if("pe_eqptno".equals(item_type)) {
				List<EquipmentVO> eqptList = eqptSvc.getAll();
				for(EquipmentVO eqpt : eqptList) {
					if(vd_no.equals(eqpt.getEqptvdno())) {
						if(item_no.equals(eqpt.getEqptno())) {
							out.print(eqpt.getEqptprice());
						}
					}
				}
			}else if("pf_foodno".equals(item_type)) {
				List<FoodVO> foodList = foodSvc.getAll();
				for(FoodVO food : foodList) {
					if(vd_no.equals(food.getFoodvdno())) {
						if(item_no.equals(food.getFoodno())) {
							out.print(food.getFoodprice());
						}
					}
				}
			}
		}
		// promoCreate form data process
		if("create".equals(action)) {
			// Get parameters from promoCreate.jsp post form data
			String pro_name = req.getParameter("pro_name");
			String pro_start = req.getParameter("pro_start");
			String pro_end = req.getParameter("pro_end");
			String[] pc_campnoList = req.getParameterValues("pc_campno");
			String[] pc_priceList = req.getParameterValues("pc_price");
			String[] pe_eqptnoList = req.getParameterValues("pe_eqptno");
			String[] pe_priceList = req.getParameterValues("pe_price");
			String[] pf_foodnoList = req.getParameterValues("pf_foodno");
			String[] pf_priceList = req.getParameterValues("pf_price");
			// Insert promotion record & get inserted record's pro_no.
			PromotionService proSvc = new PromotionService();
			PromotionVO proVO = new PromotionVO();
			proVO.setPro_name(pro_name);
			proVO.setPro_start(Date.valueOf(pro_start));
			proVO.setPro_end(Date.valueOf(pro_end));
			proVO.setPro_vdno(vd_no);
			proVO.setPro_stat(0);
			String pro_no = proSvc.insert(proVO);
			System.out.println("Promotion : " + pro_no + " inserted.");
			// Insert promo_camp
			PromoCampService pcSvc = new PromoCampService();
			for(int i = 0; i < pc_campnoList.length; i++) {
				PromoCampVO pcVO = new PromoCampVO();
				pcVO.setPc_prono(pro_no);
				pcVO.setPc_campno(pc_campnoList[i]);
				pcVO.setPc_price(Integer.parseInt(pc_priceList[i]));
				pcSvc.insert(pcVO);
				System.out.println("PromoCamp : " + pc_campnoList[i] + " inserted.");
			}
			// Insert promo_eqpt
			PromoEqptService peSvc = new PromoEqptService();
			for(int i = 0; i < pe_eqptnoList.length; i++) {
				PromoEqptVO peVO = new PromoEqptVO();
				peVO.setPe_prono(pro_no);
				peVO.setPe_eqptno(pe_eqptnoList[i]);
				peVO.setPe_price(Integer.parseInt(pe_priceList[i]));
				peSvc.insert(peVO);
				System.out.println("PromoEqpt : " + pe_eqptnoList[i] + " inserted.");
			}
			// Insert promo_food
			PromoFoodService pfSvc = new PromoFoodService();
			for(int i = 0; i < pf_foodnoList.length; i++) {
				PromoFoodVO pfVO = new PromoFoodVO();
				pfVO.setPf_prono(pro_no);
				pfVO.setPf_foodno(pf_foodnoList[i]);
				pfVO.setPf_price(Integer.parseInt(pf_priceList[i]));
				pfSvc.insert(pfVO);
				System.out.println("PromoFood : " + pf_foodnoList[i] + " inserted.");
			}
			res.sendRedirect(req.getContextPath() + "/promoSelect.jsp?vd_no=" + vd_no);
		}
		// promoDetail ajax call to show promotion details data.
		if("ajax_getDetail".equals(action)) {
			String pro_no = req.getParameter("pro_no");
			// Set promo_camp item to json array.
			CampService campSvc = new CampService();
			PromoCampService pcSvc = new PromoCampService();
			List<PromoCampVO> pcList = pcSvc.getByPc_prono(pro_no);
			JsonArray pcJarr = new JsonArray();
			for(PromoCampVO pcVO : pcList) {
				JsonObject jobj = new JsonObject();
				jobj = JsonParser.parseString(new Gson().toJson(pcVO)).getAsJsonObject();
				jobj.addProperty("camp_name", campSvc.getOneCamp(pcVO.getPc_campno()).getCampname());
				pcJarr.add(jobj);
			}
			// Set promo_eqpt item to json array.
			PromoEqptService peSvc = new PromoEqptService();
			List<PromoEqptVO> peList = peSvc.getByPe_prono(pro_no);
			JsonArray peJarr = new JsonArray();
			for(PromoEqptVO peVO : peList) {
				peJarr.add(new Gson().toJson(peVO));
			}
			// Set promo_food item to json array.
			PromoFoodService pfSvc = new PromoFoodService();
			List<PromoFoodVO> pfList = pfSvc.getByPf_prono(pro_no);
			JsonArray pfJarr = new JsonArray();
			for(PromoFoodVO pfVO : pfList) {
				pfJarr.add(new Gson().toJson(pfVO));
			}
			// Set all 3 json array to a json object
			JsonObject json = new JsonObject();
			json.add("pcList", pcJarr);
			json.add("peList", peJarr);
			json.add("pfList", pfJarr);
			out.print(json);
		}
		

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
