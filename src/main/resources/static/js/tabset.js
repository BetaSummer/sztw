/**
 * Created by Administrator on 2017/4/27.
 */



$(function () {
    var width = window.screen.width;
    if (width<768){
        $(function () {
            $(".sub-ft").click(function () {
                $(".sub-ft").toggle();
                $(this).toggle();
                $(this).next().toggle(500);
            });
            $(".sub-nav").find("li").click(function () {
                $(".sub-nav").find("li").css('background-color','#47a899');
                $(".sub-nav").find("li").css('color','#ffffff');
                $(this).css('background-color','#0FDEBD');
            }) ;
        });
    }
    else {
        $(function () {
            $(".sub-ft").click(function () {
                $(".sub-ft").css("background-color","#f7f7f7");
                $(this).css("background-color","#dbe6ec");
                $(this).next().toggle(500);
            });
        });
        $(function () {
            $(".sub-nav").find("li").click(function () {
                $(".sub-nav").find("li").removeClass("on");
                $(this).addClass("on");
                $(".content-nav").html($(this).parent().parent().prev().html()+"/ "+$(this).html());
            }) ;
        });
    }
})