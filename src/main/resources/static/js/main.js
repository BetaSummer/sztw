/**
 * Created by Administrator on 2017/4/27.
 */

/* user */
/****************************************/

//VIEW CONTROL
var menuClubForm = false;//社团活动申请表
var menuOrganizationForm = false;//学生组织活动申请表
var menuClubFina = false;//社团财务管理
var menuClubManage = false;//社团管理

function  licenceControl(index) {
    if(index==0){
        $("#userManage").show();
    }
    if(index==1){
        $("#powerManage").show();
    }
    if(index==2){
        $("#doMessage").show();
        menuClubManage=true;
    }
    if(index==3){
        $("#form-appli").show();
        menuClubForm=true;
    }
    if(index==4){
        $("#form-view").show();
        menuClubForm=true;
    }
    if(index==5){
        $("#form-manage").show();
        menuClubForm=true;
    }
    if(index==6){
        $("#clubManage").show();
        menuClubManage=true;
    }
    if(index==7){
        $("#financeT").show();
        menuClubFina=true;
    }
    if(index==8){
        $("#financeB").show();
        menuClubFina=true;
    }
}

function viewControl(arr) {
    for(var i = 0;i<arr.length;i++){
        licenceControl(arr[i]);
    }
    if(!menuClubForm){
        $(".flag1").hide();
    }
    if(!menuClubFina){
        $(".flag4").hide();
    }
    if(!menuClubManage){
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
       login();
   });

    $("#login-reset").click(function () {
        $("#login-form")[0].reset();
    });

    // document.onkeydown = function(e){
    //     var ev = document.all ? window.event : e;
    //     if(ev.keyCode==13) {
    //         login();
    //     }
    // };

    function login() {
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
    }
});

/* manage */
$(function () {
    $("#selfManage").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/manage/selfManage");
    });
});
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

$(function () {
    $("#changeMessage").click(function () {
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/information/messageList",function () {
            $(".loading").hide();
        }) ;
    });
});
$(".deleteMessage").click(function () {
    var r = confirm("确定要删除吗？");
    if(r==true){
        var id = $(this).attr("content");
        $(".content-body").html("");
        $(".loading").show();
        $(".content-body").load("/information/deleteAnnouncementById?id="+id,function () {
            $(".loading").hide();
        });
    }
});

$(".editMessage").click(function () {
    var id = $(this).attr("content");
    $(".content-body").html("");
    $(".loading").show();
    $(".content-body").load("/information/editAnnouncementById?id="+id,function () {
        $(".loading").hide();
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
//表二申请
$(function () {
    $("#area-appli").click(function () {
        $(".content-body").load("/applyOrganizationForm/apply");
        if( window.screen.width<767){
            $(document.body).height(1050);
        }
    });
});

//表二审批list
$(function () {
    $("#area-manage").click(function () {
        $.get("/approveForm/listClubActivity",function () {
            $(".content-body").load("/approveOrganizationForm/approveList",function () {
                if( window.screen.width<767){
                    $(document.body).height(1050);
                }
            });
        });
    });
});
//表二list
$(function () {
    $("#area-view").click(function () {
        $(".content-body").html("");
        $(".content-body").load("/applyOrganizationForm/listAllForm",function () {
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