/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CacKhoangTruEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['cacKhoangTruStore'],
    config: {
        cacKhoangTruStore: null
    },
    init: function() {
        this.mainStore = this.getCacKhoangTruStore();
        this.callParent(arguments);
    }
});