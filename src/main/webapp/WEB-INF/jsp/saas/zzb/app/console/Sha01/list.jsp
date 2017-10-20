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
<title>上会名单</title>
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
				<form class=""id="importForm" enctype="multipart/form-data">

					<div class="portlet-title">
						<div class="caption">上会名单</div>
						<div class="clearfix fr">
							<span class="controllerClass btn green file_but" >
									<i class="icon-circle-arrow-up"></i>上传名单
									<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">
							</span>
								<div class="btn-group">
									<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
										批量上传 <i class="icon-angle-down"></i>
									</a>
									<ul class="dropdown-menu">
										<li >
											<a onclick="uploadFile('gbrmspb')">干部详细信息</a>
											<input type="file" style="display: none" name="gbrmspbFile" id="btn-gbrmspb"/>
										</li>
										<li>
											<a onclick="uploadFile('kccl')">考察材料</a>
											<input type="file"  style="display: none" name="kcclFile" id="btn-kccl"/>
										</li>
										<li>
											<a onclick="uploadFile('dascqk')">档案审查情况</a>
											 <input type="file" style="display: none" name="dascqkFile" id="btn-dascqk"/>
										</li>
										<li>
											<a onclick="uploadFile('grzdsx')">个人重大事项</a>
											<input type="file"  style="display: none" name="grzdsxFile" id="btn-grzdsx"/>
										</li>

									</ul>
								</div>

                                <a class="btn" href="${path }/zzb/app/console/bwh/"><i class="icon-undo"></i>返回</a>
                            </div>

                        </div>
				</form>
					<div class="clearfix">
						<div class="control-group">
							<div id="query" style="float: left;">
								<form action="${path }/zzb/app/console/Sha01/list/" method="POST" id="searchForm" name="searchForm">
									<input type="hidden" id="shpcId" name="shpcId" value="${shpcId}"/>
									<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
									<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
									<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
									姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
									<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
									<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
								</form>
							</div>
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
                                        <th width="20%">&nbsp;<br>现工作单位及职务<br>&nbsp;</th>
                                        <th>&nbsp;<br>拟调整配备意见<br>&nbsp;</th>
                                        <th width="5%">干部<br>一科<br>意见</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pager.datas}" var="vo">
                                        <tr style="text-overflow:ellipsis;">
                                            <td><a href="${path}/zzb/app/console/Sha01/view?id=${vo.id }"><c:out value="${vo.xm}"></c:out></a></td>
                                            <td><c:out value="${vo.xb}"></c:out></td>
                                            <td><c:out value="${vo.mz}"></c:out></td>
                                            <td><c:out value="${vo.jg}"></c:out></td>
                                            <td title="${vo.csny}"><c:out value="${vo.csny}"></c:out></td>
                                            <td title="${vo.cjgzsj}"><c:out value="${vo.cjgzsj}"></c:out></td>
                                            <td title="${vo.rdsj}"><c:out value="${vo.rdsj}"></c:out></td>
                                            <td title="${vo.whcd}"><c:out value="${vo.whcd}"></c:out></td>
                                            <td title="${vo.rxjbsj}"><c:out value="${vo.rxjbsj}"></c:out></td>
                                            <td title="${vo.mztjqk}"><c:out value="${vo.mztjqk}"></c:out></td>
                                            <td title="${vo.xgzdwjzw}"><c:out value="${vo.xgzdwjzw}"></c:out></td>
                                            <td title="${vo.ntzpbyj}"><c:out value="${vo.ntzpbyj}"></c:out></td>
                                            <td title="${vo.shyj}"><c:out value="${vo.shyj}"></c:out></td>
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
						showTip("提示", "请上传zip文件", 2000);
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
			<%--window.location.href ="${path}/zzb/app/console/Sha01/list?shpcId=${shpcId}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
			$("#pageNum").val(pageNum);
			$("#pageSize").val(pageSize);
			$("#searchForm").submit();
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
		function clearData(){
			$("#xmQuery").val('');
			document.searchForm.submit();
		}
	</script>
</body>
</html>
