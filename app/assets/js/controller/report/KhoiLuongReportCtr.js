/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.KhoiLuongReportCtr', {
    extend: 'Deft.mvc.ViewController',
    control: {
        doReportBtn: {
            selector: 'button[action=doReport]',
            listeners: {
                click: 'doReport'
            }
        },
        pdfBtn: {
            selector: 'button[action=downloadPdf]',
            listeners: {
                click: 'doDownloadPdf'
            }
        },
        xlsBtn: {
            selector: 'button[action=downloadXls]',
            listeners: {
                click: 'doDownloadXls'
            }
        },
        donViCb: {
            selector: 'donvicb'
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
        this.callParent(arguments);
    },
    doReport: function () {
        var me = this;
        me.request('/report/khoiluongreport', function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/khoiluongreport?fileType=pdf', function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/khoiluongreport?fileType=xls', function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    request: function (url, success) {
        var me = this;
        Ext.Ajax.request({
            url: url,
            params: {
                month: me.getMonthCb().getValue(),
                year: me.getYearCb().getValue(),
                donViId: me.getDonViCb().getValue(),
                phongBanId: me.getPhongBanCb().getValue()
            },
            method: 'GET',
            success: success
        });
    },
    showMsg: function (url) {
        Ext.Msg.show({
            title: 'Báo cáo',
            msg: 'Tạo báo cáo thành công <a target="_blank" href="' + url + '"><b>download</b></a>',
            icon: Ext.MessageBox.INFO,
            buttons: Ext.MessageBox.OK
        });
    }
});