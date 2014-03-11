/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.role.RoleEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['roleStore'],
    config: {
        roleStore: null
    },
    init: function() {
        this.mainStore = this.getRoleStore();
        this.callParent(arguments);
    }
});