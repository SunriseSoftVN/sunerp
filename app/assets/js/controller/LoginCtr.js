/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.controller.LoginCtr', {
    extend: 'Ext.app.Controller',

    views: [
        'Login'
    ],

    init: function () {
        var me = this;
        this.control({
            'login button[action=login]': {
                click: me.doLogin
            },
            'login textfield': {
                specialkey: function (field, e) {
                    if (e.getKey() == e.ENTER) {
                        me.doLogin(field);
                    }
                }
            }
        })
    },

    doLogin: function (button) {
        var form = button.up('form');
        if (form.isValid()) {
            form.submit({
                method: 'POST',
                waitTitle: 'Connecting',
                waitMsg: 'Checking you username and password...',
                success: function () {
                    window.location = "/";
                },
                failure: function (form, action) {
                    var error = Ext.decode(action.response.responseText);
                    Ext.Msg.alert('Login Failed!', error[0].msg);
                }
            });
        }
    }
});