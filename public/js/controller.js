angular.module('app')

  .controller('mainController', ['$scope', 'mainService', function($scope, mainService){

      $scope.hiWorld = "hello world!";

      $scope.login = function(){mainService.login($scope.uName, $scope.pWord)};

  }])
