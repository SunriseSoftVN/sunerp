/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.phongbang.PhongBangList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.phongBangList',
    requires: ['sunerp.controller.phongbang.PhongBangListCtr'],
    controller: 'sunerp.controller.phongbang.PhongBangListCtr',
    inject: ['phongBangStore'],
    config: {
        phongBangStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getPhongBangStore();
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
    }
});