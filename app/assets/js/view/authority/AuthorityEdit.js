Ext.define('sunerp.view.authority.AuthorityEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.authorityEdit',
    title: 'Edit Authority',

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name : 'domain',
                        allowBlank: false,
                        fieldLabel: 'Domain'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Role',
                        name: 'roleId',
                        store: Ext.create('sunerp.store.Roles', {
                            proxy: {
                                type: 'ajax',
                                url: '/role/all',
                                reader: 'json'
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Select a role...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});