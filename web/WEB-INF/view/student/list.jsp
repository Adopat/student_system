<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${path}/static/css/layout.css">
    <link rel="stylesheet" href="${path}/static/bootstrap/css/bootstrap.min.css">
    <script src="${path}/static/js/jquery.js"></script>
    <script src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
    <title>学生管理</title>
</head>

<body>
<div id="main">
    <!-- 左侧菜单 -->
    <div id="left">
        <!-- logo -->
        <div id="logo">
            <img src="${path}/static/image/student.png" width="75%" height="100%">
        </div>
        <!-- 菜单 -->
        <div id="menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="actives"><a href="${path}/student/list">学生管理</a></li>
                <li class="actives"><a href="${path}/classes/list">班级管理</a></li>
            </ul>
        </div>
    </div>
    <div id="right">
        <!-- 头部信息 -->
        <div id="head">
            <ul class="nav nav-tabs" style="border:0px;float:right">
                <li><a href="javascript:;" style="margin-right:-18px">管理员:</a></li>
                <li>
                    <a data-toggle="dropdown" href="#">
                        ${admin.username} <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="actives"><a href="${path}/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- 列表信息 -->
        <div id="center" style="padding: 20px;">
            <div class="panel panel-default">
                <div class="panel-heading">学生信息</div>
                <div class="panel-body row">
                    <div>
                        <form class="form-inline" action="${path}/student/list" method="GET">
                            <div class="form-group">
                                <label for="name">&nbsp;姓名</label>
                                <input type="text" class="form-control" name="name" id="name" value="${paramMap.name}"
                                       placeholder="请输入学生姓名">
                            </div>
                            <div class="form-group">
                                <label for="classId">班级</label>
                                <select class="form-control" id="classId" name="classId">
                                    <option value="">请选择班级</option>
                                    <c:forEach items="${classList}" var="classes">
                                        <option value="${classes.id}" <c:if
                                                test="${paramMap.classId==classes.id}"> selected="selected"</c:if>>${classes.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">搜索</button>
                            <button id="clear" class="btn btn-default">清除条件</button>
                            <button style="margin-left: 20px" type="button" class="btn btn-success" id="addStudent">新增学生</button>
                        </form>
                    </div>
                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover table-condensed">
                    <tr>
                        <td>序号</td>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>年龄</td>
                        <td>班级</td>
                        <td colspan="2" width="40px">操作</td>
                    </tr>
                    <c:forEach items="${page.list}" var="student" varStatus="status">
                        <tr>
                            <td style="vertical-align:middle">${status.index+1}</td>
                            <td>${student.name}</td>
                            <td>${student.sex==0?"女":"男"}</td>
                            <td>${student.age}</td>
                            <td>${student.className}</td>
                            <td width="20px">
                                <a onclick="return showStudentDetailModal('${path}/student/find?id=${student.id}')">
                                    <button type="button" class="btn btn-default">编辑</button>
                                </a>
                            </td>
                            <td width="20px">
                                <a onclick="return showDelStudentModal('${path}/student/delete?id=${student.id}','${student.name}')">
                                    <button type="button" class="btn btn-danger">删除</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(page.list) <= 0}">
                        <tr>
                            <td colspan="6" style="color:#b1afaf;text-align:center">大哥，真的查不到您要的数据！</td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <!-- 分页 -->
            <c:if test="${fn:length(page.list) > 0}">
                <div id="page" style="text-align: center;">
                    <nav aria-label="navigation">
                        <ul class="pagination">
                            <li>
                                <c:if test="${page.currentPage!=1}">
                                    <a href="${path}/student/list?currentPage=${page.prePage}&name=${paramMap.name}&classId=${paramMap.classId}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </c:if>
                            </li>
                            <li class="disabled">
                                <c:if test="${page.currentPage==1}">
                                    <a href="javascript:;" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </c:if>
                            </li>
                            <c:forEach var="i" begin="1" end="${page.totalPage}">
                                <c:if test="${page.currentPage==i}">
                                    <li class="active">
                                        <a href="${path}/student/list?currentPage=${i}&name=${paramMap.name}&classId=${paramMap.classId}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${page.currentPage!=i}">
                                    <li>
                                        <a href="${path}/student/list?currentPage=${i}&name=${paramMap.name}&classId=${paramMap.classId}">${i}</a>
                                    </li>
                                </c:if>

                            </c:forEach>
                            <li class="disabled">
                                <c:if test="${page.currentPage==page.totalPage}">
                                    <a href="javascript:;" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${page.currentPage!=page.totalPage}">
                                    <a href="${path}/student/list?currentPage=${page.nextPage}&name=${paramMap.name}&classId=${paramMap.classId}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                            </li>
                        </ul>
                    </nav>
                </div>
            </c:if>
        </div>
        <!-- 底部信息 -->
        <div id="footer">
            <p> 学生管理系统 隐私权 政策 法律声明 知识产权 | © 2003-现在 zhifou.com 版权所有</p>
            <p> Designed and built with all the love in the world by @rich and @beauty. Maintained by the core team with
                the help of our contributors.</p>
        </div>
    </div>
</div>

<!-- 信息删除确认 -->
<div class="modal fade" id="showDelStudentModal">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">删除学生</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除学生<span id="studentInfo" style="color:#d75453;font-weight:bold"></span>的信息吗?</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="delUrl"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a onclick="delStudent()" class="btn btn-success" data-dismiss="modal">确定</a>
            </div>
        </div>
    </div>
</div>

<!-- 添加/编辑学生信息 -->
<div class="modal fade" id="showStudentDetailModal">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="title">编辑学生信息</h4>
            </div>
            <div class="modal-body">
                <form action="${path}/student/saveOrUpdate" method="post">
                    <input type="hidden" name="id" id="studentId">
                    <div class="form-group">
                        <label for="studentName">姓名</label>
                        <input type="text" class="form-control" id="studentName" required name="name"
                               placeholder="请输入姓名">
                    </div>
                    <div class="form-group">
                        <label for="studentAge">年龄</label>
                        <input type="text" class="form-control" id="studentAge" required name="age" placeholder="请输入年龄">
                    </div>
                    <div class="form-group">
                        <label for="studentClassId">班级</label>
                        <select class="form-control" id="studentClassId" required name="classId">
                            <option value="0">请选择班级</option>
                            <c:forEach items="${classList}" var="classes">
                                <option value="${classes.id}"
                                        <c:if test="${student.classId==classes.id}"> selected="selected"</c:if>>${classes.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="studentSex">性别&nbsp;&nbsp;</label>
                        <div class="form-group" id="studentSex">
                            <input type="radio" name="sex" class="studentSex" value="1" checked> 男
                            <input type="radio" name="sex" class="studentSex" value="0"> 女
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" id="updateUrl"/>
                        <button type="submit" class="btn btn-success">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#clear").click(function () {
        $("#name").val("");
        $("#classId option:first").prop("selected", 'selected');
    });

    // 删除学生弹出框
    function showDelStudentModal(url, name) {
        $('#delUrl').val(url);//给会话中的隐藏属性URL赋值
        $('#studentInfo').text(name);
        $('#showDelStudentModal').modal();
    }

    // 删除学生
    function delStudent() {
        let url = $("#delUrl").val();
        window.location.href = url;
    }

    // 新增学生-显示模态框
    $("#addStudent").click(function () {
        $("#title").text("新增学生信息");
        $("#showStudentDetailModal").modal();
    });

    function showStudentDetailModal(url) {
        $("#title").text("编辑学生信息");
        $('#showStudentDetailModal').modal();
        // 回显数据
        $.ajax({
            url: url,
            dataType: "json",
            success: function (data) {
                $("#studentId").val(data.id);
                $("#studentName").val(data.name);
                $("input[name='sex'][value='" + data.sex + "']").attr("checked", true);
                $("#studentAge").val(data.age);
                $("#studentClassId").val(data.classId);
            }
        });
    }
</script>
</body>

</html>