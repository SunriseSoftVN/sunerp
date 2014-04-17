/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.phongban.PhongBanList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.phongBanList',
    controller: 'sunerp.controller.phongban.PhongBanListCtr',
    inject: ['phongBanStore'],
    config: {
        phongBanStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getPhongBanStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Tên', dataIndex: 'name', flex: 1},
            {header: 'Tên viết tắt', dataIndex: 'shortName', flex: 1},
            {
                header: 'Đơn vị',
                dataIndex: 'donVi.name',
                flex: 1
            },
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
        var donViCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.DonViCb', {
                name: 'Đơn vị',
                width: 200
            }),
            fieldName: 'donViId',
            store: me.store
        });
        me.tbar.insert(1, donViCbFilter)
    }
});