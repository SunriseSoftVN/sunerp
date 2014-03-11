Ext.define('sunerp.view.authority.AuthorityList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.authorityList',
    requires: ['sunerp.controller.authority.AuthorityListCtr'],
    controller: 'sunerp.controller.authority.AuthorityListCtr',
    inject: ['authorityStore'],
    config: {
        authorityStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getAuthorityStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Domain', dataIndex: 'domain', flex: 1},
            {header: 'Role', dataIndex: 'role.name', flex: 1},
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