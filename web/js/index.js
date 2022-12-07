$(document).ready(function () {
    // 获取缓存中用户信息
    if (localStorage.getItem('user') === null) {
        window.location.href = 'login.html';
    }
    let user = JSON.parse(localStorage.getItem('user'));
    $('#loginInfo').html('登陆人：' + user.userName);

    // 退出登录
    $('#logout').click(function () {
        localStorage.removeItem('user');
        window.location.href = 'login.html';
    });

    query();
    // 获取所有医院 渲染下拉列表
    $.ajax({
        async: true,
        url: 'hospitalServlet',
        type: 'post',
        data: {
            op: 'findAll'
        },
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                let html = '<option value="0">请选择医院</option>';
                let hospitals = [];
                for (let i = 0; i < data.data.length; i++) {
                    hospitals.push({
                        id: data.data[i].id,
                        hospitalName: data.data[i].hospitalName
                    })
                    html += '<option value="' + data.data[i].id + '">' + data.data[i].hospitalName + '</option>';
                }
                $('#hospital').html(html);
                localStorage.setItem('hospital', JSON.stringify(hospitals));
            }
        },
        error: function (xhr, status, error) {
            alert("异步请求失败!");
        }
    });

    // 医院绑定change事件
    $('#hospital').change(function () {
        // 填充科室下拉列表
        let hospitalId = $('#hospital').val();
        if (hospitalId === '0') {
            $('#department').html('');
            return;
        }
        $.ajax({
            async: true,
            url: 'departmentServlet',
            type: 'post',
            data: {
                op: 'findByHospitalId',
                hospitalId: hospitalId
            },
            dataType: 'json',
            success: function (data) {
                if (data.code === 200 && data.data.length > 0) {
                    let typeHtml = '<option value="0">请选择科室</option>';
                    let types = data.data[0].departmentTypeDtoList;
                    let department = {};
                    let departmentType = [];
                    for (let i = 0; i < types.length; i++) {
                        departmentType.push({
                            departmentTypeId: types[i].departmentTypeId,
                            departmentTypeName: types[i].departmentTypeName
                        })
                        department['type_' + types[i].departmentTypeId] = types[i].departmentList
                        typeHtml += '<option value="' + types[i].departmentTypeId + '">' + types[i].departmentTypeName + '</option>';
                    }
                    $('#departmentType').html(typeHtml);
                    localStorage.setItem('department', JSON.stringify(department));
                    localStorage.setItem('departmentType', JSON.stringify(departmentType));
                } else {
                    $('#department').html('');
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        });
    });

    // 科室类型绑定change事件
    $('#departmentType').change(function () {
        let departmentTypeId = $('#departmentType').val();
        if (departmentTypeId === '0') {
            $('#department').html('');
            return;
        }
        let department = JSON.parse(localStorage.getItem('department'));
        let html = '<option value="0">请选择科目</option>';
        for (let i = 0; i < department['type_' + departmentTypeId].length; i++) {
            html += '<option value="' + department['type_' + departmentTypeId][i].departmentId + '">' + department['type_' + departmentTypeId][i].departmentName + '</option>';
        }
        $('#department').html(html);
    });

    // 搜索
    $('#searchBtn').click(function () {
        query();
    });

    function query() {
        let hospital = $('#hospital').val();
        let departmentType = $('#departmentType').val();
        let department = $('#department').val();
        let status = $('#status').val();
        let registerTime = $('#registerTime').val();
        let params = {op: 'query'};
        if (hospital && hospital !== '0') {
            params['hospitalId'] = hospital;
        }
        if (departmentType && departmentType !== '0') {
            params['departmentTypeId'] = departmentType;
        }
        if (department && department !== '0') {
            params['departmentId'] = department.replace('type_', '');
        }
        if (status && status !== 'no') {
            params['status'] = status;
        }
        if (registerTime) {
            params['registerTime'] = registerTime;
        }
        $.ajax({
            async: true,
            url: 'registerOrderServlet',
            type: 'post',
            data: params,
            dataType: 'json',
            success: function (data) {
                if (data.code === 200) {
                    $('#tableBody').empty();
                    for (let i = 0; i < data.data.length; i++) {
                        let d = data.data[i];
                        let trHtml = '<tr><td>' + d.hospitalName + '</td><td>' + d.departmentTypeName + '</td>' +
                            '<td>' + d.departmentName + '</td><td>' + d.doctorName + '</td><td>' + d.userName + '</td>' +
                            '<td>' + d.registerTime.substring(0, d.registerTime.lastIndexOf(":")) + '</td><td>' + d.status + '</td>';
                        if (d.status === '未就诊') {
                            // <a class="edit" style="margin-left: 10px" data-registerOrderId=\'' + d.id + '\'>编辑</a>
                            trHtml += '<td><a class="cancel" data-registerOrderId=\'' + d.id + '\'>取消预约</a><a class="finish" style="margin-left: 10px" data-registerOrderId=\'' + d.id + '\'>完成就诊</a>';
                        } else {
                            // <a style="margin-left: 10px; color: gray;  cursor:no-drop" disabled="true">编辑</a>
                            trHtml += '<td><a disabled="true" style="color: gray; cursor:no-drop">取消预约</a><a style="margin-left: 10px; color: gray; cursor:no-drop" disabled="true">完成就诊</a>';
                        }
                        trHtml += '<a class="detail" style="margin-left: 10px;" data-registerOrderId=\'' + d.id + '\'>详情</a></td></tr>';
                        $('#tableBody').append(trHtml);
                    }
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        })
    }

    /*模态框显示*/
    $(document).on('click', '.detail', function () {
        $("#myModalDetail").css('display', 'block');
        // 获取预约单id渲染数据
        let registerOrderId = $(this).attr('data-registerOrderId');
        $.ajax({
            async: true,
            url: 'registerOrderServlet',
            type: 'post',
            data: {
                op: 'queryDetail',
                id: registerOrderId
            },
            dataType: 'json',
            success: function (data) {
                if (data.code === 200) {
                    let registerOrder = data.data;
                    $('input[name="hospital"]').val(registerOrder.hospitalName);
                    $('input[name="departmentType"]').val(registerOrder.departmentTypeName);
                    $('input[name="department"]').val(registerOrder.departmentName);
                    $('input[name="doctor"]').val(registerOrder.doctorName);
                    $('input[name="registerTime"]').val(registerOrder.registerTime.substring(0, registerOrder.registerTime.lastIndexOf(":")));
                    $('input[name="userName"]').val(registerOrder.userName);
                    $('input[name="status"]').val(registerOrder.status);
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        });
    });

    // 重置
    $('#reset').click(function () {
        $('#hospital').val('0');
        $('#departmentType').val('0');
        $('#department').val('0');
        $('#status').val('no');
        $('#registerTime').val('');
    });

    // 取消预约
    $(document).on("click", ".cancel", function () {
        if (window.confirm("是否确定取消预约？")) {
            let registerOrderId = $(this).attr('data-registerOrderId');
            $.ajax({
                async: true,
                url: 'registerOrderServlet',
                type: 'post',
                data: {
                    op: 'cancel',
                    id: registerOrderId
                },
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        alert('取消成功');
                        query();
                    } else {
                        alert('取消失败');
                    }
                },
                error: function (xhr, status, error) {
                    alert("异步请求失败!");
                }
            })
        }
    });

    // 完成就诊
    $(document).on("click", ".finish", function () {
        if (window.confirm("是否确定完成就诊？")) {
            let registerOrderId = $(this).attr('data-registerOrderId');
            $.ajax({
                async: true,
                url: 'registerOrderServlet',
                type: 'post',
                data: {
                    op: 'finish',
                    id: registerOrderId
                },
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        alert('完成就诊');
                        query();
                    } else {
                        alert('操作失败');
                    }
                },
                error: function (xhr, status, error) {
                    alert("异步请求失败!");
                }
            })
        }
    });
});