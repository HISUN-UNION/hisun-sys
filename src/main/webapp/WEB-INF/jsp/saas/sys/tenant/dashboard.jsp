<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="${path }/images/favicon.ico"/>
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <!-- END PAGE LEVEL STYLES -->
    <title>首页</title>
    <style type="text/css">
        .page-content{ background-color:#eaedf1 !important;margin:0 !important; padding-right:0 !important;}
        body {background-color: #eaedf1 !important;}
        .tabbable-custom > .tab-content{ padding:10px 10px 10px 10px !important;}
    </style>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${path}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${path}/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${path}/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${path}/css/glyphicons.css" rel="stylesheet"/>
    <link href="${path}/css/halflings.css" rel="stylesheet"/>
    <%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
    <style>
        .dropdown-menu {
            background-color: inherit;
            box-shadow: none;
            border: none;
        }

        .navigdrop_down {
            left: -116px;
            width: 200px;
            padding: 0;
        }

        .navigdrop_down02 {
            left: -27px;
            padding: 0;
            width: 120px;
        }
    </style>
</head>
<body>
<div class="navig">
    <div class="navig_header">
        <div class="naviglogo"><img src="${path}/images/${logo}"></div>
        <ul class="ulnavheaderright">
            <c:if test="${!empty userId && username ne 'admin'}">
                <li class="navli01 dropdown">
                    <a href="#" data-toggle="dropdown">
                        <i class="icon-bell-alt" style=""></i>
                        <span class="badge" id="message_badge">0</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <div class="dropdown-menu navigdrop_down">
                        <p class="mod_zt"></p>
                        <ul class="dropdown-navbell" id="messages">
                        </ul>
                    </div>
                </li>
            </c:if>
            <shiro:hasPermission name="adminticekt:ticketservice ">
                <li class="navli03 dropdown">
                    <a href="#" data-toggle="dropdown">
                        <span class="username">工单服务</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <div class="dropdown-menu navigdrop_down03">
                        <p class="mod_zt02"></p>
                        <ul class="dropdown-navbell02">
                            <shiro:hasPermission name="adminticket:ticket_createIndex">
                                <li>
                                    <a style="" href="${path}/sys/admin/ticket/createIndex?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                                        <i class="icon-pencil"></i> 提交工单
                                    </a>
                                </li>
                                <li>
                                    <a style="" href="${path}/sys/admin/ticket/mine?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                                        <i class="icon-folder-open"></i> 我的工单</a>
                                </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="adminticket:ticket_customadmin">
                                <li>
                                    <a style="" href="${path}/sys/admin/ticket/custom/admin/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                                        <i class="icon-pencil"></i> 工单列表
                                    </a>
                                </li>
                            </shiro:hasPermission>
                        </ul>
                    </div>
                </li>
            </shiro:hasPermission>
            <c:if test="${!empty tenant.id}">
                <li class="navli02 dropdown">
                    <a href="#" data-toggle="dropdown">
                        <img alt="" style=" height:29px; width:29px; border-radius:50%;"
                             src="${path}/sys/tenant/user/headimg/${userId}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"/>
                        <span class="username"><%=username%></span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <div class="dropdown-menu navigdrop_down02">
                        <p class="mod_zt02"></p>
                        <ul class="dropdown-navbell02">
                            <li><a href="<%=path%>/sys/tenant/user/profile?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"><i class="icon-user"></i> 个人中心</a></li>
                            <li class="external"><a href="<%=path%>/logout"><i class="icon-off"></i> 退出</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>
        </ul>


    </div>
    <div class="navig_contact">
        <a class="nav_a" href="#?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
            <dl class="dlnavigcont">
                <dt><img src="${path}/images/templateImage/navIco01.png"></dt>
                <dd>
                    <h4>辅助决策APP管理</h4>
                    <p class="navp01">为领导辅助决策APP提供完整的数据管理以及上会投票统计等功能。</p>
                </dd>
            </dl>
        </a>
        <%--<a class="nav_a" href="${path}/cloudSecurity/dashboard?_show=true&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">--%>
            <%--<dl class="dlnavigcont">--%>
                <%--<dt><img src="${path}/images/templateImage/navIco02.png"></dt>--%>
                <%--<dd>--%>
                    <%--<h4>云安全</h4>--%>
                    <%--<p class="navp01">多层的安全检测工序对系统系统进行深度检测并提供专业的解决方案，您身边的安全专家。</p>--%>
                <%--</dd>--%>
            <%--</dl>--%>
        <%--</a>--%>
        <%--<a class="nav_a" href="${path}/dataAnalyse/dashboard?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">--%>
            <%--<dl class="dlnavigcont">--%>
                <%--<dt><img src="${path}/images/templateImage/navIco04.png"></dt>--%>
                <%--<dd>--%>
                    <%--<h4>云数据分析</h4>--%>
                    <%--<p class="navp01">针对安全行为等及时记录、分析与合规性审计，形成告警及安全提示。</p>--%>
                <%--</dd>--%>
            <%--</dl>--%>
        <%--</a>--%>
        <%--<a class="nav_a" href="${path}${isSaas=='1'?'/sacm/host/ci/list':'/sacm/index'}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">--%>
            <%--<dl class="dlnavigcont">--%>
                <%--<dt><img src="${path}/images/templateImage/navIco05.png"></dt>--%>
                <%--<dd>--%>
                    <%--<h4>资产管理</h4>--%>
                    <%--<p class="navp01">“一键任务化”进行资产统计与管理，让你的资产一目了然。</p>--%>
                <%--</dd>--%>
            <%--</dl>--%>
        <%--</a>--%>
    </div>
</div>
</body>
<script src="${path}/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${path}/js/app.js" type="text/javascript"></script>
<script>
    $(function () {
        getMessage();
        autoFixed();
        //当浏览器大小改变的时候,要重新计算
        $(window).resize(function () {
            autoFixed();
        });
        setInterval(getMessage, 30000);
    })
    function autoFixed() {
        //定义内容区域的高度 var mainHeight = $(window).height()-$(".navbar-inner").outerHeight()
        var mainHeight = $(window).height();
        $('.navig').height(mainHeight);
    }

    function getMessage() {
        var messages = $("#messages");
        var message_badge = $("#message_badge");

        //发送post请求
        localPost("${path}/sys/tenant/message/pending", null,
                function (data) {
                    messages.empty();
                    message_badge.text(data.count);
                    $("#message01").text(data.count);
                    if (!data.count) {
                        data.count = 0;
                    }
                    var start = "<li><p>您有" + data.count
                            + "条新消息</p></li>";
                    var conent = "";
                    var message = data.message;
                    for (var i in message) {
                        conent += "<li><a style=\"white-space: inherit;\" href='${path }/sys/tenant/message/messages?type="
                                + message[i].type
                                + "&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}'><i class=\"icon-warning-sign\"></i>"
                                + message[i].title + "</a></li>";
                    }

                    var end = "<li class=\"external\"><a href=\"${path }/sys/tenant/message/messages?type=all&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}\">查看所有消息<i class=\"m-icon-swapright\"></i></a></li> ";
                    messages.append(start + conent + end);
                }, "json", {"OWASP_CSRFTOKEN" : "${sessionScope.OWASP_CSRFTOKEN}"});
    }
</script>
</body>
</html>
