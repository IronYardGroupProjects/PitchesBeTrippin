angular.module('app')

  .controller('mainController', ['$scope', 'mainService', function($scope, mainService){


      $scope.userHasPitch = false;

      $scope.loggedIn = function(){
        return mainController.loggedIn();
      }

      $scope.newUserPitch = function(){
        mainService.addPitch($scope.pitchTitle, $scope.pitchDescript)
      }


      $scope.userHasPitch = mainService.userHasPitch();

      $scope.allPitches = mainService.getAll();

      $scope.userPitch = mainService.getUserPitch();

      $scope.InterstedPitches = mainService.getInterested();

      $scope.toggleInterest = function(pitch){
        console.log(pitch);
        mainService.toggleInterest(pitch.id);
      }


  }])
