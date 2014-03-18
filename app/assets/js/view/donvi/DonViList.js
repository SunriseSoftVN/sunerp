/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.donvi.DonViList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.donViList',
    requires: ['sunerp.controller.donvi.DonViListCtr'],
    controller: 'sunerp.controller.donvi.DonViListCtr',
    inject: ['donViStore'],
    config: {
        donViStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getDonViStore();
        me.columns = [
            {xtype: 'rownumberer'},
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
    }
});