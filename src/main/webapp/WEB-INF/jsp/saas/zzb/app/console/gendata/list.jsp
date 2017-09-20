<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>生成APP数据包</title>
	<link href="${path }/css/news.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		form .form-hint {
			display: block;
			color: #888888;
			line-height: 24px;
			clear: both;
			margin-left: 160px;
			padding-left: 20px;
		}
	</style>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">
	<%-- BEGIN PAGE CONTENT--%>
	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<span class="hidden-480">生成APP数据包</span>
					</div>
					<div class="tools">
						<a type="button" class="btn green" href="${path }/zzb/app/console/gendata/generator"  style="color: #ffffff;height: auto"><i class="icon-download"></i>生成</a>
						<a type="button" class="btn" style="height: 20px;" href="#" ><i class="icon-refresh"></i>刷新</a>
					</div>
				</div>

				<div class="portlet-body form">
					<form action="" class="form-horizontal" id="form1" method="post">

						<div class="form-actions">
							<a type="button" class="btn green" href="${path }/zzb/app/console/gendata/generator"  style="color: #ffffff;height: auto"><i class="icon-download"></i>生成</a>
							<a type="button" class="btn" style="height: 20px;" href="#" ><i class="icon-refresh"></i>刷新</a>
						</div>
					</form>
				</div>

			</div>

			<%-- END SAMPLE FORM PORTLET--%>
		</div>
	</div>

	<%-- END PAGE CONTENT--%>
</div>


<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
<script type="text/javascript" src="${path }/js/common/custom-validate.js"></script>
<script type="text/javascript" src="${path }/js/monitor/monitorInstSnmp.js"></script>
<script type="text/javascript" src="${path }/js/monitor/monitorInstNotice.js"></script>
<script type="text/javascript" src="${path }/js/monitorCommon.js"></script>
<script type="text/javascript" src="${path }/js/saas/monitorMySql.js"></script>
<script src="${path }/js/common/loading.js" type="text/javascript" ></script>
<script type="text/javascript">
	(function(){
		App.init();
	})();

</script>
</body>
</html>
