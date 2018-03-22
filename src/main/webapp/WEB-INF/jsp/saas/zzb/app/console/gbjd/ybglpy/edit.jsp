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
									<button type="button" class="btn green" onclick=""> 选择机构</button>
									<button type="button" class="btn green" onclick=""><i class="icon-ok"></i> 确定</button>
									<a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
									<table class="table table-striped table-bordered table-hover dataTable table-set">
										<thead>
										<tr>
											<th width="150">评议机构名称</th>
											<th style="text-align: left">评议分数</th>
										</tr>
										</thead>
										<tbody>
										<tr style="text-overflow:ellipsis;">
											<td  >州纪委</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="95"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委办</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="88"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委政策研究室</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="87"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委组织部</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="85"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委老干部局</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="84"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委宣传部</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="82"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委政法委</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="80"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州委统战部</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="68"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州直属机关工委</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="68"/>分</td>
										</tr>
										<tr style="text-overflow:ellipsis;">
											<td  >州机构编制委员会办公室</td>
											<td  style="text-align: left"><input type="text" name="jsonDataVos" placeholder="必填项"	 required style="width: 50px" value="59"/>分</td>
										</tr>

										</tbody>
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
