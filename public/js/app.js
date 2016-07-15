angular.module('app', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider){
      $routeProvider
        .when('/', {
          templateUrl:'views/login.html',
          controller:'loginController as lgnCTRL'
        })
        .when('/pitches', {
          templateUrl:'views/projects.html',
          controller:'mainController as mainCTRL'
        })
        .otherwise({
          redirectTo: '/404'
        });
    }]);
