/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.KHSCTXQuyCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    control: {
        donViCb: {
            selector: 'donvicb'
        },
        quartercb: {
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
            quarter: me.geQuarterCb().getValue(),
            year: me.getYearCb().getValue(),
            donViId: me.getDonViCb().getValue()
        }
    }
});