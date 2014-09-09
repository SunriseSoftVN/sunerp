/**
 * Created by dungvn3000 on 4/21/14.
 */

Ext.define('sunerp.view.setting.CongThucLuongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.congThucLuongList',
    controller: 'sunerp.controller.setting.CongThucLuongListCtr',
    inject: ['congThucLuongStore'],
    config: {
        congThucLuongStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getCongThucLuongStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Tên', dataIndex: 'name', flex: 1},
            {header: 'Value', dataIndex: 'value', flex: 1},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true
            }
        ];
        me.callParent(arguments);
    }
});