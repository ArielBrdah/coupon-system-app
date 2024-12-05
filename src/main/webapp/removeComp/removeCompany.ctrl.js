var module = angular.module("myApp")

module.controller("removeCompanyCtrl", removeCompanyCtrlCtor)

function removeCompanyCtrlCtor( $http )
{
   // var self = this
    this.id;
    this.success = false;
    this.failure = false;
	this.removeCompany = function(){
	    this.success = false;
	    this.failure = false;
	    var self = this;
        $http.delete('http://localhost:8080/projectcoupon/webapi/myresource/removecompany/' +this.id).then(
        		function(resp){
        			self.success = true;
        			self.failure = false;
            },
            function(err){
            	self.success = false;
    			self.failure = true;
                alert('error in delete')
            });
       }
     
    }
