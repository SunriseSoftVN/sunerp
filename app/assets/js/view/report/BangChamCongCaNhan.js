Ext.define('sunerp.view.report.BangChamCongCaNhan', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.BangChamCongCaNhanCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'monthcb', width: 100},
            {xtype: 'yearcb', width: 100}
        ]);
    }
});