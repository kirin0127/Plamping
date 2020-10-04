<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%@ page import="com.promocamp.model.*"%>
<%@ page import="com.promoeqpt.model.*"%>
<%@ page import="com.promofood.model.*"%>
<%@ page import="com.camp.model.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.food.model.*"%>
<!DOCTYPE html>
<jsp:useBean id="proSvc" scope="page" class="com.promotion.model.PromotionService"/>
<jsp:useBean id="pcSvc" scope="page" class="com.promocamp.model.PromoCampService"/>
<jsp:useBean id="peSvc" scope="page" class="com.promoeqpt.model.PromoEqptService"/>
<jsp:useBean id="pfSvc" scope="page" class="com.promofood.model.PromoFoodService"/>
<jsp:useBean id="campSvc" scope="page" class="com.camp.model.CampService"/>
<jsp:useBean id="eqptSvc" scope="page" class="com.equipment.model.EquipmentService"/>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService"/>
<html>
<head>
	<meta charset="UTF-8">
    <title>promoSelect</title>
    <style type="text/css">
        #searchBar{
            display: inline;
            position: relative;
            left: 250px;
        }
        #searchIcon{
            transform: translate(2px, 6px);
        }
        div.btnBar{
            margin-bottom: 30px;
        }
        table, th, td{
            border: 1px solid black;
        }
        table{
            width: 600px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>促銷專案管理</h1>
        <div class="btnBar">
            <a href="promoCreate.jsp?vd_no="><button>新增促銷專案</button></a>
            <form id="searchBar" method="post" action="/PromotionServlet">
                <svg id="searchIcon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                    <path fill-rule="evenodd" d="M14.53 15.59a8.25 8.25 0 111.06-1.06l5.69 5.69a.75.75 0 11-1.06 1.06l-5.69-5.69zM2.5 9.25a6.75 6.75 0 1111.74 4.547.746.746 0 00-.443.442A6.75 6.75 0 012.5 9.25z"></path>
                </svg>
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyWord" placeholder="輸入關鍵字...">
                <input type="submit" value="查詢">
            </form>
        </div>
        <div class="promoRows">
            <table>
                <thead>
                    <th>#</th>   
                    <th>促銷專案名稱</th>
                    <th>促銷開始日期</th>
                    <th>促銷結束日期</th>
                    <th>狀態</th>
                </thead>
                <tbody>
                    <c:forEach var="proVO" items="${proSvc.allPro}" varStatus="proVOi">
                    	<tr>
	                        <td>${proVOi.index + 1}</td>
	                        <td><a href=${"/promoDetail.jsp?pro_no="}${proVO.pro_no}>${proVO.pro_name}</a></td>
	                        <td>${proVO.pro_start}</td>
	                        <td>${proVO.pro_end}</td>
	                        <td>
								<c:if test="${proVO.pro_stat == 1}">
									啟用
								</c:if>
								<c:if test="${proVO.pro_stat == 0}">
									未啟用
								</c:if>
	                        </td>
                    	</tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<script type="text/javascript">
	//get vd_no from promoSelect page.
	var currentURL = new URL(window.location.href);
	var vd_no = currentURL.searchParams.get("vd_no");
	console.log(vd_no);
	// prompt vd_no if haven't type in yet.
	var createBtn = document.querySelector("div.btnBar").firstElementChild;
	if(vd_no == null){
		var vd_no1 = window.prompt("Please type in your vendor no. : ", "V000000001");
	    createBtn.href = createBtn.href + vd_no1;
	}else{
		createBtn.href = createBtn.href + vd_no;
	}
</script>
</html>