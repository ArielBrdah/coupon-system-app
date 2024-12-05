
var module = angular.module("myApp")

module.controller("getAllCompaniesCtrl", getAllCompaniesCtrlCtor)

function getAllCompaniesCtrlCtor( $http )
{
    var self = this
    this.newCompany = {}

      $http.get('http://localhost:8080/projectcoupon/webapi/myresource/getallcompanies').then(
      function(resp)
      {
          self.arr = resp.data
      },
      function(err)
     {
          alert('error')
      })
  }
