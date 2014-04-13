/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.view.chucvu.ChucVuList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.chucVuList',
    controller: 'sunerp.controller.chucvu.ChucVuListCtr',
    inject: ['chucVuStore'],
    config: {
        chucVuStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getChucVuStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Tên chức vụ', dataIndex: 'name', flex: 1},
            {header: 'Phụ cấp trách nhiệm', dataIndex: 'phuCapTrachNhiem', flex: 1},
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