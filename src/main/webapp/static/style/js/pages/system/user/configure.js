var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
};

$(document).ready(function(){
    var puserid = document.getElementById("puserid").value;
    $.ajax({
        type: "post",
        url: baselocation + "/xst/areas/getAreas?id=" + puserid,
        success: function (msg) {
            $.fn.zTree.init($("#treeRegion"), setting, msg);
        }
    });
});


function zTreeOnClick(event, treeId, treeNode) {       //第二步
    var treeObj = $.fn.zTree.getZTreeObj("treeRegion"),
        nodes = treeObj.getCheckedNodes(true),
        v = "";
    for (var i = 0; i < nodes.length; i++) {
        v += nodes[i].id + ",";
        //console.log("节点id:" + nodes[i].id + "节点名称" + v); //获取选中节点的值
    }
    var puserid = document.getElementById("puserid").value;
    $.ajax({
        url:baselocation + "/xst/areas/saveAreasZtree?puserid=" + puserid +"&data=" + v,
        dataType:'json',
        type:'post',
        success:function(data){
            if(data.retcode == 0) {
                layer.alert(data.retmsg, {
                    icon: 5,
                    title: '失败',
                    time: 2000,
                    shade: 0,
                    btn: 0,
                    offset: ['40px', ($(window).width() - 300) + 'px']
                });
            }else{
                parent.showSuccessMessage("授权成功！");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }
    })
}