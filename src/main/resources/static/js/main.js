/**
 * Created by Administrator on 2017/4/27.
 */

/* user */
/****************************************/

/* login */
$(function () {
   $("#login-form").submit(function () {
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
               success: function(data){
                   if(data.status==0){
                       $(location).attr('href',"/user/index");
                   }
                   else {
                       alert('wrong');
                   }
               },
               error: function(data){
                   alert('Error');
               }
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
            alert('Error');
        }
    });
}) ;

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
    });
});

/* approval */
$(function () {
    $("#club-entry").click(function () {
       $(".approval-form").toggle(300);
    });
});
