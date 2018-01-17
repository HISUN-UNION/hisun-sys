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
<title>${queryName} 干部列表</title>
</head>
<body>

	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">${queryName} 干部列表</div>
						<div class="clearfix fr">

							<div class="btn-group">
								<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
									选择条件 <i class="icon-angle-down"></i>
								</a>
								<ul class="dropdown-menu">
									<c:forEach items="${appAsetA01Querys}" var="vo">
										<li >
											<a onclick="queryByCondition('${vo.id}')">${vo.queryName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>

							<a class="btn green" href="#">
								另存为“干部名册”
							</a>
							<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/asetA01Query/queryList">
								条件管理
							</a>
						</div>
					</div>
			
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th width="60">姓名</th>
									<th width="40">性别</th>
									<th width="60">出生<br>年月</th>
									<th>现任职务</th>
									<th width="100" style="text-align: center">全日制<br>学历学位
									</th>
									<th width="150" style="text-align: center">在职<br>学历学位
									<th width="80"style="text-align: center">任现职级<br>时间
									</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<td title="${vo.xm}"><a href="${path}/zzb/app/console/asetA01Query/view?id=${vo.id }&queryId=${queryId}"><c:out value="${vo.xm}"></c:out></a></td>
										<%--<td title="${vo.xm}">--%>
										<%--<c:out value="${vo.xm}"></c:out>--%>
										<%--</td>--%>
									<td title="${vo.xb}" ><c:out value="${vo.xb}"></c:out></td>
									<td title="${vo.csnyStr}" style="text-align: center"><c:out value="${vo.csnyStr}"></c:out><br>(<c:out value="${vo.nl}"></c:out>岁)</td>
									<td title="${vo.xrzw}"><c:out value="${vo.xrzw}"></c:out></td>
									<td ><c:out value="${vo.gbrmspbQrzxlxw}"></c:out></td>
									<td ><c:out value="${vo.gbrmspbZzxlxw}"></c:out></td>
									<td ><c:out value="${vo.xrzjsjStr}"></c:out></td>
										<%--<td><c:out value="${vo.zyjszw}"></c:out></td>--%>
										<%--<td><c:out value="${vo.xrzwsj}"></c:out></td>--%>
										<%--<td title="${vo.xrzjsj}"><c:out value="${vo.xrzjsj}"></c:out></td>--%>
										<%--<td class="Left_alignment">--%>
										<%--<a href="javascript:del('${vo.id }','${vo.xm}')" class="">删除</a>--%>
										<%--</td>--%>
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
	<script type="text/javascript">
		(function(){
			App.init();
		})();
	
		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/asetA01Query/?queryId=${queryId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}
		
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01Query/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01Query/?queryId=${queryId}"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};
		function queryByCondition(queryId){
			window.location.href = "${path}/zzb/app/console/asetA01Query/?queryId="+queryId;
		}
	</script>
</body>
</html>
