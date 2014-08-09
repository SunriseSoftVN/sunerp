Ext.define('sunerp.view.khoasophancong.KhoaSoPhanCongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.donViList',
    controller: 'sunerp.controller.khoasophancong.KhoaSoPhanCongListCtr',
    inject: ['khoaSoPhanCongStore'],
    config: {
        khoaSoPhanCongStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getKhoaSoPhanCongStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Tên', dataIndex: 'name', flex: 1},
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
        me.tbar.insert(1, [momthCbFilter]);
    }
});