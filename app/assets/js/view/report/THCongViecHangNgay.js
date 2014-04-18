/**
 * Created by dungvn3000 on 4/18/14.
 */

Ext.define('sunerp.view.report.THCongViecHangNgay', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.THCongViecHangNgayCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'donvicb', addShowAll: false, domainKey: 'thcongviechangngay'},
            {xtype: 'phongbancb', addShowAll: false, emptyText: 'Phòng ban', domainKey: 'thcongviechangngay'},
            {xtype: 'monthcb', width: 100},
            {xtype: 'yearcb', width: 100}
        ]);
    }
});