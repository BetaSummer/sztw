/**
 * Created by Administrator on 2017/4/27.
 */
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
