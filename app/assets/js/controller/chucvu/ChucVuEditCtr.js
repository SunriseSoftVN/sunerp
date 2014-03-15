/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.controller.chucvu.ChucVuEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['chucVuStore'],
    config: {
        chucVuStore: null
    },
    init: function() {
        this.mainStore = this.getChucVuStore();
        this.callParent(arguments);
    }
});
