/**
 * Created by Administrator on 2017/4/27.
 */

/* user */
/****************************************/

//VIEW CONTROL
function  licenceControl(index) {
    if(index==3){
        $("#form-appli").show();
    }
    if(index==4){
        $("#form-view").show();
    }
    if(index==5){
        $("#form-manage").show();
    }
}
$(function () {
    $(".licence").each(function () {
        licenceControl($(this).html());
    });
});

$(function () {
    $("#form-manage").click(function () {
        $(".loading").show();
        $.get("/approveForm/listClubActivity",function () {
            $(".content-body").load("/approveForm/listClubActivity",function () {
                $(".loading").hide();
            });
        });
    });
});
/* login */
$(function () {
   $("#login-btn").click(function () {
       var username = $("#username").val();
       var password = $("#password").val();
       if(username!=""&&password!=""){
           var data={
               "username":username,
               "password":password
           };
           $.ajax({
               type: "POST",
               url: "/user/login",
               data: data,
               dataType:"json",
               success: function(r){
                   if(r.status === 0){
                       window.location.href = "/index";
                       window.location.replace();
                   }else if(r.status === 1){
                       alert(r.message);
                   }

               },
               error: function(XMLHttpRequest, textStatus, errorThrown){
                   alert(XMLHttpRequest.status);
                   alert(XMLHttpRequest.readyState);
                   alert(textStatus);
               },
           });
       }
       else{
           alert("用户名密码不能为空");
       }
   }) ;
   $("#login-reset").click(function () {
      $("#login-form")[0].reset();
   });
});

/* finance */
// $(function () {
//    $("#f-list").click(function () {
//        $(".content-body").load("/information/listAllFinancialFlow",function () {
//            $(".loading").hide();
//        }) ;
//    });
// });


/* tabset  */
/*******************************************/

$(function () {
   $("#form-view").click(function () {
       $(".content-body").html("");
       $(".loading").show();
       $(".content-body").load("/applyClubForm/listAllForm",function () {
           $(".loading").hide();
       }) ;
   });
});
$(function () {
    $("#f-list").click(function () {
        // $(".content-body").html("");
        $("#f-flow").fadeIn();$("#manage-a-show").hide();$("#public-news-").hide();$("#president-management").hide();$("#club-finance").hide();$("#manage-personal").hide();
    });
});
$(function () {
    $("#manage-a").click(function () {
        // $(".content-body").html("");
        $("#manage-a-show").fadeIn();$("#f-flow").hide();$("#public-news-").hide();$("#president-management").hide();$("#club-finance").hide();$("#manage-personal").hide();
    });
});
$(function () {
    $("#-president-management").click(function () {
        // $(".content-body").html("");
        $("#president-management").fadeIn();$("#f-flow").hide();$("#public-news-").hide();$("#manage-a-show").hide();$("#club-finance").hide();$("#manage-personal").hide();
    });
});
$(function () {
    $("#public-news").click(function () {
        // $(".content-body").html("");
        $("#public-news-").fadeIn();$("#f-flow").hide();$("#manage-a-show").hide();$("#president-management").hide();$("#club-finance").hide();$("#manage-personal").hide();
    });
});
$(function () {
    $("#-club-finance").click(function () {
        // $(".content-body").html("");
        $("#club-finance").fadeIn();$("#public-news-").hide();$("#f-flow").hide();$("#manage-a-show").hide();$("#president-management").hide();$("#manage-personal").hide();
    });
});
$(function () {
    $("#-manage-personal").click(function () {
        // $(".content-body").html("");
        $("#manage-personal").fadeIn();$("#public-news-").hide();$("#f-flow").hide();$("#manage-a-show").hide();$("#president-management").hide();$("#club-finance").hide();
    });
});

/* club */
/********************************/
function download() {
    var base64doc = btoa(unescape(encodeURIComponent(document.documentElement.innerHTML))),
        a = document.createElement('a'),
        e = new MouseEvent('click');
    a.download = 'form.doc';
    a.href = 'data:text/html;base64,' + base64doc;
    a.dispatchEvent(e);
}
$(function () {
   $("#club-load").click(function () {
       $(".approval-right").hide();
       $("#base-css").removeAttr('href');
       $("#common-css").removeAttr('href');
       $("#club-css").removeAttr('href');
       download();
       $(".approval-right").show();
       location.replace(location);
   });
});
/* application */
$(function () {
    $("#form-appli").click(function () {
        $(".loading").hide();
       $(".content-body").load("/applyClubForm/applyFormClubActivity");
       if( window.screen.width<767){
           $(document.body).height(1050);
       }
    });
});

/* approval */
$(function () {
    $("#club-entry").click(function () {
       $(".approval-form").toggle(300);
    });
    $("#agree-btn").click(function () {
        var data={
            "formId": $("#formId").html(),
            "comment":"",
            "applySelfMoney":$("#applySelfMoney").html(),
            "applyReserveMoney":$("#applyReserveMoney").html()
        };
        $.ajax({
            type: "GET",
            url: "/approveForm/approve",
            data: data,
            dataType:"json",
            success: function(r){
                alert(r.message);
                window.close();
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            },
        });
    });
});

/* footer */

function heightListener() {
    var $height = $(window).height();
    $(".sub-content").css("min-height",$height-90-53.2);
    $(".content-body").css("min-height",$height-90-53.2-140);
    if($height>$(document.body).height()){
        $("footer").css("position","fixed");
        $("footer").css("top", $height-53.2);
    }
    else{
        $("footer").css("position","static");
        $("footer").css("top", $(document.body).height()-53.2);
    }
}

$(function () {
    $(document).ready(heightListener());
    $(window).resize(heightListener());
});