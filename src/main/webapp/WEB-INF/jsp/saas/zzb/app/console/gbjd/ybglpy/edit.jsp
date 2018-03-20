<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${path }/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<title>修改2017年评议机构</title>
</head>
<body>
			<div class="container-fluid">

				<div class="row-fluid">
					<div class="span12">
						<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>   

						<div class="portlet box grey">

							<div class="portlet-title">

								<div class="caption">

									<i class="icon-reorder"></i>

									<span class="hidden-480">修改2017年评议机构</span>

								</div>
								<div class="tools">
									<button type="button" class="btn green" onclick=""><i class="icon-ok"></i> 确定</button>
									<a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
									<table  border="0" style="width:100%;" cellPadding="5px">
										<tr>
											<td width="50%" colspan="2">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">选择评议单位<span class="required">*</span></label>
													<div class="controls">
														<div class="btn-group span12" style="font-size: 12px">
															<input type="hidden" name="b01Id" id="b01Id" value="${vo.b01Id}"/>
															<input type="text" id="b0101" name="b0101" readonly="readonly"
																   class="span8 m-wrap" style="cursor: pointer;" onclick="$('#objectTreeSelDiv').toggle();" value="州纪委,州委办,州委政策研究室,州委组织部,州委老干部局,州委宣传部,州委政法委">
															<div class="span8 m-wrap" style="border:solid 1px #ccc;overflow-y: scroll;overflow-x: auto;position: absolute;
											top: 100%;left: 0;z-index: 1000;display: none;float: left;list-style: none;text-shadow: none;padding: 0px;margin: 0px;height: 200px;background-color: white;" id="objectTreeSelDiv">
																<ul id="treeDemo" class="ztree" style="margin: 0px;padding: 0px;height: 200px;"></ul>
															</div>
														</div>
													</div>
												</div>
											</td>


										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州纪委" />
													</div>
												</div>
											</td>
											<td width="70%" >
													<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
													<div class="controls" style="margin-left:20px">
														<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="90" />分
													</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委办" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="88" />分
												</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委政策研究室" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="88" />分
												</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委组织部" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="86" />分
												</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委老干部局" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="85" />分
												</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委宣传部" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="82" />分
												</div>
											</td>
										</tr>
										<tr>
											<td width="30%">
												<div id="ciNameGroup" class="control-group">
													<label class="control-label">单位名称</label>
													<div class="controls">
														<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" readonly id="ciName" value="州委政法委" />
													</div>
												</div>
											</td>
											<td width="70%" >
												<label class="control-label"style="width: 60px">评分<span class="required">*</span></label>
												<div class="controls" style="margin-left:20px">
													<input type="text" style="width: 50px" name="ciName" required maxlength="128" id="ciName" value="80" />分
												</div>
											</td>
										</tr>
										</table>
																</form>
							</div>

						</div>

						<%-- END SAMPLE FORM PORTLET--%>
					</div>
				</div>
				
				<%-- END PAGE CONTENT--%>  
			</div>

			<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
			<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
			<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
			<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
			<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
			<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
			<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>
			<script type="text/javascript" src="${path }/js/zTree/jquery.ztree.all-3.5.js"></script>
			<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">
	jQuery(document).ready(function() {
		App.init();
		var startDate = $("#pcsjValue").datepicker({
			language:  'zh-CN',
			format: "yyyymmdd",
			pickerPosition: "bottom-left",
			weekStart : 1,
			autoclose : true
		});
		var sjwcsj = $("#pcsjValue").datepicker({
			language:  'zh-CN',
			format: "yyyymmdd",
			pickerPosition: "bottom-left",
			weekStart : 1,
			autoclose : true
		});

	});


	var setting = {
		view : {
			selectedMulti : false
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : zTreeOnClick
		},
		check: {
			enable: true,
			nocheckInherit: true

		}
	};
	var resTree;
	$(function(){
		localPost("<%=path%>/zzb/app/console/bset/ajax/load/tree",{"status":1}, function(data,status){
			if (status == "success") {
				//setting.data.key.url = "_url" ;
				resTree = $.fn.zTree.init($("#treeDemo"), setting, data.customTree);

				localPost("<%=path%>/zzb/app/console/asetA01Query/selectB01?queryId=${vo.id}",null, function(data,status){
					if (status == "success") {
						var resIdList = data.data;
						if (resTree != null && resIdList != null) {

							$.each(resIdList, function(index, resId) {
								var node = resTree.getNodeByParam("id", resId, null);
								if(node){
									resTree.checkNode(node, true, false);
								}
							});
						}
					}
				},"json",{"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
			}
		},"json",{"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
	});
	function zTreeOnClick() {
		var nodes = resTree.getCheckedNodes(true);
		var b01IdObj = $("[id='b01Id']");
		var b0101Obj = $("[id='b0101']");
		var b01Ids = "";
		var b0101s = "";
		$.each(nodes, function(index, node) {
			if(b01Ids==""){
				b01Ids = node.id;
				b0101s = node.name;
			}else{
				b01Ids = b01Ids+";"+ node.id;
				b0101s = b0101s+","+ node.name;
			}
		});
		b01IdObj.val(b01Ids);
		b0101Obj.val(b0101s);
	}
	function cancel(){
		window.location.href = "${path}/zzb/app/console/gbjd/ybglpy";

	}
</script>
</body>
</html>
