/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CacKhoanCongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['cacKhoanCongStore'],
    config: {
        cacKhoanCongStore: null
    },
    init: function() {
        this.mainStore = this.getCacKhoanCongStore();
        this.callParent(arguments);
    }
});