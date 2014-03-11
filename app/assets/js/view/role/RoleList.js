Ext.define('sunerp.view.role.RoleList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.roleList',
    requires: ['sunerp.controller.role.RoleListCtr'],
    controller: 'sunerp.controller.role.RoleListCtr',
    inject: ['roleStore'],
    config: {
        roleStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = me.getRoleStore();
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