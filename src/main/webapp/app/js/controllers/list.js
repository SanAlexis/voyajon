app.controller("listController",
        function listController($state, $scope, toaster, $http, DTOptionsBuilder, $modal, $log, ErrorService, $resource, $location, initialData, $stateParams) {

            $scope.errorService = ErrorService;
            $scope.categorie = initialData.data.categorie;
            $scope.listmodel = initialData.data;
            $scope.dtColumnDefs = initialData.data.columns;
            $scope.dtOptions = DTOptionsBuilder
                    .newOptions()
                    .withOption('ajax', {
                        url: $stateParams.categorie+'/datatable' ,
                        type: 'GET'
                    })
                    .withDataProp('data')
                    .withPaginationType('full')
                    .withOption("serverSide", true)
                    .withOption("bFilter", false)

                    .withBootstrap()
                    // Add Table tools compatibility
                    .withTableTools('vendor/jquery/datatables/copy_csv_xls.swf')
                    .withTableToolsOption("sRowSelect", "multi")
                    .withTableToolsButtons([
                        "addrow","editrow", "delrow", "csv", "select_all", "select_none"
                    ]);

            $scope.dtOptions1 = DTOptionsBuilder
                    .fromSource( $scope.categorie+'/list')
                    .withPaginationType('full')
                    .withBootstrap()
                    // Add Table tools compatibility
                    .withTableTools('vendor/jquery/datatables/copy_csv_xls.swf')
                    .withTableToolsOption("sRowSelect", "multi")
                    .withTableToolsButtons([
                        "addrow", "editrow", "delrow", "csv", "select_all", "select_none"
                    ]);


            $.fn.dataTable.TableTools.buttons.addrow = $.extend(
                    {}, $.fn.dataTable.TableTools.buttonBase,
                    {
                        "sButtonText": "<i class=\"fa fa-plus\"></i>",
                        "sToolTip": "New Data",
                        "fnClick": function(nButton, oConfig) {
                            $scope.openFormRelation('lg', -1);
                        }

                    }
            );
            $.fn.dataTable.TableTools.buttons.editrow = $.extend(
                    {}, $.fn.dataTable.TableTools.BUTTONS.select,
                    {
                        "sButtonText": "<i class=\"fa fa-edit\"></i>",
                        "sToolTip": "Edit Data",
                        "fnClick": function(nButton, oConfig) {
                            $state.go('app.form.elt', {id: (this.fnGetSelectedData()[0]).Index, categorie: $scope.categorie});
                        }
                    }
            );
            $.fn.dataTable.TableTools.buttons.editrow1 = $.extend(
                    {}, $.fn.dataTable.TableTools.buttons.editrow,
                    {
                        "fnClick": function(nButton, oConfig) {
                            $scope.openFormRelation('lg', (this.fnGetSelectedData()[0]).Index);
                        }
                    }
            );

            $.fn.dataTable.TableTools.buttons.delrow = $.extend(
                    {}, $.fn.dataTable.TableTools.BUTTONS.select,
                    {
                        "sButtonText": "<i class=\"fa fa-trash-o\"></i>",
                        "sToolTip": "Delete Data",
                        "fnClick": function(nButton, oConfig) {
                            //confirm dialogue
                            var r = confirm("Confirm ?");
                            if (r == true) {
                                $http.get($scope.categorie + '/del/' + (this.fnGetSelectedData()[0]).code + '?row=' + (this.fnGetSelectedData()[0]).Index)
                                        .success(function(data) {
                                            toaster.pop("success", "Success", "Suppression Effectuée avec Succès");
                                            $('#dataTables-example').DataTable().ajax.reload();
                                        }).error(function(data) {
                                    toaster.pop("error", "Error", data);
                                    $('#dataTables-example').DataTable().ajax.reload();
                                });
                            }
                            ;

                        }
                    }
            );


        });


