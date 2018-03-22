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
	<title>信访举报</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">信访举报</div>
					<div class="clearfix fr">

						<a id="sample_editable_1_new" class="btn green" href="#">
							添加
						</a>

						<%--<span class="controllerClass btn green file_but" >--%>
							<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
							<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
					</div>

				</div>
			</form>
				<div class="clearfix">
					<div class="control-group">
						<div id="query" style="float: left;">
							<form action="${path }/zzb/app/console/asetA01/ajax/list" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								被举报人：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
								所在单位：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
								处理结果：
								<select class="select_form" tabindex="-1" name="type" id="type" style="width: 100px; margin-bottom: 0px;" >
									<option value="">全部</option>
									<option value="">结果属实</option>
									<option value="">不属实</option>
									<option value="">待查</option>
								</select>
								举报时间：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
								到<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
								<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
								<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
							</form>
						</div>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="90">被举报人姓名</th>
							<th >单位职务</th>
							<th width="150">职级</th>
							<th width="90">被举报时间</th>
							<th width="70">处理状态</th>
							<th width="70">处理结果</th>
							<th width="150">备注</th>
							<th width="150">下载报告</th>
							<th width="90">操作</th>
						</tr>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="${path}/zzb/app/console/gbjd/xfjb/edit" class="">刘洋</a></td>
								<td  >州委组织部副部长，州委老干部局局长</td>
								<td  >副处级</td>
								<td  >2018.01.09</td>
								<td  >已处理</td>
								<td  >结果属实</td>
								<td  >换届期间</td>
								<td  >
									<a href="#">举报件</a> |
									<a href="#">调查报告下载</a>
								</td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">张翠珍</a></td>
								<td  >州委老干部局副局长</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">王照训</a></td>
								<td  >州委老干部局副局长</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">彭小平</a></td>
								<td  >州委老干部局副处级干部、州级机关离休干部休养所所长</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">石利民</a></td>
								<td  >州委老干部局关工委秘书长（副处级）</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">黄华</a></td>
								<td  >州委宣传部副处级干部</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">苏明超</a></td>
								<td  >州委宣传部副调研员</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">向东</a></td>
								<td  >州委宣传部副调研员</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">马本江</a></td>
								<td  >州委政法委执法监察室主任（副处级）</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="#" class="">欧金忠</a></td>
								<td  >州委政法委政治部主任（副处级）</td>
								<td  >副处级</td>
								<td  >2017.09.10</td>
								<td  >已处理</td>
								<td  >不属实</td>
								<td  >公式期间</td>
								<td  ><a href="#">举报件</a> |
									<a href="#">调查报告下载</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>

						</tbody>
					</table>
					<jsp:include page="/WEB-INF/jsp/common/page.jsp">
						<jsp:param value="${pager.total }" name="total"/>
						<jsp:param value="${pager.pageCount }" name="endPage"/>
						<jsp:param value="${pager.pageSize }" name="pageSize"/>
						<jsp:param value="${pager.pageNum }" name="page"/>
					</jsp:include>
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

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});
		var myLoading = new MyLoading("${path}",{zindex:20000});
		function ajaxSubmit(){
			var fileInput = document.getElementById("btn-browseTemplate");
			if(fileInput.files.length>0){
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if(arr.length<2 || !(arr[arr.length-1]=="doc" || arr[arr.length-1]=="docx"|| arr[arr.length-1]=="DOC"|| arr[arr.length-1]=="DOCX")){
					showTip("提示","请上传word文件",2000);
					return;
				}
			}else{
				showTip("提示","请选择文件上传",2000);
				return;
			}
			//hideErrorMsg();
			$("#importForm").ajaxSubmit({
				url : "${path }/zzb/app/console/asetA01/ajax/execute?b01Id=${b01Id}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend:function(XHR){
					myLoading.show();
				},
				success : function(json){
					if(json.code == 1){
						searchSubmit()
						showTip("提示","操作成功",2000);
					}else if(json.code == -1){
						showTip("提示", json.message,2000);
					}else if(json.code == -2){
						showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
						//$('#downloanErrorTd').show();
						//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
						//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
					}else{
						showTip("提示","出错了,请检查网络!",2000);
					}
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","出错了,请检查网络!",2000);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}

		//批量上传干部人员审批表
		$("#btn-moreAttTemplate").bind("change", function (evt) {
			if ($(this).val()) {
				gbrmspbSubmit();
			}
			$(this).val('');
		});

		function gbrmspbSubmit() {
			var fileInput = document.getElementById("btn-moreAttTemplate");
			if (fileInput.files.length > 0) {
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
					showTip("提示", "请上传zip文件", 2000);
					return;
				}
			} else {
				showTip("提示", "请选择文件上传", 2000);
				return;
			}
			$("#importForm").ajaxSubmit({
				url: "${path }/zzb/app/console/GbMca01/gbrmspb/ajax/batch/upload?b01Id=${b01Id}",
				type: "post",
				headers: {
					OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend: function (XHR) {
					myLoading.show();
				},
				success: function (json) {
					if (json.code == 1) {
						showTip("提示","上传成功",2000);
						<%--window.location.href="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}";--%>
					} else if (json.code == -1) {
						showTip("提示", json.message, 2000);
					} else {
						showTip("提示", "出错了,请检查网络!", 2000);
					}
				},
				error: function (arg1, arg2, arg3) {
					showTip("提示", "出错了,请检查网络!", 2000);
				},
				complete: function (XHR, TS) {
					myLoading.hide();
				}
			});
		}
	})();

	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
				'pageNum':pageNum,
				'pageSize':pageSize
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	function searchSubmit(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}


	var view = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/view",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'id':id,
				'b01Id':"${b01Id}"
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#xmQuery").val('');
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	function exportGbrmsp(){
		$.cloudAjax({
			path : '${path}',
			url : "${path }/zzb/app/console/asetA01/ajax/exportGbrmsp",
			type : "post",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data.success == true){
					showTip("提示","生成干部任免审批表成功!",2000);
					//setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", "生成干部任免审批表失败!", 2000);
				}
			},
			error : function(){
				showTip("提示","出错了请联系管理员",2000);
			}
		});
	}
</script>
</body>
</html>
