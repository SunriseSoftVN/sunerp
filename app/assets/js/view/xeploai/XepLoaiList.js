/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.view.xeploai.XepLoaiList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.xepLoaiList',
    requires: [
        'sunerp.component.MonthCb'
    ],
    controller: 'sunerp.controller.xeploai.XepLoaiListCtr',
    inject: ['xepLoaiStore'],
    config: {
        xepLoaiStore: null
    },
    searchField: 'nhanVien.firstName',
    initComponent: function () {
        var me = this;
        me.store = this.getXepLoaiStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Họ tên', dataIndex: 'nhanVien.firstName', flex: 1},
            {header: 'Xếp loại', dataIndex: 'xepLoai', flex: 1},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];
        me.callParent(arguments);
    },
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        var momthCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.MonthCb', {
                name: 'Tháng',
                width: 100
            }),
            fieldName: 'month',
            store: me.store
        });
        me.tbar.insert(1, [momthCbFilter])
    }
});