angular.module('app')

  .factory('mainService', ['$http', '$location', function($http, $location){

    var UserPitch = [];
    var InterstedPitches=[];
    var AllPitches=[];

     ///LOGIN / LOGOUT / SIGNUP

     var login = function(uname, pword) {
       $http.post('/login', {username:uname, password: pword}).then(function(data){
         data.statusText === "OK" ? $location.path('/pitches') : null
       })
     }

     var logout = function(){
       $http.post('/logout').then(function(data){
         console.log(data);
       })
     }

     //uname password firstName lastName
     var signUp = function(a,b,c,d){
       return $http.post('/users/create', {username: a, password: b, firstName: c, lastName: d});
     }



     //USER PITCH ROUTES
     var getUserPitch = function(){
       $http.get('/pitches/owner').then(function(data){
         angular.copy(data.data, UserPitch)
       })
       return UserPitch;
     }

      //title, description,
     var postPitch = function(title, description){
       $http.post('/pitches', {title: title, description: description}).then(function(data){
         console.log(data);
       })
     }

      //title, description,
     var editPitch = function(){
       $http.put('/pitches').then(function(data){
         console.log(data);
       })
     }

     var deletePitch = function(){
       $http.delete('/pitches').then(function(data){
         console.log(data);
       })
     }

     var toggleInterest = function(id){
       $http.put('/pitches/'+id+'/interest').then(function(data){
         console.log(data);
       })
     }

      //ALL PITCHES
      var getInterestedPitches = function(){
        $http.get('/pitches/interest').then(function(data){
          angular.copy(data, InterstedPitches);
        })
        return InterstedPitches;
      }

      var getAllPitches = function(){
        $http.get('/pitches').then(function(data){
          angular.copy(data.data, AllPitches);
          console.log(AllPitches);
        })
        return AllPitches;
      }


    return {

      //user account
      login : login,

      logout: logout,

      signUp : signUp,

      userHasPitch: function(){
        console.log(UserPitch.length);
        if(UserPitch.length >= 1){
          return true;
        }else{
          return false;
        }
      },

      //user pitches

      deletePitch: deletePitch,

      editPitch: editPitch,

      addPitch: postPitch,

      getUserPitch: getUserPitch,

      toggleInterest : toggleInterest,

      //all pitches

      getAll : getAllPitches,

      getInterested: getInterestedPitches,

    }
  }])
