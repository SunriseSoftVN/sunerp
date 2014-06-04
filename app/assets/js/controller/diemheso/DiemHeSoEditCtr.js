/**
 * Created by dungvn3000 on 5/9/14.
 */

Ext.define('sunerp.controller.diemheso.DiemHeSoEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['diemHeSoStore'],
    config: {
        diemHeSoStore: null
    },
    init: function() {
        this.mainStore = this.getDiemHeSoStore();
        this.callParent(arguments);
    }
});