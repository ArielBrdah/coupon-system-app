(function()
    {
 
        var module = angular.module("myApp");
 
        module.service("mockServiceHTTP", mockServiceHTTPCtor);
 
        function Coupon(id, name)
            {
                    this.id = id;
                    this.name = name;
            }
 
            function mockServiceHTTPCtor( $q )
                {
                        this.coupons = [
                                    new Coupon (1, "Cafe cafe"),
                                    new Coupon (2, "Greg")
                                ];
 
                        this.getCoupons = function()
                        {
                                var deferred = $q.defer();
                                deferred.resolve({ 
                                    status : 200,
                                    data : this.coupons
                                });
                            return  deferred.promise;
                        }
                    this.getCouponFailue = function()
                    {
                            return $q.reject(
                                 { status : 404, data : 'coupon not found'}
                                 );
                            }
                        // in the future  will be replaced by 
                        //return $http.get('URL');
 
                        this.addCoupon = function(coupon)
                        {
                                this.coupons.push(coupon);
                                var deferred = $q.defer();
                                deferred.resolve({ 
                                    status : 200,
                                    data : this.coupons
                                });
                            return  deferred.promise;
                        }
                    // in the future will be replaced by 
                    //return $http.post('URL', coupon);
                }
 
 
        })();
 
 
        