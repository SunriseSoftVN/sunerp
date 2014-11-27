/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.NhanVienEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['nhanVienStore', 'userService'],
    config: {
        nhanVienStore: null,
        userService: null
    },
    control: {
        maNv: {
            selector: 'textfield[name=maNv]'
        },
        password: {
            selector: 'textfield[name=password]'
        },
        firstName: {
            selector: 'textfield[name=firstName]'
        },
        lastName: {
            selector: 'textfield[name=lastName]'
        },
        chucVuId: {
            selector: 'comboboxx[name=chucVuId]'
        },
        phongBanId: {
            selector: 'comboboxx[name=phongBanId]'
        }
        //heSoLuong: {
        //    selector: 'numberfield[name=heSoLuong]'
        //}
    },
    init: function () {
        this.mainStore = this.getNhanVienStore();
        var gioHan = this.getUserService().checkGioiHan("nhanvien");
        if(gioHan == "donvi") {
            this.getMaNv().setDisabled(true);
            this.getPassword().setDisabled(true);
            this.getFirstName().setDisabled(true);
            this.getLastName().setDisabled(true);
            this.getChucVuId().setDisabled(true);
            //this.getHeSoLuong().setDisabled(true);
        }
        this.callParent(arguments);
    }
});
