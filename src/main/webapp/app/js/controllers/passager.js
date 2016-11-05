app.controller("PassagerForm",
        function PassagerForm($scope, $modalInstance, toaster, ErrorService, CrudService, idvoyage, nbreplaces,url) {

            $scope.errorService = ErrorService;
            $scope.errorService.clear();

            $scope.passagers = [];
            for (var i = 0; i < nbreplaces; i++) {
                $scope.passagers.push({});
            }
            $scope.isFormMode = true;

            $scope.save = function () {
                $scope.errorService.setwaiting();
                CrudService.save({categorie: url, id: idvoyage}, $scope.passagers,
                        function (data) {
                            toaster.pop("success", "Success", "Reservation Effectuée avec Succès");
                            $scope.errorService.setSucces();
                            $modalInstance.close();
                        },
                        function (error) {
                            toaster.pop("error", "Error", error);
                            $scope.errorService.clear();
                            $modalInstance.close();
                        });
            };
            
            $scope.closemodal = function () {
                $modalInstance.dismiss('cancel');
            };
        });
