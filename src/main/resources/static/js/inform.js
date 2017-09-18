$(function () {
    $('.news-li:odd').css('background','rgba(255,255,255,0)');
    $('.news-li:even').css('background','rgba(255,255,255,0.3)');

    var $width = window.screen.width;
    if($width<768){
        var li = $("li");
        $(".inform").height((li.length-1)*61+29);
    }
});

