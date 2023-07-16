<%--
  Created by IntelliJ IDEA.
  User: keepgo
  Date: 2023/6/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>企业人事管理系统</title>
    <%--设置参考路径--%>
    <base href="<%=request.getContextPath() + "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/login.css">
    <script src="jsencrypt-master/bin/jsencrypt.js"></script>
    <title>登陆</title>
</head>
<body>
<main class="main" role="main">
    <div class="container">
        <div class="row">
            <div class="offset-md-3 col-md-6">
                <form id="loginForm" class="form-container" method="POST" action="userServlet">
                    <input type="hidden" name="action" value="login">
                    <h2>企业人事管理系统-登陆</h2>
                    <div class="form-group">
                        <label for="exampleInputName">账号</label>
                        <input name="username" id="username" type="text" class="form-control" id="exampleInputName"
                               value="${requestScope.username}">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword">密码</label>
                        <input name="password" id="password" type="password" class="form-control"
                               id="exampleInputPassword"
                               value="">
                    </div>

                    <div class="form-group">
                        <label for="userType">用户类型</label>
                        <select name="usertype" id="usertype" class="form-control" id="userType">
                            <option value="1" selected="true">管理员</option>
                            <option value="0">员工</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">立即登录</button>
                    </div>
                    <span style="padding-right: 135px; font-size: 18pt;font-weight: bold;float: right;color: gainsboro">
                        ${requestScope.msg}
                    </span>
                </form>

                <input type="hidden" id="encryptedData" name="encryptedData">

            </div>

        </div>
    </div>
</main>

<!--获取公钥-->
<script>
    window.addEventListener('DOMContentLoaded', function () {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/sql_design/rsaServlet?action=getKey', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 在此处处理响应内容
                var parse = JSON.parse(xhr.responseText);
                document.getElementById("encryptedData").innerText =parse.public_key;
            }
        };
        xhr.send();
    });
</script>

<!--表单提交数据加密-->
<script>

    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault(); // 阻止表单的默认提交行为

        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var usertype = document.getElementById("usertype").value;

        var publicKeyString = document.getElementById("encryptedData").innerText;
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(publicKeyString);

        var encryptedUsername = encrypt.encrypt(username);
        var encryptedPassword = encrypt.encrypt(password);
        // var encryptedUsertype = encrypt.encrypt(usertype);

        // //解密
        // var decrypt = new JSEncrypt();
        // var privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJBkQTx5QU4OP94IhcexIN9GUmSSm3ayMGuLDpixxZD7cD4ivn1QXCeyI88Zxewg2Tt0uZa0Yu7RcI86SCksnSPGqvlBS7wM0zWQfZhyK7ISY6c5XsIEoLrs+ljShhnZoIu3Gwld4o8kfAHZ1R98wuHvOZaemRIWwacOHwe3ub5qk1wT7lkHkhrx546HGHarITh5DLhrUqWrdwiYxGbzXn84mgCSFLg4Tt988UDNDyqXuX8uhAuPfmXNBj5y+W51zmmsHa0IwHkx0pakaIe4eC21IUy3Vs2Ugz0zyL6/lKPkFntOLYxzpO3aMk4jawWSpcivZh/YFSdbpTbYyoNWjfAgMBAAECggEBAIfJZtM3Fx7tkeH3jQogh86+XzJmlGmISSiTA/Yb/hagBRxbGGlgZ5nHjjFYuT4KDYxRuyAo917vYsFjOKaQsmGdr5eZJka9FFyLIMVHLnrEqe6ItoxNnzMeo/WKAfdiR8/ZeRumGIb5OXM/pbQh3PvjfkGMfIJu3YMCVFSM+fyOTteE8v1TLHvxeVo4CKnBboy9BEi/EB0wB2KvUtl9mXD3QuJnzRw29u9VGJm0MHILxmqNOb7K3SJ9mwEuLhQAwZdwrv+kiBFUUYAXr2hk3z7dW5ecTjtWKrGFDqqqXC7VAMlWOxk1qOOjpznEITR+eXbdxr5Dg+aVIrMPyaXa4TECgYEAyk2s51tKE0LdvrghiZv7qDlVm1nacQgESXbvQA6b/DxwHaSiEHr2PkXawaQJlU4hYlKF7tJdJgxOoQjLsGkuq3XHI1GcHkNbP8s2Rn8eZtXOgThXmI+L/IWfuRY9qZpqQaqa93tDJSBypuGDlxA0EsrsNmgk3yqcwz/pO6InYokCgYEArWTy0qY1uZHt6Va2qYmhJz3fghOSrjT3SsScQDE/l+qGL4Miu4uvNttXSPCUQEgBRYxQ/YfN9Gp+5hIi2eX5ONEovJlIA0R+neA29V2mbkNuYaS6Ua1VJ8ury/VT+EbXcRFkfxkhc2GWkwcA6E1NKRzHxhLiLQVVP7cDRmjoticCgYBgwe/to7L1LWkDW+vmdi1SE0IHkF0y7IOoY3MgrxwZ8wook2JUAYue0yhCr2NJ/tlmvlOCmyikFwTQbnWFhXkl1qoNEW5a5xpQk6/83bW7t40SLtFujVGF0J9JFgMeCFXD3IZL0a+WpUNQ39FhQgW160o452yjNC1QEVRLCk5OCQKBgGReevDUpWS1xDG9sB6TPGWquyZgjhepMTxmcsv1tgoKB4KZNhG52cK3VN8z450SESpg/sDDS8SfoLUs6l+Xb5wj9qC1WaoKjAB3OVjuzNgm0E5VPETrQM+4Zcm6SND+sNcSaknoEiTn6HFTLINLu86AimNtqt7Ep4QqPAsVzx8hAoGAYd3EzzVuftYOQAk/BS0PZalviMN7r0FNTPrLpdaJxTV83jTVQtc0VPogr4eznY8oZqSedHZJ8QC3+X7N47TqP7SoSaOL6F7vFkGD8lLK8PuMbUpT2E8agSs8xRzn5mPK1bnylzEmwJkbhJZr2rzpoFFSBa+ANFi+iQIkIn36vlQ="
        // decrypt.setPrivateKey(privateKey);
        // var uncrypted = decrypt.decrypt(encryptedUsername);
        // console.log('这是解密后的密文' + uncrypted);
        // 更新表单字段的值
        document.getElementById("username").value = encryptedUsername;
        document.getElementById("password").value = encryptedPassword;
        // document.getElementById("usertype").value = encryptedUsertype;
        document.getElementById("loginForm").submit();

    });

</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>