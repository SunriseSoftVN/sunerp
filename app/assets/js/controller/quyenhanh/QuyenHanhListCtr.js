/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.controller.quyenhanh.QuyenHanhListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['quyenHanhStore'],
    config: {
        quyenHanhStore: null
    },
    editView: 'sunerp.view.quyenhanh.QuyenHanhEdit',
    searchField: 'domain',
    init: function () {
        this.mainStore = this.getQuyenHanhStore();
        this.callParent(arguments);
    }
});