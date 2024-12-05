
var module = angular.module("myApp")

module.controller("createCustCtrl", createCustCtrlCtor)

function createCustCtrlCtor( $http )
{
   var self = this;
   this.newCustomer = {};
   this.failure = false;
   this.success = false;
    this.createCustomer = function()
    {
	   if (this.newCustomer.custName == undefined || this.newCustomer.id == undefined || this.newCustomer.password == undefined)
       {
           this.success = false;
           this.failure = true;
           return;}
	   
	   this.failure = false;
	   this.success = false;
	   var self = this;
        $http.post('http://localhost:8080/projectcoupon/webapi/myresource/createcustomer',
        		this.newCustomer)
        .then(
        function(resp)
        {
            self.newCustomer = {};
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

