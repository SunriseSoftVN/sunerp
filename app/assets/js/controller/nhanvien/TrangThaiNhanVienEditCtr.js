/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.TrangThaiNhanVienEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['trangThaiNhanVienStore'],
    config: {
        trangThaiNhanVienStore: null
    },
    control: {
        nhanVienPicker: {
            selector: 'nhanvienpicker'
        },
        monthCb: {
            selector: 'monthcb'
        }
    },
    init: function () {
        this.mainStore = this.getTrangThaiNhanVienStore();
        this.callParent(arguments);
        if(this.getView().getModel()) {
            var data = this.getView().getModel().data;
            this.getNhanVienPicker().setValue(data.nhanVien.maNv + " - " + data.nhanVien.fullName);
            this.getNhanVienPicker().setSelect(data.nhanVien);
            this.getNhanVienPicker().setReadOnly(true);
        }
    }
});
