/**
 * Created by dungvn3000 on 4/22/14.
 */

Ext.define('sunerp.view.report.InSoPhanCongCaNhan', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.InSoPhanCongCaNhanCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'monthcb', width: 100},
            {xtype: 'yearcb', width: 100}
        ]);
    }
});