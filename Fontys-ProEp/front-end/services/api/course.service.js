'use strict';

/**
 *this service provides all the http requests to back-end related to person.
 */
angular.module('appServiceAPI').service('courseService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    // var secure = '/secure';
    var baseUrl = http + localhost + ':8090/menu-items';

    self.createPage = function (page) {
        return $http.post(baseUrl + secure, page);
    };

    self.findOneMenuItem = function (id) {
        return $http.get(baseUrl + '/' + id);
    };

    self.findAllMenuItems = function () {
        return $http.get(baseUrl);
    };

    /**
     * gets all the menuItems excluding the sub pages
     * @returns {HttpPromise}
     */
    self.findAllTopMenuItems = function () {
        return $http.get(baseUrl + '/top');
    };

    /**
     * gets all the top menuItems which are not draft(published) together with their published(live) sub pages
     * @returns {HttpPromise}
     */
    self.findAllTopLiveMenuItems = function () {
        return $http.get(baseUrl + '/top/live');
    };


    /**
     * gets all the top menuItems which are in trash
     * @returns {HttpPromise}
     */
    self.findAllTrashTopMenuItems = function () {
        return $http.get(baseUrl + '/trash');
    };

    /**
     * gets the top menuItem of a sub home
     * @returns {HttpPromise}
     */
    self.findParentMenuItem = function (childId) {
        return $http.get(baseUrl + '/parent/child/' + childId);
    };

    self.updateMenuItem = function (menuItem) {
        return $http.put(baseUrl + secure, menuItem);
    };

    /**
     * updates the top menuItem of a sub home
     * the parent reference of a sub home either changes to another top menuItem
     * or
     * it sends -1 to make the sub home become top itself(by removing the parent reference in back-end)
     * @returns {HttpPromise}
     */
    self.updateParent = function (menuItem, parentId) {
        return $http.put(baseUrl + secure + '/set-parent/' + parentId, menuItem);
    };

    /**
     * restore home to draft pages from trash section.
     * @param menuItem
     * @returns {HttpPromise}
     */
    self.restoreMenuItem = function (menuItem) {
        return $http.put(baseUrl + secure + '/restore', menuItem);
    };

    /**
     * updates the changes in sequences of the menuItems.
     * @param menuItems
     * @returns {HttpPromise}
     */
    self.updateAllChangedSequence = function (menuItems) {
        return $http.put(baseUrl + secure + '/afterDnD', menuItems);
    };

    /**
     * request to update sequence when a menuItem is removed from the list, deletes the home by given Id and send it to trash pages.
     * @param menuItemId
     * @returns {*}
     */
    self.deletePageByMenuItemAndUpdateSequences = function (menuItemId) {
        return $http.delete(baseUrl + secure + '/send-to-trash/?menuItemId=' + menuItemId);
    };

    self.deletePageForever = function (menuItemId) {
        return $http.delete(baseUrl + secure + '/delete/?menuItemId=' + menuItemId);
    };

    self.clearTrash = function () {
        return $http.delete(baseUrl + secure + '/clear-trash');
    };
});