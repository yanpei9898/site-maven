var datatable = null,idList=[];
$(function() {
    datatable = $('.table-sort').DataTable({
        aoColumnDefs: [
            {"bVisible": false, "aTargets": [0],} //控制列的隐藏显示
        ],
        ajax: {
            url: baselocation + "/role/showPageRole",
            type: 'post',
            data: function(d) {
                d.search = $('#search').val();
                d.state = $('#state').val();
            }
        },
        columns: [{
            data: null,
            orderable:false,
            render: function(data, type, row, meta) {
                var startIndex = meta.settings._iDisplayStart;
                return startIndex + meta.row + 1;
            }
        }, {
            data: "id",
            defaultContent: "",
            orderable:false,
            render: function(data, type, row, meta) {
                return '<input id="input-' + data + '" type="checkbox" name="single"><label for="input-' + data + '"></label>';
            }
        }, {
            data: "rolename",
            defaultContent: "",
        }, {
            data: "description",
            defaultContent: "",
        }, {
            data: "insertdatetime",
            render: function(data, type, row, meta) {
                return data ? new Date(data).pattern("yyyy-MM-dd HH:mm:ss") : '';
            }
        }, {
            data: "operatordatetime",
            render: function(data, type, row, meta) {
                return data ? new Date(data).pattern("yyyy-MM-dd HH:mm:ss") : '';
            }
        }, {
            data: "state",
            defaultContent: "",
            render: function(data, type, row, meta) {
                return '<span class="label label-' + clazz[data] + ' radius">' + text[data] + '</span>';
            }
        }, {
            data: "state",
            defaultContent: "",
            orderable:false,
            responsivePriority: 1,
            render: function(data, type, row, meta) {
                var a = "";
                a += '<a title="' + state[data] + '" style="text-decoration:none" onClick="changeStatus(' + "'" +  state[data] + "'" + ',[' + "'" + row.rolename + "'" + '],\'/menu/menuState\',' + row.id + ', '+ flag[data] + ')" href="javascript:;"><i class="Hui-iconfont">' + icon[data] + '</i></a>';
                a += '<a title="编辑" href="javascript:;" onclick="edit_show([' + "'" + row.rolename + "'" + '],\'/menu/edit\',' + row.id + ',\'893\',\'400\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>';
                a += '<a title="删除" href="javascript:;" onclick="del([' + row.id + '],\'/menu/delete\',[' + "'" + row.rolename + "'" + '],reloadTable)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                return a;
            }
        }],
    });


    $('#state').on('change',function(){
        datatable.ajax.reload();
    });

});


function reloadTable() {
    datatable.ajax.reload(null, false);
}

function getDTSelect() {
    var lines = datatable.rows('.selected').data();
    if(lines.length > 0){
        for (var i = 0; i < lines.length; i++) {
            idList.push(lines[i].id);
        }
        return idList;
    }else{
        return 0;
    }
}


$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        $("#doSearch").trigger("click");
    }
});