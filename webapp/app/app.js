var app = angular.module('mainApp',['ngRoute','angularjs-datetime-picker']);

app.run(function($rootScope) {
      $rootScope.gmtDate = new Date('2015-01-01 00:00:00 -00:00');
    });

app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider
                    
      .when('/', {
                templateUrl : 'home.html',
                controller  : 'homeController'
            })


            // route for the about page
            .when('/login', {
                templateUrl : 'login.html',
                controller  : 'loginController'
            })
                        // route for the about page
            .when('/signup', {
                templateUrl : 'signup.html',
                controller  : 'signupController'
            })
            .when('/bookinSearch', {
                templateUrl : 'bookinSearch.html',
                controller  : 'bookinSearchController'
            })
            .when('/employeeDashboard', {
                templateUrl : 'employeeDashboard.html',
                controller  : 'employeeDashboardController'
            })
         
         .otherwise({
                        redirectTo: '/'
                    });



        }]);






    // create the controller and inject Angular's $scope
    app.controller('mainController', function($scope) {

        // create a message to display in our view
        
    });



    // create the controller and inject Angular's $scope
    app.controller('homeController', function($scope) {

        // create a message to display in our view
        
    });


     app.controller('loginController', function($scope) {

         $scope.showLoadingImageFlg = false;
         // create a message to display in our view
         $scope.login = function(){

           $scope.showLoadingImageFlg = true; 

          var email=$scope.loginEmail;
          var password = $scope.loginPassword;

        var data = null;

       var xhr = new XMLHttpRequest();
     xhr.withCredentials = true;

     xhr.addEventListener("readystatechange", function () {
     if (this.readyState === 4) {
   
    if(this.responseText.search(email) != -1){
     $scope.showLoadingImageFlg = false;
     window.location = "#employeeDashboard"
     }else{
        alert("Invalid Login");
     }
    }
  });

xhr.open("POST", "https://book-my-room.appspot.com/_ah/api/bookMyRoomAPI/v1/login?email="+email+"&password="+password);
xhr.setRequestHeader("cache-control", "no-cache");
xhr.setRequestHeader("postman-token", "4055b8df-dbf4-6742-9b3b-260df63ef0b2");

xhr.send(data);




           


         } //login 


       $scope.openSignupForm = function(){

        window.location = "#signup"
       }

    });




     app.controller('signupController', function($scope,$http) {

         $scope.showLoadingImageFlg = false;
         var url = "https://book-my-room.appspot.com/_ah/api/bookMyRoomAPI/v1/signup";

         $scope.signUP = function() {
         $scope.showLoadingImageFlg = true;



       var data = null;
       var email= $scope.employee.email;
       var password = $scope.employee.password;
       var designation = $scope.employee.designation

      var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    
    $scope.showLoadingImageFlg = false;
    window.location = "#login"

  }
});

xhr.open("POST", "https://book-my-room.appspot.com/_ah/api/bookMyRoomAPI/v1/signup?designation="+designation+"&email="+email+"&password="+password);
xhr.setRequestHeader("cache-control", "no-cache");
xhr.setRequestHeader("postman-token", "fa7c76b8-dbcb-01b5-2da1-337d9cf3604b");

xhr.send(data);
          




         } //signup
        



    });











app.controller('bookinSearchController',function($scope){





$scope.search = function (){


var capacity= $scope.booking.capacity;
var endTime= booking.endDate;
var startTime=$scope.booking.startDate;
var facilities = "";
//for facility
if(booking.facility.WB) facilities+="WB,";
if(booking.facility.PR) facilities+="PR,";
if(booking.facility.IC) facilities+="IC,";
if(booking.facility.WF) facilities+="WF,";
if(booking.facility.IF) facilities+="IF,";
if(booking.facility.TC) facilities+="TC,";
if(booking.facility.VC) facilities+="VC,";

var type = $scope.booking.meetingPurpose;

var data = JSON.stringify({
  "capacity": capacity,
  "endTime": "2016-05-22T16:20:00.520Z",
  "facilities": facilities,
  "startTime": "2016-05-22T15:20:00.520Z",
  "type": type,
  "user": {
    "email": "kalyan@gmail.com",
    "hashedPassword": "kalyan"
  }
});

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function () {
  if (this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "https://book-my-room.appspot.com/_ah/api/bookMyRoomAPI/v1/findRoom");
xhr.setRequestHeader("email", "kalyan@gmail.com");
xhr.setRequestHeader("password", "kalyan");
xhr.setRequestHeader("content-type", "application/json");
xhr.setRequestHeader("cache-control", "no-cache");
xhr.setRequestHeader("postman-token", "9b6796a8-526e-4d11-5def-8512871a9e92");

xhr.send(data);

}


});//contrioll



app.controller('employeeDashboardController', function($scope) {

//scope object
    $scope.record = [
    {
        "rommName": "Room01", 

        "startTime": "9",
        "endTime" : "10",
        "bookedBY" : "cool"
    }, 
    {
        "rommName": "Room02", 

        "startTime": "11",
        "endTime" : "12",
        "bookedBY" : "chandan"
    }, 
    {
        "rommName": "Room03", 

        "startTime": "9",
        "endTime" : "10",
        "bookedBY" : "kalyan"
    }, 
    {
        "rommName": "Room04", 

        "startTime": "9",
        "endTime" : "10",
        "bookedBY" : "amit"
    }, 
    {
        "rommName": "Room01", 

        "startTime": "4",
        "endTime" :  "5",
        "bookedBY" : "arun"
    }];


 var roomName = $scope.record.map(function(record){

return record.rommName;

 })

//get the unique room name from data
$scope.cols = [];
$scope.cols.push("Time");
for(var i=0; i<roomName.length ; i++)
{
    if($scope.cols.indexOf(roomName[i]) == -1)
     $scope.cols.push(roomName[i]);
}


//create date object to display
var time = [];

for(var i=9;i<=17;i++){
var tempDate = new Date();
if(i<13){
tempDate.setHours(i);
} else {
    tempDate.setHours(i-12);
}

time.push(tempDate);

}





//create a row to show
 $scope.rows = [];

for(var m=0;m<time.length;m++){

   var tempObject = {};
   tempObject.Time = time[m].getHours()+":00";

for(var i=0;i<$scope.cols.length;i++){


   for(var j=0;j<$scope.record.length;j++){
     
       if($scope.record[j].rommName.indexOf($scope.cols[i]) != -1) {

            
           if( Number(time[m].getHours()) >= Number($scope.record[j].startTime)    &&  Number(time[m].getHours()) <= Number($scope.record[j].endTime)   ) {

                 console.log($scope.cols[i]);
                tempObject[$scope.cols[i]] = $scope.record[j].bookedBY;
                break;

           } else {

            tempObject[$scope.cols[i]] = " ";
           }
       } 

   }//3nd for
  


}//2st for

$scope.rows.push(tempObject);
}//1st for

console.log($scope.rows[0]);
 //$scope.cols = Object.keys($scope.rows[0]);

 });
