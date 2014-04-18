/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.THCongViecHangNgayCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    inject: ['userService'],
    config: {
        userService: null,
        currentUserGioiHan: null
    },
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
        yearCb: {
            selector: 'yearcb'
        },
        iframe: {
            selector: 'uxiframe'
        }
    },
    init: function () {
        var me = this;
        me.setCurrentUserGioiHan(me.getUserService().checkGioiHan('thcongviechangngay'));
        var donViId = me.getUserService().getCurrentUser().donViId;
        var phongBanId = me.getUserService().getCurrentUser().phongBanId;
        me.getDonViCb().select(donViId);
        if (me.getCurrentUserGioiHan() == "phongban") {
            me.getDonViCb().hide();
            me.getPhongBanCb().select(phongBanId);
            me.getPhongBanCb().hide();
        } else if (me.getCurrentUserGioiHan() == "donvi") {
            me.getDonViCb().hide();
        }
        me.callParent(arguments);
    },
    doReport: function () {
        var me = this;
        me.request('/report/doPhongBanReport', me.getParams(), function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/doPhongBanReport?fileType=pdf', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/doPhongBanReport?fileType=xls', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    getParams: function () {
        var me = this;
        return {
            month: me.getMonthCb().getValue(),
            year: me.getYearCb().getValue(),
            donViId: me.getDonViCb().getValue(),
            phongBanId: me.getPhongBanCb().getValue()
        }
    },
    onDonViCbChange: function (comp, newValue, oldValue, eOpts) {
        var me = this;
        me.getPhongBanCb().getDonViFilter().setValue(sunerp.Utils.toString(newValue));
        me.getPhongBanCb().getStore().load();
    }
});