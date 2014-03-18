/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.soluong.SoLuongEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.soLuongEdit',
    title: 'Edit SoLuong',
    requires: ['sunerp.controller.soluong.SoLuongEditCtr'],
    controller: 'sunerp.controller.soluong.SoLuongEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'chucVu',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Chức vụ'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'heSoLuong',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Hệ số lương'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'luongNd',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Luong ND'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'k2',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'K2'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'luongSP',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Lương SP'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'luongTgCong',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Công'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'luongTgTien',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Tiền'
                    },
                    {
                        xtype: 'datefield',
                        name: 'createdDate',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Ngày tạo'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});