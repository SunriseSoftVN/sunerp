/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.view.setting.SettingEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.settingEdit',
    title: 'Edit Setting',
    controller: 'sunerp.controller.setting.SettingEditCtr',
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
                        readOnly: true,
                        disabled: true,
                        allowBlank: false,
                        fieldLabel: 'Name'
                    },
                    {
                        xtype: 'textfield',
                        anchor: '100%',
                        name: 'value',
                        allowBlank: false,
                        fieldLabel: 'Value'
                    }
                ]
            }
        ];
        this.callParent(arguments);
    }
});