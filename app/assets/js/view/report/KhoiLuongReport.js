/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.view.report.KhoiLuongReport', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.ux.IFrame'
    ],
    controller: 'sunerp.controller.report.KhoiLuongReportCtr',
    layout: 'fit',
    tbar: [
        { xtype: 'button', text: 'Button 1' }
    ],
    items: [
        {
            xtype: 'uxiframe'
        }
    ]
});