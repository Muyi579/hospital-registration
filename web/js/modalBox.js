$(document).ready(function () {
    clearForm();
    /*模态框显示*/
    $("#registerBtn").click(function () {
        $("#myModal").css('display', 'block');
    });
    /*模态框关闭*/
    $("#closeBtn").click(function () {
        $("#myModal").css('display', 'none');
        clearForm();
    });

    /*模态框关闭*/
    $("#closeBtnDetail").click(function () {
        $("#myModalDetail").css('display', 'none');
        clearForm();
    });

    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    $('body').click(function (event) {
        if (event.target.id === 'myModal') {
            $("#myModal").css('display', 'none');
            clearForm();
        } else if (event.target.id === 'myModalDetail') {
            $("#myModalDetail").css('display', 'none');
            clearForm();
        }
    });
    // 预约
    $("#submitBtn").click(function () {
        let departmentId = $('select[name="department"]').val();
        if (departmentId === '0' || !departmentId) {
            alert('请选择科室');
            return;
        }
        let doctorId = $('select[name="doctor"]').val();
        if (doctorId === '0' || !doctorId) {
            alert('请选择医生');
            return;
        }
        let registerTime = $('input[name="registerTime"]').val();
        if (!registerTime) {
            alert('请选择预约时间');
            return;
        }
        // 登陆人
        let user = JSON.parse(localStorage.getItem('user'));
        $.ajax({
            async: true,
            url: 'registerOrderServlet',
            type: 'post',
            data: {
                op: 'insert',
                departmentId: departmentId,
                doctorId: doctorId,
                registerTime: registerTime,
                userId: user.id
            },
            success: function (data) {
                data = JSON.parse(data);
                alert(data.msg);
                if (data.code === 200) {
                    $("#myModal").css('display', 'none');
                    clearForm();
                    $('#searchBtn').click();
                }
            },
            error: function (xhr, status, error) {
                alert("异步请求失败!");
            }
        })
    });

    // 清空表单
    function clearForm() {
        $('#registerForm').find('input').val('');
        $('#registerForm').find('select').val('');
    }

    // 渲染医院下拉框
    renderHospitalSelect();

    function renderHospitalSelect() {
        let hospital = localStorage.getItem('hospital') ? JSON.parse(localStorage.getItem('hospital')) : [];
        let html = '<option value="0">请选择医院</option>';
        for (let i = 0; i < hospital.length; i++) {
            html += '<option value="' + hospital[i].id + '">' + hospital[i].hospitalName + '</option>';
        }
        $('select[name="hospital"]').html(html);
    }

    // 医院下拉框绑定change事件
    $('select[name="hospital"]').change(function () {
        renderDepartmentTypeSelect();
    });

    // 渲染科室下拉框
    function renderDepartmentTypeSelect() {
        let hospitalId = $('select[name="hospital"]').val();
        if (hospitalId === '0') {
            $('select[name="departmentType"]').html('');
            return;
        }
        let departmentType = localStorage.getItem('departmentType') ? JSON.parse(localStorage.getItem('departmentType')) : [];
        if (departmentType.length === 0) {
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
                        departmentType = data.data[0].departmentTypeDtoList;
                        let department = {};
                        $.each(departmentType, function (index, item) {
                            department['type_' + item.id] = item.departmentDtoList;
                        });
                        localStorage.setItem('department', JSON.stringify(department));
                    } else {
                        $('select[name="department"]').html('');
                    }
                },
                error: function (xhr, status, error) {
                    alert("异步请求失败!");
                }
            });
        }
        let html = '<option value="0">请选择科室</option>';
        for (let i = 0; i < departmentType.length; i++) {
            html += '<option value="' + departmentType[i].departmentTypeId + '">' + departmentType[i].departmentTypeName + '</option>';
        }
        $('select[name="departmentType"]').html(html);
    }

    // 科室下拉框绑定change事件
    $('select[name="departmentType"]').change(function () {
        renderDepartmentSelect();
    });

    // 渲染科目下拉框
    function renderDepartmentSelect() {
        let departmentTypeId = $('select[name="departmentType"]').val();
        if (departmentTypeId === '0') {
            $('select[name="department"]').html('');
            return;
        }
        let department = localStorage.getItem('department') ? JSON.parse(localStorage.getItem('department')) : [];
        let html = '<option value="0">请选择科目</option>';
        for (let i = 0; i < department['type_' + departmentTypeId].length; i++) {
            html += '<option value="' + department['type_' + departmentTypeId][i].departmentId + '">' + department['type_' + departmentTypeId][i].departmentName + '</option>';
        }
        $('select[name="department"]').html(html);
    }

    // 科目下拉框绑定change事件
    $('select[name="department"]').change(function () {
        renderDoctorSelect();
    })

    // 渲染医生下拉框
    function renderDoctorSelect() {
        let departmentId = $('select[name="department"]').val();
        if (departmentId === '0') {
            $('select[name="doctor"]').html('');
            return;
        }
        console.log($('select[name="department"]'));
        $.ajax({
            async: true,
            url: 'doctorServlet',
            type: 'post',
            data: {
                op: 'findByDepartmentId',
                departmentId: departmentId
            },
            dataType: 'json',
            success: function (data) {
                if (data.code === 200) {
                    let html = '<option value="0">请选择医生</option>';
                    for (let i = 0; i < data.data.length; i++) {
                        html += '<option value="' + data.data[i].id + '">' + data.data[i].doctorName + '</option>';
                    }
                    $('select[name="doctor"]').html(html);
                }
            }
        })
    }

    dateFilter();

    // 日期不能选择第二天的日期
    function dateFilter() {
        let date = new Date();
        date.setDate(date.getDate() + 1);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hh = date.getHours();
        let mm = date.getMinutes();

        if (month < 10) {
            month = '0' + month;
        }
        if (day < 10) {
            day = '0' + day;
        }
        if (hh < 10) {
            hh = '0' + hh;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        let dateStr = year + '-' + month + '-' + day + 'T' + hh + ':' + mm;
        $('input[name="registerTime"]').attr('min', dateStr);
    }
});