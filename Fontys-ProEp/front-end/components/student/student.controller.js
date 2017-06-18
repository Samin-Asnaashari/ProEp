'use strict';

angular.module('appComponent.student').controller('studentCtrl', function (Notification, $state, $scope, studentsResolve, studentService, $mdDialog, EventStudent) {

    var vm = this;
    vm.students = studentsResolve.students;

    // vm.RowNumber = function (student) {
    //     return vm.students.indexOf(student) + 1;
    // };

    EventStudent.subscribeOnAStudentAdded($scope, function (event, data) {
        var add = true;
        angular.forEach(vm.students, function (s) {
            if (s.pcn === data.student.pcn) {
                add = false;
            }
        });
        if (add === true) {
            return studentService.addStudent(data.student)
                .then(function (response) {
                    vm.students.push(data.student);
                }, function (error) {
                    Notification.error("Error adding student!");
                });
        }else{
            Notification.error(data.student.pcn + " is already in the list!");
        }
    });

    EventStudent.subscribeOnAStudentRemoved($scope, function (event, data) {
        return studentService.deleteStudent(data.pcn)
            .then(function (response) {
                angular.forEach(vm.students, function (s) {
                    if (s.pcn === data.pcn) {
                        var index = vm.students.indexOf(s);
                        vm.students.splice(index, 1);
                    }
                });
            }, function (error) {
                Notification.error("Error deleting " + data.firstName + "!");
            });
    });

    EventStudent.subscribeOnStudentsAdded($scope, function (event, data) {
        var addedStudents = [];
        angular.forEach(data.students, function (s1) {
            var add = true;
            angular.forEach(vm.students, function (s2) {
                if (s1.pcn === s2.pcn) {
                    add = false;
                }
            });
            if (add == true) {
                vm.students.push(s1);
                addedStudents.push(s1);
            }else{
                Notification.error(s1.pcn + " is already in the list!");
            }
        });
        return studentService.addStudents(addedStudents)
            .then(function (response) {
            }, function (error) {
                Notification.error("Error adding students!");
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
                Notification.error("Error deleting students!");
            });
    });

    vm.goToFontysStudentDialog = function () {
        return studentService.getAllFontysStudents()
            .then(function (response) {
                var allStudents = [];
                allStudents = response.data;
                vm.showDialog(allStudents);
            }, function (error) {
                Notification.error("Error showing students!");
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
                vm.allFontysStudents = fontysStudentList;

                vm.close = function () {
                    $mdDialog.cancel();
                };
            },
            controllerAs: 'vmFontysStudentsDialog'

        })
        ;
    };
});