/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.view.company.CompanyList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.companyList',
    controller: 'sunerp.controller.company.CompanyListCtr',
    inject: ['companyStore'],
    config: {
        companyStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getCompanyStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Tên công ty', dataIndex: 'name', flex: 1},
            {header: 'Địa chỉ', dataIndex: 'address', flex: 1},
            {header: 'Số điện thoại', dataIndex: 'phone', flex: 1},
            {header: 'Email', dataIndex: 'email', flex: 1},
            {header: 'Mã số thuế', dataIndex: 'mst', flex: 1},
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