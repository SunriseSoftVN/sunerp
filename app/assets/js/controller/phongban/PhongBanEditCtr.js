/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.phongban.PhongBanEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['phongBanStore'],
    config: {
        phongBanStore: null
    },
    init: function() {
        this.mainStore = this.getPhongBanStore();
        this.callParent(arguments);
    }
});