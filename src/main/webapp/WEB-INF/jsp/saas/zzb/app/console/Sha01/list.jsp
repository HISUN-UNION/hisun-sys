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
<!-- END PAGE LEVEL STYLES -->
<title>上会名单</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<form class="portlet box grey"id="importForm" enctype="multipart/form-data">
					<input type="hidden" id="shpcId" name="shpcId" value="${shpcId}"/>
					<div class="portlet-title">
						<div class="caption">上会名单</div>
						<div class="clearfix fr">
							<span class="controllerClass btn file_but" >
									<span><i class="icon-circle-arrow-up"></i>上传名单</span>
									<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">
							</span>
							<span class="controllerClass btn file_but" >
									<span><i class="icon-circle-arrow-up"></i>批量上传</span>
									<input class="file_progress" type="file" name="attachMoreFile" id="btn-MoreTemplate">
							</span>
							<a class="btn" href="${path }/zzb/app/console/bwh/">返回</a>
						</div>

					</div>

					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th width="6%">姓名</th>
									<th width="5%">性别</th>
									<th width="5%">民族</th>
									<th width="5%">籍贯</th>
									<th width="5%">出生<br><br>年月</th>
									<th width="5%">参加<br>工作<br>时间</th>
									<th width="5%">入党<br><br>时间</th>
									<th width="8%">文化<br><br>程度</th>
									<th width="5%">任现<br>级别<br>时间</th>
									<th width="10%">民主<br>推荐<br>情况</th>
									<th width="20%">现工作单位及职务</th>
									<th>拟调整配备意见</th>
									<th width="5%">干部<br>一科<br>意见</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td><a href="${path}/zzb/app/console/Sha01/view?id=${vo.id }"><c:out value="${vo.xm}"></c:out></a></td>
										<td><c:out value="${vo.xb}"></c:out></td>
										<td><c:out value="${vo.mz}"></c:out></td>
										<td><c:out value="${vo.jg}"></c:out></td>
										<td title="${vo.csny}"><c:out value="${vo.csny}"></c:out></td>
										<td title="${vo.cjgzsj}"><c:out value="${vo.cjgzsj}"></c:out></td>
										<td title="${vo.rdsj}"><c:out value="${vo.rdsj}"></c:out></td>
										<td title="${vo.whcd}"><c:out value="${vo.whcd}"></c:out></td>
										<td title="${vo.rxjbsj}"><c:out value="${vo.rxjbsj}"></c:out></td>
										<td title="${vo.mztjqk}"><c:out value="${vo.mztjqk}"></c:out></td>
										<td title="${vo.xgzdwjzw}"><c:out value="${vo.xgzdwjzw}"></c:out></td>
										<td title="${vo.ntzpbyj}"><c:out value="${vo.ntzpbyj}"></c:out></td>
										<td title="${vo.shyj}"><c:out value="${vo.shyj}"></c:out></td>
									</tr>
								</c:forEach>
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
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
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
					url : "${path }/zzb/app/console/Sha01/ajax/execute",
					type : "post",
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend:function(XHR){
						myLoading.show();
					},
					success : function(json){
						if(json.code == 1){
							showTip("提示","操作成功",500);
							//loadCiList(ciObjectId);
							window.location.href="${path }/zzb/app/console/Sha01/list?shpcId=${shpcId}";
						}else if(json.code == -1){
							showTip("提示", json.message, 500);
						}else if(json.code == -2){
							showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
							//$('#downloanErrorTd').show();
							//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
							//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
						}else{
							showTip("提示","出错了,请检查网络!",500);
						}
					},
					error : function(arg1, arg2, arg3){
						showTip("提示","出错了,请检查网络!",500);
					},
					complete : function(XHR, TS){
						myLoading.hide();
					}
				});
			}

			//批量上传
			$("#btn-MoreTemplate").bind("change",function(evt){
				if($(this).val()){
					ajaxMoreSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}",{zindex:20000});
			function ajaxMoreSubmit(){
				var fileInput = document.getElementById("btn-MoreTemplate");
				if(fileInput.files.length>0){
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if(arr.length<2 || !(arr[arr.length-1]=="zip" || arr[arr.length-1]=="ZIP")){
						showTip("提示","请上传zip包文件",2000);
						return;
					}
				}else{
					showTip("提示","请选择文件上传",2000);
					return;
				}
				//hideErrorMsg();
				$("#importForm").ajaxSubmit({
					url : "${path }/zzb/app/console/Sha01/ajax/moreExecute",
					type : "post",
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend:function(XHR){
						myLoading.show();
					},
					success : function(json){
						if(json.code == 1){
							showTip("提示","操作成功",500);
							//loadCiList(ciObjectId);
							window.location.href="${path }/zzb/app/console/Sha01/list?shpcId=${shpcId}";
						}else if(json.code == -1){
							showTip("提示", json.message, 500);
						}else if(json.code == -2){
							showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
							//$('#downloanErrorTd').show();
							//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
							//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
						}else{
							showTip("提示","出错了,请检查网络!",500);
						}
					},
					error : function(arg1, arg2, arg3){
						showTip("提示","出错了,请检查网络!",500);
					},
					complete : function(XHR, TS){
						myLoading.hide();
					}
				});
			}
		})();

		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/Sha01/list?shpcId=${shpcId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}

		function searchSubmit(){
			document.searchForm.submit();
		}

		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/bwh/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", data.message, 2000);
				}
			});
		};


	</script>
</body>
</html>
