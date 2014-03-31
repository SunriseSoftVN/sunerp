/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.view.quyenhanh.QuyenHanhList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.quyenHanhList',
    requires: ['sunerp.controller.quyenhanh.QuyenHanhListCtr'],
    controller: 'sunerp.controller.quyenhanh.QuyenHanhListCtr',
    inject: ['quyenHanhStore'],
    config: {
        quyenHanhStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getQuyenHanhStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Domain', dataIndex: 'domain', flex: 1},
            {header: 'Read', dataIndex: 'read', flex: 1, xtype: 'booleancolumn'},
            {header: 'Write', dataIndex: 'write', flex: 1, xtype: 'booleancolumn'},
            {header: 'Show all', dataIndex: 'showAll', flex: 1, xtype: 'booleancolumn'},
            {header: 'Chức vụ', dataIndex: 'chucVu.name', flex: 1},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];
        me.callParent(arguments);
    }
});