/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.controller.quyenhanh.QuyenHanhEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['quyenHanhStore'],
    config: {
        quyenHanhStore: null
    },
    init: function() {
        this.mainStore = this.getQuyenHanhStore();
        this.callParent(arguments);
    }
});