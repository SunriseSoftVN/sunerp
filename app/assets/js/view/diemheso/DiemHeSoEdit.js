/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.diemheso.DiemHeSoEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.DiemHeSoEdit',
    title: 'Edit Hệ Số',
    controller: 'sunerp.controller.diemheso.DiemHeSoEditCtr',
    inject: ['userService'],
    requires: [
        'sunerp.store.NhanVienStore'
    ],
    config: {
        userService: null
    },
    year: null,
    initComponent: function () {
        var donViId = this.getUserService().getCurrentUser().donViId;
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'comboboxx',
                        name: 'nhanVienId',
                        allowBlank: false,
                        modelName: 'nhanVien',
                        fieldLabel: 'Họ và Tên',
                        store: Ext.create('sunerp.store.NhanVienStore', {
                            filters: [
                                {
                                    property: 'donViId',
                                    value: String(donViId)
                                }
                            ]
                        }),
                        valueField: 'id',
                        displayField: 'fullName'
                    },
                    {
                        xtype: 'numberfield',
                        fieldLabel: 'Hệ số',
                        name: 'heSo',
                        minValue: 0,
                        allowBlank: false
                    },
                    {
                        fieldLabel: 'Year',
                        xtype: 'numberfield',
                        name: 'year',
                        value: this.year,
                        hidden: true,
                        allowBlank: false
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});