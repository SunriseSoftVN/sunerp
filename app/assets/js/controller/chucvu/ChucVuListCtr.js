/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.controller.chucvu.ChucVuListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['chucVuStore'],
    config: {
        chucVuStore: null
    },
    editView: 'sunerp.view.chucvu.ChucVuEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getChucVuStore();
        this.callParent(arguments);
    }
});