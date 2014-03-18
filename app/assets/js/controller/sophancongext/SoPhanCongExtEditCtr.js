/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.SoPhanCongExtEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['soPhanCongExtStore'],
    config: {
        soPhanCongExtStore: null
    },
    init: function() {
        this.mainStore = this.getSoPhanCongExtStore();
        this.callParent(arguments);
    }
});