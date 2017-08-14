/**
 * Created by Administrator on 2017/4/27.
 */

/* user */
/****************************************/

//VIEW CONTROL
var flag1 = false;
var flag2 = false;
var flag3 = false;
var flag4 = false;
var flag5 = false;

function  licenceControl(index) {
    if(index==0){
        $("#userManage").show();
        flag3=true;
    }
    if(index==1){
        $("#powerManage").show();
        flag3=true;
    }
    if(index==2){
        $("#doMessage").show();
        flag5=true;
    }
    if(index==3){
        $("#form-appli").show();
        flag1=true;
    }
    if(index==4){
        $("#form-view").show();
        flag1=true;
    }
    if(index==5){
        $("#form-manage").show();
        flag1=true;
    }
    if(index==6){
        $("#clubmanage").show();
        flag5=true;
    }
    if(index==7){
        $("#financeT").show();
        flag4=true;
    }
    if(index==8){
        $("#financeB").show();
        flag4=true;
    }
}

function viewControl(arr) {
    for(var i = 0;i<arr.length;i++){
        licenceControl(arr[i]);
    }
    if(!flag1){
        $(".flag1").hide();
    }
    if(!flag3){
        $(".flag3").hide();
    }
    if(!flag4){
        $(".flag4").hide();
    }
    if(!flag5){
        $(".flag5").hide();
    }
}

$(function () {
    var licenceCookie = $.cookie("licenceCookie").split(",");
    viewControl(licenceCookie);
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

/* manage */
$(function () {
   $("#userManage").click(function () {
       $(".content-body").html("");
       $(".loading").show();
       $(".content-body").load("/manage/userManage",function () {
           $(".loading").hide();
       }) ;
   });
});
$(function () {
    $("#powerManage").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/manage/powerManage",function () {
            $(".loading").hide();
        }) ;
    });
});
$(function () {
    $("#financeT").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/finance/financeT",function () {
            $(".loading").hide();
        }) ;
    });
});
$(function () {
    $("#financeB").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/finance/financeB",function () {
            $(".loading").hide();
        }) ;
    });
});

$(function () {
    $("#clubManage").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/manage/clubManage",function () {
            $(".loading").hide();
        }) ;
    });
});
$(function () {
    $("#doMessage").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/information/doMessage",function () {
            $(".loading").hide();
        }) ;
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
   $("#extrafile").click(function () {
       window.location.href="/applyClubForm/getFile?formId="+$("#formId").html();
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

$(function () {
    $("#form-manage").click(function () {
        $(".loading").show();
        $.get("/approveForm/listClubActivity",function () {
            $(".content-body").load("/approveForm/listClubActivity",function () {
                $(".loading").hide();
                if( window.screen.width<767){
                    $(document.body).height(1050);
                }
            });
        });
    });
});

$(function () {
    $("#form-view").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/applyClubForm/listAllForm",function () {
            $(".loading").hide();
            if( window.screen.width<767){
                $(document.body).height(1050);
            }
        }) ;
    });
});
//TODO new table interface
$(function () {
    $("#area-appli").click(function () {
        $(".content-body").load("/applyClubForm/ariaForm");
        if( window.screen.width<767){
            $(document.body).height(1050);
        }
    });
});

$(function () {
    $("#area-manage").click(function () {
        $.get("/approveForm/listClubActivity",function () {
            $(".content-body").load("/approveForm/areaApprove",function () {
                if( window.screen.width<767){
                    $(document.body).height(1050);
                }
            });
        });
    });
});

$(function () {
    $("#area-view").click(function () {
        $(".content-body").html("");
        $(".content-body").load("/applyClubForm/areaApprove",function () {
            if( window.screen.width<767){
                $(document.body).height(1050);
            }
        }) ;
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
            "comment": $("#comment").val(),
            "applySelfMoney": $("#applySelfMoney").html(),
            "applyReserveMoney": $("#applyReserveMoney").html(),
            "isApprove":1
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
    $("#disgree-btn").click(function () {
        var data={
            "formId": $("#formId").html(),
            "comment": $("#comment").val(),
            "applySelfMoney": $("#applySelfMoney").html(),
            "applyReserveMoney": $("#applyReserveMoney").html(),
            "isApprove":0
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