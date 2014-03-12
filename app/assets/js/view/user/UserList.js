Ext.define('sunerp.view.user.UserList', {
    extend: 'sunerp.view.core.BaseListView',
    requires: ['sunerp.controller.user.UserListCtr'],
    controller: 'sunerp.controller.user.UserListCtr',
    alias: 'widget.userList',
    inject: ['userStore'],
    config: {
        userStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = me.getUserStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Username', dataIndex: 'username', flex: 1},
            {
                header: 'Role',
                dataIndex: 'role.name',
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