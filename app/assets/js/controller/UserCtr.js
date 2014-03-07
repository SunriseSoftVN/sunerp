Ext.define('sunerp.controller.UserCtr', {
    extend: 'sunerp.controller.BaseController',
    stores: [
        'Users'
    ],
    models: ['User'],
    views: [
        'user.UserList',
        'user.UserEdit'
    ],
    mainStore: 'Users',
    editViewName: 'userEdit',
    listViewName: 'userList',
    searchField: 'username'
});