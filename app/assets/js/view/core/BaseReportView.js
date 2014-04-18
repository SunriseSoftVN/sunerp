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
    ]
});