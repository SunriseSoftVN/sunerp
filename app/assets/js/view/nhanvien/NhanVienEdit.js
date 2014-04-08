/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.nhanvien.NhanVienEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.nhanVienEdit',
    title: 'Edit NhanVien',
    requires: ['sunerp.controller.nhanvien.NhanVienEditCtr'],
    controller: 'sunerp.controller.nhanvien.NhanVienEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'maNv',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Mã NV'
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'password',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Mật khẩu'
                    },
                    {
                        xtype: 'textfield',
                        name: 'firstName',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Tên'
                    },
                    {
                        xtype: 'textfield',
                        name: 'lastName',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Họ'
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
                        emptyText: 'Chọn một chức vụ...'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Phòng ban',
                        name: 'phongBanId',
                        modelName: 'phongBan',
                        store: Ext.create('sunerp.store.PhongBanStore', {
                            proxy: {
                                type: 'ajax',
                                url: '/phongban/all',
                                reader: 'json'
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn một phòng ban...'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'heSoLuong',
                        minValue:0,
                        allowBlank: false,
                        fieldLabel: 'Hệ số lương'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});