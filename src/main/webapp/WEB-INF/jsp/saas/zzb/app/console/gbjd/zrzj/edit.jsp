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
<title>修改责任追责信息</title>
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

									<span class="hidden-480">修改责任追责信息</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
									<div id="pcmcGroup" class="control-group">
										<label class="control-label">姓名<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="pcmc" required maxlength="200" id="pcmc" value="张三"/>
										</div>

									</div>

									<div class="control-group" id="shlxGroup">

										<label class="control-label">单位<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="pcmc" required maxlength="200" id="pcmc" value="民政局"/>

										</div>

									</div>
									<div class="control-group" id="sjlxGroup">
										<label class="control-label">职务<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="pcmc" required maxlength="200" id="pcmc" value="副主任"/>
										</div>
									</div>
									<div class="control-group" id="mbGroup">

										<label class="control-label">职级<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
												<option value=""></option>
												<option value="厅局级正职" <c:if test="${vo.xrzj == '厅局级正职'}">selected="selected"</c:if>>厅局级正职</option>
												<option value="厅局级副职" <c:if test="${vo.xrzj == '厅局级副职'}">selected="selected"</c:if>>厅局级副职</option>
												<option value="县处级正职" >县处级正职</option>
												<option value="县处级副职" selected>县处级副职</option>
												<option value="乡科级正职" <c:if test="${vo.xrzj == '乡科级正职'}">selected="selected"</c:if>>乡科级正职</option>

												<option value="乡科级副职" <c:if test="${vo.xrzj == '乡科级副职'}">selected="selected"</c:if>>乡科级副职</option>
												<option value="科员" <c:if test="${vo.xrzj == '科员'}">selected="selected"</c:if>>科员</option>
												<option value="办事员" <c:if test="${vo.xrzj == '办事员'}">selected="selected"</c:if>>办事员</option>
												<option value="试用期人员" <c:if test="${vo.xrzj == '试用期人员'}">selected="selected"</c:if>>试用期人员</option>
												<option value="其他" <c:if test="${vo.xrzj == '其他'}">selected="selected"</c:if>>其他</option>
											</select>
										</div>
									</div>
									<div class="control-group" id="mbGroup">

										<label class="control-label">责任追究方式<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
												<option value=""></option>
												<option value="组织处理" <c:if test="${vo.xrzj == '组织处理'}">selected="selected"</c:if>>组织处理</option>
												<option value="纪律处分"selected>纪律处分</option>
												<option value="行政处罚" >行政处罚</option>
												<option value="刑事追究">刑事追究</option>
											</select>
										</div>
									</div>

									<div id="pcsjValueGroup" class="control-group">
										<label class="control-label">责任追究时间<span class="required">*</span></label>
										<div class="controls">
											<input size="16" type="text" readonly=" " required style="width: 120px;" value="20170301"
												   id="pcsjValue" name="pcsjValue" >

											<!--<input type="text" class="span6 m-wrap"  name="pcsj" formatter="yyyymmdd"  required maxlength="8" id="pcsj" type="date"/>-->
										</div>

									</div>
									<div id="pxGroup" class="control-group">
										<label class="control-label">处理时间<span class="required">*</span></label>
										<div class="controls">
											<input size="16" type="text" readonly=" " required style="width: 120px;" value="20170804"
												   id="sjwcsj" name="sjwcsj" >
										</div>

									</div>
									<div class="control-group" id="mbGroup">

										<label class="control-label">处理状态<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
												<option value="已处理" <c:if test="${shpc.mb eq '广州模板'}">selected</c:if>>已处理</option>
												<option value="待处理" <c:if test="${shpc.mb eq '湖南模板'}">selected</c:if>>待处理</option>
											</select>
										</div>
									</div>
									<div  id="clFileGroup" class="control-group" >
										<label class="control-label">责任处分书</label>
										<div class="controls">
											<input type="file" class="default" name="clFile" id="clFile" fileSizeLimit="20" fileType="doc,docx,DOC,DOCX"/>
											<div class="btn-group" id="gbrmspbDownDiv" <c:if test="${empty shpc.filePath}">
												 style="visibility:hidden"</c:if>>
												<a class="btn blue" herf="javascript:void(0)" onclick="fileDown()"><i
														class="icon-circle-arrow-down"></i>下载文件</a>
											</div>
											<p class="textprompt">附件支持的格式有：'doc','docx'</p>
											<p class="Errorred" id="attachFileError"></p>
										</div>

									</div>
									<div class="control-group">
										<div class="controls mt10">
											<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>

											<a class="btn" href="${path }/zzb/app/console/gbjd/zrzj"><i class="icon-remove-sign"></i> 取消</a>
										</div>
									</div>
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
			<script type="text/javascript" src="${path }/js/common/loading.js"></script>
			<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">
	var myLoading = new MyLoading("${path}",20000);
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

	var myVld = new EstValidate("form1");
	<%--function formUpdate(){--%>
		<%--var bool = myVld.form();--%>
		<%--if(bool){--%>
			<%--$.cloudAjax({--%>
				<%--path : '${path}',--%>
				<%--url : "${path }/zzb/app/console/bwh/save",--%>
				<%--type : "post",--%>
				<%--data : $("#form1").serialize(),--%>
				<%--dataType : "json",--%>
				<%--success : function(data){--%>
					<%--if(data.success){--%>
						<%--showTip("提示","操作成功",2000);--%>
						<%--setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);--%>
					<%--}else{--%>
						<%--showTip("提示", json.message, 2000);--%>
					<%--}--%>
				<%--},--%>
				<%--error : function(){--%>
					<%--showTip("提示","出错了请联系管理员",2000);--%>
				<%--}--%>
			<%--});--%>
		<%--}--%>
	<%--}--%>
	function formSubmit(){
		var bool = myVld.form();
		if(!bool){
			return;
		}
		var fileInput = document.getElementById("clFile");
		if (fileInput.files.length > 0) {
			var name = fileInput.files[0].name
			var arr = name.split(".");
			if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
				showTip("提示", "请上传word文件", 2000);
				return;
			}
		}
		myLoading.show();
		$("#form1").ajaxSubmit({
			url : "${path }/zzb/app/console/bwh/save",
			type : "post",
			dataType : "json",
			enctype : "multipart/form-data",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(data){
				myLoading.hide();
				if(data.success){
					showTip("提示","操作成功",2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/?pageNum=${shpcPageNum}"},2000);
				}else{
					showTip("提示", json.message, 2000);
				}
			},
			error : function(arg1, arg2, arg3){
				myLoading.hide();
				showTip("提示","出错了请联系管理员");
			}
		});
	}

	function fileDown() {
		window.open("${path }/zzb/app/console/bwh/ajax/down?id=${shpc.id}");
	}
	function changeFile(obj){
		if(obj.value =="1"){
			window.document.getElementById("clFileGroup").style.visibility = "hidden";
			window.document.getElementById("mbGroup").style.display = "block";
		}else{
			window.document.getElementById("mbGroup").style.display = "none";
			window.document.getElementById("clFileGroup").style.visibility = "visible";
		}
	}
</script>
</body>
</html>
