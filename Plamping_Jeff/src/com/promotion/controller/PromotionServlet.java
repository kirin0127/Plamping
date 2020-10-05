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
		res.setContentType("text/html; charset=UTF-8");
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
			// Set promotion info to json array.
			PromotionService proSvc = new PromotionService();
			PromotionVO proVO = proSvc.getOnePro(pro_no);
			JsonObject proJobj = new JsonObject();
			proJobj.addProperty("pro_name", proVO.getPro_name());
			proJobj.addProperty("pro_start", proVO.getPro_start().toString());
			proJobj.addProperty("pro_end", proVO.getPro_end().toString());
			proJobj.addProperty("pro_stat", proVO.getPro_stat());
			// Set promo_camp item to json array.
			CampService campSvc = new CampService();
			PromoCampService pcSvc = new PromoCampService();
			List<PromoCampVO> pcList = pcSvc.getByPc_prono(pro_no);
			JsonArray pcJarr = new JsonArray();
			for(PromoCampVO pcVO : pcList) {
				JsonObject jobj = JsonParser.parseString(new Gson().toJson(pcVO)).getAsJsonObject();
				CampVO campVO = campSvc.getOneCamp(pcVO.getPc_campno());
				jobj.addProperty("camp_name", campVO.getCampname());
				jobj.addProperty("camp_price", campVO.getCampprice());
				pcJarr.add(jobj);
			}
			// Set promo_eqpt item to json array.
			EquipmentService eqptSvc = new EquipmentService();
			PromoEqptService peSvc = new PromoEqptService();
			List<PromoEqptVO> peList = peSvc.getByPe_prono(pro_no);
			JsonArray peJarr = new JsonArray();
			for(PromoEqptVO peVO : peList) {
				JsonObject jobj = JsonParser.parseString(new Gson().toJson(peVO)).getAsJsonObject();
				EquipmentVO eqptVO = eqptSvc.getOneEquipment(peVO.getPe_eqptno());
				jobj.addProperty("eqpt_name", eqptVO.getEqptname());
				jobj.addProperty("eqpt_price", eqptVO.getEqptprice());
				peJarr.add(jobj);
			}
			// Set promo_food item to json array.
			FoodService foodSvc = new FoodService();
			PromoFoodService pfSvc = new PromoFoodService();
			List<PromoFoodVO> pfList = pfSvc.getByPf_prono(pro_no);
			JsonArray pfJarr = new JsonArray();
			for(PromoFoodVO pfVO : pfList) {
				JsonObject jobj = JsonParser.parseString(new Gson().toJson(pfVO)).getAsJsonObject();
				FoodVO foodVO = foodSvc.getOneFood(pfVO.getPf_foodno());
				jobj.addProperty("food_name", foodVO.getFoodname());
				jobj.addProperty("food_price", foodVO.getFoodprice());
				pfJarr.add(jobj);
			}
			// Set all 3 json array to a json object
			JsonObject json = new JsonObject();
			json.add("promotion", proJobj);
			json.add("pcList", pcJarr);
			json.add("peList", peJarr);
			json.add("pfList", pfJarr);
			out.print(json);
		}
		
		// promoDetail form data process
		if("edit".equals(action)) {
			// Get parameters from promoDetail.jsp post form data
			String pro_no = req.getParameter("pro_no");
			String pro_name = req.getParameter("pro_name");
			String pro_start = req.getParameter("pro_start");
			String pro_end = req.getParameter("pro_end");
			String pro_stat = req.getParameter("pro_stat");
			String[] pc_campnoList = req.getParameterValues("pc_campno");
			String[] pc_priceList = req.getParameterValues("pc_price");
			String[] pe_eqptnoList = req.getParameterValues("pe_eqptno");
			String[] pe_priceList = req.getParameterValues("pe_price");
			String[] pf_foodnoList = req.getParameterValues("pf_foodno");
			String[] pf_priceList = req.getParameterValues("pf_price");
			// promotion update
			PromotionService proSvc = new PromotionService();
			PromotionVO proVO = proSvc.getOnePro(pro_no);
			System.out.println("proVO check pro_no: " + proVO.getPro_no());
			System.out.println("proVO check pro_name: " + proVO.getPro_name());
			System.out.println("proVO check pro_start: " + proVO.getPro_start());
			System.out.println("proVO check pro_end: " + proVO.getPro_end());
			System.out.println("proVO check pro_vdno: " + proVO.getPro_vdno());
			System.out.println("proVO check pro_stat: " + proVO.getPro_stat());
			proVO.setPro_name(pro_name);
			proVO.setPro_start(Date.valueOf(pro_start));
			proVO.setPro_end(Date.valueOf(pro_end));
			if(pro_stat == null) {
				proVO.setPro_stat(0);
			}else {
				proVO.setPro_stat(Integer.parseInt(pro_stat));
			}
			System.out.println("===================================");
			System.out.println("proVO check pro_no: " + proVO.getPro_no());
			System.out.println("proVO check pro_name: " + proVO.getPro_name());
			System.out.println("proVO check pro_start: " + proVO.getPro_start());
			System.out.println("proVO check pro_end: " + proVO.getPro_end());
			System.out.println("proVO check pro_vdno: " + proVO.getPro_vdno());
			System.out.println("proVO check pro_stat: " + proVO.getPro_stat());
			proSvc.update(proVO);
			System.out.println("Promotion : " + pro_no + " updated.");
			
			// promo_camp update, insert, delete
			PromoCampService pcSvc = new PromoCampService();
			List<PromoCampVO> pcVOList_DB = pcSvc.getByPc_prono(pro_no);
			int[] updatedPcArr = new int[pcVOList_DB.size()]; //default int array value will be 0.
			for(int i = 0; i < pc_campnoList.length; i++) {
				String pc_campno = pc_campnoList[i];
				int updateFlag = 0; // check if the item is existing in previous promotion set or not.
				for(int j = 0; j < pcVOList_DB.size(); j++) {
					String pc_campno_DB = pcVOList_DB.get(j).getPc_campno();
					// Update promo_camp
					if(pc_campno.equals(pc_campno_DB)) {
						PromoCampVO pcVO = pcVOList_DB.get(j);
						pcVO.setPc_price(Integer.parseInt(pc_priceList[i]));
						pcSvc.update(pcVO);
						System.out.println("PromoCamp : " + pc_campnoList[i] + " updated.");
						updatedPcArr[j] = 1;
						updateFlag = 1;
					}
				}
				// Insert promo_camp
				if(updateFlag == 0) {
					PromoCampVO pcVO = new PromoCampVO();
					pcVO.setPc_prono(pro_no);
					pcVO.setPc_campno(pc_campnoList[i]);
					pcVO.setPc_price(Integer.parseInt(pc_priceList[i]));
					pcSvc.insert(pcVO);
					System.out.println("PromoCamp : " + pc_campnoList[i] + " inserted.");
				}
			}
			// Delete promo_camp
			for(int i = 0; i < updatedPcArr.length; i++) {
				if(updatedPcArr[i] == 0) {
					PromoCampVO pcVO = pcVOList_DB.get(i);
					pcSvc.delete(pcVO);
					System.out.println("PromoCamp : " + pcVO.getPc_campno() + " deleted.");
				}
			}
			
			// promo_eqpt update, insert, delete
			PromoEqptService peSvc = new PromoEqptService();
			List<PromoEqptVO> peVOList_DB = peSvc.getByPe_prono(pro_no);
			int[] updatedPeArr = new int[peVOList_DB.size()]; //default int array value will be 0.
			for(int i = 0; i < pe_eqptnoList.length; i++) {
				String pe_eqptno = pe_eqptnoList[i];
				int updateFlag = 0; // check if the item is existing in previous promotion set or not.
				for(int j = 0; j < peVOList_DB.size(); j++) {
					String pe_eqptno_DB = peVOList_DB.get(j).getPe_eqptno();
					// Update promo_eqpt
					if(pe_eqptno.equals(pe_eqptno_DB)) {
						PromoEqptVO peVO = peVOList_DB.get(j);
						peVO.setPe_price(Integer.parseInt(pe_priceList[i]));
						peSvc.update(peVO);
						System.out.println("PromoEqpt : " + pe_eqptnoList[i] + " updated.");
						updatedPeArr[j] = 1;
						updateFlag = 1;
					}
				}
				// Insert promo_eqpt
				if(updateFlag == 0) {
					PromoEqptVO peVO = new PromoEqptVO();
					peVO.setPe_prono(pro_no);
					peVO.setPe_eqptno(pe_eqptnoList[i]);
					peVO.setPe_price(Integer.parseInt(pe_priceList[i]));
					peSvc.insert(peVO);
					System.out.println("PromoEqpt : " + pe_eqptnoList[i] + " inserted.");	
				}
			}
			// Delete promo_eqpt
			for(int i = 0; i < updatedPeArr.length; i++) {
				if(updatedPeArr[i] == 0) {
					PromoEqptVO peVO = peVOList_DB.get(i);
					peSvc.delete(peVO);
					System.out.println("PromoEqpt : " + peVO.getPe_eqptno() + " deleted.");
				}
			}
			
			
			// promo_food update, insert, delete
			PromoFoodService pfSvc = new PromoFoodService();
			List<PromoFoodVO> pfVOList_DB = pfSvc.getByPf_prono(pro_no);
			int[] updatedPfArr = new int[pfVOList_DB.size()]; //default int array value will be 0.
			for(int i = 0; i < pf_foodnoList.length; i++) {
				String pf_foodno = pf_foodnoList[i];
				int updateFlag = 0; // check if the item is existing in previous promotion set or not.
				for(int j = 0; j < pfVOList_DB.size(); j++) {
					String pf_foodno_DB = pfVOList_DB.get(j).getPf_foodno();
					// Update promo_food
					if(pf_foodno.equals(pf_foodno_DB)) {
						PromoFoodVO pfVO = pfVOList_DB.get(j);
						pfVO.setPf_price(Integer.parseInt(pf_priceList[i]));
						pfSvc.update(pfVO);
						System.out.println("PromoFood : " + pf_foodnoList[i] + " updated.");
						updatedPfArr[j] = 1;
						updateFlag = 1;
					}
				}
				if(updateFlag == 0) {
					// Insert promo_food
					PromoFoodVO pfVO = new PromoFoodVO();
					pfVO.setPf_prono(pro_no);
					pfVO.setPf_foodno(pf_foodnoList[i]);
					pfVO.setPf_price(Integer.parseInt(pf_priceList[i]));
					pfSvc.insert(pfVO);
					System.out.println("PromoFood : " + pf_foodnoList[i] + " inserted.");	
				}
			}
			// Delete promo_food
			for(int i = 0; i < updatedPfArr.length; i++) {
				if(updatedPfArr[i] == 0) {
					PromoFoodVO pfVO = pfVOList_DB.get(i);
					pfSvc.delete(pfVO);
					System.out.println("PromoFood : " + pfVO.getPf_foodno() + " deleted.");
				}
			}
			res.sendRedirect(req.getContextPath() + "/promoSelect.jsp?vd_no=" + vd_no);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
