package com.promotion.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PromotionServlet")
@MultipartConfig()
public class PromotionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		System.out.println(action);
		if("ajax_getPrice".equals(action)) {
			System.out.println("get ajax req from promoCreate.html");
			String vd_no = req.getParameter("vd_no");
			String item_type = req.getParameter("item_type");
			String item_no = req.getParameter("item_no");
			System.out.println("vd_no : " + vd_no);
			System.out.println("item_type : " + item_type);
			System.out.println("item_no : " + item_no);
			out.print(6666);
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
