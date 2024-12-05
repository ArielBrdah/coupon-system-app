
var module = angular.module("myApp")

module.controller("removeCouponCtrl", removeCouponCtrlCtor)

function removeCouponCtrlCtor( $http )
{
   var self = this
    this.id
    this.removeCoupon = function()
    {
	   this.failure = false;
	   this.success = false;
	   var self = this;
        $http.delete('http://localhost:8080/projectcoupon/webapi/companyfacade/removecoupon/'+
        		this.id)
        .then(
        function(resp)
        {
        	  self.failure = false;
       	   self.success = true;
        },
        function(err)
        {
        	  self.failure = true;
       	   self.success = false;
        })    	
    }
}

