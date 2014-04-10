/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.NhanVienListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['nhanVienStore'],
    config: {
        nhanVienStore: null,
        donViFilter: null
    },
    control: {
        phongBanCb: {
            selector: 'phongbancb',
            listeners: {
                change: 'onChange'
            }
        }
    },
    editView: 'sunerp.view.nhanvien.NhanVienEdit',
    init: function () {
        this.mainStore = this.getNhanVienStore();
        this.callParent(arguments);
    },
    afterInit: function () {
        this.mainStore.clearFilter(true);
        this.setDonViFilter(new Ext.util.Filter({
            property: 'phongBanId',
            value: null
        }));
        this.mainStore.addFilter(this.getDonViFilter(), true);
    },
    onChange: function (comp, newValue, oldValue, eOpts) {
        this.getDonViFilter().setValue(sunerp.Utils.toString(newValue));
        this.mainStore.loadPage(1);
    }
});