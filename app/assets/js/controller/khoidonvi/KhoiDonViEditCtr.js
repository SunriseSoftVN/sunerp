/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.KhoiDonViEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['khoiDonViStore'],
    config: {
        khoiDonViStore: null
    },
    init: function() {
        this.mainStore = this.getKhoiDonViStore();
        this.callParent(arguments);
    }
});