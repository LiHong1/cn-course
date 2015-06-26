<%@ page language="java" import="java.io.File,java.io.FileInputStream" pageEncoding="utf-8" %>
<%@ page import="java.io.OutputStream" %>
<%@ page contentType="application/msword;charset=gb2312" %>
<%
    File f = new File("E:/apache-tomcat-8.0.12/webapps/b.doc");
    FileInputStream fin = new FileInputStream(f);
    OutputStream output = response.getOutputStream();
    byte[] buf = new byte[1024];
    int r = 0;
    response.setContentType("application/msword;charset=GB2312");
    while ((r = fin.read(buf, 0, buf.length)) != -1) {
        output.write(buf, 0, r);//response.getOutputStream()
    }
    fin.close();
    output.close();
%>
