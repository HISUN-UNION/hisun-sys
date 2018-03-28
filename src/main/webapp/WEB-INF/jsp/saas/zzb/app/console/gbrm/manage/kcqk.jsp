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
	<title>考察情况</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<div class="portlet-title">
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
			<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${shpc.id }" id="id">
				<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">

						<table  border="0" style="width:100%;" cellPadding="5px">
							<tr>
								<td colspan="2">
									<div id="xmGroup" class="control-group">
										<label class="control-label">考察结果 </label>
										<div class="controls">
											<textarea class="span11 m-wrap" rows="5"> </textarea>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="xmGroup" class="control-group">
										<label class="control-label">主要表现及任免理由<br>（呈批表）
										</label>
										<div class="controls">
											<textarea class="span11 m-wrap" rows="5"></textarea>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="xmGroup" class="control-group">
										<label class="control-label">任免理由<br>（审批表） </label>
										<div class="controls">
											<textarea class="span11 m-wrap" rows="5"></textarea>
										</div>
									</div>
								</td>
							</tr>

							<tr>
								<td colspan="2">
									<div class="portlet-title">
										<div class="caption">考察材料 </div>
										<div class="clearfix fr">
											<a id="sample_editable_1_new" class="btn green" href="#)">
												上传考察材料
											</a>

										</div>

									</div>
									<div class="portlet-body">
										<table class="table table-striped table-bordered table-hover dataTable table-set">
											<thead>
											<tr>
												<th>材料名称</th>
												<th width="90">上传者</th>
												<th width="100">上传时间</th>
												<th width="120">操作</th>
											</tr>
											</thead>
											<tbody>
											<tr style="text-overflow:ellipsis;">
												<td ><a href=#" class="">刘洋考察材料</a></td>
												<td >张某</td>
												<td >2017.1.13</td>
												<td>
													<a href="#" class="">预览</a>|
													<a href="#" class="">下载</a>|
													<a href="#" class="">删除</a>
												</td>
											</tr>
											</tbody>
										</table>

									</div>
								</td>
							</tr>

						</table>


			</form>
		</div>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
	function loadNextPage(){
		zqyjLoad();
		$("#barDiv").width("100%");
		$("[id='#tab_2_4']").tab('show');
	}
</script>
</body>
</html>
