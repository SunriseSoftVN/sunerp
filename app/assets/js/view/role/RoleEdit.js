Ext.define('sunerp.view.role.RoleEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.roleEdit',
    title: 'Edit Role',
    requires: ['sunerp.controller.role.RoleEditCtr'],
    controller: 'sunerp.controller.role.RoleEditCtr',
    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        minLength: 4,
                        fieldLabel: 'Name'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});