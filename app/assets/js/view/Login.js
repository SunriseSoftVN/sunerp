/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.view.Login', {
    extend: 'Ext.form.Panel',
    url: '/user/auth',
    xtype: 'login',
    title: 'SunERP Login',
    frame:true,
    width: 320,
    bodyPadding: 10,

    defaultType: 'textfield',
    defaults: {
        anchor: '100%'
    },

    items: [
        {
            allowBlank: false,
            fieldLabel: 'Username',
            name: 'username',
            emptyText: 'Username'
        },
        {
            allowBlank: false,
            fieldLabel: 'Password',
            name: 'password',
            emptyText: 'Password',
            inputType: 'password'
        },
        {
            xtype:'checkbox',
            fieldLabel: 'Remember me',
            name: 'remember'
        }
    ],

    buttons: [
        { text:'Login', action: 'login' }
    ]
});