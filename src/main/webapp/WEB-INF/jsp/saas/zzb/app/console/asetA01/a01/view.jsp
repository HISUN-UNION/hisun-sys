
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
    <title>“${a01Vo.xm}”个人信息</title>
    <style type="text/css">
        .showdabzcss{width:450px;;overflow:hidden;text-overflow:ellipsis; display: inline-block; white-space: nowrap; color: #333; font-size: 13px;
            vertical-align: middle; cursor: pointer; background-color: #f8f8f8; height: 34px; line-height: 34px; padding-left: 10px;  }
        .showdabzcss:hover{  color:#009ae1;}
        form {
            margin: 0 0 0px;
        }
    </style>
</head>

<body>
<div id="viewDaModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="title" >
                    “${a01Vo.xm}”档案图片
                </h3>
            </div>
            <div class="modal-body" id="viewDaDiv" style="background-color: #f1f3f6;">
            </div>
        </div>
    </div>
</div>
<div id="virwGbjdModal" class="modal container hide fade" tabindex="-1" data-width="700">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="titleView" >
                    干部监督情况
                </h3>
            </div>
            <div class="modal-body" id="virwGbjdDiv">
            </div>
        </div>
    </div>
</div>
<div class="xwbmain">

    <div class="mainone">

        <div class="mainoneleft">
            <img class="imgtp" width="180" height="200" src="${path}/zzb/app/console/asetA01/${a01Vo.id}/photo?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" />
        </div>
        <div class="clearfix fr">
            <%--<a class="btn red" href="javascript:del('${a01Vo.xm }')"><i class=" icon-remove-sign"></i>删除</a>--%>
                 <%--<a class="btn blue" herf="javascript:void(0)" onclick="fileDown()"><i--%>
                         <%--class="icon-circle-arrow-down"></i>干部任免审批表</a>--%>
                <div class="btn-group">
                    <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                        输出 <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li >
                            <a onclick="fileDown()">输出任免表</a>
                        </li>
                        <li >
                            <a onclick="">输出任免表Lrmx</a>
                        </li>
                        <li >
                            <a onclick="">输出任免表Lrm</a>
                        </li>
                        <li >
                            <a onclick="">输出个人数据HZB</a>
                        </li>
                        <li >
                            <a onclick="">输出个人数据7z</a>
                        </li>
                    </ul>
                </div>
                <a class="btn blue" herf="javascript:void(0)" onclick="">任免文件</a>
                <a class="btn blue" herf="javascript:void(0)" onclick="view()">电子档案</a>
                <a class="btn blue" herf="javascript:void(0)" onclick="viewGbjd()">干部监督信息</a>

                <a class="btn" href="javascript:returnList()"><i class="icon-undo"></i>返回</a>
        </div>
        <div class="mainoneright" style="width: 560px;">
            <div class="Fullname">${a01Vo.xm}</div>
            <div class="gerenintrodu">${a01Vo.csnyStr}生，${a01Vo.jg}人，${a01Vo.cjgzsjStr}参加工作，
                <c:if test="${empty a01Vo.dp}">
                    群众
                </c:if>
                <c:if test="${!empty a01Vo.dp}">
                    <c:if test="${a01Vo.dp eq '中共党员'}">
                        ${a01Vo.rdsjStr}加入中国共产党
                    </c:if>
                    <c:if test="${a01Vo.dp ne '中共党员'}">
                        <c:if test="${!empty a01Vo.rdsjStr}">
                            ${a01Vo.rdsjStr}加入</c:if>${a01Vo.dp}
                    </c:if>
                </c:if>
                。</div>
            <ul class="ulonleftjx">
                <li><span>全日制学历学位及专业：</span>${a01Vo.gbrmspbQrzxlxw}${a01Vo.gbrmspbQrzByyxAndZy}</li>
                <li><span>&nbsp;&nbsp;&nbsp;在职学历学位及专业：</span></span>${a01Vo.gbrmspbZzxlxw}${a01Vo.gbrmspbZzByyxAndZy}</li>
            </ul>
        </div>
    </div>
    <div class="maintwo">
        <h1 class="tith1">工作经历</h1>
        <c:forEach items="${a01Vo.gzjlStrs}" var="gzjlStr">
            <p>${gzjlStr}</p>
        </c:forEach>
    </div>

</div>


<script type="text/javascript">




    jQuery(document).ready(function () {
        App.init();
        //干部详细信息附件
        $("#btn-gbrmspb").bind("change", function (evt) {
            if ($(this).val()) {
                gbrmspbSubmit();
            }
            $(this).val('');
        });


    });

    var view = function(){
        var divHeight = $(window).height()-100;
        $('#viewDaModal').attr("data-height",divHeight);
        $.ajax({
            url:"${path}/zzb/app/console/a38/ajax/viewImgManage",
            type : "post",
            data: {},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#viewDaDiv').html(html);

                $('#viewDaModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }

    var myLoading = new MyLoading("${path}", {zindex: 20000});
    function gbrmspbSubmit() {
        var fileInput = document.getElementById("btn-gbrmspb");
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
        $("#importForm").ajaxSubmit({
            url: "${path }/zzb/app/console/GbMca01/gbrmspb/ajax/uploadFile?gbMcA01Id=${a01Vo.id}",
            type: "post",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            beforeSend: function (XHR) {
                myLoading.show();
            },
            success: function (json) {
                if (json.code == 1) {
                    //showTip("提示","操作成功",2000);

                    window.document.getElementById("gbrmspbDownDiv").style.visibility = "visible";
                    window.location.href="${path }/zzb/app/console/gbmc/a01/view?id=${a01Vo.id}";
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

    function fileDown() {
        //if("${a01Vo.filepath}"==""){
       //     showTip("提示", "没有可下载的任免审批表!", 2000);
       // }else{
             window.open("${path }/zzb/app/console/asetA01/ajax/down?id=${a01Vo.id}");
       // }
    }


     function del(itemName){
        actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/${a01Vo.id}",{} ,function(data,status){
            if (data.success == true) {
                showTip("提示","删除成功", 1000);
                returnList();
                <%--setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/a01/list?mcid=${mcid}&mcb01id=${mcb01id}"},1000);--%>
            }else{
                showTip("提示", data.message, 2000);
            }
        });
    }

    function returnList(){
        $.ajax({
            async:false,
            type:"POST",
            url:"${path}/zzb/app/console/asetA01/ajax/list",
            dataType : "html",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            data:{
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

    function viewGbjd(){

        $.ajax({
            url : "${path}/zzb/app/console/gbjd/ajax/viewGbjd",
            type : "post",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#virwGbjdDiv').html(html);
//                $('#titleView').text(tjmc);
                $('#virwGbjdModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }
</script>
</body>
</html>
