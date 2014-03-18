/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.donvi.DonViEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['donViStore'],
    config: {
        donViStore: null
    },
    init: function() {
        this.mainStore = this.getDonViStore();
        this.callParent(arguments);
    }
});