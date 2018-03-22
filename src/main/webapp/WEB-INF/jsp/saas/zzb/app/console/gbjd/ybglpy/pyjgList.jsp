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
	<title>2017年评议机构</title>
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
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">2017年评议机构</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="#">
							导出
						</a>
						<a class="btn" href="${path }/zzb/app/console/gbjd/ybglpy"><i class="icon-undo"></i>返回</a>
						<%--<span class="controllerClass btn green file_but" >--%>
							<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
							<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
					</div>

				</div>
			</form>

				<div class="clearfix" style="font-size: 14px;"><b>
					2017年共<font color="#ff4500">10</font>个机构进行评分、其中95以上<font color="#ff4500">1</font> 个；
					85~94分<font color="#ff4500">3</font>个；70~84分<font color="#ff4500">3</font>个；60~69分<font color="#ff4500">2</font>个；60分以下<font color="#ff4500">1</font>个</b>
				</div>
				<div class="portlet-body">
					<table>
						<tr>
							<td width="40%">
								<table class="table table-striped table-bordered table-hover dataTable table-set">
									<thead>
									<tr>
										<th >机构名称</th>
										<th width="150">评议分</th>
									</tr>
									</thead>
									<tbody>
									<tr style="text-overflow:ellipsis;">
										<td  >州纪委</td>
										<td  >95分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委办</td>
										<td  >88分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委政策研究室</td>
										<td  >87分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委组织部</td>
										<td  >85分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委老干部局</td>
										<td  >84分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委宣传部</td>
										<td  >82分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委政法委</td>
										<td  >80分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州委统战部</td>
										<td  >68分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州直属机关工委</td>
										<td  >68分</td>
									</tr>
									<tr style="text-overflow:ellipsis;">
										<td  >州机构编制委员会办公室</td>
										<td  >59分</td>
									</tr>

									</tbody>
								</table>

							</td>
							<td width="60%">
								<table width="100%">
									<tr>
										<td width="100%" colspan="2">
											<div class="mainright" style="margin-left:10px; height:230px;border: 0px solid ;">
												<div style="padding:12px 0 12px 0 ;font-size:14px;text-align:center; ">2017年评分情况</div>
												<div id="MonitorTopN01" style="width:100%;height:180px;"></div>
											</div>
										</td>
									</tr>
									<tr  >
										<td width="100%" colspan="2" height="20px">&nbsp;
										</td>
									</tr>
									<tr  >

										<td width="50%" style="margin-top: 20px">
											<div class="bintumain">
												<div class="ExpertTitle" style=" width:300px; margin:0 auto 0 auto;">2017年评分情况</div>
												<div id="Agedata01" style=" height:200px; width:300px; margin:0 auto 0 auto;"></div>
											</div>
										</td>
										<td width="50%" style="margin-top: 20px">
											<div class="bintumain">
												<div class="ExpertTitle" style=" width:300px; margin:0 auto 0 auto;">2016年评分情况</div>
												<div id="Agedata02" style=" height:200px; width:300px; margin:0 auto 0 auto;"></div>
											</div>
										</td>
									</tr>
								</table>

							</td>
						</tr>
					</table>

				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>
<script src="${path}/js/echarts3/echarts.min.js" type="text/javascript" ></script>
<script type="text/javascript">
	(function(){
		App.init();

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});

	})();
	var myChart1 = echarts.init(document.getElementById('MonitorTopN01'));
	option1 = {
		/*title: {
		 text: '世界人口总量',
		 subtext: '数据来自网络'
		 },*/
		color: ['#007cd5'],

		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			},
			backgroundColor: 'rgba(248,248,248,0.7)',     // 提示背景颜色，默认为透明度为0.7的黑色
			borderColor: '#3f88bb',       // 提示边框颜色
			borderRadius: 4,           // 提示边框圆角，单位px，默认为4
			borderWidth: 1,            // 提示边框线宽，单位px，默认为0（无边框）
			padding: 5,                // 提示内边距，单位px，默认各方向内边距为5，
			textStyle: {
				color: '#333333'
			},
		},

		grid: {
			top: '3%',
			left: '3%',
			right: '3%',
			bottom: '1%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : ['95以上','85~94分','70~84分','60~69分','60分以下'],
				axisLine : {
					show : true,
					lineStyle : {
//						color: '#409fff'
					}
				},
				axisTick : {
					show : true,
					lineStyle : {
//						color: '#0a5bad'
					}
				},
				splitLine : {
					show : false,
					lineStyle : {
//						color: '#ddd'
					}
				},
				axisLabel: {
					show: true,
					textStyle: {
//						color: "#409fff"
					},
				},
				boundaryGap: [0, 0.01]
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLine : {
					show : true,
					lineStyle : {
//						color: '#409fff'
					}
				},
				axisTick : {
					show : true,
					lineStyle : {
//						color: '#0a5bad'
					}
				},
				splitLine : {
					show : true,
					lineStyle : {
//						color: '#073971'
					}
				},
				axisLabel: {
					show: true,
					textStyle: {
//						color: "#409fff"
					},
				},
			}
		],
		series : [
			{
				name:'总人数',
				type:'bar',
				barWidth: '30%',
				data:[1, 3, 3, 2, 1]
			}
		]
	};
	myChart1.setOption(option1, true);

	var myChart1 = echarts.init(document.getElementById('Agedata01'));
	option1 = {
		color: [
			"#2ec7c9",
			"#b6a2de",
			"#5ab1ef",
			"#ffb980",
			"#d87a80",
		],
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			top: 18,
			data:['95以上','85~94分','70~84分','60~69分','60分以下'],
			itemWidth:18,
			itemHeight:8,

			textStyle:{
				fontSize: 14,
//				color: '#fff',
			},


		},
		series: [
			{
				name:'2017年评分情况',
				type:'pie',
				//radius: ['50%', '70%'],  空心圆
				radius : '80%',
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				center:[
					180,
					90
				],
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[
					{value:2, name:'95以上'},
					{value:3, name:'85~94分'},
					{value:3, name:'70~84分'},
					{value:2, name:'60~69分'},
					{value:1, name:'60分以下'}
				]
			}
		]
	};
	myChart1.setOption(option1, true);

	var myChart2 = echarts.init(document.getElementById('Agedata02'));
	option2 = {
		color: [
			"#2ec7c9",
			"#b6a2de",
			"#5ab1ef",
			"#ffb980",
			"#d87a80",
		],
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			top: 18,
			data:['95以上','85~94分','70~84分','60~69分','60分以下'],
			itemWidth:18,
			itemHeight:8,

			textStyle:{
				fontSize: 14,
//				color: '#fff',
			},


		},
		series: [
			{
				name:'2016年评分情况',
				type:'pie',
				//radius: ['50%', '70%'],  空心圆
				radius : '80%',
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				center:[
					180,
					90
				],
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[
					{value:1, name:'95以上'},
					{value:3, name:'85~94分'},
					{value:3, name:'70~84分'},
					{value:2, name:'60~69分'},
					{value:1, name:'60分以下'}
				]
			}
		]
	};
	myChart2.setOption(option2, true);
	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
				'pageNum':pageNum,
				'pageSize':pageSize
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	function searchSubmit(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}


	var view = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/view",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'id':id,
				'b01Id':"${b01Id}"
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
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
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	function exportGbrmsp(){
		$.cloudAjax({
			path : '${path}',
			url : "${path }/zzb/app/console/asetA01/ajax/exportGbrmsp",
			type : "post",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data.success == true){
					showTip("提示","生成干部任免审批表成功!",2000);
					//setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", "生成干部任免审批表失败!", 2000);
				}
			},
			error : function(){
				showTip("提示","出错了请联系管理员",2000);
			}
		});
	}
</script>
</body>
</html>
