/**
 * Created by BlackZXK on 4/8/2017.
 */

/*富文本框内容 start*/

var editor = new wangEditor($("#contentIn"));


editor.config.menus = $.map(wangEditor.config.menus, function(item, key) {
    if (item === 'fullscreen') {
        return null;
    }
    if (item === 'location') {
        return null;
    }
    return item;
});

//测试批量读取文件名（失败）
// var fso = new ActiveXObject("Scripting.FileSystemObject");
// var f = fso.GetFolder(document.all.fixfolder.value);
// var fc = new Enumerator(f.files);
// var s = [];
// for (; !fc.atEnd(); fc.moveNext())
// {
//     s.push(new Array([]));
// }

editor.config.emotions = {

    'default': {
        title: '默认',
        data: [{
            'icon': '../img/icon/中毒.png',
            'value': '[中毒]'
        }, {
            'icon': '../img/icon/中毒-1.png',
            'value': '[中毒-1]'
        },{
            'icon': '../img/icon/亲吻.png',
            'value': '[亲吻]'
        },{
            'icon': '../img/icon/亲吻-1.png',
            'value': '[亲吻-1]'
        },{
            'icon': '../img/icon/亲吻-2.png',
            'value': '[亲吻-2]'
        },{
            'icon': '../img/icon/僵尸.png',
            'value': '[僵尸]'
        },{
            'icon': '../img/icon/受伤.png',
            'value': '[受伤]'
        },{
            'icon': '../img/icon/口罩.png',
            'value': '[口罩]'
        },{
            'icon': '../img/icon/吐舌.png',
            'value': '[吐舌]'
        },{
            'icon': '../img/icon/吐舌-1.png',
            'value': '[吐舌-1]'
        },{
            'icon': '../img/icon/吐舌-2.png',
            'value': '[吐舌-2]'
        },{
            'icon': '../img/icon/呕吐.png',
            'value': '[呕吐]'
        },{
            'icon': '../img/icon/呕吐-1.png',
            'value': '[呕吐-1]'
        },{
            'icon': '../img/icon/哭.png',
            'value': '[哭]'
        },{
            'icon': '../img/icon/哭-1.png',
            'value': '[哭-1]'
        },{
            'icon': '../img/icon/天使.png',
            'value': '[天使]'
        },{
            'icon': '../img/icon/头晕.png',
            'value': '[头晕]'
        },{
            'icon': '../img/icon/奸笑.png',
            'value': '[奸笑]'
        },{
            'icon': '../img/icon/害怕.png',
            'value': '[害怕]'
        },{
            'icon': '../img/icon/害怕-1.png',
            'value': '[害怕-1]'
        },{
            'icon': '../img/icon/开心.png',
            'value': '[开心]'
        },{
            'icon': '../img/icon/开心-1.png',
            'value': '[开心-1]'
        },{
            'icon': '../img/icon/开心-2.png',
            'value': '[开心-2]'
        },{
            'icon': '../img/icon/微笑-1.png',
            'value': '[微笑1-]'
        },{
            'icon': '../img/icon/思考.png',
            'value': '[思考]'
        },{
            'icon': '../img/icon/懵B.png',
            'value': '[懵B]'
        },{
            'icon': '../img/icon/斜眼.png',
            'value': '[斜眼]'
        },{
            'icon': '../img/icon/流汗.png',
            'value': '[流汗]'
        },{
            'icon': '../img/icon/热恋.png',
            'value': '[热恋]'
        },{
            'icon': '../img/icon/生气.png',
            'value': '[生气]'
        },{
            'icon': '../img/icon/生病.png',
            'value': '[生病]'
        },{
            'icon': '../img/icon/疲惫.png',
            'value': '[疲惫]'
        },{
            'icon': '../img/icon/眨眼.png',
            'value': '[懵眨眼]'
        },{
            'icon': '../img/icon/眼红.png',
            'value': '[眼红]'
        },{
            'icon': '../img/icon/睡觉.png',
            'value': '[睡觉]'
        },{
            'icon': '../img/icon/笑.png',
            'value': '[笑]'
        },{
            'icon': '../img/icon/笑-1.png',
            'value': '[笑-1]'
        },{
            'icon': '../img/icon/笑哭.png',
            'value': '[笑哭]'
        },{
            'icon': '../img/icon/迷茫.png',
            'value': '[迷茫]'
        },{
            'icon': '../img/icon/酷.png',
            'value': '[酷]'
        },{
            'icon': '../img/icon/酷-1.png',
            'value': '[酷-1]'
        },{
            'icon': '../img/icon/闭嘴.png',
            'value': '[闭嘴]'
        },{
            'icon': '../img/icon/难过.png',
            'value': '[难过]'
        },{
            'icon': '../img/icon/难过-1.png',
            'value': '[难过-1]'
        },{
            'icon': '../img/icon/震惊.png',
            'value': '[震惊]'
        },{
            'icon': '../img/icon/震惊-1.png',
            'value': '[震惊-1]'
        },{
            'icon': '../img/icon/静音.png',
            'value': '[静音]'
        },{
            'icon': '../img/icon/面无表情.png',
            'value': '[面无表情]'
        },{
            'icon': '../img/icon/面无表情-1.png',
            'value': '[面无表情-1]'
        },{
            'icon': '../img/icon/魔鬼.png',
            'value': '[魔鬼]'
        }]
    }
};

editor.config.uploadImgUrl = "/information/uploadFile";
editor.config.uploadImgFileName = "picture";

editor.create();

/*富文本框内容 end*/