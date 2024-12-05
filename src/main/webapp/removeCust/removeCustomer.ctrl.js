var module = angular.module("myApp")

module.controller("removeCustomerCtrl", removeCustomerCtrlCtor)

function removeCustomerCtrlCtor( $http )
{
    var self = this
    this.id
    this.removeCustomer = function(){
    	this.success = false;
    	this.failure = false;
    	var self = this;
        $http.delete('http://localhost:8080/projectcoupon/webapi/myresource/removecustomer/'+this.id)
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
