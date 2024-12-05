
var module = angular.module("myApp")

module.controller("getCouponCtrl", getCouponCtrlCtor)
function getCouponCtrlCtor( $http )
{
    var self = this
    this.id
     this.getCoupon = function(){
        $http.get('http://localhost:8080/projectcoupon/webapi/companyfacade/getcoupon/' +this.id).then(
            function(resp){
                self.arr = [resp.data]
            },
            function(err){
                alert('error in searching ...')
            });
       }

     }