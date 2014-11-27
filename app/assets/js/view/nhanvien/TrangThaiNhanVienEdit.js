/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.nhanvien.TrangThaiNhanVienEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.heSoLuongEdit',
    title: 'Edit TrangThaiNhanVien',
    width: 400,
    requires: [
        'sunerp.component.NhanVienCb',
        'sunerp.component.NhanVienPicker',
        'sunerp.component.MonthCb'
    ],
    year: null,
    controller: 'sunerp.controller.nhanvien.TrangThaiNhanVienEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'nhanvienpicker',
                        name: 'nhanVienId',
                        modelName: 'nhanVien',
                        width: 360,
                        allowBlank: false,
                        fieldLabel: 'Mã NV'
                    },
                    {
                        xtype: 'checkbox',
                        name: 'nghiViec',
                        width: 360,
                        inputValue: true,
                        uncheckedValue: false,
                        fieldLabel: 'Nghỉ việc'
                    },
                    {
                        xtype: 'monthcb',
                        name: 'month',
                        width: 360,
                        allowBlank: false,
                        fieldLabel: 'Tháng'
                    },
                    {
                        xtype: 'numberfield',
                        name: 'year',
                        width: 360,
                        value: this.year,
                        hidden: true,
                        allowBlank: false,
                        fieldLabel: 'Năm'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});