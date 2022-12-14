angular.module('portal').controller('moderateController', function ($scope, $http, $location) {


    const contextPathModerate = 'http://localhost:5555/moderate';
    const contextPathContents = 'http://localhost:5555/core/api/v1/mem/';

    $scope.fillTable = function (page) {
        $http({
            url: contextPathModerate,
            method: 'GET',
            params: {
                page: page,
            }
        }).then(function (response) {
            $scope.mems = response.data;
            $scope.counts = Object.keys(response.data).length;
        });
    };

    $scope.deleteContents = function (id) {
        $http.delete(contextPathContents + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };


    const $button_inc = document.querySelector('.increment-btn');
    const $button_dec = document.querySelector('.decrement-btn');
    const $counter = document.querySelector('.counter');

    $button_inc.addEventListener('click', function () {

        if ($scope.counts > 1){
            $counter.value = parseInt($counter.value) + 1
        }
        $scope.fillTable($counter.value);
    }, false);

    $button_dec.addEventListener('click', function () {
        if ($counter.value > 1){
            $counter.value = parseInt($counter.value) - 1;
        } else $counter.value = 1
        $scope.fillTable($counter.value);
    }, false);

$scope.moder = function (id) {
    $http.get(contextPathModerate + '/' + id)
        .then(function (response) {
            $scope.fillTable();
        });
}


// $scope.fillTableM = function () {
//     $http.get('http://localhost:8080/moder')
//         .then(function (response) {
//             $scope.mems = response.data;
//             console.log($scope.mems);
//         });
// };


// $scope.incrementLike = function (id){
//     $http.get('http://localhost:8081/demo/api/v1/mem/inc/' +id)
//         .then(function (response){
//             $scope.fillTable();
//         });
// }
// $scope.decrementLike = function (id){
//     $http.get('http://localhost:8081/demo/api/v1/mem/dec/' +id)
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

$scope.fillTable();
})
;