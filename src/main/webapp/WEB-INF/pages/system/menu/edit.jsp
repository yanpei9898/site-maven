<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/common.jsp" %>
<html>
<head>
<title>修改菜单</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-member-edit">
		<input type="hidden" name="id" id="menuId" value="${menu.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="menuname" name="menuname" value="${menu.menuname }" maxlength="10" />
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上级菜单：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select class="select" size="1" id="pid" name="pid">
						<option value="1">顶级菜单</option>
						<c:forEach items="${menuList }" var="menuList">
                            <option value="${menuList.getOpcode() }" <c:if test="${menu.pid == menuList.opcode }">selected</c:if>>${menuList.getMenuname() }</option>
                        </c:forEach>
					</select>
				</span>
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="url" name="url" value="${menu.url }" maxlength="50" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select class="select" size="1" name="state" />
					    <option value="1" <c:if test="${menu.state == 1 }">selected</c:if>>启用</option>
						<option value="0" <c:if test="${menu.state == 0 }">selected</c:if>>禁用</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" name="remark" value="${menu.remark }" maxlength="50" />
			</div>
		</div>
		<div class="row cl hide">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>
<script src="${ctxstc}/styles/h-ui.admin/js/H-ui.admin.js"></script>
<script src="${ctxstc}/style/js/pages/system/menu/edit.js"></script>
</body>
</html>