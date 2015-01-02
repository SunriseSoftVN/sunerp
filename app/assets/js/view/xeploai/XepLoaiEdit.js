/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.xeploai.XepLoaiEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.xepLoaiEdit',
    title: 'Edit Xep Loai',
    controller: 'sunerp.controller.xeploai.XepLoaiEditCtr',
    inject: ['userService'],
    requires: [
        'sunerp.store.NhanVienStore'
    ],
    config: {
        userService: null
    },
    month: null,
    year: null,
    initComponent: function () {
        var phongBanId = this.getUserService().getCurrentUser().phongBanId;
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
                                    property: 'phongBanId',
                                    value: String(phongBanId)
                                }
                            ]
                        }),
                        valueField: 'id',
                        displayField: 'fullName'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Xếp loại',
                        name: 'xepLoai',
                        queryMode: 'local',
                        valueField: 'value',
                        displayField: 'name',
                        allowBlank: false,
                        store: Ext.create('Ext.data.Store', {
                            fields: ['value', 'name'],
                            data: [
                                {value: "A", name: "A"},
                                {value: "B", name: "B"},
                                {value: "C", name: "C"}
                            ]
                        })
                    },
                    {
                        fieldLabel: 'Month',
                        xtype: 'numberfield',
                        name: 'month',
                        value: this.month,
                        hidden: true,
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