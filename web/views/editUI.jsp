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
        //发送请求函数
        function sendRequest(flag) {
            var key = document.getElementById("key").value;
            var value = document.getElementById("value").value;
            var responseData="";
            var XMLHttpReq;
            //创建XMLHttpRequest对象
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
            if (flag == "1") {
//                var url = "setString.do?key=" + key + "+&value=" + value;
                var url = "setString.do";
            }
            if (flag == "2") {
//                var url = "append.do?key=" + key + "+&value=" + value;
                var url = "append.do";
            }
            if (flag == "0") {
                var url = "myFresh.do?t=" + new Date().getTime();
            }
            XMLHttpReq.open("GET", url, true);
            XMLHttpReq.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
            XMLHttpReq.send();  // 发送请求
            XMLHttpReq.onreadystatechange = function () {
                if (XMLHttpReq.readyState == 4) { // 判断对象状态
                    if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
                        responseData = XMLHttpReq.responseText;
                        document.getElementById("times").innerHTML = responseData;
                    }
                }
            };
        }
        setInterval('sendRequest("0")', 3000); //指定1秒刷新一次
        //        setTimeout("location.href='hello.do'",2000)
    </script>
</head>
<body>
springMVC实例！<span id="times" style="color: green">${message}次<br></span>
<form method="post" action="">
    key：<input id="key" name="key" type="text"><br>
    value：<input id="value" name="value" type="text"><br>
    <button onclick=sendRequest("1")>新键</button>
    &nbsp;
    <button onclick=sendRequest("2")>追加</button>
</form>
</body>
</html>
