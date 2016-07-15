angular.module('app')

  .controller('mainController', ['$scope', 'mainService', function($scope, mainService){

      $scope.loggedIn = function(){
        return mainController.loggedIn();
      }

      $scope.newUserPitch = function(){
        mainService.addPitch($scope.pitchTitle, $scope.pitchDescript)
      }

      $scope.userHasPitch = function(){
        return mainService.userHasPitch();
      }

    //  $scope.allPitches = mainService.getAll();

    //  $scope.userPitch = mainService.getUserPitch();

  //    $scope.InterstedPitches = mainService.getInterested();

    //  $scope.toggleInterest = function(){
  //      mainService.getInterested();
    //  }


  }])
