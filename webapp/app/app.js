var module =angular.module('mainApp',['ngRoute']);


module.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider
                    
      .when('/', {
                templateUrl : 'home.html',
                controller  : 'mainController'
            })


            // route for the about page
            .when('/login', {
                templateUrl : 'login.html',
                controller  : 'loginController'
            })
         
         .otherwise({
                        redirectTo: '/'
                    });



        }]);






    // create the controller and inject Angular's $scope
    app.controller('mainController', function($scope) {

        // create a message to display in our view
        
    });

     app.controller('loginController', function($scope) {

        // create a message to display in our view
        
    });