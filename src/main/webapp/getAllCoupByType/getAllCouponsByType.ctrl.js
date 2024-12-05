
var module = angular.module("myApp")

module.controller("getAllCouponsByTypeCtrl", getAllCouponsByTypeCtrlCtor)

function getAllCouponsByTypeCtrlCtor( $http )
{
   var self = this
   this.type
   this.getAllCouponsByType = function(){ 
     $http.get('http://localhost:8080/projectcoupon/webapi/companyfacade/getallcouponsbytype/'+this.type)
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
}

