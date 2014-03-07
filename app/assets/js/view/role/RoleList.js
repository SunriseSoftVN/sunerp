Ext.define('sunerp.view.role.RoleList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.roleList',
    store: 'Roles',
    initComponent: function () {
        var me = this;

        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Name', dataIndex: 'name', flex: 1},
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