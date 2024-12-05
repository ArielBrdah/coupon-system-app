
var module = angular.module("myApp")

module.controller("getAllPurchasedCouponsByPriceCtrl", getAllPurchasedCouponsByPriceCtrlCtor)

function getAllPurchasedCouponsByPriceCtrlCtor( $http )
{
   var self = this
   this.price
   this.getAllPurchasedCouponsByPrice = function(){ 
     $http.get('http://localhost:8080/projectcoupon/webapi/customerfacade/getallpurchasedcouponsbyprice/'+this.price)
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

