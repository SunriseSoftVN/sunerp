/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.view.report.KhoiLuongReport', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.ux.IFrame',
        'sunerp.component.DonViCb',
        'sunerp.component.MonthCb',
        'sunerp.component.YearCb',
        'sunerp.component.PhongBanCb'
    ],
    controller: 'sunerp.controller.report.KhoiLuongReportCtr',
    layout: 'fit',
    tbar: [
        {xtype: 'donvicb', addShowAll: false},
        {xtype: 'phongbancb', addShowAll: false, emptyText: 'Phòng ban'},
        {xtype: 'monthcb', width: 100},
        {xtype: 'yearcb', width: 100},
        {xtype: 'button', action: 'doReport', iconCls: 'report', text: 'Xem báo cáo' },
        {xtype: 'button', iconCls: 'pdf', text: 'Download Pdf' },
        {xtype: 'button', iconCls: 'excel', text: 'Download Excel' }
    ],
    items: [
        {
            xtype: 'uxiframe'
        }
    ]
});