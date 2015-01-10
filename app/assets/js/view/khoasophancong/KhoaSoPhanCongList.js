Ext.define('sunerp.view.khoasophancong.KhoaSoPhanCongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.donViList',
    controller: 'sunerp.controller.khoasophancong.KhoaSoPhanCongListCtr',
    inject: ['khoaSoPhanCongStore'],
    config: {
        khoaSoPhanCongStore: null
    },
    searchField: 'donVi.name',
    initComponent: function () {
        var me = this;
        me.store = this.getKhoaSoPhanCongStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Tên', dataIndex: 'donVi.name', flex: 1},
            {
                header: 'Khóa', dataIndex: 'lock', width: 90,
                xtype: 'checkcolumn'
            },
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true
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
        var yearCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.YearCb', {
                name: 'Năm',
                width: 70
            }),
            fieldName: 'year',
            store: me.store
        });
        var saveButton = Ext.create('Ext.Button', {
            text: 'Lưu',
            tooltip: 'Lưu',
            iconCls: 'save',
            action: 'save'
        });
        me.tbar.insert(1, [momthCbFilter, yearCbFilter, saveButton]);
    }
});