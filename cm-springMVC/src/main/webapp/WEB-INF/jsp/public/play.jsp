<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>FlexPaper</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/flexPaper/css/flexpaper.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/flexPaper/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/flexPaper/js/flexpaper.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/flexPaper/js/flexpaper_handlers.js"></script>
</head>
<body>

        <div style="width:770px;margin:auto">
        <div id="documentViewer" class="flexpaper_viewer" style="width:770px;height:500px;margin:auto"></div>
        <span onclick="window.history.back()" style="cursor: pointer;">返回</span>
        </div>
<script type="text/javascript">
    var startSWF="${pageContext.request.contextPath}/${file}";
    $(function(){
        $('#documentViewer').FlexPaperViewer(
                { config : {
                    SWFFile : startSWF,
                    Scale : 0.6,
                    ZoomTransition : 'easeOut',
                    ZoomTime : 0.5,
                    ZoomInterval : 0.2,
                    FitPageOnLoad : true,
                    FitWidthOnLoad : false,
                    FullScreenAsMaxWindow : false,
                    ProgressiveLoading : false,
                    MinZoomSize : 0.2,
                    MaxZoomSize : 5,
                    SearchMatchAll : false,
                    InitViewMode : 'Portrait',
                    RenderingOrder : 'flash',
                    StartAtPage : '',
                    jsDirectory : '${pageContext.request.contextPath}/resources/flexPaper/flexPaper/js/',
                    ViewModeToolsVisible : true,
                    ZoomToolsVisible : true,
                    NavToolsVisible : true,
                    CursorToolsVisible : true,
                    SearchToolsVisible : true,
                    //WMode : 'window',
                    localeChain: 'zh_CN'
                }}
        );
    });
</script>

</body>
</html>
