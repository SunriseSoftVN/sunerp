/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.khoidonvi.KhoiDonViList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.khoiDonViList',
    requires: ['sunerp.controller.khoidonvi.KhoiDonViListCtr'],
    controller: 'sunerp.controller.khoidonvi.KhoiDonViListCtr',
    inject: ['khoiDonViStore'],
    config: {
        khoiDonViStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getKhoiDonViStore();
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