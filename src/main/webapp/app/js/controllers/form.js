app.controller('formController',
        ['$modal', 'formData', 'formModel', '$state', '$scope', '$http', 'toaster', 'ErrorService',
            function formController($modal, formData, formModel, $state, $scope, $http, toaster, ErrorService) {
                $scope.master = {};
                $scope.errorService = ErrorService;
                $scope.errorService.clear();
                $scope.tsoftitem = formData.data;
                $scope.master = angular.copy(formData.data);

                $scope.formmodel = formModel.data;

                $scope.gotoElement = function(row) {
                    $state.go('app.form.elt', {id: row, categorie: formData.data.categorie});
                };

                $scope.reset = function() {
                    $scope.tsoftitem = angular.copy($scope.master);
                    $scope.form.$setPristine();
                };

                $scope.del = function(row, code, nbrow) {
                    var r = confirm("Confirm ?");
                    if (r == true) {
                        $http.get(formData.data.categorie + '/del/' + code + '?row=' + row)
                                .success(function(data) {
                                    toaster.pop("success", "Success", "Suppression Effectuée avec Succès");
                                    if (nbrow >= 2) {
                                        $scope.gotoElement(0);
                                    }
                                    else {
                                        $scope.gotoElement(-1);
                                    }

                                }).error(function(data) {
                            toaster.pop("error", "Error", data);
                        });

                    }

                };

                $scope.getElement = function(row) {
                    $http.get(formData.data.categorie + '/getElement/' + row)
                            .success(function(data) {
                                $scope.tsoftitem = data;
                                $scope.master = angular.copy(data);
                                $scope.form.$setPristine();
                            });
                };

                $scope.getElementClone = function(row) {
                    $http.get(formData.data.categorie + '/getElementClone/' + row)
                            .success(function(data) {
                                $scope.tsoftitem = data;
                                $scope.master = angular.copy(data);
                                $scope.form.$setPristine();
                            });
                };

                $scope.beforesave = function() {
                    angular.forEach($scope.formmodel.joincolumns, function(item) {
                        if ($scope.tsoftitem[item] !== null) {
                            $scope.tsoftitem[item] = $scope.tsoftitem[item].code;
                        }
                    });
                    $scope.errorService.setwaiting();
                };

                $scope.aftersave = function() {
                    toaster.pop("success", "Success", "Operation Effectuée avec Succès");
                    $scope.errorService.setSucces();
                    $scope.form.$setPristine();
                };
                $scope.saveitem = function(modal, saveclose) {
                    if ($scope.formmodel.ismultipart) {
                        $scope.saveFile(modal, saveclose);
                    } else {
                        $scope.save(modal, saveclose);
                    }
                };
                $scope.save = function(modal, saveclose) {
                    $scope.beforesave();
                    $http({
                        method: 'POST',
                        url: formData.data.categorie + '/save',
                        data: $.param(($scope.tsoftitem)), // pass in data as strings
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                    })
                            .success(function(data) {
                                if (modal === true) {
                                    if (saveclose === true) {

                                    } else {
                                        $scope.getElement(-1);
                                    }
                                } else {
                                    $scope.gotoElement(data.row);
                                }

                                $scope.aftersave();
                            });
                };

                
                //autocompletion
                $scope.getItems = function(val, subcategorie, name) {
                    if (val.length < 4)
                        return;
                    return $http.get(subcategorie + '/autocomplete', {
                        params: {
                            cval: val
                        }
                    }).then(function(res) {
                        var items = [];
                        angular.forEach(res.data, function(item) {
                            items.push(item);
                        });
                        $scope.formmodel.selectmodels[name] = items;
                        return items;
                    });
                };
 
            }]);


