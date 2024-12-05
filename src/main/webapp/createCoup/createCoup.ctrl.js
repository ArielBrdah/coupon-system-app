
var module = angular.module("myApp")

module.controller("createCoupCtrl", createCoupCtrlCtor)

function createCoupCtrlCtor( $http )
{
   var self = this
    this.newCoupon = {}
   this.success = false
   this.failure = false
    this.addCoupon = function()
    {
	   if (this.newCoupon.id == undefined || this.newCoupon.title == undefined
			   || this.newCoupon.type == undefined || this.newCoupon.amount == undefined
			   || this.newCoupon.message == undefined || this.newCoupon.image == undefined
		   || this.newCoupon.startDate == undefined || this.newCoupon.endDate == undefined || this.newCoupon.price == undefined )
       {
           this.success = false;
           this.failure = true;
           return;}
	   this.success = false;
	   this.failure = false;
	   var self = this;
        $http.post('http://localhost:8080/projectcoupon/webapi/companyfacade/createcoupon',
        		this.newCoupon)
        .then(
        function(resp)
        {
        	self.newCoupon = {};
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

