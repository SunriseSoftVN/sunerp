/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.NhanVienListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['nhanVienStore'],
    config: {
        nhanVienStore: null
    },
    editView: 'sunerp.view.authority.NhanVienEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getNhanVienStore();
        this.callParent(arguments);
    }
});