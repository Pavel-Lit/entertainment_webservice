angular.module('portal').controller('registrationController', function ($scope, $http, $location) {

    $scope.tryToRegistration = function () {
        if($scope.newUser.password == $scope.newUser.confirmPassword){
            $http.post('http://localhost:5555/auth/registaration', $scope.newUser)
                .then(function successCallback(response) {
                    $location.path('/');
                });
        } else {
            alert("Пароли не соответствуют друг другу")
        }

    };

});
