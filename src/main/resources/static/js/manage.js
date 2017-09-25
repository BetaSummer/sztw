/**
 * Created by Administrator on 2017/7/19.
 */





//financeB

function writeInformation(index) {
    if(index){
        var trTemp = $('#fin-table tr:eq(' + index + ')');

        $(".manage-alert-title").after("<tr class='manage-alert-in delete-tr'><td>"+trTemp.children('td').eq(1).html()+"</td><td>"+trTemp.children('td').eq(2).html()+"</td><td>"+trTemp.children('td').eq(3).html()+"</td><td> <select> <option value='1'>增加</option> <option value='-1'>减少</option> </select> </td> <td> <select> <option value='1'>预留</option> <option value='2'>自留</option> </select> </td> <td><input type='text' name='moneychange' placeholder='（元）'/></td><td style='display: none;'>"+trTemp.children('td').eq(4).html()+"</td></tr>");

    }
}
$(function () {
    $(".manage-btn").click(function () {
        $(".delete-tr").remove();
        var index = $(this).parent().parent().index();
        writeInformation(index);
        $(".manage-alert").show(200);
    });
    $("#manageAll").click(function(){
        $(".delete-tr").remove();
        $("input[type='checkbox']").each(function(){
            if($(this).is(':checked')&&($(this).prev().html()!="全选")){
                var index = $(this).parent().parent().index();
                writeInformation(index);
            }
        });
        $(".manage-alert").show(200);
    });
});

//userManage
function writeInformation2(index) {
    if(index){
        var trTemp = $('#fin-table tr:eq(' + index + ')');
        $(".manage-alert-title").after("<tr class='manage-alert-in delete-tr'><td style='display: none;'>"+trTemp.children('td').eq(2).html()+"</td><td>"+trTemp.children('td').eq(5).html()+"</td><td>"+trTemp.children('td').eq(4).html()+"</td><td><input type='text' name='moneychange' placeholder='修改密码'/></td><td><input type='text' name='moneychange' placeholder='联系方式' value='"+trTemp.children('td').eq(3).html()+"'/></td> <td><input type='text' name='moneychange' placeholder='邮箱' value='"+trTemp.children('td').eq(1).html()+"'/></td></tr>");

    }
}
$(function () {
    $(".manage-btn2").click(function () {
        $(".delete-tr").remove();
        var index = $(this).parent().parent().index();
        writeInformation2(index);
        $(".manage-alert").show(200);
    });
    $("#manageAll2").click(function(){
        $(".delete-tr").remove();
        $("input[type='checkbox']").each(function(){
            if($(this).is(':checked')&&($(this).prev().html()!="全选")){
                var index = $(this).parent().parent().index();
                writeInformation2(index);
            }
        });
        $(".manage-alert").show(200);
    });
});

//checkAll
$(function () {
    $("#checkall").click(function(){
        if($(this).is(':checked')){
            $("input[name='checkname']").prop('checked', true).parent().parent().addClass("on");
        }else{
            $("input[name='checkname']").prop('checked', false).parent().parent().removeClass("on");
        }
    });
    $("input[name='checkname']").click(function () {
        if($(this).is(':checked')){
            $(this).parent().parent().addClass("on");
        }
        else {
            $(this).parent().parent().removeClass("on");
        }
    });
});




//out
$(function () {
    $(".manage-out").click(function () {
       $(".manage-alert").hide(200);
    });
});

//finance
$(function () {
    $("#finance-btn").click(function () {
        var dataLength = $(".manage-alert-in").length;
        var dataTrArr = $(".manage-alert-in");
        var data = new Array(dataLength-1);
        var dataString ="[";
        var comment = $("#comment").val();
        for(var i = 0;i<dataLength-1;i++){
            var id = dataTrArr[i].children[6].innerHTML;
            var change = dataTrArr[i].children[3].firstElementChild.value;
            var selfReserve = dataTrArr[i].children[4].firstElementChild.value;
            var money = dataTrArr[i].children[5].firstElementChild.value;
            dataString += "{'id':"+id+",'change':"+change+",'selfReserve':"+selfReserve+",'money':"+money+"},";
        }
        dataString =dataString.substring(0,dataString.length-1);
        dataString += "]";
        var dataAll = {
            "data":dataString,
            "comment":comment
        };
        console.log(data);
        $.ajax({
            type: "GET",
            url: "/finance/changClubFinance",
            data: dataAll,
            dataType:"json",
            success: function(r){
                alert(r.message);
                $(".loading").show();
                $(".content-body").load("/finance/financeB",function () {
                    $(".loading").hide();
                }) ;
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });
});
$(function () {
    $("#manageChange").click(function () {
        $(".fb-change").toggle();
    }) ;
});
$(function () {
    $(".fb-confirm").click(function () {
        var i = 0;
        var Arr = new Array();
        var change = $("#fb-select1").val();
        var selfReserve = $("#fb-select2").val();
        var money = $("#fb-num").val();
        $("input[type='checkbox']").each(function(){
            if($(this).is(':checked')&&($(this).prev().html()!="全选")){
                Arr[i]=$(this).parent().next().next().next().next().html();
                i++;
            }
        });
        var dataString ="[";
        for(var j = 0;j<Arr.length;j++){
            var id=Arr[j];
            dataString += "{'id':"+id+",'change':"+change+",'selfReserve':"+selfReserve+",'money':"+money+"},";
        }
        dataString =dataString.substring(0,dataString.length-1);
        dataString += "]";
        var dataAll = {
            "data":dataString,
            "comment":$("#fb-comment").val()
        };
        $.ajax({
            type: "GET",
            url: "/finance/changClubFinance",
            data: dataAll,
            dataType:"json",
            success: function(r){
                alert(r.message);
                $(".loading").show();
                $(".content-body").load("/finance/financeB",function () {
                    $(".loading").hide();
                }) ;
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });
});

$(function () {
    $("#self-btn").click(function () {
        var id = $("#id").text();
        var password = $("#password").val();
        password = password===""?"-1":password;
        var tel = $("#tel").val();
        var eMail = $("#eMail").val();
        var dataString ="[";
        dataString += "{'id':"+id+",'password':'"+password+"','tel':'"+tel+"','eMail':'"+eMail+"'},";
        dataString =dataString.substring(0,dataString.length-1);
        dataString += "]";
        console.log(dataString);
        var data = {
            "data": dataString
        }
        $.ajax({
            type: "GET",
            url: "/manage/updateUserInfo",
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
    });
    $("#user-btn").click(function () {
        var dataLength = $(".manage-alert-in").length;
        var dataTrArr = $(".manage-alert-in");
        var data = new Array(dataLength-1);
        var dataString ="[";
        for(var i = 0;i<dataLength-1;i++){
            var id = dataTrArr[i].children[0].innerHTML;
            var password = dataTrArr[i].children[3].firstElementChild.value;
            password = password===""?"-1":password;
            var tel = dataTrArr[i].children[4].firstElementChild.value;
            var eMail = dataTrArr[i].children[5].firstElementChild.value;
            dataString += "{'id':"+id+",'password':'"+password+"','tel':'"+tel+"','eMail':'"+eMail+"'},";
        }
        dataString =dataString.substring(0,dataString.length-1);
        dataString += "]";
        console.log(data);
        var data = {
            "data": dataString
        }
        $.ajax({
            type: "GET",
            url: "/manage/updateUserInfo",
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
    });
});

$(function () {
    $("#manage-view").click(function () {

    });

    $(".manage-download").click(function () {
        window.location.href = "/finance/download?clubId="+$(this).attr("id");
    });

    // $("#manageAll3").click(function(){
    //     $(".delete-tr").remove();
    //     $("input[type='checkbox']").each(function(){
    //         if($(this).is(':checked')){
    //             var index = $(this).parent().parent().index();
    //             writeInformation2(index);
    //         }
    //     });
    //     $(".manage-alert").show(200);
    // });

    $("#download-btn").click(function () {
        var dataString = "[";
        $("input[type='checkbox']").each(function(){
            if($(this).is(':checked')){
                var index = $(this).parent().parent().index();
                var trTemp = $('#fin-table tr:eq(' + index + ')');
                var id = trTemp.children("td").eq(5).children("span").eq(1).attr("id");
                dataString+="{'id':"+id+"},";
            }
        });
        dataString =dataString.substring(0,dataString.length-1);
        dataString+="]";
    });
})

//发布公告和通知
$(function () {
    $("#publish").click(function () {
        var check = $("input[name='message-type']:checked").val();
        var title = $("#title").val();
        var html = editor.$txt.html();
        if('1'===check){
            var data = {
                "title": title,
                "comment": html
            };
            $.ajax({
                type: "POST",
                url: "/information/publishAnnouncement",
                data: data,
                dataType: "json",
                success: function(r){
                    alert(r.message);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            })
        }else if('2'===check){

        }
    });

    $("#republish").click(function () {
        var check = $("input[name='message-type']:checked").val();
        var title = $("#title").val();
        var html = editor.$txt.html();
        if('1'===check){
            var data = {
                "id": /*[[$announcement.id]]*/'id',
                "title": title,
                "comment": html
            };
            $.ajax({
                type: "POST",
                url: "/information/republishAnnouncement",
                data: data,
                dataType: "json",
                success: function(r){
                    alert(r.message);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            })
        }else if('2'===check){

        }
    });
})
