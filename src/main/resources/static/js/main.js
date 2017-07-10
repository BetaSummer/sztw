/**
 * Created by Administrator on 2017/4/27.
 */

/* user */
/****************************************/

$(function () {
    $.ajax({
        type: "POST",
        url: "/user/getLv",
        dataType:"json",
        success: function(r){
            if(r.data>1){
                $("#form-view").after("<li class='sub-item' id='form-manage'>管理</li>");
                $("#form-view").hide();
                $("#form-appli").hide();
                $("#form-manage").click(function () {
                    $(".loading").show();
                    $.get("/approveForm/listClubActivity",function () {
                        $(".content-body").load("/approveForm/listClubActivity",function () {
                            $(".loading").hide();
                        });
                    });
                });
                //re creat js(because new html code has not events)
                $(".sub-nav").find("li").click(function () {
                    $(".sub-nav").find("li").removeClass("on");
                    $(this).addClass("on");
                    $(".content-nav").html($(this).parent().parent().prev().html()+"/ "+$(this).html());
                }) ;
            }
        },
        error: function(data){

        }
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
                   window.location.href = "/index";
                   window.location.replace();
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


/* tabset  */
/*******************************************/

$(function () {
   $("#form-view").click(function () {
       $(".content-body").html("");
       $(".loading").show();
       $.get("/applyClubForm/getListAllForm",function () {
          $(".content-body").load("/applyClubForm/getListAllForm",function () {
              $(".loading").hide();
          }) ;
       });
   }) ;
});
$(function () {
    $("#f-list").click(function () {
        // $(".content-body").html("");
        $("#f-flow").show();$("#manage-a-show").hide();
    });
});
$(function () {
    $("#manage-a").click(function () {
        // $(".content-body").html("");
        $("#manage-a-show").show();$("#f-flow").hide();
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
});

/* footer */

function heightListener() {
    var $height = $(window).height();
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