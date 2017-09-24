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
<title>"${tprxm}"  投票情况</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<form class="portlet box grey"id="importForm">
					<div class="portlet-title">
						<div class="caption">"${tprxm}"  投票情况
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            注：各颜色代表的含义  &nbsp;  &nbsp;<h style="color: #00FF40 ">同意</h>
                            <h style="color: #FF0000 ">不同意</h>
                            <h style="color: #C0C0C0 "> 弃权</h>
                        </div>
						<div class="clearfix fr">
                                <a class="btn" href="${path }/zzb/app/sh/tp/tplist?shpcId=${shpcId}"><i class="icon-undo"></i>返回</a>
                            </div>

                        </div>

                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover dataTable table-set">
                                <thead>
                                    <tr>
                                        <th width="6%">&nbsp;<br>姓名<br>&nbsp;</th>
                                        <th width="5%">&nbsp;<br>性别<br>&nbsp;</th>
                                        <th width="5%">&nbsp;<br>民族<br>&nbsp;</th>
                                        <th width="5%">&nbsp;<br>籍贯<br>&nbsp;</th>
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
                                        <tr style="text-overflow:ellipsis;" >
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if>>
                                                <c:out value="${vo.sha01Vo.xm}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if>>
                                                <c:out value="${vo.sha01Vo.xb}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if>>
                                                <c:out value="${vo.sha01Vo.mz}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if>>
                                                <c:out value="${vo.sha01Vo.jg}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.csny}">
                                                <c:out value="${vo.sha01Vo.csny}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.cjgzsj}">
                                                <c:out value="${vo.sha01Vo.cjgzsj}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.rdsj}">
                                                <c:out value="${vo.sha01Vo.rdsj}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.whcd}">
                                                <c:out value="${vo.sha01Vo.whcd}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.rxjbsj}">
                                                <c:out value="${vo.sha01Vo.rxjbsj}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.mztjqk}">
                                                <c:out value="${vo.sha01Vo.mztjqk}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.xgzdwjzw}">
                                                <c:out value="${vo.sha01Vo.xgzdwjzw}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.ntzpbyj}">
                                                <c:out value="${vo.sha01Vo.ntzpbyj}"></c:out>
                                            </td>
                                            <td <c:if test="${vo.tp eq 1}">style="color: #00FF40"</c:if>
                                                <c:if test="${vo.tp eq 2}">style="color: #FF0000"</c:if>
                                                <c:if test="${vo.tp eq 3}">style="color: #C0C0C0"</c:if> title="${vo.sha01Vo.shyj}">
                                                <c:out value="${vo.sha01Vo.shyj}"></c:out>
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
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
	<script src="${path }/js/bootstrap-fileupload.js"></script>
	<script src="${path }/js/bootstrap-fileupload.js"></script>
	<!— 引入确认框模块 —>
	<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp" %>
	<script type="text/javascript">




		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/tpsj/?shtpId=${shtpId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}



	</script>
</body>
</html>
