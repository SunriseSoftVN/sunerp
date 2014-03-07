Ext.define('sunerp.view.user.UserEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.userEdit',
    title: 'Edit User',

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name : 'username',
                        allowBlank: false,
                        minLength: 4,
                        fieldLabel: 'Username'
                    },
                    {
                        xtype: 'textfield',
                        name : 'fullname',
                        fieldLabel: 'Fullname',
                        allowBlank: false,
                        minLength: 4
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name : 'password',
                        fieldLabel: 'Password',
                        allowBlank: false,
                        minLength: 4
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