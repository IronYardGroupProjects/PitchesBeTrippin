angular.module('app')

  .controller('loginController', ['$scope', 'mainService', function($scope, mainService){

        $scope.signupToggled = false;


        $scope.login = function(){
          mainService.login($scope.uName, $scope.pWord)
        };

        $scope.signUp = function(){
          mainService.signUp($scope.signupUser, $scope.signupPassword, $scope.firstName, $scope.lastName).then(function(data){
            data.data === 'OK' ? $scope.signupToggled = false : null;
          })
        };

        $scope.logout = function(){
          mainService.logout();
        };

        $scope.toggleSignup = function(){
          $scope.signupToggled ? $scope.signupToggled = false : $scope.signupToggled = true;
        }
  }])
