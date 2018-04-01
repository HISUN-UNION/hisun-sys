<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id="jsonDataFormGroup" class="control-group">
	<div class="portlet-body">
		<form class="form-horizontal" id="jsonDataForm">
			<div class="actions fr" style="margin-bottom: 5px;">
				<input type="hidden" name="shpcId"  value="${shpcId }"/>
				<div class="clearfix fr">
					<a id="sample_editable_1_new" class="btn green" href="javascript:void('')">
						查询
					</a>
					<a class="btn green" href="javascript:void('')">
						保存查询条件
					</a>
					<a  class="btn green" href="javascript:void('')">
						清空
					</a>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
				</div>

			</div>
			<div class="dataTables_wrapper form-inline">
				<table class="table table-striped table-bordered table-hover dataTable table-set" id="jsonDataFormTable">
					<tr>
						<th width="100">逻辑关系</th>
						<th width="60">左括号</th>
						<th width="120">查询项</th>
						<th width="80">条件</th>
						<th >查询内容(不填则为空)</th>
						<th width="60">右括号</th>
						<th width="80px">操作</th>
					</tr>
					<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
						<td>
							<input type="radio" name="luoji">并且	<input type="radio" name="luoji">或者
						</td>
						<td>
							<input type="text" name="jsonDataVos[${status.index}].name" 	 required style="width: 90%;" value=""/>
						</td>
						<td>
							<select name="" style="width: 90%;">
								<option></option>
								<option>人员基本信息</option>
								<option>单位职务信息</option>
								<option>学历学位</option>
								<option>工作经历</option>
								<option>社会关系</option>
								<option>奖励情况</option>
								<option>惩戒情况</option>
								<option>年度考核情况</option>

							</select>
						</td>
						<td>
							<select name="" style="width: 90%;">
								<option></option>
								<option>等于</option>
								<option>不等于</option>
								<option>大于</option>
								<option>大于等于</option>
								<option>小于</option>
								<option>小于等于</option>
								<option>包含</option>
								<option>不包含</option>
								<option>开头是</option>
								<option>开头不是</option>
								<option>结尾是</option>
								<option>结尾不是</option>

							</select>
						</td>
						<td>
							<input type="text" name="jsonDataVos[${status.index}].name" 	 required style="width: 90%;" value=""/>
						</td>
						<td>
							<input type="text" name="jsonDataVos[${status.index}].name" 	 required style="width: 90%;" value=""/>
						</td>
						<td>
							<a href="javascript:void(0)" class="" onclick="void('')">添加</a>|
							<a href="javascript:void(0)" class="" onclick="void('')">清空</a>
						</td>
					</tr>

				</table>
				<table class="table table-striped table-bordered table-hover dataTable table-set" id="jsonDataFormTable">
					<tr>
						<th width="100">逻辑关系</th>
						<th width="60">左括号</th>
						<th width="120">查询项</th>
						<th width="80">条件</th>
						<th >查询内容</th>
						<th width="60">右括号</th>
						<th width="120">操作</th>
					</tr>
					<tr style="text-overflow:ellipsis;">
						<td>
							并且
						</td>
						<td>

						</td>
						<td>
							干部状态
						</td>
						<td>
							等于
						</td>
						<td>
							现职
						</td>
						<td>
						</td>
						<td>
							<i class=" icon-circle-arrow-up"></i>&nbsp;
							<i class="icon-circle-arrow-down"></i>&nbsp;|
							<a href="#" class="">编辑</a>|
							<a href="#" class="">删除</a>
						</td>
					</tr>
					<tr style="text-overflow:ellipsis;">
						<td>
							并且
						</td>
						<td>

						</td>
						<td>
							查询人员范围
						</td>
						<td>
							等于
						</td>
						<td>
							市管职务
						</td>
						<td>
						</td>
						<td>
							<i class=" icon-circle-arrow-up"></i>&nbsp;
							<i class="icon-circle-arrow-down"></i>&nbsp;|
							<a href="#" class="">编辑</a>|
							<a href="#" class="">删除</a>
						</td>
					</tr>
					<tr style="text-overflow:ellipsis;">
						<td>
							并且
						</td>
						<td>

						</td>
						<td>
							任职状态
						</td>
						<td>
							等于
						</td>
						<td>
							已任,拟任
						</td>
						<td>
						</td>
						<td>
							<i class=" icon-circle-arrow-up"></i>&nbsp;
							<i class="icon-circle-arrow-down"></i>&nbsp;|
							<a href="#" class="">编辑</a>|
							<a href="#" class="">删除</a>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<B>查询表达式:</B>干部状态='现职' 并且 查询人员范围='市管职务' 并且 任职状态='已任,拟任'
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
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
					showTip("提示", "请上传word文件", 2000);
					return;
				}
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