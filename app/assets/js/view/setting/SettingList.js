/**
 * Created by dungvn3000 on 4/21/14.
 */

Ext.define('sunerp.view.setting.SettingList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.congThucTinhLuongList',
    controller: 'sunerp.controller.setting.SettingListCtr',
    inject: ['companySettingStore'],
    config: {
        companySettingStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getCompanySettingStore();
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