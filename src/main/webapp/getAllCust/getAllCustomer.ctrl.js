
var module = angular.module("myApp")

module.controller("getAllCustomerCtrl", getAllCustomerCtrlCtor)

function getAllCustomerCtrlCtor( $http )
{
// this, is the MainCtrlCtor object's self
    var self = this
    this.newCompany = {}

      $http.get('http://localhost:8080/projectcoupon/webapi/myresource/getallcustomer').then(
      function(resp)
      {
          self.arr = resp.data
      },
      function(err)
     {
          alert('error')
      })
     }
