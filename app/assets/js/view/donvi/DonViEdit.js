/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.donvi.DonViEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.donViEdit',
    title: 'Edit DonVi',
    requires: ['sunerp.controller.donvi.DonViEditCtr'],
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
                        xtype: 'comboboxx',
                        fieldLabel: 'Khối đơn vị',
                        name: 'khoiDonViId',
                        modelName: 'khoiDonVi',
                        store: Ext.create('sunerp.store.KhoiDonViStore', {
                            proxy: {
                                type: 'ajax',
                                url: '/khoidonvi/all',
                                reader: 'json'
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn một khối đơn vị...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});