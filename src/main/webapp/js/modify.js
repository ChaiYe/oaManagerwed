function change(obj) {
    if ($(obj).text() == "修改") {
        $(obj).parents("tr").find("input").removeAttr("readonly").removeClass("inputs");
        $(obj).text("取消");
    }
    else if ($(obj).text() == "取消") {

        $(obj).parents("tr").find("input").attr("readonly", "readonly").addClass("inputs");
        $(obj).text("修改");
    }
}
