angular.module('app')

  .factory('mainService', ['$http', function($http){

    var UserPitch = [];
    var InterstedPitches=[];
    var AllPitches=[];

     ///LOGIN / LOGOUT / SIGNUP

     var login = function(uname, pword) {
       $http.post('/login', {username:uname, password: pword}).then(function(data){
         console.log(data);
       })
     }

     var logout = function(){
       $http.post('/logout').then(function(data){
         console.log(data);
       })
     }

     //uname password firstName lastName
     var signUp = function(a,b,c,d){
       $http.post('/users/create', {username: a, password: b, firstName: c, lastName: d}).then(function(data){
         console.log(data);
       })
     }



     //USER PITCH ROUTES
     var getUserPitch = function(){
       $http.get('/pitches/owner').then(function(data){
         angular.copy(data, UserPitch)
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
          angular.copy(data, AllPitches);
        })
        return AllPitches;
      }


    return {

      //user account
      login : login,

      logout: logout,

      signUp : signUp,

      userHasPitch: function(){
        return UserPitch.length > 1 ? true : false;
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
