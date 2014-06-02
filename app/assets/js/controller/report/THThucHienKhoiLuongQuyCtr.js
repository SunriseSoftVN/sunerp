/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.THThucHienKhoiLuongQuyCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    control: {
        donViCb: {
            selector: 'donvicb'
        },
        quarterCb: {
            selector: 'quartercb'
        },
        yearCb: {
            selector: 'yearcb'
        },
        iframe: {
            selector: 'uxiframe'
        }
    },
    doReport: function () {
        var me = this;
        me.request('/report/doThKhoiLuongQuyReport', me.getParams(), function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/doThKhoiLuongQuyReport?fileType=pdf', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/doThKhoiLuongQuyReport?fileType=xls', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    getParams: function () {
        var me = this;
        return {
            quarter: me.getQuarterCb().getValue(),
            year: me.getYearCb().getValue(),
            donViId: me.getDonViCb().getValue()
        }
    }
});