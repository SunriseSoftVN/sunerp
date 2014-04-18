/**
 * Created by dungvn3000 on 4/18/14.
 */

Ext.define('sunerp.view.report.THCongViecHangNgay', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.KhoiLuongReportCtr',
    tbar: [
        {xtype: 'donvicb', addShowAll: false},
        {xtype: 'phongbancb', addShowAll: false, emptyText: 'Phòng ban'},
        {xtype: 'monthcb', width: 100},
        {xtype: 'yearcb', width: 100},
        {xtype: 'button', action: 'doReport', iconCls: 'report', text: 'Xem báo cáo' },
        {xtype: 'button', action: 'downloadPdf', iconCls: 'pdf', text: 'Download Pdf' },
        {xtype: 'button', action: 'downloadXls', iconCls: 'excel', text: 'Download Excel' }
    ]
});