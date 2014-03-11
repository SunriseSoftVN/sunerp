/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.authority.AuthorityListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['authorityStore'],
    config: {
        authorityStore: null
    },
    editView: 'sunerp.view.authority.AuthorityEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getAuthorityStore();
        this.callParent(arguments);
    }
});