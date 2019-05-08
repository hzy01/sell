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
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单Id</th>
                                <th>商品总金额</th>
                                <th>订单状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.getOrderId()}</td>
                                <td>${orderDTO.getOrderAmount()}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>商品Id</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                <#list orderDTO.getOrderDetailList() as orderDetail>
                <tr>
                    <td>${orderDetail.getProductId()}</td>
                    <td>${orderDetail.getProductName()}</td>
                    <td>${orderDetail.getProductPrice()}</td>
                    <td>${orderDetail.getProductQuantity()}</td>
                    <td>${orderDetail.getProductPrice()*orderDetail.getProductQuantity()}</td>
                </tr>
                </#list>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().getMsg()=="新订单">
                <a href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}" type="button" class="btn btn-default btn-danger">取消订单<a/>
            </#if>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>