/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.HeSoLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['heSoLuongStore'],
    config: {
        heSoLuongStore: null
    },
    editView: 'sunerp.view.nhanvien.NhanVienEdit',
    init: function () {
        this.mainStore = this.getHeSoLuongStore();
        this.callParent(arguments);
    }
});