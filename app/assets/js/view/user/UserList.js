Ext.define('sunerp.view.user.UserList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.userList',
    store: 'Users',
    initComponent: function () {
        var me = this;

        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Username', dataIndex: 'username', flex: 1},
            {header: 'Fullname', dataIndex: 'fullname', flex: 1},
            {
                header: 'Role',
                dataIndex: 'role.name',
                flex: 1,
                renderer: function(value, metaData, record) {
                    return record.get('role').name;
                }
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