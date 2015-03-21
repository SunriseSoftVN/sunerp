/**
 * Created by dungvn3000 on 4/18/14.
 */

Ext.define('sunerp.controller.core.BaseReportCtr', {
    extend: 'Deft.mvc.ViewController',
    constructor: function (config) {
        this.control['doReportBtn'] = {
            selector: 'button[action=doReport]',
            listeners: {
                click: 'doReport'
            }
        };
        this.control['pdfBtn'] = {
            selector: 'button[action=downloadPdf]',
            listeners: {
                click: 'doDownloadPdf'
            }
        };
        this.control['xlsBtn'] = {
            selector: 'button[action=downloadXls]',
            listeners: {
                click: 'doDownloadXls'
            }
        };
        this.callParent(config);
    },
    doReport: function () {

    },
    doDownloadPdf: function () {

    },
    doDownloadXls: function () {

    },
    request: function (url, params, success) {
        var me = this;
        Ext.Ajax.request({
            url: url,
            params: params,
            method: 'GET',
            timeout: 90000,
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