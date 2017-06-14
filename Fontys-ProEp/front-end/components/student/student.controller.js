'use strict';

angular.module('appComponent.student').controller('studentCtrl', function ($state, $scope, studentsResolve, studentService, $mdDialog, EventStudent) {

    var vm = this;
    vm.students = studentsResolve.students;

    // vm.RowNumber = function (student) {
    //     return vm.students.indexOf(student) + 1;
    // };
    //
    // vm.DeleteStudent = function (student) {
    //     studentService.deleteStudent(student.pcn)
    //         .then(function (response) {
    //             console.log("success");
    //             vm.students.splice(vm.students.indexOf(student), 1);
    //         }, function (error) {
    //             console.log("error");
    //         });
    // };

    EventStudent.subscribeOnAStudentAdded($scope, function (event, data) {
        var add = true;
        angular.forEach(vm.students, function (s) {
            if (s.pcn == data.student.pcn) {
                add = false;
            }
        });
        if (add == true) {
            return studentService.addStudent(data.student)
                .then(function (response) {
                    vm.students.push(data.student);
                }, function (error) {
                });
        }
        /*TODO show student already existed notification*/
    });

    EventStudent.subscribeOnAStudentRemoved($scope, function (event, data) {
        return studentService.deleteStudent(data.pcn)
            .then(function (response) { //TODO fix it
                angular.forEach(vm.students, function (s) {
                    if (s.pcn == data.pcn) {
                        var index = vm.students.indexOf(s);
                        vm.students.splice(index, 1);
                    }
                });
            }, function (error) {

            });
    });

    EventStudent.subscribeOnStudentsAdded($scope, function (event, data) {
        var addedStudents = [];
        angular.forEach(data.students, function (s1) {
            var add = true;
            angular.forEach(vm.students, function (s2) {
                if (s1.pcn == s2.pcn) {
                    add = false;
                }
            });
            if (add == true) {
                vm.students.push(s1);
                addedStudents.push(s1);
            }
            /*TODO else show that student already exist*/
        });
        return studentService.addStudents(addedStudents)
            .then(function (response) {
            }, function (error) {

            });
    });

    EventStudent.subscribeOnStudentsRemoved($scope, function (event, data) {
        return studentService.deleteStudents(data.students)
            .then(function (response) {
                angular.forEach(data.students, function (s) {
                    var index = vm.students.indexOf(s);
                    vm.students.splice(index, 1);
                });
            }, function (error) {

            });
    });

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
            templateUrl: './components/student/fontysStudentsDialog/fontys.student.dialog.html',
            clickOutsideToClose: true,
            parent: angular.element(document.body),
            locals: {fontysStudentList: fontysStudentList},
            controller: function () {
                var vm = this;
                vm.allFontysStudents = fontysStudentList; //TODO Check

                vm.close = function () {
                    $mdDialog.cancel();
                };
            },
            controllerAs: 'vmFontysStudentsDialog'

        })
        ;
    };
});