/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.view.chucvu.ChucVuEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.chucvuEdit',
    title: 'Edit Chức vụ',
    requires: ['sunerp.controller.chucvu.ChucVuEditCtr'],
    controller: 'sunerp.controller.chucvu.ChucVuEditCtr',
    inject: ['chucVuStore'],
    config: {
        model: null,
        roleStore: null
    },
    width: 400,
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
                        xtype: 'numberfield',
                        name: 'phuCapTrachNhiem',
                        allowBlank: false,
                        fieldLabel: 'Phụ cấp trách nhiệm'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});
