<html>
<#include "../comment/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
    <#include "../comment/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.getCategoryName())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类别编号</label>
                            <input name="categoryType" type="text" class="form-control" value="${(category.getCategoryType())!''}"/>
                        </div>
                        <input type="hidden" name="categoryId" value="${(category.getCategoryId())!''}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>







