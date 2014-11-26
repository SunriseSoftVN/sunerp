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
        maNv: {
            selector: 'textfield[name=maNv]'
        },
        heSoLuong: {
            selector: 'numberfield[name=heSoLuong]'
        }
    },
    init: function () {
        this.mainStore = this.getHeSoLuongStore();
        this.callParent(arguments);
    }
});
