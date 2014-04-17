/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.donvi.DonViEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.donViEdit',
    title: 'Edit DonVi',
    controller: 'sunerp.controller.donvi.DonViEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'name',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Tên'
                    },
                    {
                        xtype: 'textfield',
                        name: 'shortName',
                        fieldLabel: 'Tên viết tắt'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Công ty',
                        name: 'companyId',
                        modelName: 'company',
                        store: Ext.create('sunerp.store.CompanyStore'),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn một công ty...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});