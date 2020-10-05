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
<html>
<head>
	<meta charset="UTF-8">
	<title>promoDetail</title>
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
            display: inline;
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
        select{
        	color: black;
        }
        button.addBtn{
        	display: none;
        }
        #editBtn{
        	display: block;
        	margin: 20px;
        }
        input[type='submit']{
        	display: none;
        }
        div.campItem{
        	display: none;
        }
        div.eqptItem{
        	display: none;
        }
        div.foodItem{
        	display: none;
        }
    </style>
</head>
<html>
<body>
    <div class="container">
        <h1>促銷專案詳情</h1>
        <button type="button" id="editBtn">編輯促銷專案</button>
        <form method="post" action="PromotionServlet">
            <input type="hidden" name="action" value="edit">
            <div class="info">
                <span>促銷專案</span>
                促銷專案名稱：<input type="text" name="pro_name" value="(範例)春節第一波促銷專案" disabled>
                促銷開始日期：<input type="date" name="pro_start" value="2020-01-01" disabled>
                促銷結束日期：<input type="date" name="pro_end" value="2020-01-31" disabled>
                <input type="hidden" name="vd_no" value="">
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
                營位名稱：<select name="pc_campno" disabled>
                    <option value="" selected>(範例)大木屋</option>
                    <c:forEach var="campVO" items="${campSvc.all}">
                    	<c:if test="${campVO.campvdno == param.vd_no}">
                    		<option value="${campVO.campno}">${campVO.campname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="camp_price" value="" disabled>
                    促銷價：<input type="number" name="pc_price" disabled>
                </div>
            </div>
            <button type="button" class="addBtn">新增營位</button>
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
                裝備名稱：<select name="pe_eqptno" disabled>
                    <option value="" selected>(範例)大帳篷</option>
                    <c:forEach var="eqptVO" items="${eqptSvc.all}">
                    	<c:if test="${eqptVO.eqptvdno == param.vd_no}">
                    		<option value="${eqptVO.eqptno}">${eqptVO.eqptname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="eqpt_price" value="" disabled>
                    促銷價：<input type="number" name="pe_price" disabled>
                </div>
            </div>
            <button type="button" class="addBtn">新增裝備</button>
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
                食材名稱：<select name="pf_foodno" disabled>
                    <option value="" selected>(範例)大雞腿</option>
                    <c:forEach var="foodVO" items="${foodSvc.all}">
                    	<c:if test="${foodVO.foodvdno == param.vd_no}">
                    		<option value="${foodVO.foodno}">${foodVO.foodname}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <div class="editPrice">
                    原價：<input type="number" name="food_price" value="" disabled>
                    促銷價：<input type="number" name="pf_price" disabled>
                </div>
            </div>
            <button type="button" class="addBtn">新增食材</button>
        </div>
        <input type="submit" value="完成編輯">
    </form>
</div>
</body>
<script type="text/javascript">
	// get vd_no from promoSelect page.
	var currentURL = new URL(window.location.href);
	var vd_no = currentURL.searchParams.get("vd_no");
	document.querySelector("div.info input[name='vd_no']").value = vd_no;
	
	// get pro_no from promoSelect page.
	var currentURL = new URL(window.location.href);
	var pro_no = currentURL.searchParams.get("pro_no");
	
	// Ajax get promotion detail data.
    var formData = new FormData();
    formData.append("action", "ajax_getDetail");
    formData.append("vd_no", vd_no);
    formData.append("pro_no", pro_no);
    var xhr = new XMLHttpRequest();
	var url = "PromotionServlet";
    xhr.open("POST", url);
    xhr.send(formData);
    xhr.onreadystatechange = function(){
        if(this.readyState == 4){
            console.log("promoDetail data request complete.");
            if(this.status == 200){
                console.log("request network OK.");
                var detailJson = JSON.parse(xhr.responseText);
                console.log(detailJson);
                // Get promotion data & set to html
                var promoInfo = detailJson.promotion;
                document.querySelector("input[name='pro_name']").value = promoInfo.pro_name;
                document.querySelector("input[name='pro_start']").value = promoInfo.pro_start;
                document.querySelector("input[name='pro_end']").value = promoInfo.pro_end;
                // Get promo_camp data & set to html
                var pcList = detailJson.pcList;
                var campItem = document.querySelector("div.campItem");
                for(let i = 0; i < pcList.length; i++){
                	let campItemi = campItem.cloneNode(true);
                	campItemi.style.display = "block";
                	if(i != 0){
                		campItemi.children[2].firstElementChild.removeAttribute("selected");
                	}
                	campItemi.children[2].firstElementChild.innerText = pcList[i].camp_name;
                	campItemi.children[2].firstElementChild.value = pcList[i].pc_campno;
                	campItemi.children[3].firstElementChild.value = pcList[i].camp_price;
                	campItemi.children[3].lastElementChild.value = pcList[i].pc_price;
                	// register select onchange & deleteIcon onclick behavior.
                    for(let itemChild of campItemi.children){
                        if(itemChild.tagName == "SELECT"){
                            itemChild.onchange = function(){
                            	itemShowPrice(this, vd_no, this.name, this.value);
                            }	
                        }
                        if(itemChild.getAttribute("class") == "deleteIcon"){
                            itemChild.onclick = function(){
                            	campItemi.remove();
                            }
                        }
                    };
                	document.querySelector("div.camp  button").before(campItemi);
                	console.log(pcList[i].pc_prono + " " + pcList[i].pc_campno + "insert complete.");
                }
                campItem.remove();
                
                // Get promo_eqpt data & set to html
                var peList = detailJson.peList;
                var eqptItem = document.querySelector("div.eqptItem");
                for(let i = 0; i < peList.length; i++){
                	let eqptItemi = eqptItem.cloneNode(true);
                	eqptItemi.style.display = "block";
                	if(i != 0){
                		eqptItemi.children[2].firstElementChild.removeAttribute("selected");
                	}
                	eqptItemi.children[2].firstElementChild.innerText = peList[i].eqpt_name;
                	eqptItemi.children[2].firstElementChild.value = peList[i].pe_eqptno;
                	eqptItemi.children[3].firstElementChild.value = peList[i].eqpt_price;
                	eqptItemi.children[3].lastElementChild.value = peList[i].pe_price;
                	// register select onchange & deleteIcon onclick behavior.
                    for(let itemChild of eqptItemi.children){
                        if(itemChild.tagName == "SELECT"){
                            itemChild.onchange = function(){
                            	itemShowPrice(this, vd_no, this.name, this.value);
                            }	
                        }
                        if(itemChild.getAttribute("class") == "deleteIcon"){
                            itemChild.onclick = function(){
                            	eqptItemi.remove();
                            }
                        }
                    };
                	document.querySelector("div.eqpt button").before(eqptItemi);
                	console.log(peList[i].pe_prono + " " + peList[i].pe_eqptno + "insert complete.");
                }
                eqptItem.remove();
                
                // Get promo_food data & set to html
                var pfList = detailJson.pfList;
                var foodItem = document.querySelector("div.foodItem");
                for(let i = 0; i < pfList.length; i++){
                	let foodItemi = foodItem.cloneNode(true);
                	foodItemi.style.display = "block";
                	if(i != 0){
                		foodItemi.children[2].firstElementChild.removeAttribute("selected");
                	}
                	foodItemi.children[2].firstElementChild.innerText = pfList[i].food_name;
                	foodItemi.children[2].firstElementChild.value = pfList[i].pf_foodno;
                	foodItemi.children[3].firstElementChild.value = pfList[i].food_price;
                	foodItemi.children[3].lastElementChild.value = pfList[i].pf_price;
                	// register select onchange & deleteIcon onclick behavior.
                    for(let itemChild of foodItemi.children){
                        if(itemChild.tagName == "SELECT"){
                            itemChild.onchange = function(){
                            	itemShowPrice(this, vd_no, this.name, this.value);
                            }	
                        }
                        if(itemChild.getAttribute("class") == "deleteIcon"){
                            itemChild.onclick = function(){
                            	foodItemi.remove();
                            }
                        }
                    };
                	document.querySelector("div.food  button").before(foodItemi);
                	console.log(pfList[i].pf_prono + " " + pfList[i].pf_foodno + "insert complete.");
                }
                foodItem.remove();
                
            }else{
                console.log("request network Failed. " + this.status);
            }
        }
    }
    
 	// click editBtn to let user edit fields.
    var editBtn = document.getElementById("editBtn");
    var submitBtn = document.querySelector("input[type='submit']");
    editBtn.onclick = function(){
    	// vanish the edit button when user editting.
    	editBtn.style.display = 'none';
    	// show the submit button when user editting.
    	submitBtn.style.display = 'block';
    	// remove disabled attribute to all edited field.
    	document.querySelector("input[name='pro_name']").removeAttribute("disabled"); // promotion name
    	document.querySelector("input[name='pro_start']").removeAttribute("disabled"); // promotion start date
    	document.querySelector("input[name='pro_end']").removeAttribute("disabled"); // promotion end date
    	// camp items
    	document.querySelectorAll("select[name='pc_campno']").forEach(function(campSelecti, index){ 
    		campSelecti.removeAttribute("disabled");
    		campSelecti.nextElementSibling.lastElementChild.removeAttribute("disabled");
    		if(index != 0){
    			campSelecti.previousElementSibling.previousElementSibling.style.display = "none";
    			campSelecti.previousElementSibling.style.display = "inline";
    		}
    	});
    	// eqpt items
    	document.querySelectorAll("select[name='pe_eqptno']").forEach(function(eqptSelecti, index){ 
    		eqptSelecti.removeAttribute("disabled");
    		eqptSelecti.nextElementSibling.lastElementChild.removeAttribute("disabled");
    		if(index != 0){
    			eqptSelecti.previousElementSibling.previousElementSibling.style.display = "none";
    			eqptSelecti.previousElementSibling.style.display = "inline";
    		}
    	});
    	// food items
    	document.querySelectorAll("select[name='pf_foodno']").forEach(function(foodSelecti, index){ 
    		foodSelecti.removeAttribute("disabled");
    		foodSelecti.nextElementSibling.lastElementChild.removeAttribute("disabled");
    		if(index != 0){
    			foodSelecti.previousElementSibling.previousElementSibling.style.display = "none";
    			foodSelecti.previousElementSibling.style.display = "inline";
    		}
    	});
    	// addBtn in all items form block.
    	document.querySelectorAll("button.addBtn").forEach(function(addBtni){
    		addBtni.style.display = "block";
    	});
    }
    
    // show new item when click add btn.
    var addBtn = document.querySelectorAll("button:last-child");
    addBtn.forEach(function(addBtni){
        addBtni.onclick = function(){
            var item = this.previousElementSibling.cloneNode(true);
            item.children[2].firstElementChild.value = "";
            item.children[2].firstElementChild.innerText = "請選擇食材...";
            item.lastElementChild.firstElementChild.value = ""; //origin price field
            item.lastElementChild.lastElementChild.value = ""; //promotion price field
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
    

    
    // select onchange callback function to show price field with origin price.
    function itemShowPrice(selectDOM, vd_no, item_type, item_no){
        if(selectDOM.value == ""){
        	selectDOM.nextElementSibling.style.display = "none";
        }else{
            ajaxGetItemPrice(selectDOM, vd_no, item_type, item_no);
            selectDOM.nextElementSibling.style.display = "inline";
        }
    }
    // Ajax get item origin price.
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
                if(this.status == 200){
                    selectDOM.nextElementSibling.firstElementChild.value = xhr.responseText;
                    console.log(vd_no);
                    console.log(item_type);
                    console.log(item_no);
                    console.log(xhr.responseText);
                }else{
                    console.log("request network Failed. " + this.status);
                }
            }
        }
    }
    
    
</script>
</html>