<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<title>任免材料</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="viewDaModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					“${a01Vo.xm}”档案图片
				</h3>
			</div>
			<div class="modal-body" id="viewDaDiv" style="background-color: #f1f3f6;">
			</div>
		</div>
	</div>
</div>
<div id="virwGbjdModal" class="modal container hide fade" tabindex="-1" data-width="700">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="titleView" >
					干部监督情况
				</h3>
			</div>
			<div class="modal-body" id="virwGbjdDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">任免材料 ：满延春</div>
					<div class="clearfix fr">
						<%--<a class="btn green" href="javascript:void();">--%>
							<%--保存--%>
						<%--</a>--%>
						<a class="btn green" href="javascript:viewDa();">
							电子档案
						</a>
						<a class="btn green" href="javascript:void();">
							任免文件
						</a>
						<a class="btn green" href="javascript:viewGbjd();">
							干部监督信息
						</a>
						<a class="btn green" href="javascript:void();">
							输出
						</a>
						<a class="btn" href="${path }/zzb/app/console/gbrm/ry/list"><i class="icon-undo"></i>返回</a>
					</div>

				</div>
			</form>
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills" id="tabs">
								<li class="span3 active">
									<a class="step active" data-toggle="tab" href="#tab1" id="#tab_2_1">
										<span class="number">1</span>
										<span class="desc"><i class="icon-ok"></i> 干部任免审批表（已完成）</span>
									</a>
								</li>
								<li class="span3">
									<a class="step" data-toggle="tab" href="#tab2"  id="#tab_2_2">
										<span class="number">2</span>
										<span class="desc"><i class="icon-ok"></i> 任免情况（已完成）</span>
									</a>
								</li>

								<li class="span3">
									<a class="step" data-toggle="tab" href="#tab3"  id="#tab_2_3">
										<span class="number">3</span>
										<span class="desc"><i class="icon-ok"></i> 考察情况（已完成）</span>
									</a>
								</li>

								<li class="span3 ">
									<a class="step" data-toggle="tab" href="#tab4"  id="#tab_2_4">
										<span class="number">4</span>
										<span class="desc"><i class="icon-ok"></i> 征求意见</span>
									</a>
								</li>

							</ul>
						</div>
					</div>
				</div>
				<div class="progress progress-success progress-striped" >
					<div id="barDiv" class="bar" style="width: 25%;"></div>
				</div>
				<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
					<div class="tab-pane active" id="tab_show">
					</div>
				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
	(function(){
		App.init();
		baseLoad();
		$("#tabs li a").each(function(){
			$(this).click(function(){
				if($(this).attr("id")=="#tab_2_1"){
					baseLoad();
					$("#barDiv").width("25%");
				}else if($(this).attr("id")=="#tab_2_2"){
					rmqkLoad();
					$("#barDiv").width("50%");
				}else if($(this).attr("id")=="#tab_2_3"){
					kcqkLoad();
					$("#barDiv").width("75%");
				}else if($(this).attr("id")=="#tab_2_4"){
					zqyjLoad();
					$("#barDiv").width("100%");
				}
			});
		});
	})();

	function baseLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/gbrm/ajax/base",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}

	function rmqkLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/gbrm/ajax/rmqk",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}
	function kcqkLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/gbrm/ajax/kcqk",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}
	function zqyjLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/gbrm/ajax/zqyj",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}
	function viewGbjd(){
		$.ajax({
			url : "${path}/zzb/app/console/gbjd/ajax/viewGbjd",
			type : "post",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#virwGbjdDiv').html(html);
//                $('#titleView').text(tjmc);
				$('#virwGbjdModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	var viewDa = function(){
		var divHeight = $(window).height()-100;
		$('#viewDaModal').attr("data-height",divHeight);
		$.ajax({
			url:"${path}/zzb/app/console/a38/ajax/viewImgManage",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#viewDaDiv').html(html);

				$('#viewDaModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
</script>
</body>
</html>
