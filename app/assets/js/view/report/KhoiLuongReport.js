/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.view.report.KhoiLuongReport', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.ux.IFrame',
        'sunerp.component.DonViCb',
        'sunerp.component.PhongBanCb'
    ],
    controller: 'sunerp.controller.report.KhoiLuongReportCtr',
    layout: 'fit',
    tbar: [
        {xtype: 'donvicb', addShowAll: false},
        {xtype: 'phongbancb', addShowAll: false},
        { xtype: 'button', text: 'Button 1' }
    ],
    items: [
        {
            xtype: 'uxiframe'
        }
    ]
});