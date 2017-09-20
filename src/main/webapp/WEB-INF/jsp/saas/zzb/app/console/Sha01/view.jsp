<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/style-metro.css">
<link rel="stylesheet" type="text/css" href="/WEB-INF/css/bootstrap-fileupload.css">
<script src="/WEB-INF/js/jquery-1.10.1.min.js"></script>
<script src="js/bootstrap-fileupload.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>“${shpa01Vo.xm}”个人信息</title>
</head>
<body>

<div class="xwbmain win1000">

    <div class="mainone">

        <div class="mainoneleft">
            <img class="imgtp" width="180" height="200" src="${path}/zzb/app/console/Sha01/${shpa01Vo.id}/photo?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" />
        </div>
        <div class="clearfix fr">
							<span class="controllerClass btn file_but">
                <a class="btn" href="${path }/zzb/app/console/Sha01/list?shpcId=${shpcId}"><i class="icon-remove-sign"></i> 返回</a>
                </span>
        </div>
        <div class="mainoneright">
            <div class="Fullname">${shpa01Vo.xm}</div>
            <div class="gerenintrodu">${shpa01Vo.xb}，${shpa01Vo.csny}生，${shpa01Vo.jg}人，${shpa01Vo.cjgzsj}参加工作，${shpa01Vo.rdsj}加入中国共产党，${shpa01Vo.whcd}学历。</div>
            <ul class="ulonleftjx">
                <li><span>现工作单位及职务：</span>${shpa01Vo.xgzdwjzw}</li>
                <li><span>拟调整配备意见：</span>${shpa01Vo.ntzpbyj}</li>
            </ul>
        </div>
    </div>
    <div class="maintwo">
        <h1 class="tith1">工作经历</h1>
        <c:forEach items="${shpa01Vo.gzjls}" var="vo">
            <p><c:out value="${vo.jlsm}"></c:out></p>
        </c:forEach>
    </div>
    <div class="mainthree">
        <h1 class="tith1" style="margin-bottom:30px;">其他材料</h1>

        <form class="form-horizontal" id="importForm" enctype="multipart/form-data">
            <div class="control-group">
                <label class="control-label">干部详细信息</label>

                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                       <span class="btn btn-file">
                        <span class="fileupload-new">点击上传</span>
                        <input type="file" class="default" name="gbrmspbFile" id="btn-gbrmspb"/>
                        </span>
                        <div class="btn-group" id="gbrmspbDownDiv"
                             <c:if test="${!isHavagbrmspbFile }">style="visibility:hidden"</c:if>>
                            <a class="btn blue" herf="javascript:void(0)" onclick="gbrmspbDown()"><i
                                    class="icon-circle-arrow-down"></i>下载文件</a>
                        </div>
                        <%--<a herf="javascript:void(0)" onclick="gbrmspbDelete()" class="close fileupload-exists" data-dismiss="fileupload" style="float: none"></a>--%>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">考察材料</label>

                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                       <span class="btn btn-file">
                        <span class="fileupload-new">点击上传</span>
                        <input type="file" class="default" name="kcclFile" id="btn-kccl"/>
                        </span>
                        <div class="btn-group" id="kcclDownDiv"
                             <c:if test="${!isHavakcclFile }">style="visibility:hidden"</c:if>>
                            <a class="btn blue" herf="javascript:void(0)" onclick="kcclDown()"><i
                                    class="icon-circle-arrow-down"></i>下载文件</a>
                        </div>
                        <%--<a herf="javascript:void(0)" onclick="kcclDelete()" class="close fileupload-exists" data-dismiss="fileupload" style="float: none"></a>--%>




                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">档案审查情况</label>

                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                       <span class="btn btn-file">
                        <span class="fileupload-new">点击上传</span>
                        <input type="file" class="default" name="dascqkFile" id="btn-dascqk"/>
                        </span>
                        <div class="btn-group" id="dascqkDownDiv"
                             <c:if test="${!isHavaDascqkFile }">style="visibility:hidden"</c:if>>
                            <a class="btn blue" herf="javascript:void(0)" onclick="dascqkDown()"><i
                                    class="icon-circle-arrow-down"></i>下载文件</a>
                        </div>
                        <%--<a herf="javascript:void(0)" onclick="dascqkDelete()" class="close fileupload-exists" data-dismiss="fileupload" style="float: none"></a>--%>


                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">个人重大事项</label>

                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <span class="btn btn-file">
                        <span class="fileupload-new">点击上传</span>
                        <input type="file" class="default" name="grzdsxFile" id="btn-grzdsx"/>
                        </span>
                        <div class="btn-group" id="grzdsxDownDiv"
                             <c:if test="${!isHavaGrzdsxFile }">style="visibility:hidden"</c:if>>
                            <a class="btn blue" herf="javascript:void(0)" onclick="grzdsxDown()"><i
                                    class="icon-circle-arrow-down"></i>下载文件</a>
                        </div>
                        <%--<a herf="javascript:void(0)" onclick="grzdsxDelete()" class="close fileupload-exists" data-dismiss="fileupload" style="float: none"></a>--%>
                    </div>
                </div>
            </div>
        </form>
    </div>
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
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp" %>
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
                url: "${path }/zzb/app/Sha01/gbrmspb/ajax/uploadFile?sha01Id=${shpa01Vo.id}",
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
                        window.location.href="${path }/zzb/app/console/Sha01/view?id=${shpa01Vo.id}";
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
                if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
                    showTip("提示", "请上传word文件", 2000);
                    return;
                }
            } else {
                showTip("提示", "请选择文件上传", 2000);
                return;
            }
            $("#importForm").ajaxSubmit({
                url: "${path }/zzb/app/Sha01/kccl/ajax/uploadFile?sha01Id=${shpa01Vo.id}",
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
                        window.document.getElementById("kcclDownDiv").style.visibility = "visible";
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
                if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
                    showTip("提示", "请上传word文件", 2000);
                    return;
                }
            } else {
                showTip("提示", "请选择文件上传", 2000);
                return;
            }
            $("#importForm").ajaxSubmit({
                url: "${path }/zzb/app/Sha01/dascqk/ajax/uploadFile?sha01Id=${shpa01Vo.id}",
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
                        window.document.getElementById("dascqkDownDiv").style.visibility = "visible";
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
                if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
                    showTip("提示", "请上传word文件", 2000);
                    return;
                }
            } else {
                showTip("提示", "请选择文件上传", 2000);
                return;
            }
            $("#importForm").ajaxSubmit({
                url: "${path }/zzb/app/Sha01/grzdsx/ajax/uploadFile?sha01Id=${shpa01Vo.id}",
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
                        window.document.getElementById("grzdsxDownDiv").style.visibility = "visible";
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
    function gbrmspbDown() {
        window.open("${path }/zzb/app/Sha01/gbrmspb/ajax/down?sha01Id=${shpa01Vo.id}");
    }
    function kcclDown() {
        window.open("${path }/zzb/app/Sha01/kccl/ajax/down?sha01Id=${shpa01Vo.id}");
    }
    function dascqkDown() {
        window.open("${path }/zzb/app/Sha01/dascqk/ajax/down?sha01Id=${shpa01Vo.id}");
    }
    function grzdsxDown() {
        window.open("${path }/zzb/app/Sha01/grzdsx/ajax/down?sha01Id=${shpa01Vo.id}");
    }


</script>
</body>
</html>
