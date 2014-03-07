Ext.define('sunerp.controller.AuthorityCtr', {
    extend: 'sunerp.controller.BaseController',
    stores: [
        'Authorities'
    ],
    models: ['Authority'],
    views: [
        'authority.AuthorityList',
        'authority.AuthorityEdit'
    ],
    mainStore: 'Authorities',
    editViewName: 'authorityEdit',
    listViewName: 'authorityList',
    searchField: 'domain'
});