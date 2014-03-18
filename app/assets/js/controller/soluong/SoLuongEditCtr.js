/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.soluong.SoLuongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['soLuongStore'],
    config: {
        soLuongStore: null
    },
    init: function() {
        this.mainStore = this.getSoLuongStore();
        this.callParent(arguments);
    }
});