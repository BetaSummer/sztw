/**
 * Created by x1654 on 2017/7/18.
 */
$(function () {
    $("#news-publish").click(function () {
        var str = $("input:radio[name='type']:checked").val();
        if("公告"===str){
            var data = {
                "title": $("#news-theme").val(),
                "comment": $("#comment").val()
            };
            $.ajax({
                type: "POST",
                url: "/information/publishAnnouncement",
                data: data,
                dataType:"json",
                success: function(r){
                    alert(r.message);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }else if("通知"===str){
            alert("功能还在开发中");
        }
    });

    $("#news-hold").click(function () {
        var str = $("input:radio[name='type']:checked").val();
        if("公告"===str){
            var id = $("#news-id").val();
            var data = {
                "id": ""===id?0:id,
                "title": $("#news-theme").val(),
                "comment": $("#comment").val()
            };
            $.ajax({
                type: "POST",
                url: "/information/saveAnnouncement",
                data: data,
                dataType:"json",
                success: function(r){
                    alert(r.message);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }else if("通知"===str){
            alert("功能还在开发中");
        }
    })
});