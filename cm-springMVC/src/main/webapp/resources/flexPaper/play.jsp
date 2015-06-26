<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>FlexPaper</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width"/>
    <style type="text/css" media="screen">
        html, body {
            height: 100%;
        }

        body {
            padding: 0;
            overflow: auto;
        }

    </style>

    <link rel="stylesheet" type="text/css" href="css/flexpaper.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/flexpaper.js"></script>
    <script type="text/javascript" src="js/flexpaper_handlers.js"></script>
</head>
<body>
<div style="width:770px;margin:auto">
    <div id="documentViewer" class="flexpaper_viewer" style="width:770px;height:500px;margin:auto"></div>
    <span onclick="window.history.back()" style="cursor: pointer;">返回</span>
</div>
<script type="text/javascript">
    var startSWF = "1.swf";
    $('#documentViewer').FlexPaperViewer(
            {
                config: {
                    SWFFile: startSWF,
                    Scale: 0.6,
                    ZoomTransition: 'easeOut',
                    ZoomTime: 0.5,
                    ZoomInterval: 0.2,
                    FitPageOnLoad: true,
                    FitWidthOnLoad: false,
                    FullScreenAsMaxWindow: false,
                    ProgressiveLoading: false,
                    MinZoomSize: 0.2,
                    MaxZoomSize: 5,
                    SearchMatchAll: false,
                    InitViewMode: 'Portrait',
                    RenderingOrder: 'flash',
                    StartAtPage: '',

                    ViewModeToolsVisible: true,
                    ZoomToolsVisible: true,
                    NavToolsVisible: true,
                    CursorToolsVisible: true,
                    SearchToolsVisible: true,
                    //WMode : 'window',
                    localeChain: 'zh_CN'
                }
            }
    );
</script>

<script type="text/javascript">
    /** var url = window.location.href.toString();

     if(location.length==0){
        url = document.URL.toString();
    }

     if(url.indexOf("file:")>=0){
        //jQuery('#documentViewer').html("<div style='position:relative;background-color:#ffffff;width:420px;font-family:Verdana;font-size:10pt;left:22%;top:20%;padding: 10px 10px 10px 10px;border-style:solid;border-width:5px;'><img src='http://flexpaper.devaldi.com/resources/warning_icon.gif'>&nbsp;<b>You are trying to use FlexPaper from a local directory.</b><br/><br/> FlexPaper needs to be copied to a web server before the viewer can display its document properlty.<br/><br/>Please copy the FlexPaper files to a web server and access the viewer through a http:// url.</div>");
    }**/
</script>
</body>
</html>
