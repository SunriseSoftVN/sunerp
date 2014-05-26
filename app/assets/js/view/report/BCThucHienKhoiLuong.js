/**
 * Created by dungvn3000 on 4/21/14.
 */

Ext.define('sunerp.view.report.BCThucHienKhoiLuong', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.BCThucHienKhoiLuongCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'donvicb', addShowAll: false},
            {xtype: 'phongbancb', addShowAll: false, emptyText: 'Phòng ban'},
            {xtype: 'monthcb', width: 100},
            {xtype: 'yearcb', width: 100}
        ]);
    }
});