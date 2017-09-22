/**
 * Created by zhaowq on 2016/8/6.
 */
$(function () {
    $("span.next").click(function () {
        var $parent = $(this).parents("div.v_show");
        var $v_show = $parent.find("div.v_content_list");
        var $v_content =$parent.find("div.v_content");

        var v_width = $v_content.width();
    })
})