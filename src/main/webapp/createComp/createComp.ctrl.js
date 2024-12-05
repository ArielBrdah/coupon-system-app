
var module = angular.module("myApp")

module.controller("createCompCtrl", createCompCtrlCtor)

function createCompCtrlCtor( $http )
{
    this.newCompany = {}
    this.success = false;
    this.failure = false;
    
// function to create a company
    this.createCompany = function()
    {
    	//  Checking if the company that entry is true company
        if (this.newCompany.compName == undefined || this.newCompany.id == undefined || this.newCompany.password == undefined)
        {
            this.success = false;
            this.failure = true;
            return;}
        this.success = false;
        this.failure = false;
        var self = this;
        $http.post('http://localhost:8080/projectcoupon/webapi/myresource/createcompany',
        		this.newCompany)
        .then(
        function(resp)
        {
        	self.company = resp.data;
        	self.newCompany = {};
            self.success = true;
            self.failure = false;
        },
        function(err)
        {
        	alert('DATA ERROR: '+err.data)
            self.success = false;
            self.failure = true;
        })    	
    }
}
