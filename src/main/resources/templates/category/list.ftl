<html>
<#include "../comment/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
    <#include "../comment/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>名字</th>
                            <th>类别编号</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list categoryList as productCategory>
                <tr>
                    <td>${productCategory.getCategoryId()}</td>
                    <td>${productCategory.getCategoryName()}</td>
                    <td>${productCategory.getCategoryType()}</td>
                    <td>${productCategory.getCreateTime()}</td>
                    <td>${productCategory.getUpdateTime()}</td>
                    <td><a href="/sell/seller/category/index?categoryId=${productCategory.getCategoryId()}">修改</a></td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>