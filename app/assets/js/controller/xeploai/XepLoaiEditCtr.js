/**
 * Created by dungvn3000 on 5/9/14.
 */

Ext.define('sunerp.controller.xeploai.XepLoaiEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['xepLoaiStore'],
    config: {
        xepLoaiStore: null
    },
    init: function() {
        this.mainStore = this.getXepLoaiStore();
        this.callParent(arguments);
    }
});