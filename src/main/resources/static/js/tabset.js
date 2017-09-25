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
                if($(this).hasClass('fton')){
                    $(this).removeClass('fton').next().hide(500);
                    $(this).find('div').eq(2).css("transform","rotate(360deg)");
                }
                else{
                    $(this).addClass('fton').next().show(500);
                    $(this).find('div').eq(2).css("transform","rotate(180deg)");
                }
            });
        });
        $(function () {
            $(".sub-nav").find("li").click(function () {
                $(".sub-nav").find("li").removeClass("on");
                $(this).addClass("on");
                $(".content-nav").html($(this).parent().parent().prev().attr('name')+"/ "+$(this).attr('name'));
            }) ;
        });
    }
})