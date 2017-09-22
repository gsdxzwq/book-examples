/**
 * Created by zhaowq on 2016/8/23.
 */
$(function () {
    $("#inputSearch").focus(function () {//focus获得焦点
        $(this).addClass("focus");
        if($(this).val()==this.defaultValue){
            $(this).val("");
        }
    }).blur(function () {           //blur失去焦点
        $(this).removeClass("focus");
        if($(this).val()==''){
            $(this).val(this.defaultValue);
        }
    }).keyup(function (e) {          //keyup键盘弹起
        if(e.which==13){
            alert("回车提交表单");
        }
    })
})
