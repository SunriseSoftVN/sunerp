/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.role.RoleListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['roleStore'],
    config: {
        roleStore: null
    },
    editView: 'sunerp.view.role.RoleEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getRoleStore();
        this.callParent(arguments);
    }
});