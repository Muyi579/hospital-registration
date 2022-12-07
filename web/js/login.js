$(document).ready(function () {
    // 登录注册页面切换
    $('.item').first().children('div').click(function () {
        if ($(this).html() === '登录') {
            window.location.href = 'login.html';
        } else {
            window.location.href = 'register.html';
        }
    });

    // 登录
    $('#login').click(function () {
        login();
    });

    // 回车登录
    $(document).keydown(function (event) {
        if (event.keyCode === 13) {
            login();
        }
    });

    function login() {
        let userName = $('#userName').val();
        let password = $('#password').val();
        if (userName === '' || password === '') {
            alert('用户名或密码不能为空！');
            return;
        }
        $.ajax({
            async: true,
            url: 'loginServlet',
            type: 'post',
            data: {
                userName: userName,
                password: password,
                op: 'login'
            },
            dataType: 'json',
            success: function (data) {
                alert(data.msg);
                if (data.code === 200) {
                    localStorage.setItem('user', JSON.stringify(data.data));
                    window.location.href = 'index.html';
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        });
    }

    // 手机号正则校验
    $('#phone').blur(function () {
        let phone = $('#phone').val();
        if (phone === '') return;
        let reg = /^1[3456789]\d{9}$/;
        if (!reg.test(phone)) {
            alert('手机号格式错误！');
            $('#phone').val("");
        }
    });

    // 密码正则校验 英文与数字结合大于6位
    $('#password').blur(function () {
        let password = $('#password').val();
        if (password === '') return;
        let reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
        if (!reg.test(password)) {
            alert('密码格式错误！需包含英文与数字，大于6位');
            $('#password').val("");
        }
    });

    // 注册
    $('#register').click(function () {
        let userName = $('#userName').val();
        let phone = $('#phone').val();
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();
        if (phone === '' || password === '' || confirmPassword === '' || userName === '') {
            alert('用户名手机号或密码不能为空！');
            return;
        }
        if (password !== confirmPassword) {
            alert('两次密码不一致！');
            return;
        }
        $.ajax({
            async: true,
            url: 'loginServlet',
            type: 'post',
            data: {
                userName: userName,
                phone: phone,
                password: password,
                op: 'register'
            },
            dataType: 'json',
            success: function (data) {
                alert(data.msg);
                if (data.code === 200) {
                    window.location.href = 'login.html';
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        });
    });

});