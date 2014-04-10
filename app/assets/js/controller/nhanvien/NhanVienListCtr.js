/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.NhanVienListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['nhanVienStore'],
    config: {
        nhanVienStore: null
    },
    editView: 'sunerp.view.nhanvien.NhanVienEdit',
    init: function () {
        this.mainStore = this.getNhanVienStore();
        this.callParent(arguments);
    }
});