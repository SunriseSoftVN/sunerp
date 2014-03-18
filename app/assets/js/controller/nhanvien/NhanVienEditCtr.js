/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.NhanVienEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['nhanVienStore'],
    config: {
        nhanVienStore: null
    },
    init: function() {
        this.mainStore = this.getNhanVienStore();
        this.callParent(arguments);
    }
});
