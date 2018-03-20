<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<link href="${path }/css/style.css" rel="stylesheet" type="text/css">
	<!-- END PAGE LEVEL STYLES -->
	<title>知识资源库</title>
	<style type="text/css">


		.dropdown-menu{ background-color:inherit; box-shadow: none; border:none;}
		.navigdrop_down{left:-116px;width: 200px;padding: 0;}
		.navigdrop_down02{left:-27px;padding: 0;width: 120px;}

		.nav_a{ border-radius: 8px; color: #ffffff; float: left; margin-top: 50px; margin-left: 20px; transition: background 0.4s ease-in-out 0s;  width: 200px; padding:15px 10px;}
		.navig_contact{width:1200px; margin-top:50px;}
		.dlnavigcont{ height:180px; width: 200px; text-align:center; margin-left:0;}
		.dlnavigcont dd {padding-top: 1px; width:200px;}
		.dlnavigcont dt {float: inherit;height: 72px;width: 200px;margin-left:0;}
		.dlnavigcont dt img{ max-height:72px;}
		.dlnavigcont dd h4 {font-size: 16px;color:#000;padding: 5px 0 3px;text-align:center;}
		.dlnavigcont dd .navp01{ text-align: center;}
	</style>
</head>
<body>
<div class="navig">
	<div class="navig_contact">
		<a class="nav_a" href="javascript:openGzzzb()">
			<dl class="dlnavigcont">
				<dt><img src="${path}/images/templateImage/wjk1.jpg"></dt>
				<dd>
					<h4>组工文件库</h4>
					<p class="navp01">用于管理、查看组工文件。</p>
				</dd>
			</dl>
		</a>
		<a class="nav_a" href="zzb/app/console/asetA01/?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
			<dl class="dlnavigcont">
				<dt><img src="${path}/images/templateImage/wjk2.jpg"></dt>
				<dd>
					<h4>部门文件库</h4>
					<p class="navp01">用于管理、查看部门文件。</p>
				</dd>
			</dl>
		</a>
		<a class="nav_a" href="javascript:openGzzzb()">
			<dl class="dlnavigcont">
				<dt><img src="${path}/images/templateImage/wjk3.jpg"></dt>
				<dd>
					<h4>个人文件库</h4>
					<p class="navp01">用于管理、查看个人文件。</p>
				</dd>
			</dl>
		</a>
	</div>
</div>
<script language="JavaScript">

	function openGzzzb(){
		var url ="http://localhost:8080/GZZZB/rm/resource/rmShouWenIndexMain.jsp?storeroomId=772227561E1E1E0B006BBA7A8AA163CF&storeroomCode=0004";
		window.open(url);
	}
</script>
</body>
</html>
