/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.HeSoLuongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['heSoLuongStore'],
    config: {
        heSoLuongStore: null
    },
    control: {
        nhanVienPicker: {
            selector: 'nhanvienpicker'
        }
    },
    init: function () {
        this.mainStore = this.getHeSoLuongStore();
        this.callParent(arguments);
        if(this.getView().getModel()) {
            var data = this.getView().getModel().data;
            this.getNhanVienPicker().setValue(data.nhanVien.maNv + " - " + data.nhanVien.fullName);
            this.getNhanVienPicker().setSelect(data.nhanVien);
            this.getNhanVienPicker().setReadOnly(true);
        }
    }
});
