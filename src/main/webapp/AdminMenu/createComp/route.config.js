(function () {
 
    var module = angular.module("myApp");

        // http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
        module.config(['$locationProvider', function ($locationProvider) {
                $locationProvider.hashPrefix('');
            }]);

        // router config
        module.config(function ($stateProvider, $urlRouterProvider) {

            $stateProvider
            .state("createCompany", {
                url: "/createcomp",
                templateUrl: "projectcoupon/createComp/createcompany.html",
                controller: "GetCouponsCtrl as g"
            })
            .state("createCoupon", {
                url: "/create",
                templateUrl: "create.html",
                controller: "CreateCouponCtrl as c"
            });
            $stateProvider
            .state("createCompany", {
                url: "/createcomp",
                templateUrl: "createcompany.html",
                controller: "GetCouponsCtrl as g"
            })
            .state("createCoupon", {
                url: "/create",
                templateUrl: "create.html",
                controller: "CreateCouponCtrl as c"
            });

            $stateProvider
            .state("createCompany", {
                url: "/createcomp",
                templateUrl: "createcompany.html",
                controller: "GetCouponsCtrl as g"
            })
            .state("createCoupon", {
                url: "/create",
                templateUrl: "create.html",
                controller: "CreateCouponCtrl as c"
            });

            $stateProvider
            .state("createCompany", {
                url: "/createcomp",
                templateUrl: "createcompany.html",
                controller: "GetCouponsCtrl as g"
            })
            .state("createCoupon", {
                url: "/create",
                templateUrl: "create.html",
                controller: "CreateCouponCtrl as c"
            });

            $stateProvider
            .state("createCompany", {
                url: "/createcomp",
                templateUrl: "createcompany.html",
                controller: "GetCouponsCtrl as g"
            })
            .state("createCoupon", {
                url: "/create",
                templateUrl: "create.html",
                controller: "CreateCouponCtrl as c"
            });


        $urlRouterProvider.when("", "/get"); // first browsing postfix is empty --> route it to /main
        // $urlRouterProvider.otherwise('/404'); // when no switch case matches --> route to /404
    });
 
})();