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
        iframe: {
            selector: 'uxiframe'
        }
    },
    init: function () {
        this.callParent(arguments);
    },
    doReport: function () {
        var me = this;
        Ext.Ajax.request({
            url: '/report/khoiluongreport',
            success: function (response) {
                me.getIframe().load('/report/file/' + response.responseText)
            }
        });
    },
    doDownloadPdf: function () {
        var me = this;
        Ext.Ajax.request({
            url: '/report/khoiluongreport?fileType=pdf',
            success: function (response) {
                me.showMsg('/report/file/' + response.responseText + '?download=true')
            }
        });

    },
    doDownloadXls: function () {
        var me = this;
        Ext.Ajax.request({
            url: '/report/khoiluongreport?fileType=xls',
            success: function (response) {
                me.showMsg('/report/file/' + response.responseText + '?download=true');
            }
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