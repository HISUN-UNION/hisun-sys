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
	<title>任免情况</title>
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
				<div class="portlet-title">
					<div class="caption">现任职务拟免</div>
					<div class="clearfix fr">
						<input type="checkbox" checked>已完成该步骤
						<a class="btn green" href="javascript:void();">
							保存
						</a>
						<a class="btn green" href="javascript:loadNextPage();">
							完成并进入下一步
						</a>
					</div>

				</div>

				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="180">单位</th>
							<th width="100">职务</th>
							<th width="60">拟免</th>
							<th>免职原因</th>
						</tr>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td>州纪委</td>
								<td >副书记</td>
								<td ><input type="checkbox" class="" checked>拟免</td>
								<td style="padding-top:0px;padding-bottom:0px"><input type="text"  style="width: 90%;padding-top: 6px;padding-bottom: 0px"></td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>州人民政府</td>
								<td >党组成员</td>
								<td ><input type="checkbox" class="">拟免</td>
								<td style="padding-top:0px;padding-bottom:0px"></td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>州监察局</td>
								<td >局长</td>
								<td ><input type="checkbox" class="">拟免</td>
								<td style="padding-top:0px;padding-bottom:0px"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<div class="portlet-title">
					<div class="caption">拟任职务</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:void()">
							添加拟任职务
						</a>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th>拟任单位</th>
							<th width="100">拟任职务</th>
							<th width="60">任职性质</th>
							<th width="90">试用期</th>
							<th width="90" style="text-align: center">辅助决策</th>
							<th width="90">操作</th>
						</tr>
						</thead>
						<tbody>
						<tr style="text-overflow:ellipsis;">
							<td>州纪委</td>
							<td >书记</td>
							<td >提拔</td>
							<td >试用期一年</td>
							<td style="text-align: center"><i class="icon-question-sign"></i></td>
							<td>
								<a href="#" class="">修改</a>|
								<a href="#" class="">删除</a>
							</td>
						</tr>
						</tbody>
					</table>

				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
	function loadNextPage(){
		kcqkLoad();
		$("#barDiv").width("75%");
		$("[id='#tab_2_3']").tab('show');
	}

</script>
</body>
</html>
