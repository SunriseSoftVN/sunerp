/**
 * Created by dungvn3000 on 4/18/14.
 */


Ext.define('sunerp.view.core.BaseReportView', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.ux.IFrame',
        'sunerp.component.DonViCb',
        'sunerp.component.MonthCb',
        'sunerp.component.YearCb',
        'sunerp.component.PhongBanCb'
    ],
    layout: 'fit',
    items: [
        {
            xtype: 'uxiframe'
        }
    ],
    initComponent: function() {
        var me = this;
        me.initTBar();
        me.callParent(arguments);
    },
    initTBar: function () {
        var me = this;
        var tbar = Ext.create('Ext.toolbar.Toolbar', {
            items: [
                {xtype: 'button', action: 'doReport', iconCls: 'report', text: 'Xem báo cáo' },
                {xtype: 'button', action: 'downloadPdf', iconCls: 'pdf', text: 'Download Pdf' },
                {xtype: 'button', action: 'downloadXls', iconCls: 'excel', text: 'Download Excel' }
            ]
        });
        me.tbar = tbar;
    }
});