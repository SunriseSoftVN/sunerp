/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.authority.AuthorityEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['authorityStore'],
    config: {
        authorityStore: null
    },
    init: function() {
        this.mainStore = this.getAuthorityStore();
        this.callParent(arguments);
    }
});