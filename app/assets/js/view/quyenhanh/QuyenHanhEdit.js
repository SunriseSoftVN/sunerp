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
                        name: 'write',
                        fieldLabel: 'Write',
                        inputValue: true,
                        uncheckedValue: false
                    },
                    {
                        xtype: 'checkboxfield',
                        name: 'read',
                        fieldLabel: 'Read',
                        inputValue: true,
                        uncheckedValue: false
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