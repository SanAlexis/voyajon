/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('tsoftApp.services', [])
        .service('ErrorService', function () {
            return {
                errorMessage: null,
                succesMessage: null,
                waiting: null,
                setwaiting: function () {
                    this.waiting = "Waiting";
                },
                setError: function (msg) {
                    this.errorMessage = msg;
                    this.succesMessage = null;
                    this.waiting = null;
                },
                setSucces: function () {
                    this.errorMessage = null;
                    this.succesMessage = "Success";
                    this.waiting = null;
                },
                clear: function () {
                    this.errorMessage = null;
                    this.succesMessage = null;
                    this.waiting = null;
                }
            };
        })
// register the interceptor as a service, intercepts ALL angular ajax http calls
        .factory('errorHttpInterceptor', ['$q', 'ErrorService', '$rootScope',
            function ($q, ErrorService, $rootScope) {

                return {
                    responseError: function (rejection) {
                        console.log('Error in response ', rejection);
                        if (rejection.status === 401) {
                            $rootScope.$broadcast('event:loginRequired');

                        }
                        if (rejection.status >= 402 && rejection.status < 510) {
                            ErrorService.setError('Server was unable to find what you were looking for... Sorry!!');
                            console.log(rejection);
                            alert(rejection.data);
                        }

                        return $q.reject(rejection);
                    }
                };
            }])

        .config(['$httpProvider', function ($httpProvider) {
                $httpProvider.interceptors.push('errorHttpInterceptor');
            }]);

