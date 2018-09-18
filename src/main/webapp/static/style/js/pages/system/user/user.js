var datatable = null,idList=[];
$(function() {
    datatable = $('.table-sort').DataTable({
        aoColumnDefs: [
            {"bVisible": false, "aTargets": [0],} //控制列的隐藏显示
        ],
        ajax: {
            url: baselocation + "/user/showPageUser",
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
            data: "fullname",
            defaultContent: "",
        }, {
            data: "idnumber",
            defaultContent: "",
        }, {
            data: "birthday",
            render: function(data, type, row, meta) {
                return data ? new Date(data).pattern("yyyy-MM-dd") : '';
            }
        }, {
            data: "sex",
            defaultContent: "",
        }, {
            data: "email",
            defaultContent: "",
        }, {
            data: "tel",
            defaultContent: "",
        }, {
            data: "roleid",
            render: function(data, type, row, meta) {
                var a = "";
                if(data > 0){
                    a = row.role.rolename;
                }else{
                    a = "暂未分配角色";
                }
                return a;
            }
        }, {
            data: "roleid",
            render: function(data, type, row, meta) {
                var a = "";
                if(data > 0){
                    a =  '<a class="btn btn-link" onclick="distribute_action_Fun(' + row.number + ')">配置</a>';
                }else{
                    a =  '<a class="btn disabled radius">无需配置</a>';
                }
                return a;
            },
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
                if(row.level == 0) {
                    a =  '<a class="btn disabled radius">暂无操作功能</a>';
                }else{
                    a += '<a title="' + state[data] + '" style="text-decoration:none" onClick="changeStatus(' + "'" + state[data] + "'" + ',[' + "'" + row.rolename + "'" + '],\'/roles/roleState\',' + row.id + ', ' + flag[data] + ')" href="javascript:;"><i class="Hui-iconfont">' + icon[data] + '</i></a>';
                    a += '<a title="编辑" href="javascript:;" onclick="edit_show([' + "'" + row.rolename + "'" + '],\'/roles/edit\',' + row.id + ',\'893\',\'320\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>';
                    a += '<a title="删除" href="javascript:;" onclick="del([' + row.id + '],\'/roles/delete\',[' + "'" + row.rolename + "'" + '],reloadTable)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                }
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