/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.setting.CongThucLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['congThucLuongStore'],
    control: {
        donViCb: {
            selector: 'donvicb',
            listeners: {
                change: 'onDonViCbChange'
            }
        },
        phongBanCb: {
            selector: 'phongbancb'
        },
        monthCb: {
            selector: 'monthcb'
        },
        copyFromLastMonthBtn: {
            selector: 'button[action=copyFromLastMonth]',
            listeners: {
                click: 'onCopyFromLastMonthBtnClick'
            }
        }
    },
    config: {
        congThucLuongStore: null
    },
    editView: 'sunerp.view.setting.CongThucLuongEdit',
    init: function () {
        this.getAddBtn().setVisible(false);
        this.mainStore = this.getCongThucLuongStore();
        this.callParent(arguments);
    },
    onCopyFromLastMonthBtnClick: function() {
        var me = this;
        Ext.Ajax.request({
            url: '/congthucluong/copyFromLastMonth/' + me.getMonthCb().getValue() + "/" + me.getPhongBanCb().getValue(),
            success: function (rep) {
                me.mainStore.reload();
            }
        });
    },
    onDonViCbChange: function (comp, newValue, oldValue, eOpts) {
        var me = this;
        me.getPhongBanCb().getDonViFilter().setValue(sunerp.Utils.toString(newValue));
        me.getPhongBanCb().getStore().load();
    }
});