'use strict';
/**
 * Config for the router
 */
angular.module('app')
        .run(
                ['$rootScope', '$state', '$stateParams',
                    function ($rootScope, $state, $stateParams) {
                        $rootScope.$state = $state;
                        $rootScope.$stateParams = $stateParams;
                    }
                ]
                )
        .run(function ($rootScope, $state, $http) {
            $rootScope.$on("event:loginRequired", function (event, toState, toParams, fromState, fromParams) {
                $state.go('access.signin');
            });
        })
        .run(function ($rootScope, $state, $http) {
            $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
//                if (toState.authenticate) {
//                    $http.get('login')
//                            .success(function (response) {
//                                $rootScope.username = response.data;
//                            });
//                }
            });
        })

        .config(
                ['$stateProvider', '$urlRouterProvider',
                    function ($stateProvider, $urlRouterProvider) {

                        $urlRouterProvider
                                .otherwise('/access/404');
                        $stateProvider
                            // Client
                                .state('client', {
                                    abstract: true,
                                     url: '/client',
                                    templateUrl: 'tpl/client/app.html',
                                    authenticate: false,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('toaster').
                                                        then(function () {
                                                            return $ocLazyLoad.load(['js/controllers/app.js']);
                                                        }
                                                        );
                                            }]
                                    }
                                })
                                .state('client.home', {
                                    url: '/home',
                                    templateUrl: 'tpl/client/home.html'
                                })
                                .state('client.resa', {
                                    url: '/reservation',
                                    templateUrl: 'tpl/client/reservation.html'
                                })
                                .state('client.feedback', {
                                    url: '/feedback',
                                    templateUrl: 'tpl/client/feedback.html'
                                })
                                .state('app', {
                                    abstract: true,
                                    // url: '/app',
                                    templateUrl: 'tpl/app.html',
                                    authenticate: true,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('toaster').
                                                        then(function () {
                                                            return $ocLazyLoad.load(['js/controllers/app.js']);
                                                        }
                                                        );
                                            }]
                                    }
                                })
                                
                                
                                .state('app.home', {
                                    url: '/home',
                                    templateUrl: 'tpl/blank.html',
                                    authenticate: true
                                })
                                // table
                                .state('app.table', {
                                    url: '/list',
                                    template: '<div ui-view></div>',
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load(['datatables']);
                                            }]
                                    }
                                })
                                .state('app.table.list', {
                                    authenticate: true,
                                    url: '/:categorie',
                                    templateUrl: function ($stateParams) {
                                        return 'tpl/' + $stateParams.categorie + '_list.html';
                                    }
                                })


                                // form
                                .state('app.form', {
                                    url: '/form',
                                    template: '<div ui-view class="fade-in"></div>',
                                    authenticate: true,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('ui.select');
                                            }]
                                    }
                                })
                                .state('app.form.elt', {
                                    url: '/:categorie',
                                    templateUrl: function ($stateParams) {
                                        return 'tpl/' + $stateParams.categorie + '_form.html';
                                    },
                                    authenticate: true,
                                    params: {
                                        id: {value: "-1"}
                                    }
                                })
                                .state('app.form.elt.agence', {
                                    views: {
                                        "AgenceListView": {
                                            templateUrl: 'tpl/Agence_list.html'
                                        }
                                    },
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('datatables');
                                            }]

                                    }
                                })
                                .state('app.form.elt.tarif', {
                                    views: {
                                        "TarifListView": {
                                            templateUrl: 'tpl/Tarif_list.html'
                                        }
                                    },
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('datatables');
                                            }]

                                    }
                                })
                                 // settings
                                .state('app.settings', {
                                    url: '/settings',
                                    templateUrl: 'tpl/settings.html',
                                    authenticate: true,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('datatables');
                                            }]

                                    }
                                })
                                 // settings
                                .state('app.employe', {
                                    url: '/employe',
                                    templateUrl: 'tpl/employe.html',
                                    authenticate: true,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('datatables');
                                            }]

                                    }
                                })
                                 
                                // pages
                                .state('app.docs', {
                                    url: '/docs',
                                    templateUrl: 'tpl/docs.html',
                                    authenticate: true
                                })
                                .state('app.changepwd', {
                                    url: '/changepwd',
                                    templateUrl: 'tpl/page_changepwd.html',
                                    authenticate: true,
                                    resolve: {
                                        deps: ['uiLoad',
                                            function (uiLoad) {
                                                return uiLoad.load(['js/controllers/user.js']);
                                            }]
                                    }
                                })
                                .state('app.process', {
                                    url: '/process/:process',
                                    templateUrl: function (stateParams) {
                                        return 'tpl/' + stateParams.process + '.html';
                                    },
                                    authenticate: true

                                })
                                // others
                                .state('lockme', {
                                    url: '/lockme',
                                    templateUrl: 'tpl/page_lockme.html'
                                })
                                .state('access', {
                                    url: '/access',
                                    template: '<div ui-view class="fade-in-right-big smooth"></div>',
                                    authenticate: false,
                                    resolve: {
                                        deps: ['$ocLazyLoad',
                                            function ($ocLazyLoad) {
                                                return $ocLazyLoad.load('toaster').
                                                        then(function () {
                                                            return $ocLazyLoad.load('js/controllers/user.js');
                                                        }
                                                        );
                                            }]
                                    }
                                })
                                .state('access.signin', {
                                    url: '/signin',
                                    templateUrl: 'tpl/page_signin.html'
                                })
                                .state('access.changepwdexp', {
                                    url: '/changepwdexp',
                                    templateUrl: 'tpl/page_changepwdexp.html'
                                })
                                .state('access.forgotpwd', {
                                    url: '/forgotpwd',
                                    templateUrl: 'tpl/page_forgotpwd.html'
                                })
                                .state('access.404', {
                                    url: '/404',
                                    templateUrl: 'tpl/page_404.html',
                                    authenticate: true
                                });
                        ;
                    }
                ]
                );

