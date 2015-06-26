<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/script/base/jquery.ui.all.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/switch.css">
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switch.js" ></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/script/jquery-1.7.2.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/script/jquery.ui.core.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/script/jquery.ui.widget.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/script/jquery.ui.mouse.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/script/jquery.ui.sortable.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core.js"></script>
      <script type="text/javascript">
    $(function(){
        var path = "${pageContext.request.contextPath}";
        $("tbody").on("click",".update",function(){
            var parent = $(this).parent().parent("td");
            parent.prevAll().each(function(index){

                    var text = $(this).html();
                    if(index != 3){
                        if(index == 1)
                            $(this).html("<input type='input' value='"+text+"' style='width:250px'> ");
                        if(index == 0)
                            $(this).html("<input type='input' value='"+text+"' style='width:120px'> ");
                        else
                            $(this).html("<input type='input' value='"+text+"'>");
                    }
           });
            $(this).parent().html("<img src='"+path+"/resources/style/images/menu/save.png' alt='保存' title='保存' class='save'>");
        });
        $("tbody").on("click",".save",function() {
            var td = $(this).parent().parent();
            var tr = td.parent();
            var name;
            var url;
            var authority;
            tr.children().each(function (index) {
                $(this).html($(this).find("input").val());
            });
            name = tr.find("td:eq(1)").html();
            url = tr.find("td:eq(2)").html();
            authority = tr.find("td:eq(3)").html();
            td.html("<span><img src='" + path + "/resources/style/images/menu/delete.gif' alt='删除' title='删除' class='delete'></span>" +
            "<span><img src='" + path + "/resources/style/images/menu/update.png' alt='修改' title='修改' class='update'></span>");
            if ($(".add").size() == 0)
                addButton($("tbody"));
            var id = tr.attr("id");
            if (id==undefined) {
                var order = tr.parent().children().size();
                $.get("menu/add?name="+name+"&url="+url+"&authority="+authority+"&order="+order,  function (data) {
                    alert(date);
                });
            } else {
                $.get("menu/update", {
                    id: id,
                    name: name,
                    url: url,
                    authority: authority
                }, function (data) {
                    alert(date);
                });
            }
        });

        $("tbody").on("click",".add",function(){
            var tbody= $("tbody");
            var order = tbody.children().last().find("td:eq(0)").html();
            tbody.append("<tr><td>"+(Number(order)+1)+"</td><td><input type='text'></td><td><input type='text' style='width:250px'></td><td><input type='text'></td><td><span><img src='"+path+"/resources/style/images/menu/save.png' alt='保存' title='保存' class='save'></span></td></tr>");
            $(this).parent().html("");

        });
        $("tbody").on("click",".delete",function(){
            var parent = $(this).parent().parent("td").parent();
            var id = parent.attr("id");
            $.get("menu/delete/"+id,function(date){

            });
            parent.remove();
            addButton($("tbody"));
        });
        var addButton = function (tbody){
            var tr = tbody.children().last();
            if(tr.size() == 0)
            tbody.append("<tr><td></td><td></td><td></td><td></td><td><span><img src='"+path+"/resources/style/images/menu/add.png' alt='增加' title='增加' class='add'></span></td></tr>");
            else{
                $(".add").parent().remove();
                var td = tr.find("td").last();
                td.append("<span><img src='"+path+"/resources/style/images/menu/add.png' alt='增加' title='增加' class='add'></span>");
            }
        };
        addButton($("tbody"));
        $(".sortable").sortTable({
            path:path
        });
    })
</script>
<style type="text/css">
    tbody img{
        cursor: pointer;
        width: 20px;
        height: 20px;
    }
    #right{
        width:80%;
    }
</style>
</head>
  <body>
     <div id="title">菜单设置</div>
     <table class="sortable">
         <thead>
             <tr>
                 <td width="60px">序号</td>
                 <td width="170px">名称</td>
                 <td width="350px">url</td>
                 <td width="120px">权限</td>
                 <td width="120px">操作</td>
             </tr>
         </thead>
         <tbody>
         <c:forEach items="${menuItems}" var="c" varStatus="state">
         <tr id="${c.id}">
             <td>${state.count}</td>
             <td>${c.name}</td>
             <td>${c.url}</td>
             <td>${c.authority}</td>
             <td class="operate">
                 <span><img src="${pageContext.request.contextPath}/resources/style/images/menu/delete.gif" alt="删除" title="删除" class="delete"></span>
                 <span><img src="${pageContext.request.contextPath}/resources/style/images/menu/update.png" alt="修改" title="修改" class="update"></span>
                 <input type="hidden" value="123">
             </td>
         </tr>
         </c:forEach>
         </tbody>
         <tfoot>
         <tr>
             <td colspan="4" class="rightTd"><a id="beginOrder" href="#" title="#" class="list_op">开始排序</a>
                 &nbsp;<a id="saveOrder" href="#" title="#" class="list_op">存储排序</a>&nbsp;
             </td>
         </tr>
         </tfoot>
     </table>

  </body>
</html>