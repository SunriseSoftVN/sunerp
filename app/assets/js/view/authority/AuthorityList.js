Ext.define('sunerp.view.authority.AuthorityList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.authorityList',
    store: 'Authorities',
    initComponent: function () {
        var me = this;

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