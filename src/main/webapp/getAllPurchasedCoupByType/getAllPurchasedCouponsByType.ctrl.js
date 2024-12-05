
var module = angular.module("myApp")

module.controller("getAllPurchasedCouponsByTypeCtrl",getAllPurchasedCouponsByTypeCtrlCtor)

function getAllPurchasedCouponsByTypeCtrlCtor( $http )
{
   var self = this
   this.type
   this.getAllPurchasedCouponsByType = function(){ 
     $http.get('http://localhost:8080/projectcoupon/webapi/customerfacade/getallpurchasedcouponsbytype/'+this.type)
     .then(
     function(resp)
     {
         alert('success')
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
}

