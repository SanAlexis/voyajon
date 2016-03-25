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
                ['$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG',
                    function ($stateProvider, $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {

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
                                .state('landing', {
                                    url: '/landing',
                                    templateUrl: 'tpl/landing.html'
                                })
                                .state('app', {
                                    abstract: true,
                                    // url: '/app',
                                    templateUrl: 'tpl/admin/app.html',
                                    authenticate: true,
                                    resolve: load(['toaster', 'js/controllers/app.js'])
                                })


                                .state('app.home', {
                                    url: '/home',
                                    templateUrl: 'tpl/blank.html',
                                    authenticate: true
                                })
                                // fullCalendar
                                .state('app.calendar', {
                                    url: '/calendar',
                                    templateUrl: 'tpl/admin/Voyage_Calendar.html',
                                    // use resolve to load other dependences
                                    resolve: load(['moment', 'fullcalendar', 'ui.calendar'])
                                })
                                // table
                                .state('app.table', {
                                    url: '/list',
                                    template: '<div ui-view></div>',
                                    resolve: load('smart-table')
                                })
                                .state('app.table.list', {
                                    authenticate: true,
                                    url: '/:categorie',
                                    templateUrl: function ($stateParams) {
                                        return 'tpl/admin/' + $stateParams.categorie + '_list.html';
                                    }
                                })


                                // form
                                .state('app.form', {
                                    url: '/form',
                                    template: '<div ui-view class="fade-in"></div>',
                                    authenticate: true,
                                    resolve: load('ui.select')
                                })
                                .state('app.form.elt', {
                                    url: '/:categorie/:id',
                                    templateUrl: function ($stateParams) {
                                        return 'tpl/admin/' + $stateParams.categorie + '_form.html';
                                    },
                                    authenticate: true,
                                })
                                .state('app.form.elt.agence', {
                                    views: {
                                        "AgenceListView": {
                                            templateUrl: 'tpl/admin/Agence_list.html'
                                        }
                                    },
                                    resolve: load('smart-table')
                                })
                                .state('app.form.elt.tarif', {
                                    views: {
                                        "TarifListView": {
                                            templateUrl: 'tpl/admin/Tarif_list.html'
                                        }
                                    },
                                    resolve: load('smart-table')
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


                        function load(srcs, callback) {
                            return {
                                deps: ['$ocLazyLoad', '$q',
                                    function ($ocLazyLoad, $q) {
                                        var deferred = $q.defer();
                                        var promise = false;
                                        srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                                        if (!promise) {
                                            promise = deferred.promise;
                                        }
                                        angular.forEach(srcs, function (src) {
                                            promise = promise.then(function () {
                                                if (JQ_CONFIG[src]) {
                                                    return $ocLazyLoad.load(JQ_CONFIG[src]);
                                                }
                                                angular.forEach(MODULE_CONFIG, function (module) {
                                                    if (module.name == src) {
                                                        name = module.name;
                                                    } else {
                                                        name = src;
                                                    }
                                                });
                                                return $ocLazyLoad.load(name);
                                            });
                                        });
                                        deferred.resolve();
                                        return callback ? promise.then(function () {
                                            return callback();
                                        }) : promise;
                                    }]
                            }
                        }
                    }
                ]
                );

