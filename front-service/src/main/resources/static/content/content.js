angular.module('portal').controller('contentController', function ($scope, $http, $localStorage, $rootScope) {

 const contextPath = 'http://localhost:5555/core/api/v1/mem/';


    $scope.fillTable = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.mems = response.data;
                console.log($scope.mems);
            });
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.memPortalUser) {
            return true;
        } else {
            return false;
        }
    };


    $scope.decrementLike = function (id){
        $http.get(contextPath + 'likes/' +id)
            .then(function (response){
                $scope.fillTable();
            });
    }

        $scope.fillTable();

    // $scope.fillTableM = function () {
    //     $http.get('http://localhost:8080/moder')
    //         .then(function (response) {
    //             $scope.mems = response.data;
    //             console.log($scope.mems);
    //         });
    // };

    // $scope.moder = function (id){
    //     $http.put('http://localhost:8080/moder/' +id)
    //         .then(function (response){
    //             $scope.fillTable();
    //         });
    // }





    // $scope.deleteStudent = function (id) {
    //     $http.delete('http://localhost:8081/interview/api/v1/student/' + id)
    //         .then(function (response) {
    //             $scope.fillTable();
    //         });
    // }
    //
    // $scope.createNewStudent = function () {
    //     // console.log($scope.newProduct);
    //     $http.post('http://localhost:8081/interview/api/v1/student', $scope.newStudent)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.fillTable();
    //         });
    // }
    //
    // $scope.updateSomeStudent = function (){
    //     $http.put('http://localhost:8081/interview/api/v1/student/update', $scope.updateStudent)
    //         .then(function (response) {
    //             $scope.updateStudent = null;
    //             $scope.fillTable();
    //         })
    // }


});