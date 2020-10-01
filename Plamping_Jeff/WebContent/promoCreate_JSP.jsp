<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.camp.model.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.food.model.*"%>
<!DOCTYPE html>
<jsp:useBean id="campSvc" scope="page" class="com.camp.model.CampService"/>
<jsp:useBean id="eqptSvc" scope="page" class="com.equipment.model.EquipmentService"/>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService"/>
<% String vd_no = request.getParameter("vd_no"); %>
<html>
<head>
    <title>promoCreate</title>
    <style type="text/css">
        span{
            display: block;
            margin-bottom: 10px;
            font-size: 20px;
        }
        div select{
            display: inline-block;
            margin-right: 20px;
        }
        button:last-child{
            display: block;
            margin-top: 10px;
        }
        form{
            border: 1px solid black;
        }
        form > input[type="submit"]{
            margin: 20px;
        }
        div.info{
            margin: 30px;
            padding: 10px;
            border-bottom: 1px solid black;
        }
        div input{
            margin-right: 20px;
        }
        div.camp{
            margin: 30px;
            padding: 10px;
            border-bottom: 1px solid black;
        }
        div.eqpt{
            margin: 30px;
            padding: 10px;
            border-bottom: 1px solid black;
        }
        div.food{
            margin: 30px;
            padding: 10px;
            border-bottom: 1px solid black;
        }
        div.editPrice{
            display: none;
        }
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        svg.deleteIcon{
            width: 12px;
            height: 12px;
            cursor: pointer;
            display: none;
        }
        svg.blankIcon{
            width: 12px;
            height: 12px;
            display: inline;
        }
    </style>
</head>
<body>
	<%=vd_no %>
    <div class="container">
        <h1>新增促銷專案</h1>
        <form method="post" action="PromotionServlet">
            <input type="hidden" name="action" value="create">
            <div class="info">
                <span>促銷專案</span>
                促銷專案名稱：<input type="text" name="pro_name">
                促銷開始日期：<input type="date" name="pro_start">
                促銷結束日期：<input type="date" name="pro_end">
                <input type="hidden" name="pro_vdno" value="">
            </div>
            <div class="camp">
                <span>促銷營位</span>
                <div class="campItem">
                    <svg class="blankIcon" width="492" height="492" xmlns="http://www.w3.org/2000/svg">
                      <rect fill="#fff" id="canvas_background" height="492" width="492" y="-1" x="-1"/>
                    </svg>
                    <svg class="deleteIcon" transform="translate(0, 0)" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                    viewBox="0 0 492 492" xml:space="preserve">
                    <path d="M300.188,246L484.14,62.04c5.06-5.064,7.852-11.82,7.86-19.024c0-7.208-2.792-13.972-7.86-19.028L468.02,7.872
                    c-5.068-5.076-11.824-7.856-19.036-7.856c-7.2,0-13.956,2.78-19.024,7.856L246.008,191.82L62.048,7.872
                    c-5.06-5.076-11.82-7.856-19.028-7.856c-7.2,0-13.96,2.78-19.02,7.856L7.872,23.988c-10.496,10.496-10.496,27.568,0,38.052
                    L191.828,246L7.872,429.952c-5.064,5.072-7.852,11.828-7.852,19.032c0,7.204,2.788,13.96,7.852,19.028l16.124,16.116
                    c5.06,5.072,11.824,7.856,19.02,7.856c7.208,0,13.968-2.784,19.028-7.856l183.96-183.952l183.952,183.952
                    c5.068,5.072,11.824,7.856,19.024,7.856h0.008c7.204,0,13.96-2.784,19.028-7.856l16.12-16.116
                    c5.06-5.064,7.852-11.824,7.852-19.028c0-7.204-2.792-13.96-7.852-19.028L300.188,246z"/>
                    </svg>
                營位名稱：<select name="pc_campno">
                    <option value="" selected>請選擇營位...</option>
                    <c:forEach var="campVO" items="${campSvc.all}">
                    	<c:if test="${campVO.campvdno == param.vd_no}">
                    		<option value="${campVO.campno}">${campVO.campname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="camp_price" value="1000" disabled>
                    促銷價：<input type="number" name="pc_price">
                </div>
            </div>
            <button type="button">新增營位</button>
        </div>
        <div class="eqpt">
            <span>促銷裝備</span>
            <div class="eqptItem">
                <svg class="blankIcon" width="492" height="492" xmlns="http://www.w3.org/2000/svg">
                      <rect fill="#fff" id="canvas_background" height="492" width="492" y="-1" x="-1"/>
                </svg>
                <svg class="deleteIcon" transform="translate(0, 0)" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                    viewBox="0 0 492 492" xml:space="preserve">
                    <path d="M300.188,246L484.14,62.04c5.06-5.064,7.852-11.82,7.86-19.024c0-7.208-2.792-13.972-7.86-19.028L468.02,7.872
                    c-5.068-5.076-11.824-7.856-19.036-7.856c-7.2,0-13.956,2.78-19.024,7.856L246.008,191.82L62.048,7.872
                    c-5.06-5.076-11.82-7.856-19.028-7.856c-7.2,0-13.96,2.78-19.02,7.856L7.872,23.988c-10.496,10.496-10.496,27.568,0,38.052
                    L191.828,246L7.872,429.952c-5.064,5.072-7.852,11.828-7.852,19.032c0,7.204,2.788,13.96,7.852,19.028l16.124,16.116
                    c5.06,5.072,11.824,7.856,19.02,7.856c7.208,0,13.968-2.784,19.028-7.856l183.96-183.952l183.952,183.952
                    c5.068,5.072,11.824,7.856,19.024,7.856h0.008c7.204,0,13.96-2.784,19.028-7.856l16.12-16.116
                    c5.06-5.064,7.852-11.824,7.852-19.028c0-7.204-2.792-13.96-7.852-19.028L300.188,246z"/>
                </svg>
                裝備名稱：<select name="pc_eqptno">
                    <option value="" selected>請選擇裝備...</option>
                    <c:forEach var="eqptVO" items="${eqptSvc.all}">
                    	<c:if test="${eqptVO.eqptvdno == param.vd_no}">
                    		<option value="${eqptVO.eqptno}">${eqptVO.eqptname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="eqpt_price" value="1000" disabled>
                    促銷價：<input type="number" name="pe_price">
                </div>
            </div>
            <button type="button">新增裝備</button>
        </div>
        <div class="food">
            <span>促銷食材</span>
            <div class="foodItem">
                <svg class="blankIcon" width="492" height="492" xmlns="http://www.w3.org/2000/svg">
                      <rect fill="#fff" id="canvas_background" height="492" width="492" y="-1" x="-1"/>
                </svg>
                <svg class="deleteIcon" transform="translate(0, 0)" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                    viewBox="0 0 492 492" xml:space="preserve">
                    <path d="M300.188,246L484.14,62.04c5.06-5.064,7.852-11.82,7.86-19.024c0-7.208-2.792-13.972-7.86-19.028L468.02,7.872
                    c-5.068-5.076-11.824-7.856-19.036-7.856c-7.2,0-13.956,2.78-19.024,7.856L246.008,191.82L62.048,7.872
                    c-5.06-5.076-11.82-7.856-19.028-7.856c-7.2,0-13.96,2.78-19.02,7.856L7.872,23.988c-10.496,10.496-10.496,27.568,0,38.052
                    L191.828,246L7.872,429.952c-5.064,5.072-7.852,11.828-7.852,19.032c0,7.204,2.788,13.96,7.852,19.028l16.124,16.116
                    c5.06,5.072,11.824,7.856,19.02,7.856c7.208,0,13.968-2.784,19.028-7.856l183.96-183.952l183.952,183.952
                    c5.068,5.072,11.824,7.856,19.024,7.856h0.008c7.204,0,13.96-2.784,19.028-7.856l16.12-16.116
                    c5.06-5.064,7.852-11.824,7.852-19.028c0-7.204-2.792-13.96-7.852-19.028L300.188,246z"/>
                </svg>
                食材名稱：<select name="pc_foodno">
                    <option value="" selected>請選擇食材...</option>
                    <c:forEach var="foodVO" items="${foodSvc.all}">
                    	<c:if test="${foodVO.foodvdno == param.vd_no}">
                    		<option value="${foodVO.foodno}">${foodVO.foodname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="food_price" value="1000" disabled>
                    促銷價：<input type="number" name="pf_price">
                </div>
            </div>
            <button type="button">新增食材</button>
        </div>
        <input type="submit" value="新增促銷專案">
    </form>
</div>
</body>
<script type="text/javascript">
	// get vd_no from promoSelect page.
	var currentURL = new URL(window.location.href);
	var vd_no = currentURL.searchParams.get("vd_no");
	document.querySelector("div.info input[name='pro_vdno']").value = vd_no;
    // show price edit field when select item.
    var select = document.querySelectorAll("select");
    select.forEach(function(selecti){
        selecti.onchange = function(){
        	itemShowPrice(this, vd_no, this.name, this.value);
        }
        
    });
    // show new item when click add btn.
    var addBtn = document.querySelectorAll("button:last-child");
    addBtn.forEach(function(addBtni){
        addBtni.onclick = function(){
            var item = this.previousElementSibling.cloneNode(true);
            item.lastElementChild.style.display = "none"; //price field
            item.firstElementChild.style.display = "none"; // blankIcon svg
            item.firstElementChild.nextElementSibling.style.display = "inline"; // deleteIcon svg
            this.before(item);
            // register new create item select onchange listener.
            for(let itemChild of item.children){
                if(itemChild.tagName == "SELECT"){
                    itemChild.onchange = function(){
                    	itemShowPrice(this, vd_no, this.name, this.value);
                    }	
                }
                if(itemChild.getAttribute("class") == "deleteIcon"){
                    itemChild.onclick = function(){
                        item.remove();
                    }
                }
            };
        }
    });
    // onchange callback function of select element.
    function itemShowPrice(selectDOM, vd_no, item_type, item_no){
        if(selectDOM.value == ""){
        	selectDOM.nextElementSibling.style.display = "none";
        }else{
            ajaxGetItemPrice(selectDOM, vd_no, item_type, item_no);
            selectDOM.nextElementSibling.style.display = "inline";
        }
    }
    // Ajax update selected item original price.
    function ajaxGetItemPrice(selectDOM, vd_no, item_type, item_no){
        var formData = new FormData();
        formData.append("action", "ajax_getPrice");
        formData.append("vd_no", vd_no);
        formData.append("item_type", item_type);
        formData.append("item_no", item_no);
        var xhr = new XMLHttpRequest();
		var url = "PromotionServlet";
        xhr.open("POST", url);
        xhr.send(formData);
        xhr.onreadystatechange = function(){
            if(this.readyState == 4){
                console.log("request complete.");
                if(this.status == 200){
                    console.log("request network OK.");
                    selectDOM.nextElementSibling.firstElementChild.value = xhr.responseText;
                    return xhr.responseText;
                }else{
                    console.log("request network Failed. " + this.status);
                }
            }
        }
    }
    
</script>
</html>