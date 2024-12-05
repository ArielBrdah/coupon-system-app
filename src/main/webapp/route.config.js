(function () {
 
    var module = angular.module("myApp");

       // http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
        module.config(['$locationProvider', function ($locationProvider) {
                $locationProvider.hashPrefix('');
            }]);

        // router config
        module.config(function ($stateProvider, $urlRouterProvider) {

            $stateProvider
            .state("createComp", {
                url: "/createcompany",
                templateUrl: "createComp/createcompany.html",
                controller: "createCompCtrl as company"
            })
            .state("createCoup", {
                url: "/createcoupon",
                templateUrl: "createCoup/createcoupon.html",
                controller: "createCoupCtrl as coupon"
            })
            .state("createCust", {
                url: "/createcustomer",
                templateUrl: "createCust/createcustomer.html",
                controller: "createCustCtrl as customer"
            })
            .state("getAllComp", {
                url: "/getallcompanies",
                templateUrl: "getAllComp/getallcompanies.html",
                controller: "getAllCompaniesCtrl as company"
            })
            .state("getAllCoup", {
                url: "/getallcoupons",
                templateUrl: "getAllCoup/getallcoupons.html",
                controller: "getAllCouponsCtrl as coupon"
            })
            .state("getAllCoupByType", {
                url: "/getallcouponsbytype",
                templateUrl: "getAllCoupByType/getallcouponsbytype.html",
                controller: "getAllCouponsByTypeCtrl as coupon"
            })
            .state("getAllCust", {
                url: "/getallcustomer",
                templateUrl: "getAllCust/getallcustomer.html",
                controller: "getAllCustomerCtrl as customer"
            })
            .state("getAllPurchasedCoup", {
                url: "/getallpurchasedcoupons",
                templateUrl: "getAllPurchasedCoup/getallpurchasedcoupons.html",
                controller: "getAllPurchasedCouponsCtrl as coupon"
            })
            .state("getAllPurchasedCoupByPrice", {
                url: "/getallpurchasedcouponsbyprice",
                templateUrl: "getAllPurchasedCoupByPrice/getallpurchasedcouponsbyprice.html",
                controller: "getAllPurchasedCouponsByPriceCtrl as coupon"
            })
            .state("getAllPurchasedCoupByType", {
                url: "/getallpurchasedcouponsbytype",
                templateUrl: "getAllPurchasedCoupByType/getallpurchasedcouponsbytype.html",
                controller: "getAllPurchasedCouponsByTypeCtrl as coupon"
            })
            .state("getComp", {
                url: "/getcompany",
                templateUrl: "getComp/getcompany.html",
                controller: "getCompanyCtrl as company"
            })
            .state("getCoup", {
                url: "/getcoupon",
                templateUrl: "getCoup/getcoupon.html",
                controller: "getCouponCtrl as coupon"
            })
            .state("getCust", {
                url: "/getcustomer",
                templateUrl: "getCust/getcustomer.html",
                controller: "getCustomerCtrl as customer"
            })
            .state("purchaseCoup", {
                url: "/purchasecoupon",
                templateUrl: "purchaseCoup/purchasecoupon.html",
                controller: "purchaseCouponCtrl as coupon"
            })
            .state("removeComp", {
                url: "/removecompany",
                templateUrl: "removeComp/removecompany.html",
                controller: "removeCompanyCtrl as company"
            })
            .state("removeCoup", {
                url: "/removecoupon",
                templateUrl: "removeCoup/removecoupon.html",
                controller: "removeCouponCtrl as coupon"
            })
            .state("removeCust", {
                url: "/removecustomer",
                templateUrl: "removeCust/removecustomer.html",
                controller: "removeCustomerCtrl as customer"
            })
            .state("updateComp", {
                url: "/updatecompany",
                templateUrl: "updateComp/updatecompany.html",
                controller: "updateCompanyCtrl as company"
            })
            .state("updateCoup", {
                url: "/updatecoupon",
                templateUrl: "updateCoup/updatecoupon.html",
                controller: "updateCouponCtrl as coupon"
            })
            .state("updateCust", {
                url: "/updatecustomer",
                templateUrl: "updateCust/updatecustomer.html",
                controller: "updateCustomerCtrl as customer"
            })
            .state("404", {
                url: "/404",
                templateUrl: "404/404.html"
            })
            
        $urlRouterProvider.when("", "/main"); // first browsing postfix is empty --> route it to /main
            $urlRouterProvider.otherwise(function($injector, $location){
                $injector.get('$state').go('404');
            });
        //$urlRouterProvider.otherwise('/404'); // when no switch case matches --> route to /404
    });
 
})();
