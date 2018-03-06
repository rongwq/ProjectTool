<%@ page language="java" contentType="text/html; charset=utf8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>项目备忘</title>
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path ;   
%>   
<base href="<%=basePath%>" >
<link rel="stylesheet" href="<%=basePath%>/assets/css/amazeui.min.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
</head>
<body>
    <jsp:useBean id="bean" class="com.rong.core.CanlendarFace" />

    <%

        String year, month, date;

        year = request.getParameter("year");

        month = request.getParameter("month");

        date = request.getParameter("date");

        out.println(bean.showHtml(basePath,year,month,date));

    %>
    <div class="am-tabs" id="mytabs">
  <ul class="am-tabs-nav am-nav am-nav-tabs">
    <li class="am-active"><a href="javascript:void(0)">选中项目</a></li>
    <li><a href="javascript:void(0)">未完成</a></li>
    <li><a href="javascript:void(0)">完成</a></li>
    <li><a href="javascript:void(0)">逾期</a></li>
  </ul>

  <div class="am-tabs-bd">
    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
     <!-- 选中项目 start -->
            <div class="am-u-sm-12 am-scrollable-horizontal">
                <table
                    class="am-table am-table-striped am-table-hover table-main am-text-nowrap am-table-bordered am-table-centered">
                    <thead>
                        <tr>
                            <th colspan="4" id="selectDay">选则日期</th>
                        </tr> 
                        <tr>
                            <th width="10%">开始时间</th>
                            <th width="10%">过期时间</th>
                            <th width="60%">内容</th>
                            <th width="20%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="selectTBody">
                    </tbody>
                </table>
            </div>
        <!-- 选中日期项目 end -->
    </div>
    <div class="am-tab-panel am-fade" id="tab2">
         <!-- 未完成项目 start -->
            <div class="am-u-sm-12 am-scrollable-horizontal">
                <table
                    class="am-table am-table-striped am-table-hover table-main am-text-nowrap am-table-bordered am-table-centered">
                    <thead>
                        <tr>
                            <th colspan="4">未完成项目</th>
                        </tr> 
                        <tr>
                            <th width="10%">开始时间</th>
                            <th width="10%">过期时间</th>
                            <th width="60%">内容</th>
                            <th width="20%">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${unFinishedList}" var="item">
                            <tr>
                                <td><fmt:formatDate value="${item.start_date }" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${item.expir_date }" pattern="yyyy-MM-dd" /></td>
                                <td>${item.msg }</td>
                                <td>
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button type="button" name="editBtn" onclick="finish(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">完成</button>
                                            <button type="button" name="delBtn" onclick="del(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">删除</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        <!-- 未完成项目 end -->
    </div>
    <div class="am-tab-panel am-fade" id="tab3">
       <!-- 完成项目 start -->
            <div class="am-u-sm-12 am-scrollable-horizontal">
                <table
                    class="am-table am-table-striped am-table-hover table-main am-text-nowrap am-table-bordered am-table-centered">
                    <thead>
                        <tr>
                            <th colspan="4">完成项目</th>
                        </tr> 
                        <tr>
                            <th width="10%">开始时间</th>
                            <th width="10%">过期时间</th>
                            <th width="60%">内容</th>
                            <th width="20%">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${finishedList}" var="item">
                            <tr>
                                <td><fmt:formatDate value="${item.start_date }" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${item.expir_date }" pattern="yyyy-MM-dd" /></td>
                                <td>${item.msg }</td>
                                <td>
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button type="button" name="editBtn" onclick="unfinish(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">完成</button>
                                            <button type="button" name="delBtn" onclick="del(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">删除</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        <!-- 完成项目 end -->
    </div>
    <div class="am-tab-panel am-fade" id="tab4">
        <!-- 逾期项目 start -->
            <div class="am-u-sm-12 am-scrollable-horizontal">
                <table
                    class="am-table am-table-striped am-table-hover table-main am-text-nowrap am-table-bordered am-table-centered">
                    <thead>
                        <tr>
                            <th colspan="4">逾期项目</th>
                        </tr> 
                        <tr>
                            <th width="10%">开始时间</th>
                            <th width="10%">过期时间</th>
                            <th width="60%">内容</th>
                            <th width="20%">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${expirList}" var="item">
                            <tr>
                                <td><fmt:formatDate value="${item.start_date }" pattern="yyyy-MM-dd" /></td>
                                <td><fmt:formatDate value="${item.expir_date }" pattern="yyyy-MM-dd" /></td>
                                <td>${item.msg }</td>
                                <td>
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button type="button" name="editBtn" onclick="finish(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">完成</button>
                                            <button type="button" name="delBtn" onclick="del(${item.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">删除</button> 
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        <!-- 逾期项目 end -->
    </div>
  </div>
</div>
    
    
   
</body>
<script src="<%=basePath%>/assets/js/jquery.min.js"></script>
<script src="<%=basePath%>/assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>/assets/js/common.js"></script>
<script src="<%=basePath%>/index.js"></script>
</html>