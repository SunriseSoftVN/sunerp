/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.setting.CongThucLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['congThucLuongStore'],
    control: {
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
        this.getAddBtn().setDisabled(true);
        this.mainStore = this.getCongThucLuongStore();
        this.callParent(arguments);
    },
    onCopyFromLastMonthBtnClick: function() {
        var me = this;
        Ext.Ajax.request({
            url: '/congthucluong/copyFromLastMonth/' + me.getMonthCb().getValue(),
            success: function (rep) {
                me.mainStore.reload();
            }
        });
    }
});