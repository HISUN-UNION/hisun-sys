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
<title>修改领导干部个人有关事项报告查核情况</title>
</head>
<body style="text-align: center">
			<div class="container-fluid">

				<div class="row-fluid">
					<div class="span12">
						<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>   

						<div class="portlet box grey">

							<div class="portlet-title">

								<div class="caption">

									<i class="icon-reorder"></i>

									<span class="hidden-480">修改领导干部个人有关事项报告查核情况</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>
									查核时间：<input type="text" readonly=" " required style="width: 120px;margin: 0px" value="20170904"
											   id="sjwcsj" name="sjwcsj" >&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
									<a class="btn" href="${path }/zzb/app/console/gbjd/grsxch/zdch"><i class="icon-remove-sign"></i> 取消</a>
								</div>
							</div>

								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
											<table  border="1" align="center" style="width:90%;" cellPadding="5px" style=" border:1px solid #0094ff;text-align: center">
												<tr>
													<td colspan="2">
														<div id="xmGroup" class="control-group">
															<label class="control-label">姓名</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="张三"/>
															</div>
														</div>
													</td>
													<td colspan="2">
														<div id="xbGroup" class="control-group">
															<label class="control-label">工作单位及职务</label>
															<div class="controls">
																<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="市民政局副主任"/>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td width="10%">
														<div class="control-label">
															核实事项
														</div>
													</td>
													<td width="30%">
														<div class="controls">
														本人填报情况
														</div>
													</td>
													<td width="30%">
														<div class="controls">
														信息查询情况
														</div>
													</td>
													<td width="30%">
														<div class="controls">
														对比核实情况
														</div>
													</td>
												</tr>
												<tr>
													<td width="10%">
														<div class="control-label" style="text-align: left">
															本人因私出国境及其他证件，配偶、子女移居国境外及连续在国境外工作生活的情况
														</div>
													</td>
													<td width="30%">
															<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
												</tr>
												<tr>
													<td width="10%">
														<div class="control-label" style="text-align: left">
															本人、配偶及共同生活的子女的房产情况
														</div>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
												</tr>
												<tr>
													<td width="10%">
														<div class="control-label" style="text-align: left">
															本人、配偶及共同生活的子女持有股票、基金、投资型保险的情况
														</div>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
												</tr>
												<tr>
													<td width="10%">
														<div class="control-label" style="text-align: left">
															配偶、子女及其配偶经商办企业的情况
														</div>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
													<td width="30%">
														<textarea style="width: 95%;height: 80px" ></textarea>
													</td>
												</tr>
												<tr>
													<td width="10%" rowspan="2">
														<div class="control-label" style="text-align: left">
															干部监督机构审核意见
														</div>
													</td>
													<td width="90%" colspan="3">
														<textarea style="width: 98%;height: 50px" >经核对，抽查核实结果与领导干部填报个人有关事项材料内容一致</textarea>
													</td>

												</tr>
												<tr>

													<td width="90%" colspan="3">
														处理时间<input size="16" type="text" readonly=" " required style="width: 120px;" value="20170804"
															   id="sjwcsj" name="sjwcsj" >
													</td>

												</tr>
												<tr>
													<td width="10%" rowspan="2">
														<div class="control-label" style="text-align: left">
															干部管理机构处理意见
														</div>
													</td>
													<td width="90%" colspan="3">
														<textarea style="width: 98%;height: 50px" ></textarea>
													</td>

												</tr>
												<tr>

													<td width="90%" colspan="3">
														处理时间<input size="16" type="text" readonly=" " required style="width: 120px;" value="20170904"
																   id="sjwcsj" name="sjwcsj" >
													</td>

												</tr>
											</table>

								</form>

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
