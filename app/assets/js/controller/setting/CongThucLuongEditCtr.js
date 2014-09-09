/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.setting.CongThucLuongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['congThucLuongStore'],
    config: {
        congThucLuongStore: null
    },
    init: function() {
        this.mainStore = this.getCongThucLuongStore();
        this.callParent(arguments);
    }
});