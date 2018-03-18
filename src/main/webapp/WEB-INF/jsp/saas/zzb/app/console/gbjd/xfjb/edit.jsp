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
<title>修改信访举报信息</title>
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

									<span class="hidden-480">修改信访举报信息</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>
									<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
									<a class="btn" href="${path }/zzb/app/console/gbjd/xfjb"><i class="icon-remove-sign"></i> 取消</a>
								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
									<dl class="dlattrbute">
										<dt><a href="###">被举报对象</a></dt>
										<dd>
											<table  border="0" style="width:100%;" cellPadding="5px">
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">姓名</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="张三"/>
															</div>
														</div>
													</td>
													<td>
														<div id="xbGroup" class="control-group">
															<label class="control-label">单位</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="民政局"/>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">职务</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="副主任"/>
															</div>
														</div>
													</td>
													<td>
														<div id="xbGroup" class="control-group">
															<label class="control-label">举报件来源</label>
															<div class="controls">
																<select class="span8 m-wrap" tabindex="-1" name="xb" id="xb" >
																	<option value=""></option>
																	<option value="男" selected>邮件</option>
																	<option value="女">材料</option>
																	<option value="女">其他</option>
																</select>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">反映问题性质</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="${vo.xm}"/>
															</div>
														</div>
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</table>
										</dd>
									</dl>
									<dl class="dlattrbute">
										<dt><a href="###">举报人信息</a></dt>
										<dd>
											<table  border="0" style="width:100%;" cellPadding="5px">
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">姓名</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="王某"/>
															</div>
														</div>
													</td>
													<td>
														<div id="xbGroup" class="control-group">
															<label class="control-label">单位</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value=""/>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">职务</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value=""/>
															</div>
														</div>
													</td>
													<td>
														<div id="xbGroup" class="control-group">
															<label class="control-label">联系方式</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="1380000000"/>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">反映日期</label>
															<div class="controls">
																<input size="16" class="span8 m-wrap" type="text" readonly=" " required style="width: 120px;" value="20170301"
																	   id="pcsjValue" name="pcsjValue" >
															</div>
														</div>
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div id="xmGroup" class="control-group">
															<label class="control-label">反映问题摘要</label>
															<div class="controls">
																<textarea class="span10 m-wrap">经济腐败</textarea>
															</div>
														</div>
													</td>

												</tr>
											</table>
										</dd>
									</dl>
									<dl class="dlattrbute">
										<dt><a href="###">处理结果</a></dt>
										<dd>
											<table  border="0" style="width:100%;" cellPadding="5px">
												<tr>
													<td>
														<div id="xmGroup" class="control-group">
															<label class="control-label">处理状态</label>
															<div class="controls">
																<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
																	<option value="正常" <c:if test="${shpc.mb eq '广州模板'}">selected</c:if>>已处理</option>
																	<option value="异常" <c:if test="${shpc.mb eq '湖南模板'}">selected</c:if>>待处理</option>
																</select>
															</div>
														</div>
													</td>
													<td>
														<div id="xbGroup" class="control-group">
															<label class="control-label">处理结果</label>
															<div class="controls">
																<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
																	<option value="正常" <c:if test="${shpc.mb eq '广州模板'}">selected</c:if>>正常</option>
																	<option value="异常" <c:if test="${shpc.mb eq '湖南模板'}">selected</c:if>>异常</option>
																</select>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div id="xmGroup" class="control-group">
															<label class="control-label">处理意见</label>
															<div class="controls">
																<textarea class="span10 m-wrap"></textarea>
															</div>
														</div>
													</td>

												</tr>
												<tr>
													<td colspan="2">
														<div id="xmGroup" class="control-group">
															<label class="control-label">处理结果件</label>
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
													</td>

												</tr>
											</table>
										</dd>
									</dl>
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
