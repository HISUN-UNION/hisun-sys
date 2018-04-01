<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<title>电子档案系统</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="portlet box grey ">
				<div class="relationbetTop" style="height: 10px">
					<div class="relationbetTop_left">姓名：戚媚</div>
					<div class="relationbetTop_but">
						<button type="button" class="btn green" onclick=""><i class="icon-ok"></i> 保存</button>
						<div class="btn-group" style="padding-bottom: 0px">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								干部库 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li >
									<a onclick="selectTyle('gbrmspb')">查看干部信息</a>
								</li>
								<li>
									<a onclick="selectTyle('kccl')">提取干部信息</a>
								</li>
								<li>
									<a onclick="selectTyle('dascqk')">取消关联</a>
								</li>

							</ul>
						</div>
						<a id="sample_editable_1_new" class="btn green" href="#">
							删除
						</a>
						<a id="sample_editable_1_new" class="btn green" href="#">
							转出
						</a>
						<div class="btn-group" style="padding-bottom: 0px">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								下载 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li >
									<a onclick="">电子表格目录</a>
								</li>
								<li>
									<a onclick="">欠缺材料信息</a>
								</li>
								<li>
									<a onclick="">档案图片下载</a>
								</li>

							</ul>
						</div>
						<a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
					</div>
				</div>
				<div class="tabbable tabbable-custom">
					<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
						<li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">档案基本信息</a></li>
						<li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">目录信息</a></li>
						<li><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">追缴材料信息</a></li>
					</ul>
					<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
						<div class="tab-pane active" id="tab_show">
						</div>
					</div>
				</div>
			</div>
	</div>
	<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script type="text/javascript">
	$(function(){
		App.init();
//		var bzCount=0;
//		var zwCount=0;
		baseLoad();

		var ciFlag=true;

//		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//			if($(e.target).attr('id')!='#tab_1_1'){
////				if(!ciFlag){
//					$("[id='#tab_1_1']").tab('show');
//					return;
////				}
//			}
//
//		});



		$("#tabs li a").each(function(){
			$(this).click(function(){
				if($(this).attr("id")=="#tab_1_1"){
					$("[id='#tab_1_1']").tab('show');
					baseLoad();
				}else if($(this).attr("id")=="#tab_1_2"){
					$("[id='#tab_1_2']").tab('show');
					mlLoad();
				}else if($(this).attr("id")=="#tab_1_3"){
					$("[id='#tab_1_3']").tab('show');
					zjclLoad();
				}

			});
		});

	});

	function cancel(){
		var loadType = "${loadType}";
		if(loadType=="zcManageList"){
			window.location.href = "${path}/zzb/app/console/a38/zcManageList";
		}else{
			window.location.href = "${path}/zzb/app/console/a38/list";
		}


	}

	//基本信息
	function baseLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/a38/ajax/base",
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
	function mlLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/a38/ajax/mlxxManage",
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

	function zjclLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/a38/ajax/zjclList",
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



</script>
</body>
</html>
