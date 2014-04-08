/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.phongban.PhongBanEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.phongBangEdit',
    title: 'Edit PhongBang',
    requires: ['sunerp.controller.phongban.PhongBanEditCtr'],
    controller: 'sunerp.controller.phongban.PhongBanEditCtr',
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
                        fieldLabel: 'Đơn vị',
                        name: 'donViId',
                        modelName: 'donVi',
                        store: Ext.create('sunerp.store.DonViStore', {
                            proxy: {
                                type: 'ajax',
                                url: '/donvi/all',
                                reader: 'json'
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn một đơn vị...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});