(function () {
    angular
        .module('portal', ['ngRoute', 'ngStorage'])
        .config(config)
        .run();

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/content', {
                templateUrl: 'content/content.html',
                controller: 'contentController'
            })
            .when('/moderate', {
                templateUrl: 'moderate/moderate.html',
                controller: 'moderateController'
            })
            .when('/registration',{
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

})();

angular.module('portal').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.WebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.WebUser;
        $http.defaults.headers.common.Authorization = '';
    };



    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.WebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.about = function (){
        $http.get('http://localhost:5555/auth/info')
            .then(function (response){
                console.log(response)
                alert("Имя пользователя: "+response.data.name + "\nemail: "+ response.data.email)
            });
    };
});