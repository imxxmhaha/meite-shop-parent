


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>蚂蚁课堂收银台</title>
<link rel="stylesheet"
	href="http://pay.mayikt.com/static/static_layui/css/layui.css" />
<script type="text/javascript"
	src="http://pay.mayikt.com/static/static_layui/layui.js"></script>
<style>
* {
	margin: 0px;
	padding: 0px;
}

body {
	background-color: #e0e0e099;
}

.head {
	background: white;
	height: 65px;
}

.head-center {
	width: 70%;
	height: 65px;
	margin: 0 auto;
}

.head-center-logo {
	width: 126px;
	height: 50px;
	float: left;
	margin-top: 6px;
}

.head-center-font {
	float: left;
	font-size: 20px;
	padding-top: 20px;
	padding-left: 16px;
	font-size: 18px;
}

.scent {
	width: 70%;
	height: 660px;
	background: white;
	margin: 35px auto;
	border-radius: 3px;
	box-shadow: 0 0 4px #e2e2e2;
}

.footer {
	width: 70%;
	margin: 37px auto;
	text-align: center;
	font-size: 16px;
	color: #969494;
}

.head-center-user {
	float: right;
	padding-top: 21px;
	color: #757575;
	font-size: 15px;
}

.scent-order {
	float: left;
	width: 46%;
	height: 83%;
	margin-top: 85px;
}

.scent-order-info {
	padding-top: 18px;
	font-size: 18px;
	padding-left: 100px;
}

.scent-order-info-desc {
	padding-top: 13px;
	padding-left: 11px;
	font-size: 16px;
	color: #9a9696;
}

.scent-pay {
	width: 45%;
	float: right;
	margin-top: 100px;
	height: 500px;
	margin-right: 43px;
}

.scent-pay-type {
	margin-top: 20px;
}

.scent-pay-way strong {
	font-size: 18px;
}

.scent-pay-qrcode {
	margin-top: 48px;
}

.scent-pay-qrcode p {
	font-size: 14px;
	background: #00c800;
	height: 30px;
	line-height: 30px;
	color: white;
}
</style>
</head>

<body>
	<div class="head">
		<div class="head-center">
			<img class="head-center-logo"
				src="http://static.itmayiedu.com/20181024142946.png" />
			<div class="head-center-font">充值中心</div>
			<div class="head-center-user">用户名：644064779@qq.com</div>
		</div>

		<div class="scent">
			<div class="scent-order">
				<div class="scent-order-info">
					<strong>商品订单：</strong> <span style="color: #b7b0b0;">${data.orderId}</span>
				</div>

				<div class="scent-order-info">
					<strong>支付宝支付：</strong> <span
						style="color: #0ac265; font-size: 15px;"></span><span
						style="color: red; font-size: 24px;">
						￥${(data.payAmount/100)?string('0.00')}</span><br />
				</div>
				<div class="scent-order-info">
					<strong>订单详情：</strong>
					<hr />
					<div class="scent-order-info-desc">
						<span>商品名称：蚂蚁课堂-会员充值</span>
					</div>
					<div class="scent-order-info-desc">
						<span>支付订单：${data.paymentId}</span>
					</div>
					<div class="scent-order-info-desc">
						<span>应付金额： ￥${(data.payAmount/100)?string('0.00')} </span>
					</div>
					<div class="scent-order-info-desc">
						<span>购买时间：2019-3-23</span>
					</div>
				</div>
			</div>

			<div class="scent-pay">
				<div class="scent-pay-way">
					<strong>支付方式</strong>
				</div>
				<hr />
				<div class="scent-pay-type">
					<#list paymentChanneList as p>
					<a href="/channel?channelId=${p.channelId}"><button
							class="layui-btn layui-btn-primary layui-btn-lg">
							<i class="layui-icon layui-icon-rmb" style="color: #1E9FFF"></i>
							${p.channelName}
						</button></a>
						</#list>
				</div>

			</div>
		</div>

		<div class="footer">Copyright © 2016 - 2018 MeiTedu. All Rights
			Reserved 每特教育 版权所有</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	var ua = navigator.userAgent;

	var ipad = ua.match(/(iPad).*OS\s([\d_]+)/),

	isIphone = !ipad && ua.match(/(iPhone\sOS)\s([\d_]+)/),

	isAndroid = ua.match(/(Android)\s+([\d.]+)/),

	isMobile = isIphone || isAndroid;

	if (isMobile) {
		$(".head-center").css("margin-left", "12px");
		$(".head-center-user").hide();
		$(".scent").css({
			"width" : "96%",
			"margin" : "14px auto"
		});
		$(".scent-order").css({
			"width" : "100%",
			"height" : "48%",
			"margin-top" : "0px",
			"margin-left" : "-80px"
		});
		$(".scent-order-info").css("width", "100%");
		$(".scent-pay").css({
			"width" : "84%",
			"margin-top" : "2px",
			"height" : "340px"
		})
		$("#weixin").hide();
		$("#wx").css("margin-top", "-32px")
		$(".footer").css("font-size", "14px")
		console.log("蚂蚁课堂PC手机版本充值...")
	} else {
		console.log("蚂蚁课堂PC电脑版本充值...")
	}

	/* 	function orderStatus(){
			$.post("/weiXinPay/loadWeiXinPayState",{},function(data){
				if(data==1){
					window.location.href='http://www.mayikt.com/user/userVip';
				}
			})
		}
		
		setInterval("orderStatus()",3000);
	 */
</script>

</html>

