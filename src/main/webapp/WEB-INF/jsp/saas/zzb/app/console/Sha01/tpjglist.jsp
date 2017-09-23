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
<title>"${shpcmc}" 投票结果</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<form class="portlet box grey"id="importForm" enctype="multipart/form-data">
					<input type="hidden" id="shpcId" name="shpcId" value="${shpcId}"/>
					<div class="portlet-title">
						<div class="caption">"${shpcmc}" 投票结果</div>
						<div class="clearfix fr">
                                <a class="btn" href="${path }/zzb/app/sh/tp/list"><i class="icon-undo"></i>返回</a>
                            </div>

                        </div>

                            <table class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                    <tr>
                                        <th width="20%" style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px" rowspan=2>姓名</th>
										<th colspan="3" style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">投票结果</th>
										<th width="20%" style="text-align: center;" rowspan=2>得票率</th>
                                    </tr>
									<tr>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">同意(票数）</th>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">不同意(票数）</th>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">弃权(票数）</th>
									</tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pager.datas}" var="vo">
                                        <tr style="text-overflow:ellipsis;">
											<td width="20%"><c:out value="${vo.xm}"></c:out></td>
											<td width="20%"><c:out value="${vo.tyCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.btyCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.qqCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.dplCount}%"></c:out></td>
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
		(function(){
			App.init();

			$("#btn-browseTemplate").bind("change",function(evt){
				if($(this).val()){
					ajaxSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}",{zindex:20000});
			function ajaxSubmit(){
				var fileInput = document.getElementById("btn-browseTemplate");
				if(fileInput.files.length>0){
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if(arr.length<2 || !(arr[arr.length-1]=="doc" || arr[arr.length-1]=="docx"|| arr[arr.length-1]=="DOC"|| arr[arr.length-1]=="DOCX")){
						showTip("提示","请上传word文件",2000);
						return;
					}
				}else{
					showTip("提示","请选择文件上传",2000);
					return;
				}
				//hideErrorMsg();
				$("#importForm").ajaxSubmit({
					url : "${path }/zzb/app/console/Sha01/ajax/execute",
					type : "post",
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend:function(XHR){
						myLoading.show();
					},
					success : function(json){
						if(json.code == 1){
							showTip("提示","操作成功",500);
							//loadCiList(ciObjectId);
							window.location.href="${path }/zzb/app/console/Sha01/list?shpcId=${shpcId}";
						}else if(json.code == -1){
							showTip("提示", json.message, 500);
						}else if(json.code == -2){
							showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
							//$('#downloanErrorTd').show();
							//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
							//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
						}else{
							showTip("提示","出错了,请检查网络!",500);
						}
					},
					error : function(arg1, arg2, arg3){
						showTip("提示","出错了,请检查网络!",500);
					},
					complete : function(XHR, TS){
						myLoading.hide();
					}
				});
			}

			//干部详细信息附件
			$("#btn-gbrmspb").bind("change", function (evt) {
				if ($(this).val()) {
					gbrmspbSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}", {zindex: 20000});

			function gbrmspbSubmit() {
				var fileInput = document.getElementById("btn-gbrmspb");
				if (fileInput.files.length > 0) {
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
						showTip("提示", "请上传word文件", 2000);
						return;
					}
				} else {
					showTip("提示", "请选择文件上传", 2000);
					return;
				}
				$("#importForm").ajaxSubmit({
					url: "${path }/zzb/app/Sha01/gbrmspb/ajax/batch/upload?sha01Id=${shpa01Vo.id}",
					type: "post",
					headers: {
						OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend: function (XHR) {
						myLoading.show();
					},
					success: function (json) {
						if (json.code == 1) {
							showTip("提示","上传成功",2000);

						} else if (json.code == -1) {
							showTip("提示", json.message, 2000);
						} else {
							showTip("提示", "出错了,请检查网络!", 2000);
						}
					},
					error: function (arg1, arg2, arg3) {
						showTip("提示", "出错了,请检查网络!", 2000);
					},
					complete: function (XHR, TS) {
						myLoading.hide();
					}
				});
			}

			//考察材料附件
			$("#btn-kccl").bind("change", function (evt) {
				if ($(this).val()) {
					kcclSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}", {zindex: 20000});

			function kcclSubmit() {
				var fileInput = document.getElementById("btn-kccl");
				if (fileInput.files.length > 0) {
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
						showTip("提示", "请上传word文件", 2000);
						return;
					}
				} else {
					showTip("提示", "请选择文件上传", 2000);
					return;
				}
				$("#importForm").ajaxSubmit({
					url: "${path }/zzb/app/Sha01/kccl/ajax/batch/upload?sha01Id=${shpa01Vo.id}",
					type: "post",
					headers: {
						OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend: function (XHR) {
						myLoading.show();
					},
					success: function (json) {
						if (json.code == 1) {
							showTip("提示","上传成功",2000);
						} else if (json.code == -1) {
							showTip("提示", json.message, 2000);
						} else {
							showTip("提示", "出错了,请检查网络!", 2000);
						}
					},
					error: function (arg1, arg2, arg3) {
						showTip("提示", "出错了,请检查网络!", 2000);
					},
					complete: function (XHR, TS) {
						myLoading.hide();
					}
				});
			}

			//档案审查情况附件
			$("#btn-dascqk").bind("change", function (evt) {
				if ($(this).val()) {
					dascqkSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}", {zindex: 20000});

			function dascqkSubmit() {
				var fileInput = document.getElementById("btn-dascqk");
				if (fileInput.files.length > 0) {
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
						showTip("提示", "请上传word文件", 2000);
						return;
					}
				} else {
					showTip("提示", "请选择文件上传", 2000);
					return;
				}
				$("#importForm").ajaxSubmit({
					url: "${path }/zzb/app/Sha01/dascqk/ajax/batch/upload?sha01Id=${shpa01Vo.id}",
					type: "post",
					headers: {
						OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend: function (XHR) {
						myLoading.show();
					},
					success: function (json) {
						if (json.code == 1) {
							showTip("提示","上传成功",2000);
						} else if (json.code == -1) {
							showTip("提示", json.message, 2000);
						} else {
							showTip("提示", "出错了,请检查网络!", 2000);
						}
					},
					error: function (arg1, arg2, arg3) {
						showTip("提示", "出错了,请检查网络!", 2000);
					},
					complete: function (XHR, TS) {
						myLoading.hide();
					}
				});
			}

			//个人重大事项附件
			$("#btn-grzdsx").bind("change", function (evt) {
				if ($(this).val()) {
					grzdsxSubmit();
				}
				$(this).val('');
			});
			var myLoading = new MyLoading("${path}", {zindex: 20000});

			function grzdsxSubmit() {
				var fileInput = document.getElementById("btn-grzdsx");
				if (fileInput.files.length > 0) {
					var name = fileInput.files[0].name
					var arr = name.split(".");
					if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
						showTip("提示", "请上传word文件", 2000);
						return;
					}
				} else {
					showTip("提示", "请选择文件上传", 2000);
					return;
				}
				$("#importForm").ajaxSubmit({
					url: "${path }/zzb/app/Sha01/grzdsx/ajax/batch/upload?sha01Id=${shpa01Vo.id}",
					type: "post",
					headers: {
						OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
					},
					beforeSend: function (XHR) {
						myLoading.show();
					},
					success: function (json) {
						if (json.code == 1) {
							showTip("提示","上传成功",2000);
						} else if (json.code == -1) {
							showTip("提示", json.message, 2000);
						} else {
							showTip("提示", "出错了,请检查网络!", 2000);
						}
					},
					error: function (arg1, arg2, arg3) {
						showTip("提示", "出错了,请检查网络!", 2000);
					},
					complete: function (XHR, TS) {
						myLoading.hide();
					}
				});
			}
		})();

		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/Sha01/list?shpcId=${shpcId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}

		function searchSubmit(){
			document.searchForm.submit();
		}

		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/bwh/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", data.message, 2000);
				}
			});
		};

		function uploadFile(fileName){
			document.getElementById("btn-"+fileName).click();
		}

	</script>
</body>
</html>
