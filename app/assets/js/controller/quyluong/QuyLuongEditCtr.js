/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.QuyLuongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['quyLuongStore'],
    config: {
        quyLuongStore: null
    },
    init: function() {
        this.mainStore = this.getQuyLuongStore();
        this.callParent(arguments);
    }
});