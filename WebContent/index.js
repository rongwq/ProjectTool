$(function() {
    $('#mytabs').tabs();
})

function showPop(dateVal){
	if(dateVal){
		var year = $("#yearMonth").data("year");
		var month = $("#yearMonth").data("month");
		var nowDate = year+"-"+month+"-"+dateVal;
		var val = window.prompt(nowDate,nowDate+"：" );
		if(val){ 
			// 点击的是“确定”
			var dateVal = val.split("：")[0];
			var dateStr = val.split("：")[1]
			save(nowDate, dateVal, dateStr);
		}else if(val === ""){ 
			// 用户没有输入内容点击的“确定”
			return;
		}else{ 
			// 点击的是“取消”
			return;
		}
	}
}

function detail(dateVal){
	if(dateVal){
		var year = $("#yearMonth").data("year");
		var month = $("#yearMonth").data("month");
		var nowDate = year+"-"+month+"-"+dateVal;
		$.ajax({
			url : getRootPath() + "/detail",
			data : {
				"time" : nowDate
			},
			dataType : "text",
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				var list = obj.resultData;
				var str = "";
				for (var i = 0; i < list.length; i++) {
					str += "<tr>";
					str += '<td>'+list[i].start_date+'</td>';
					str += '<td>'+list[i].expir_date+'</td>';
					str += '<td>'+list[i].msg+'</td>';
					str += '<td>'+
	                    '<div class="am-btn-toolbar">'+
	                    	'<div class="am-btn-group am-btn-group-xs">'+
			                    '<button type="button" name="editBtn" onclick="finish('+list[i].id+')" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">完成</button>'+
			                    '<button type="button" name="delBtn" onclick="del('+list[i].id+')" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">删除</button> '+
				            '</div>'+
				        '</div>'+
				        '</td>';
					str += "</tr>";
				}
				$("#selectDay").text(nowDate);
				$("#selectTBody").html(str);
			}
		})
	}
}

function save(startDate,expirDate,msg){
	$.ajax({
		url : getRootPath() + "/save",
		data : {
			"startDate" : startDate,
			"expirDate" : expirDate,
			"msg" : msg
		},
		dataType : "text",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alert(obj.resultDes);
			location.reload(false);
		}
	})
}

function finish(id){
	$.ajax({
		url : getRootPath() + "/finish",
		data : {
			"id" : id
		},
		dataType : "text",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alert(obj.resultDes);
			location.reload(false);
		}
	})
}

function unfinish(id){
	$.ajax({
		url : getRootPath() + "/unfinish",
		data : {
			"id" : id
		},
		dataType : "text",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alert(obj.resultDes);
			location.reload(false);
		}
	})
}

function del(id){
	$.ajax({
		url : getRootPath() + "/del",
		data : {
			"id" : id
		},
		dataType : "text",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alert(obj.resultDes);
			location.reload(false);
		}
	})
}