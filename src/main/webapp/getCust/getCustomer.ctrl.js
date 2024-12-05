
var module = angular.module("myApp")

module.controller("getCustomerCtrl", getCustomerCtrlCtor)
function getCustomerCtrlCtor( $http )
{
    var self = this
    this.id
    
     this.getCustomer = function(){
        $http.get('http://localhost:8080/projectcoupon/webapi/myresource/getcustomer/' +this.id).then(
            function(resp){
                self.arr = [resp.data]
            },
            function(err){
                alert('error in searching the customer ...')
            });
       }

     }
