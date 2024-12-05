var module = angular.module("myApp")

module.controller("purchaseCouponCtrl", purchaseCouponCtrlCtor)

function purchaseCouponCtrlCtor( $http )
{
	var self = this
	this.id
	this.success = false;
	this.failure = false;
	$http.get('http://localhost:8080/projectcoupon/webapi/companyfacade/getallcoupons')
	.then(
	function(resp){
		self.arr =resp.data;
	},
	function(err){
		alert(err);
	}
	
	)
			this.purchaseCoupon = function()
			{
		if(this.id == undefined){
			this.success = false;
			this.failure = true;
			return;
		}
		this.success = false;
		this.failure = false;
		var self = this;
		$http.put('http://localhost:8080/projectcoupon/webapi/customerfacade/purchasecoupon/'+this.id)
		.then(
				function(resp)
				{
					self.success = true;
					self.failure = false;		
					},
				function(err)
				{
					self.success = false;
					self.failure = true;
					})    	
			}
}

