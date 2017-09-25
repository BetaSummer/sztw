
/*****************************************************/
/*  状态管理 */
$(function () {
    $(".formlist-label").each(function () {
        if($(this).html()=="审核中"){
            $(this).parent().addClass("radio1");
        }
        else if($(this).html()=="审核通过"){
            $(this).parent().addClass("radio2");
        }
        else if($(this).html()=="审核未通过"){
            $(this).parent().addClass("radio3");
        }
    }) ;
    $(".formlist-all").click(function () {
        $("button").removeClass("on");
        $(this).addClass("on");
        $(".formlist-ul").find("li").show();
    });
    $(".formlist-radio1").click(function () {
        $("button").removeClass("on");
        $(this).addClass("on");
        $(".formlist-ul").find("li").each(function () {
            if($(this).hasClass("radio1")){
                $(this).show();
            }
            else {
                $(this).hide();
            }
        }) ;
    });
    $(".formlist-radio2").click(function () {
        $("button").removeClass("on");
        $(this).addClass("on");
        $(".formlist-ul").find("li").each(function () {
            if($(this).hasClass("radio2")){
                $(this).show();
            }
            else {
                $(this).hide();
            }
        }) ;
    });
    $(".formlist-radio3").click(function () {
        $("button").removeClass("on");
        $(this).addClass("on");
        $(".formlist-ul").find("li").each(function () {
            if($(this).hasClass("radio3")){
                $(this).show();
            }
            else {
                $(this).hide();
            }
        }) ;
    });
});
/*****************************************************/