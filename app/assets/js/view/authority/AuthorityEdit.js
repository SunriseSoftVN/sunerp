Ext.define('sunerp.view.authority.AuthorityEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.authorityEdit',
    title: 'Edit Authority',
    requires: ['sunerp.controller.authority.AuthorityEditCtr'],
    controller: 'sunerp.controller.authority.AuthorityEditCtr',
    inject: ['roleStore'],
    config: {
        model: null,
        roleStore: null
    },
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
                        store: this.getRoleStore(),
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