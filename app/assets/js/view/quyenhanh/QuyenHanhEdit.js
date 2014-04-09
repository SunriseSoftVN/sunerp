/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.view.quyenhanh.QuyenHanhEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.quyenHanhEdit',
    title: 'Edit Quyền Hành',
    requires: ['sunerp.controller.quyenhanh.QuyenHanhEditCtr'],
    controller: 'sunerp.controller.quyenhanh.QuyenHanhEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'domain',
                        allowBlank: false,
                        fieldLabel: 'Domain'
                    },
                    {
                        xtype: 'checkboxfield',
                        name: 'read',
                        fieldLabel: 'Đọc',
                        inputValue: true,
                        uncheckedValue: false
                    },
                    {
                        xtype: 'checkboxfield',
                        name: 'write',
                        fieldLabel: 'Ghi',
                        inputValue: true,
                        uncheckedValue: false
                    },
                    {
                        xtype: 'comboboxx',
                        name: 'gioiHan',
                        fieldLabel: 'Giới hạn',
                        valueField: 'value',
                        displayField: 'name',
                        allowBlank: false,
                        store: Ext.create('sunerp.store.GioiHanStore')
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Chức vụ',
                        name: 'chucVuId',
                        modelName: 'chucVu',
                        store: Ext.create('sunerp.store.ChucVuStore', {
                            proxy: {
                                type: 'ajax',
                                url: '/chucvu/all',
                                reader: 'json'
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn chức vụ ...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});