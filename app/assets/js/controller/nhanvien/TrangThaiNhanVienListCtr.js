/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.TrangThaiNhanVienListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['trangThaiNhanVienStore'],
    config: {
        trangThaiNhanVienStore: null
    },
    control: {
        yearCb: {
            selector: 'yearcb'
        }
    },
    editView: 'sunerp.view.nhanvien.TrangThaiNhanVienEdit',
    init: function () {
        this.mainStore = this.getTrangThaiNhanVienStore();
        this.callParent(arguments);
    },
    showAddPanel: function () {
        var year = this.getYearCb().getValue();
        var view = Ext.create(this.editView, {
            year: year
        });
    }
});