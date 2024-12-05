
var module = angular.module("myApp")

module.controller("updateCouponCtrl", updateCouponCtrlCtor)

function updateCouponCtrlCtor( $http )
{
   var self = this
    this.newCoupon = {}
    this.updateCoupon = function()
    {
	   if (this.newCoupon.id == undefined || this.newCoupon.price == undefined || this.newCoupon.endDate == undefined )
	   {
		   this.failure=true;
		   this.success=false;
		   return;
	   }
	
	   this.failure=false;
	   this.success=false;
	   var self = this;
        $http.put('http://localhost:8080/projectcoupon/webapi/companyfacade/updatecoupon',this.newCoupon)
        .then(
        function(resp)
        {
        self.arr=resp.data;
        self.newCoupon = {};
		self.success=true;
		self.failure=false;
		},
        function(err)
        {
			 this.failure=true;
			 this.success=false;
        })    	
    }
}