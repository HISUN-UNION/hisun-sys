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
						<th>导入模板名称</th>
						<th width="80px">操作</th>
					</tr>
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
							<td>
								中组部干部模板
							</td>
							<td>
								<a href="javascript:void(0)" class="" onclick="void('')">下载</a>|
								<a class="" href="javascript:unloadFile()"><em style="width: 78px;display: inline-block">导入</em></a>
								<input type="file" style="display: none" name="unloadFile" id="btn-unloadFile"/>
							</td>
						</tr>
					<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
						<td>
							任免审批表
						</td>
						<td>
							<a href="javascript:void(0)" class="" onclick="void('')">下载</a>|
							<a class="" href="javascript:unloadFile()"><em style="width: 78px;display: inline-block">导入</em></a>
						</td>
					</tr>
				</table>
			</div>

		</form>
	</div>

</div>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script>
	function unloadFile(){
		document.getElementById("btn-unloadFile").click();
	}
	(function(){
		App.init();



		//干部详细信息附件
		$("#btn-unloadFile").bind("change", function (evt) {
			if ($(this).val()) {
				gbrmspbSubmit();
			}
			$(this).val('');
		});
		var myLoading = new MyLoading("${path}", {zindex: 20000});

		function gbrmspbSubmit() {
			var fileInput = document.getElementById("btn-unloadFile");
			if (fileInput.files.length > 0) {
//				var name = fileInput.files[0].name
//				var arr = name.split(".");
//				if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
//					showTip("提示", "请上传word文件", 2000);
//					return;
//				}
			} else {
				showTip("提示", "请选择文件上传", 2000);
				return;
			}
			$.ajax({
				async:false,
				type:"POST",
				url:"${path}/zzb/app/console/asetA01/ajax/manageList2",
				dataType : "html",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				data:{

				},
				success:function(html){
					$('#jsonDataModal').modal('hide');
					$("#catalogList").html(html);

				},
				error : function(){
					myLoading.hide();
					showTip("提示","出错了,请检查网络!",2000);
				}
			});

		}


	})();
</script>