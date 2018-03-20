<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id="jsonDataFormGroup" class="control-group">
	<div class="portlet-body">
		<form class="form-horizontal" id="jsonDataForm">

			<div class="dataTables_wrapper form-inline">
				<table class="table table-striped table-bordered table-hover dataTable table-set" id="jsonDataFormTable">
					<tr>
						<th>个人事项查核情况</th>
					</tr>
					<tr>
						<td><a href="#">2015.4.16查核存在不一致的情况</a></td>
					</tr>
					<tr>
						<th>经济责任审计</th>
					</tr>
					<tr>
						<td><a href="#">2016.3.19经济责任审计存在不合规的情况 </a></td>
					</tr>
					<tr>
						<th>三书预警</th>
					</tr>
					<tr>
						<td><a href="#">2017.9.18被函询</a></td>
					</tr>
					<tr>
						<th>责任追究</th>
					</tr>
					<tr>
						<td><a href="#">2017.09.10被行政处罚</a></td>
					</tr>
					<tr>
						<th>问责</th>
					</tr>
					<tr>
						<td><a href="#">2017.01.09被问责(诫勉)</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

</div>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script>


</script>