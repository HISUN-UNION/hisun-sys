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
<title>投票统计</title>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">投票统计</div>
						<div class="clearfix fr">
						</div>
					</div>
			
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th >批次名称</th>
									<th width="10%">上会类型</th>
									<th width="10%">上会时间</th>
									<th width="10%">投票情况</th>
									<th width="10%">投票结果</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td><c:out value="${vo.pcmc}"></c:out></td>
										<td><c:out value="${vo.shlxValue}"></c:out></td>
										<td><c:out value="${vo.pcsjValue}"></c:out></td>
										<td><a href="${path}/zzb/app/console/tp/list?shpcId=${vo.id }" class="">${vo.tpCount }人已投</a></td>
										<td class="Left_alignment">
											<a href="${path}/zzb/app/console/tp/result?shpcId=${vo.id }" class="">查看</a>
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
			window.location.href ="${path}/zzb/app/console/tp/?pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}

		

	</script>
</body>
</html>
