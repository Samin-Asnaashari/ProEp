'use strict';

angular.module('appComponent.student').controller('studentCtrl', function ($state, $scope, studentsResolve, studentService, $mdDialog) {

    var vm = this;
    vm.students = studentsResolve.students;
    vm.RowNumber = function (student) {
        return vm.students.indexOf(student) + 1;
    };

    vm.DeleteStudent = function (student) {
        studentService.deleteStudent(student.pcn)
            .then(function (response) {
                console.log("success");
                vm.students.splice(vm.students.indexOf(student), 1);
            }, function (error) {
                console.log("error");
            });
    };

    vm.goToFontysStudentDialog = function () {
        return studentService.getAllFontysStudents()
            .then(function (response) {
                var allStudents = [];
                allStudents = response.data;
                vm.showDialog(allStudents);
            }, function (error) {

            });
    };

    vm.showDialog = function (fontysStudentList) {
        $mdDialog.show({
            templateUrl: './components/student/fontysStudentDialog/fontys.student.dialog.html',
            clickOutsideToClose: true,
            parent: angular.element(document.body),
            locals: {fontysStudentList: fontysStudentList},
            controller: function (studentService) {
                var vm = this;
                vm.allFontysStudents = fontysStudentList; //TODO Check

                vm.close = function () {
                    $mdDialog.cancel();
                };
            }
            ,
            controllerAs: 'vmFontysStudentsDialog'

        })
        ;
    };
});