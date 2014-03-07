Ext.define('sunerp.controller.RoleCtr', {
    extend: 'sunerp.controller.BaseController',
    stores: [
        'Roles'
    ],
    models: ['Role'],
    views: [
        'role.RoleList',
        'role.RoleEdit'
    ],
    mainStore: 'Roles',
    editViewName: 'roleEdit',
    listViewName: 'roleList',
    searchField: 'name'
});