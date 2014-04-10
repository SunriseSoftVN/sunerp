/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.phongban.PhongBanList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.phongBanList',
    requires: ['sunerp.controller.phongban.PhongBanListCtr'],
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
            {xtype: 'rownumberer'},
            {header: 'Tên', dataIndex: 'name', flex: 1},
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
    getFilterComponents: function () {
        var me = this;
        var comps = me.callParent(arguments);
        var donViCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.DonViCb', {
                name: 'Đơn vị',
                width: 200
            }),
            fieldName: 'donViId',
            store: me.store
        });
        comps = Ext.Array.insert(comps, 1, [donViCbFilter.getComponent()]);
        return comps;
    }
});