/**
 * Created by Phoenix on 17-May-17.
 */

angular.module('appComponent.login').controller('loginCtrl', function ($state, $scope, loginService) {

    var vm = this;

    vm.login = function (pcn, pass) {
        loginService.login(pcn, pass)
            .then(function (response) {
                console.log("success request");
                /*show confirmation modal with possible warning message as 'response.message' if empty then there is no warning for admin
                 if yes is pressed then call deletion function*/
                courseService.deleteCourse(course.code)
                    .then(function (response) {
                        console.log("success deletion");
                        vm.courses.splice(vm.courses.indexOf(course), 1);
                    }, function (error) {
                        console.log("error deletion");
                    });
            }, function (error) {
                console.log("error request");
                console.log(error.message);

            });
    }
});