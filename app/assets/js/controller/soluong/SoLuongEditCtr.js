/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.SoLuongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['SoLuongStore'],
    config: {
        SoLuongStore: null
    },
    init: function() {
        this.mainStore = this.getSoLuongStore();
        this.callParent(arguments);
    }
});