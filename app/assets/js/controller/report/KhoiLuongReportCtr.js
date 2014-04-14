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
                me.getIframe().load('/report/file/report.pdf')
            }
        });
    }
});