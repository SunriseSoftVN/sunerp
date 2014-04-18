/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.THThucHienKhoiLuongCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    inject: ['userService'],
    config: {
        userService: null,
        currentUserGioiHan: null
    },
    control: {
        donViCb: {
            selector: 'donvicb'
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
        me.setCurrentUserGioiHan(me.getUserService().checkGioiHan('ththuchienkhoiluong'));
        var donViId = me.getUserService().getCurrentUser().donViId;
        me.getDonViCb().select(donViId);
        if (me.getCurrentUserGioiHan() == "donvi") {
            me.getDonViCb().hide();
        }
        me.callParent(arguments);
    },
    doReport: function () {
        var me = this;
        me.request('/report/doDonViReport', me.getParams(), function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/doDonViReport?fileType=pdf', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/doDonViReport?fileType=xls', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    getParams: function () {
        var me = this;
        return {
            month: me.getMonthCb().getValue(),
            year: me.getYearCb().getValue(),
            donViId: me.getDonViCb().getValue()
        }
    }
});