Ext.define('sunerp.view.user.UserEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.userEdit',
    title: 'Edit User',
    requires: ['sunerp.controller.user.UserEditCtr'],
    controller: 'sunerp.controller.user.UserEditCtr',
    inject: ['userStore', 'roleStore'],
    config: {
        model: null,
        userStore: null,
        roleStore: null
    },
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'username',
                        allowBlank: false,
                        minLength: 4,
                        fieldLabel: 'Username'
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'password',
                        fieldLabel: 'Password',
                        allowBlank: false,
                        minLength: 4
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