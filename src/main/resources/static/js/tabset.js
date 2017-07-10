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
                $(".sub-nav").find("li").css('background-color','#faf5f5');
                $(".sub-nav").find("li").css('color','#b3b3b3');
                $(this).css('background-color','#30ccff');
                $(this).css('color','#FFFFFF');
            }) ;
        });
    }
    else {
        $(function () {
            $(".sub-ft").click(function () {
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