
var module = angular.module("myApp")

module.controller("getCompanyCtrl", getCompanyCtrlCtor)
function getCompanyCtrlCtor( $http )
{
    var self = this
    this.id
    this.getCompany = function(){
        $http.get('http://localhost:8080/projectcoupon/webapi/myresource/getcompany/' +this.id).then(
            function(resp){
                self.arr = [resp.data]
            },
            function(err){
                alert('error in searching ...')
            });
       }

     }