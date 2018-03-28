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
	<title>干部任免审批表</title>
<style type="text/css">
	form {
		margin: 0 0 0px;
	}
	.tabbable-custom > .nav-tabs{border-bottom: 1px solid #ddd;}
	.tabbable-custom{ width:100%; float:left;}


	.NewlyaddedRight{float:right; width:320px; background-color:#f9f9f9;}
	.form-horizontal .control-label{ width:120px;}
	.form-horizontal .controls{ margin-left:130px;}
	.NewlyaddedRYtitle{ height:36px; line-height:36px; font-size:14px; text-indent:1em; background-color:#f5f5f5;border-bottom: 1px solid #ddd; margin-bottom:20px;}
	.inputtz{ width:140px; background-color:#FFFFFF !important; float:left;}
	.inputtz02{width:166px; background-color:#FFFFFF !important; float:left;}
	.inptzBtn{ height:30px; line-height:30px; background-color:#E1E1E1; display:inline-block; width:26px; text-align:center;}
	.form-horizontal .control-label{ padding-top:3px;}
	.PresentBtn{ text-align:center; padding-top:20px;}
	.PresentBtn button.btn{ margin:0 5px;}

	.VolunteerApplytable{ border:solid 1px #DDDDDD;border-bottom: none; border-right: none; color:#555;}
	.VolunteerApplytable tr td{ font-size:12px; border-bottom:solid 1px #DDDDDD; border-right:solid 1px #DDDDDD; padding:2px 2px; text-align:center; }
	.VolunteerApplytable tr td input.text01{ height:28px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
	.VolunteerApplytable tr td input.text02{ height:20px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
	.VolunteerApplytable tr td input.text03{ height:20px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
	.VolunteerApplytable tr td input.text04{ height:20px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}

	.VolunteerApplytable tr td input.text05{ height:20px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333; text-align:center;}
	.UploadPhotos{ background-color:#F6F6F6; width:150px;}

	.UploadpBtn{ width:80px; height:30px; line-height:30px; text-align:center; background-color:#a73116; font-size:14px; color:#FFFFFF; display: inline-block; border-radius:4px;}
	.UploadpBtn:hover{ background-color:#d49e4a;color:#FFFFFF;  }
	textarea:focus, input[type="text"]:focus, input[type="password"]:focus, input[type="datetime"]:focus, input[type="datetime-local"]:focus, input[type="date"]:focus, input[type="month"]:focus, input[type="time"]:focus, input[type="week"]:focus, input[type="number"]:focus, input[type="email"]:focus, input[type="url"]:focus, input[type="search"]:focus, input[type="tel"]:focus, input[type="color"]:focus, .uneditable-input:focus{box-shadow: inset 0 1px 1px rgba(0,0,0,0.0),0 0 8px rgba(82,168,236,0.0);
	}
	textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{box-shadow: inset 0 1px 1px rgba(0,0,0,0.0);}
	input[type="radio"], input[type="checkbox"]{ margin:0;}
	.textareatab{height:127px; width:100%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; font-family:"微软雅黑", Verdana, Geneva, sans-serif; color:#333333;}

	.VolunteerApplytable tr td p.pb01{ padding:5px 20px;}
	.VolunteerApplytable tr td.c01{ color:#333333; background-color:#F9F9F9;}

	.dyresume{ margin:10px 0 30px 0;}
	.dyresume i{ font-size:20px;}

	.textareatab02{height:78px; width:100%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; font-family:"微软雅黑", Verdana, Geneva, sans-serif; color:#333333;}
	.zytwoname{ margin-top:50px; margin-bottom:60px;}
	.MoveBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#0385D8 !important; margin-bottom:5px; margin-top:10px;}
	.MoveBtn:hover{background-color:#0385D8;color:#fff !important; }
	.DownBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#0385D8 !important;}
	.DownBtn:hover{background-color:#0385D8;color:#fff !important; }
	.dydeleteBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#F40101 !important;}
	.dydeleteBtn:hover{background-color:#F40101;color:#fff !important; }

	.Otherinformationtable{ border:solid 1px #DDDDDD;border-bottom: none; border-right: none; color:#555;}
	.Otherinformationtable tr td{ font-size:12px; border-bottom:solid 1px #DDDDDD; border-right:solid 1px #DDDDDD; padding:10px 8px; text-align: left; }
	.Otherinformationtable tr td.c01{ color:#333333; background-color:#F9F9F9; text-align:center;}
	.Otherinformationtable tr td input.text01{ height:28px; width:90%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}

	.spd01{ float:left; margin-right:8px;}
</style>
<body>
<div class="portlet-title">
	<div class="clearfix fr">
		<input type="checkbox" checked>已完成该步骤
		<a class="btn green" href="javascript:void();">
		   保存
		</a>
		<a class="btn green" href="javascript:loadNextPage();">
			完成并进入下一步
		</a>
	</div>

</div>
	<div class="tabbable tabbable-custom" style="text-align: center">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_1_1" data-toggle="tab">任免审批表(一)</a></li>
			<li><a href="#tab_1_2" data-toggle="tab">任免审批表(二)</a></li>
		</ul>

		<div class="tab-content pt0" style="padding-top:0px;padding-left:90px;padding-right:90px;">
			<div class="tab-pane active" id="tab_1_1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="VolunteerApplytable mt20">
					<tbody>
					<tr>
						<td width="60" class="c01"><em class="red">*</em>姓&nbsp;&nbsp;名</td>
						<td><input type="text" class="text01" value="满延春"></td>
						<td width="60" class="c01"><em class="red">*</em>性&nbsp;&nbsp;别</td>
						<td><input type="text" class="text01" value="男"></td>
						<td width="60" class="c01"><em class="red">*</em>出生<br>&nbsp;&nbsp;年月</td>
						<td><input type="text" class="text01" value="1975.09"></td>
						<!--<td rowspan="3" class="UploadPhotos">
                            <p>照片</p>
                            <a href="###" class="UploadpBtn">上传</a>
                        </td>-->
						<td rowspan="4" class="UploadPhotos">
							<img src="${path}/images/templateImage/portraitIco01.jpg">
						</td>
					</tr>
					<tr>
						<td class="c01"><em class="red">*</em>民&nbsp;&nbsp;族</td>
						<td><input type="text" class="text01" value="苗族"></td>
						<td class="c01"><em class="red">*</em>籍&nbsp;&nbsp;贯</td>
						<td><input type="text" class="text01" value="湖南泸溪"></td>
						<td class="c01"><em class="red">*</em>出生地</td>
						<td><input type="text" class="text01"value="湖南泸溪"></td>
					</tr>
					<tr>
						<td class="c01">入&nbsp;&nbsp;党<br>时&nbsp;&nbsp;间</td>
						<td><input type="text" class="text01" value="2001.06"></td>
						<td class="c01"><em class="red">*</em>参加工<br>&nbsp;&nbsp;作时间</td>
						<td><input type="text" class="text01" value="1999.07"></td>
						<td class="c01"><em class="red">*</em>健康<br>&nbsp;&nbsp;状况</td>
						<td><input type="text" class="text01" value="健康"></td>
					</tr>
					<tr>
						<td class="c01">专业技<br>术职务</td>
						<td colspan="2"><input type="text" class="text01" ></td>
						<td class="c01">熟悉专业<br>有何特长</td>
						<td colspan="2"><input type="text" class="text01"></td>
					</tr>
					<tr>
						<td rowspan="4" class="c01">学历<br>学位</td>
						<td rowspan="2" class="c01">全日制<br>教&nbsp;&nbsp;育</td>
						<td colspan="2"><input type="text" class="text02" value="大学文学学士"></td>
						<td rowspan="2" class="c01">毕业院校<br>系及专业</td>
						<td colspan="2"><input type="text" class="text03" value="吉首大学汉语言文学专业"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="text02" value=""></td>
						<td colspan="2"><input type="text" class="text03" value=""></td>
					</tr>
					<tr>
						<td rowspan="2" class="c01">在&nbsp;&nbsp;职<br>教&nbsp;&nbsp;育</td>
						<td colspan="2"><input type="text" class="text02" value=""></td>

						<td rowspan="2" class="c01">毕业院校<br>系及专业</td>
						<td colspan="2"><input type="text" class="text03" value=""></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="text02" value=""></td>
						<td colspan="2"><input type="text" class="text03" value=""></td>
					</tr>

					<tr>
						<td class="c01" colspan="2"><em class="red">*</em> 工作单位及职务</td>
						<td colspan="5"><input type="text" class="text04" value="州委组织部党员管理科科长"></td>
					</tr>
					<tr>
						<td class="c01" style=" vertical-align:top;">
							<p class="dyresume"><i class="icon-file-text"></i></p>
							<em class="red">*</em> 简历
						</td>
						<td colspan="6"><textarea class="textareatab" style="height: 220px">1995.09--1999.07  吉首大学汉语言文学专业大学学习，获文学学士学位
1999.07--2000.12  保靖县拔茅乡政府干部
2000.12--2001.11  保靖县复兴镇政府干部
2001.11--2003.02  保靖县阳朝乡政府副乡长
2003.02--2004.04  保靖县委组织部副科级组织员
2004.04--2004.08  保靖县委组织部副科级组织员、调研室主任
2004.08--2005.12  保靖县委组织部组织科科长（副科级）
2005.12--2007.02  州委组织部副科级干部
2007.02--2009.05  州委组织部主任科员
2009.05--2012.07  州委组织部政研室副主任、主任科员
2012.07--         州委组织部党员管理科科长</textarea></td>
					</tr>
					</tbody>
				</table>
			</div>

			<div class="tab-pane" id="tab_1_2">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="VolunteerApplytable mt20">
					<tbody>
					<tr>
						<td class="c01" style="width:80px;">奖惩综述</td>
						<td colspan="6"><textarea class="textareatab02">无</textarea></td>
					</tr>
					<tr>
						<td class="c01" style="width:80px;">年度考核结果
							</td>
						<td colspan="6"><textarea class="textareatab02">2016年年度考核优秀；
2015年年度考核称职；
2014年年度考核称职。</textarea></td>
					</tr>
					<tr>
						<td rowspan="9" class="c01" style="width:80px; vertical-align:top;">
							<p><a href="###" class="MoveBtn"><i class="icon-long-arrow-up"></i> 上移</a></p>
							<p><a href="###" class="DownBtn"><i class="icon-long-arrow-down"></i> 下移</a></p>
							<p class="zytwoname">主要家庭<br>成员及社<br>会重要关系</p>
							<p><a href="###" class="dydeleteBtn"><i class="icon-remove-sign"></i> 删除</a></p>
						</td>
						<td class="c01">称谓</td>
						<td class="c01">姓名</td>
						<td class="c01">年龄</td>
						<td class="c01">政治面貌</td>
						<td class="c01">工作职位及职务</td>
					</tr>
					<tr>
						<td><input type="text" class="text05" value="妻子"></td>
						<td><input type="text" class="text05" value="周发群"></td>
						<td><input type="text" class="text05" value="42"></td>
						<td><input type="text" class="text05" value="中共党员"></td>
						<td><input type="text" class="text05" style="width:150px;" value="州妇幼保健院护士"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05" value="女儿"></td>
						<td><input type="text" class="text05" value="满续缘"></td>
						<td><input type="text" class="text05" value="13"></td>
						<td><input type="text" class="text05" value="群众"></td>
						<td><input type="text" class="text05" value="湘西雅思实验学校初二学生" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05" value="父亲"></td>
						<td><input type="text" class="text05" value="满维银"></td>
						<td><input type="text" class="text05" value="79 "></td>
						<td><input type="text" class="text05" value="群众"></td>
						<td><input type="text" class="text05" value="泸溪县小章乡农民" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05" value="母亲"></td>
						<td><input type="text" class="text05" value="谭桂碧"></td>
						<td><input type="text" class="text05" value="77 "></td>
						<td><input type="text" class="text05" value="群众"></td>
						<td><input type="text" class="text05" value="泸溪县小章乡农民" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05" style="width:150px;"></td>
					</tr>
					<tr>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05"></td>
						<td><input type="text" class="text05" style="width:150px;"></td>
					</tr>
					</tbody>
				</table>

			</div>
		</div>
	</div>



<script type="text/javascript">
	var form1 = new EstValidate("form1");
	function formSubmit(){
		var bool = form1.form();
		if(bool){
			$.ajax({
				url : "${path }/zzb/app/console/appGbcxB01/save",
				type : "post",
				data : $("#form1").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.success){
						$('#jgModal').modal('hide');
						setTimeout(function(){window.location.href = "../../../../../../../../"},2000);
						showTip("提示","保存成功", 1500);
						//setTimeout(process.list,2000);
					}else{
						var message = data.msg?data.msg:data.message;
						showTip("提示", message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了,请检查网络!",2000);
				}
			});
		}
	}
	function loadNextPage(){
		rmqkLoad();
		$("#barDiv").width("50%");
		$("[id='#tab_2_2']").tab('show');
	}

</script>
</body>
</html>