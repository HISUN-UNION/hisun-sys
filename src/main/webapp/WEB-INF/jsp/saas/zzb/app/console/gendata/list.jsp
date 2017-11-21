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
	<title>数据包管理</title>
</head>
<body>

<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">数据包管理</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/gendata/loadGenerator">
							<i class="icon-plus"></i> 生成数据包
						</a>
					</div>
				</div>
				<form action="" class="form-horizontal" id="form1" method="post">
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
							<tr>
								<th>数据包生成时间</th>
								<th width="10%">数据包状态</th>
								<th width="190">操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<td><c:out value="${vo.createTimeValue}"></c:out></td>
									<td><c:out value="${vo.isCurrentPacketValue}"></c:out></td>
									<td class="Left_alignment">
										<c:if test="${vo.isCurrentPacket eq '0'}">
											<a href="javascript:changeStatue('${vo.id}')">设为当前数据包</a>|
										</c:if>
										<a href="javascript:downZip('${vo.id }')" class="">下载</a>|
										<a href="javascript:del('${vo.id }','${vo.createTimeValue}')" class="">删除</a>

									</td>
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
				</form>
			</div>
			<%-- 表格结束 --%>
		</div>
	</div>

	<%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<%--<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp" %>--%>
<script type="text/javascript">
	(function(){
		App.init();
	})();
	var myLoading = new MyLoading("${path}",{zindex:20000});


	function pagehref (pageNum ,pageSize){
		window.location.href ="${path}/zzb/app/console/gendata/?pageNum="+pageNum+"&pageSize="+pageSize;
	}

	function searchSubmit(){
		document.searchForm.submit();
	}

	var del = function(id,itemName){
		actionByConfirm1(itemName+"的数据包", "${path}/zzb/app/console/gendata/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/gendata/"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function changeStatue(id){
		$.cloudAjax({
			path : '${path}',
			url : "${path }/zzb/app/console/gendata/changePacketZt/"+id,
			type : "post",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data.success){
//					document.getElementById(id+"_shZt").innerHTML  = data.shZtValue;
					showTip("提示","成功设置为当前数据包",2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/gendata/"},2000);
				}else{
					showTip("提示", json.message, 2000);
				}
			},
			error : function(){
				showTip("提示","出错了请联系管理员",2000);
			}
		});
	}
	function downZip(id) {
		window.open("${path }/zzb/app/console/gendata/zip/down?id="+id);
	}
</script>
</body>
</html>
