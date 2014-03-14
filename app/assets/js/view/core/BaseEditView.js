/**
 * Created by dungvn3000 on 2/19/14.
 */

Ext.define('sunerp.view.core.BaseEditView', {
    extend: 'Ext.window.Window',
    requires: [
        'sunerp.component.Combobox'
    ],
    layout: 'fit',
    autoShow: true,
    bodyPadding: 10,
    modal: true,
    initComponent: function() {
        this.buttons = [
            {
                text: 'Save',
                action: 'save'
            },
            {
                text: 'Cancel',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});