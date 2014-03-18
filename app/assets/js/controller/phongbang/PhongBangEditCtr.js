/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.phongbang.PhongBangEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['phongBangStore'],
    config: {
        phongBangStore: null
    },
    init: function() {
        this.mainStore = this.getPhongBangStore();
        this.callParent(arguments);
    }
});