/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.view.report.THThucHienKhoiLuong', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.KhoiLuongReportCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'donvicb', addShowAll: false},
            {xtype: 'phongbancb', emptyText: 'Phòng ban'},
            {xtype: 'monthcb', width: 100},
            {xtype: 'yearcb', width: 100}
        ])
    }
});