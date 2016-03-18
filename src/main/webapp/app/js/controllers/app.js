app.controller("HomeController",
        function HomeController($scope, $http, $state) {
            $scope.logout = function() {
                $http.post('signout')
                        .success(function(data) {
                            $state.go("access.signin");
                        });
            };


        });
