
var module = angular.module("myApp")

module.controller("getAllCouponsCtrl", getAllCouponsCtrlCtor)

function getAllCouponsCtrlCtor( $http )
{
   var self = this
     $http.get('http://localhost:8080/projectcoupon/webapi/companyfacade/getallcoupons')
     .then(
     function(resp)
     {
         console.log(resp)
         if(resp.data !=null){
         self.arr = resp.data
         }
     },
     function(err)
     {
         alert('error')
     })
}

