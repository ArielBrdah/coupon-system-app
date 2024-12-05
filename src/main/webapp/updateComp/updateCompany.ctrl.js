
var module = angular.module("myApp")

module.controller("updateCompanyCtrl", updateCompanyCtrlCtor)

function updateCompanyCtrlCtor( $http )
{
    this.newCompany = {}
    this.success = false;
    this.failure = false;
    this.updateCompany = function()
    {
    	   if (this.newCompany.compName == undefined || this.newCompany.id == undefined || this.newCompany.password == undefined || this.newCompany.email == undefined)
           {
               this.success = false;
               this.failure = true;
               return;}
    	
        this.success = false;
        this.failure = false;
        var self = this;
        $http.put('http://localhost:8080/projectcoupon/webapi/myresource/updatecompany',
        		this.newCompany)
        .then(
        function(resp)
        {
            self.newCompany = {};
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