/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.khoidonvi.KhoiDonViEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.khoiDonViEdit',
    title: 'Edit KhoiDonVi',
    requires: ['sunerp.controller.khoidonvi.KhoiDonViEditCtr'],
    controller: 'sunerp.controller.khoidonvi.KhoiDonViEditCtr',
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
                        xtype: 'comboboxx',
                        fieldLabel: 'Công ty',
                        name: 'companyId',
                        modelName: 'company',
                        store: Ext.create('sunerp.store.CompanyStore', {
                            proxy: {
                                type: 'ajax',
                                url: '/company/all',
                                reader: 'json'
                            }
                        }),
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