/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.nhanvien.HeSoLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['heSoLuongStore'],
    config: {
        heSoLuongStore: null
    },
    control: {
        yearCb: {
            selector: 'yearcb'
        },
        donViCb: {
            selector: 'donvicb',
            listeners: {
                change: 'onDonViCbChange'
            }
        },
        phongBanCb: {
            selector: 'phongbancb'
        }
    },
    editView: 'sunerp.view.nhanvien.HeSoLuongEdit',
    init: function () {
        this.mainStore = this.getHeSoLuongStore();
        this.callParent(arguments);
    },
    showAddPanel: function () {
        var year = this.getYearCb().getValue();
        var view = Ext.create(this.editView, {
            year: year
        });
    },
    onDonViCbChange: function (comp, newValue, oldValue, eOpts) {
        var me = this;
        me.getPhongBanCb().getDonViFilter().setValue(sunerp.Utils.toString(newValue));
        me.getPhongBanCb().getStore().load();
    }
});