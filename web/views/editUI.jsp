<%--
  Created by IntelliJ IDEA.
  User: hongjian.chen
  Date: 2017/3/30
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Page</title>
    <script type="text/javascript">
        var XMLHttpReq;
        //创建XMLHttpRequest对象
        function createXMLHttpRequest() {
            if (window.XMLHttpRequest) { //Mozilla 浏览器
                XMLHttpReq = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) { // IE浏览器
                try {
                    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                    }
                }
            }
        }
        //发送请求函数
        function sendRequest(flag) {
            createXMLHttpRequest();
            var key = document.getElementById("key").value;
            var value = document.getElementById("value").value;
            if (flag == "1") {
                var url = "setString.do?key=" + key + "+&value=" + value;
            }
            if (flag == "2") {
                var url = "append.do?key=" + key + "+&value=" + value;
            }
            XMLHttpReq.open("GET", url, true);
            XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
            XMLHttpReq.send(null);  // 发送请求
        }
        // 处理返回信息函数
        function processResponse() {
            if (XMLHttpReq.readyState == 4) { // 判断对象状态
                if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
                    alert("test is ok");
                    window.location.href = "views/ok.jsp";
                } else { //页面不正常
                    window.alert("您所请求的页面有异常。" + XMLHttpReq.status);
                }
            }
        }
    </script>
</head>
<body>
springMVC实例！<span style="color: green">${message}<br></span>
<form method="post" action="setString.do">
    key：<input id="key" name="key" type="text"><br>
    value：<input id="value" name="value" type="text"><br>
    <button onclick=sendRequest("1")>新键</button>&nbsp;
    <button onclick=sendRequest("2")>追加</button>
</form>
</body>
</html>
