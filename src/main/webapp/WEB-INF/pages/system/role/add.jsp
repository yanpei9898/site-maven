<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/common.jsp" %>
<html>
<head>
<title>添加角色</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="rolename" name="rolename" maxlength="20" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="description" name="description" maxlength="50" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select class="select" size="1" name="state" />
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色等级：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box" data-toggle="tooltip" data-placement="bottom" title="超级管理员">
					<input type="radio" id="level-1" name="level" value="0" checked />
					<label for="level-1">超级管理员</label>
				</div>
				<div class="radio-box" data-toggle="tooltip" data-placement="bottom" title="需要配置菜单和功能">
					<input type="radio" id="level-2" name="level" value="1" />
					<label for="level-2">管理员</label>
				</div>
                <div class="radio-box" data-toggle="tooltip" data-placement="bottom" title="省市区县单位的管理员">
					<input type="radio" id="level-3" name="level" value="2" />
					<label for="level-3">地区管理员</label>
				</div>
                <div class="radio-box" data-toggle="tooltip" data-placement="bottom" title="需要配置地区数据">
					<input type="radio" id="level-4" name="level" value="3" />
					<label for="level-4">特殊管理员</label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>
<script src="${ctxstc}/styles/h-ui.admin/js/H-ui.admin.js"></script>
<script src="${ctxstc}/style/js/pages/system/role/add.js"></script>
</body>
</html>