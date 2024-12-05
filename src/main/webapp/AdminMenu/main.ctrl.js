
// 1 - get the angular applicaiton module [= phone book]
var module = angular.module("myApp")

var debug2 = 0;

// 2 - register controller into the app module
//      give it a name, supply a ctor function
//	MainCtrlCtor is a ~const
module.controller("MainCtrl", MainCtrlCtor)

function MainCtrlCtor( $http )
{
    var self = this
    this.newCompany = {}
    this.success = false;
    this.failure = false;

// using method get of the company service
this.getAllCompanies = function(){
     $http.get('http://localhost:8080/projectcoupon/webapi/myresource/getallcompanies')
     .then(
     function(resp)
     {
         console.log(resp)
         self.arr = resp.data

     },
     function(err)
     {
         alert('error in charging the list')
     })
}
// end of the coupon service list
    
// function to create a company
    this.createCompany = function()
    {
    	//  Checking if the company that entry is true company
        if (this.newCompany.compName == undefined || this.newCompany.id == undefined || this.newCompany.password == undefined)
        {
            this.success = false;
            this.failure = true;
            return;}
    	
        $http.post('http://localhost:8080/projectcoupon/webapi/myresource/createcompany',
        		this.newCompany)
        .then(
        function(resp)
        {
            this.success = true;
            this.failure = false;
        },
        function(err)
        {
        	alert('DATA ERROR: '+err.data)
            this.success = false;
            this.failure = true;
        })    	
    }
}
// Object Company
function Company( compName,coupons, email, id, password)
{
	this.coupons = coupons
    this.id         = id
    this.compName   = compName
    this.password   = password
    this.email      = email
}

