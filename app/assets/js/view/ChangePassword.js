/**
 * Created by dungvn3000 on 5/30/2014.
 */

Ext.define('sunerp.view.ChangePassword', {
    extend: 'Ext.window.Window',
    alias: 'widget.changePassword',
    title: 'Đổi mật khẩu',
    bodyPadding: 10,
    modal: true,
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                url: '/user/changePassword',
                items: [
                    {
                        xtype: 'textfield',
                        name: 'password',
                        allowBlank: false,
                        inputType: 'password',
                        fieldLabel: 'Mật khẩu'
                    }
                ]
            }
        ];
        this.buttons = [
            {
                text: 'Save',
                action: 'save',
                handler: function (button) {
                    var win = button.up('window');
                    var form = win.down('form');
                    if (form.isValid()) {
                        form.submit({
                            method: 'POST',
                            waitTitle: 'Connecting',
                            waitMsg: 'Saving...',
                            success: function () {
                                win.close();
                            },
                            failure: function (form, action) {
                                Ext.Msg.alert('Error!', "Something went wrong");
                            }
                        });
                    }
                }
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