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
                url: "/createcomp",
                templateUrl: "createcompany.html",
                controller: "MainCtrl as main1"
            });

        $urlRouterProvider.when("", "/createcomp"); // first browsing postfix is empty --> route it to /main
        // $urlRouterProvider.otherwise('/404'); // when no switch case matches --> route to /404
    });
 
})();
