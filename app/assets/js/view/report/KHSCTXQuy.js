/**
 * Created by dungvn3000 on 4/22/14.
 */

Ext.define('sunerp.view.report.KHSCTXQuy', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.KHSCTXQuyCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'donvicb', addShowAll: false, domainKey: me.domainKey},
            {xtype: 'quartercb', width: 100},
            {xtype: 'yearcb', width: 100}
        ])
    }
});