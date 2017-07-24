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
            if($(this).is(':checked')){
                var index = $(this).parent().parent().index();
                writeInformation(index);
            }
        });
        $(".manage-alert").show(200);
    });
});

$(function () {
    $("#finance-btn").click(function () {
        var dataLength = $(".manage-alert-in").length;
        var dataTrArr = $(".manage-alert-in");
        var data = new Array(dataLength-1);
        var comment = $("#comment").val();
        for(var i = 0;i<dataLength-1;i++){
            var id = dataTrArr[i].children[6].innerHTML;
            var change = dataTrArr[i].children[3].firstElementChild.value;
            var selfReserve = dataTrArr[i].children[4].firstElementChild.value;
            var money = dataTrArr[i].children[5].firstElementChild.value;
            data[i]={
                "id":id,
                "change":change,
                "selfReserve":selfReserve,
                "money":money
            }
        }

        var dataAll = {
            "data":{data:JSON.stringify(data)},
            "comment":comment
        }
        $.ajax({
            type: "GET",
            traditional:true,
            url: "/finance/changClubFinance",
            data: dataAll,
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
            if($(this).is(':checked')){
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


$(function () {
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

