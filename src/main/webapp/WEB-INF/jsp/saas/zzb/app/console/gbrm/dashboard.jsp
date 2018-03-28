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
    <title>干部任免管理系统</title>

    <link href="${pageContext.request.contextPath}/css/style_operate.css" rel="stylesheet" />
    <!-- END PAGE LEVEL STYLES -->
    <style type="text/css">
        .CadreSuper{ padding:0 30px;}
        .CadreSuper h2{ text-align:center; font-weight:bold; font-size:24px;}
        .CadreSjx{ font-size:14px; color:#555; line-height:220%;}
        .CadreSjx a.term{ color:#0376d8; font-size:16px; font-weight:bold; text-decoration: underline !important; margin:0 5px;}

        .ulCadreSuperlist{ margin-top:50px; overflow:hidden;}
        .ulCadreSuperlist li{ width:20%; float:left; text-align:center; font-size:16px;}
        .ulCadreSuperlist li img{ width:102px; height:102px; margin-top:51px;}
        .ulCadreSuperlist li p{ height:46px; line-height:46px;}
        .ulCadreSuperlist li:hover a{ color:#0376d8;}


    </style>
</head>
<body>

        <!--干部监督信息系统-->
        <div class="CadreSuper">
            <h2>干部任免管理系统</h2>
            <div class="CadreSjx">
                <p>“干部选拔任用工作记实监督系统”，对提高选人用人公信度、建设高素质干部队伍发挥了重要作用。</p>
                系统包含干部选拔任用全过程监控子系统、干部选拔任用过程管理子系统、干部选拔任用决策分析子系统，共<a href="###" class="term">126</a>项功能模块、<a href="###" class="term">26</a>条硬性标准、<a href="###" class="term">600</a>余个数据项，内容涉及干部选拔任用的动议、初始提名、民主推荐、有关事项报告、酝酿、讨论决定、公示、任职等每一个环节，实时监督、超前预防，构建了全方位、立体化、大跨度的监督信息网和上级监督与下级监督相结合的联动机制。有助于保障基层选拔任用工作规范、可信，提高选拔任用条例知晓率。实现了由事后监督向实时监督，由实地监督向网络监督，由上级监督向上下合力监督的转变。
                <p>全程记实、环环把关：通过管理端对用户端提交信息的即时审查、汇总和统计分析，前移监督关口，提高监督效能。为选人用人出现问题时实施责任追究提供了原始的证据保全，做到环环有把关、事事有负责、层层有监控，真正实现对干部选拔任用全周期的监督。</p>
                <p>强化选人用人责任意识：提高干部选拔任用监督效能，增强各级领导干部选人用人的责任意识，实现事前预防，事中纠偏，事后究责，防止了应报不报、漏报伪报情况发生。从制度上杜绝了“不按条例按惯例，不按规定按惯性”、“个人说了算”或“暗箱操作”现象，从程序上保证了干部选拔任用各项政策法规的贯彻落实。</p>
            </div>
            <ul class="ulCadreSuperlist">
                <li>
                    <a href="${path}/zzb/app/console/gbrm/list">
                        <img src="${path}/images/templateImage/YMico01.png">
                        <p>工作方案</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/app/console/gbrm/shcl/list">
                        <img src="${path}/images/templateImage/YMico02.png">
                        <p>上会材料</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/app/console/gbrm/rmtz/list">
                        <img src="${path}/images/templateImage/YMico03.png">
                        <p>任免通知</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/app/console/gbrm/xrjs/list">
                        <img src="${path}/images/templateImage/YMico04.png">
                        <p>任免纪实</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/app/console/gbrm/rmtj/list">
                        <img src="${path}/images/templateImage/YMico05.png">
                        <p>任免统计</p>
                    </a>
                </li>
            </ul>

        </div>
        <!--干部监督信息系统 END-->



<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${path}/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${path}/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${path}/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${path}/js/app.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!--echarts图表-->
<script src="${path}/js/echarts3/echarts.min.js" type="text/javascript" ></script>
<!--数字滚动-->
<script type="text/javascript" src="${path}/js/countUp.min.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();
    });
</script>
</body>
</html>