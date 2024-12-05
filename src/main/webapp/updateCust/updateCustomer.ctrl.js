
var module = angular.module("myApp")

module.controller("updateCustomerCtrl", updateCustomerCtrlCtor)

function updateCustomerCtrlCtor( $http )
{
    var self = this
    this.newCustomer = {}
    this.updateCustomer = function()
    {
    	if(this.newCustomer.id == undefined || this.newCustomer.custName == undefined || this.newCustomer.password == undefined)
    	{
    		this.failure = true;
    		this.success = false;
    		return;
    		}
    		this.failure = false;
    	this.success = false;
    	var self = this;
        $http.put('http://localhost:8080/projectcoupon/webapi/myresource/updatecustomer',
        		this.newCustomer)
        .then(
        function(resp)
        {
        	self.failure = false;
        	self.success = true;
        	},
        function(err)
        {
            	this.failure = true;
            	this.success = false;
        })    	
    }
}
