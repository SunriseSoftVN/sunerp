/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.view.quyenhanh.QuyenHanhEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.quyenHanhEdit',
    title: 'Edit Quyền Hành',
    controller: 'sunerp.controller.quyenhanh.QuyenHanhEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'comboboxx',
                        name: 'domain',
                        allowBlank: false,
                        valueField: 'value',
                        displayField: 'name',
                        fieldLabel: 'Domain',
                        store: Ext.create('sunerp.store.DomainStore')
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
                            listeners: {
                                load: function (store, records, successful, eOpts) {
                                    var nullModel = new sunerp.model.PhongBan({
                                        id: null,
                                        name: 'Tất cả'
                                    });
                                    store.insert(0, nullModel);
                                }
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        emptyText: 'Chọn chức vụ ...'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Phòng Ban',
                        name: 'phongBanId',
                        modelName: 'phongBan',
                        store: Ext.create('sunerp.store.PhongBanStore', {
                            listeners: {
                                load: function (store, records, successful, eOpts) {
                                    var nullModel = new sunerp.model.PhongBan({
                                        id: null,
                                        name: 'Tất cả'
                                    });
                                    store.insert(0, nullModel);
                                }
                            }
                        }),
                        valueField: 'id',
                        displayField: 'name',
                        emptyText: 'Chọn phòng Ban ...'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});