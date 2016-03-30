'use strict';

/* Controllers */
// signin controller
app.controller('UserController', ['$rootScope', '$scope', '$http', '$state', 'toaster',
    function ($rootScope, $scope, $http, $state, toaster) {
        $scope.user = {};
        $scope.authError = null;
        $scope.login = function () {
            $scope.authError = null;
            // Try to login
            $http.post('connect', $scope.user)
                    .success(function (data) {
                        $rootScope.username = data.username;
                        $state.go('app.home');
                    })
                    .error(function (data) {
                        console.log(data);
                        $scope.authError = data.message;
                        if (data.code) {
                            $state.go('access.changepwdexp');
                        }
                    });

        };

        $scope.chgtpwd = function () {
            $scope.authError = null;
            $http.post('changepassword', $scope.user)
                    .success(function (data) {
                        $state.go('access.signin');
                    })
                    .error(function (data) {
                        toaster.pop("error", "Error", data);
                    });
        };

        $scope.changepasswordexp = function () {
            $scope.authError = null;
            $http.post('changepasswordexp', $scope.user)
                    .success(function (data) {
                       $scope.login();
                    })
                    .error(function (data) {
                        $scope.authError = data;
                    });
        };
        $scope.forgotpassword = function () {
            $scope.authError = null;
             $http.post('forgotpassword', $scope.user)
                    .success(function (data) {
                       $scope.user.password = $scope.user.username;
                        $scope.login();
                        toaster.pop("Succes", "Success", "Nouveau Mot de passe" + $scope.user.username);
                    })
                    .error(function (data) {
                        $scope.authError = data;
                    });
        };
    }])
        ;