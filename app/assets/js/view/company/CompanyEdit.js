/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.view.company.CompanyEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.companyEdit',
    title: 'Edit Company',
    controller: 'sunerp.controller.company.CompanyEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        name: 'name',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Tên'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        name: 'address',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Địa chỉ'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        minLength: 4,
                        name: 'phone',
                        allowBlank: false,
                        fieldLabel: 'Số điện thoại'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        name: 'email',
                        vtype: 'email',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Email'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        minLength: 4,
                        name: 'mst',
                        allowBlank: false,
                        fieldLabel: 'Mã số thuế'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});