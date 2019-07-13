$(function () {
    //修改个人信息
    $('#updateUserNameButton').click(function () {
        //$("#updateUserNameButton").attr("disabled",true);
        var userName = $('#loginUserName').val();
        var nickName = $('#nickName').val();
        if (validUserNameForUpdate(userName, nickName)) {
            //ajax提交数据
            var params = $("#userNameForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/name",
                data: params,
                success: function (r) {
                    console.log(r);
                    if (r == 'success') {
                        layer.msg('修改成功',{time:1000})
                    } else {
                        layer.msg('修改失败',{time:1000})
                    }
                }
            });
        }
    });
    //修改密码
    $('#updatePasswordButton').click(function () {
        //$("#updatePasswordButton").attr("disabled",true);
        var originalPassword = $('#originalPassword').val();
        var newPassword = $('#newPassword').val();
        if (validPasswordForUpdate(originalPassword, newPassword)) {
            var params = $("#userPasswordForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/password",
                data: params,
                success: function (r) {
                    console.log(r);
                    if (r == 'success') {
                        layer.msg('修改成功',{time:1000},function() {
                            window.location.href = "/admin/login";
                        })
                    } else {
                        layer.msg('修改失败',{time:1000})
                    }
                }
            });
        }
    });
})

/**
 * 名称验证
 */
function validUserNameForUpdate(userName, nickName) {
    if (isNull(userName) || userName.trim().length < 1) {
        layer.msg('请输入登陆名称！',{time:1000},function() {
            return false;
        })
    }
    if (isNull(nickName) || nickName.trim().length < 1) {
        layer.msg('昵称不能为空！',{time:1000},function() {
            return false;
        })
    }
    if (!validUserName(userName)) {
        layer.msg('请输入符合规范的登录名！',{time:1000},function() {
            return false;
        })
    }
    if (!validCN_ENString2_18(nickName)) {
        layer.msg('请输入符合规范的昵称！',{time:1000},function() {
            return false;
        })
    }
    return true;
}

/**
 * 密码验证
 */
function validPasswordForUpdate(originalPassword, newPassword) {
    if (isNull(originalPassword) || originalPassword.trim().length < 1) {
        layer.msg('请输入原密码！',{time:1000},function() {
            return false;
        })
        return false;
    }
    if (isNull(newPassword) || newPassword.trim().length < 1) {
        layer.msg('新密码不能为空！',{time:1000},function() {
            return false;
        })
        return false;
    }
    return true;
}
