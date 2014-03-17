/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.view.chucvu.ChucVuEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.chucvuEdit',
    title: 'Edit Chức vụ',
    requires: ['sunerp.controller.chucvu.ChucVuEditCtr'],
    controller: 'sunerp.controller.chucvu.ChucVuEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        name: 'name',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Tên'
                    },
                    {
                        xtype: 'numberfield',
                        anchor: '100%',
                        minValue:0,
                        name: 'phuCapTrachNhiem',
                        allowBlank: false,
                        fieldLabel: 'Phụ cấp'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});
