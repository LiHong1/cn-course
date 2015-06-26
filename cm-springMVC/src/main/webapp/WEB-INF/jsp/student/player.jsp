<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>视频</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckplayer/offlights.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckplayer/ckplayer.js" charset="utf-8"></script>
</head>
<body>
          <div id="a1" style="margin-top:50px">${file}</div>
<script type="text/javascript">
    var flashvars={
        f:'${pageContext.request.contextPath}/${file}',
        c:0,
        b:1
        };
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    CKobject.embedSWF('${pageContext.request.contextPath}/resources/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
    /*
    CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
    下面三行是调用html5播放器用到的
    */
    var video=['http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4','http://www.ckplayer.com/webm/0.webm->video/webm','http://www.ckplayer.com/webm/0.ogv->video/ogg'];
    var support=['iPad','iPhone','ios','android+false','msie10+false'];
    CKobject.embedHTML5('a1','ckplayer_a1',600,400,video,flashvars,support);
    
    var box = new LightBox();
    function closelights(){//关灯
        box.Show();
        CKobject._K_('a1').style.width='940px';
        CKobject._K_('a1').style.height='550px';
        CKobject.getObjectById('ckplayer_a1').width=940;
        CKobject.getObjectById('ckplayer_a1').height=550;
    }
    function openlights(){//开灯
        box.Close();
        CKobject._K_('a1').style.width='600px';
        CKobject._K_('a1').style.height='400px';
        CKobject.getObjectById('ckplayer_a1').width=600;
        CKobject.getObjectById('ckplayer_a1').height=400;
    }
  </script>
</body>
</html>
