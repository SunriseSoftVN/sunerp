Ext.define('sunerp.view.user.UserList', {
    extend: 'sunerp.view.core.BaseListView',
    requires: ['sunerp.controller.UserCtr'],
    controller: 'sunerp.controller.UserCtr',
    alias: 'widget.userList',
    inject: ['users'],
    config: {
        users: null
    },
    initComponent: function () {
        var me = this;
        me.store = me.getUsers();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Username', dataIndex: 'username', flex: 1},
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