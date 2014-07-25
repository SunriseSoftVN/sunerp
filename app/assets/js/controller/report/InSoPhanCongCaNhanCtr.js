/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.InSoPhanCongCaNhanCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    control: {
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
    doReport: function () {
        var me = this;
        me.request('/report/inSoPhanCongCaNhan', me.getParams(), function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/inSoPhanCongCaNhan?fileType=pdf', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/inSoPhanCongCaNhan?fileType=xls', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    getParams: function () {
        var me = this;
        return {
            month: me.getMonthCb().getValue(),
            year: me.getYearCb().getValue()
        }
    }
});