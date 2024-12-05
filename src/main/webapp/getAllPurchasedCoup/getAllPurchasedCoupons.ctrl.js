
var module = angular.module("myApp")

module.controller("getAllPurchasedCouponsCtrl", getAllPurchasedCouponsCtrlCtor)

function getAllPurchasedCouponsCtrlCtor( $http )
{
   var self = this
   
     $http.get('http://localhost:8080/projectcoupon/webapi/customerfacade/getallpurchasedcoupons')
     .then(
     function(resp)
     {
         if(resp.data !=null){
         self.arr = resp.data
         }
     },
     function(err)
     {
         alert('error')
     })
}