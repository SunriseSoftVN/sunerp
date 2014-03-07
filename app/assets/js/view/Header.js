Ext.define('sunerp.view.Header', {
    extend: 'Ext.Container',
    xtype: 'appHeader',
    id: 'app-header',
    height: 40,
    layout: {
        type: 'hbox',
        align: 'middle'
    },
    items: [
        {
            xtype: 'component',
            id: 'app-header-title',
            html: 'SunERP v1.0.1',
            flex: 1
        },
        {
            xtype: 'button',
            text: 'Welcome Admin',
            menu: [{
                text:'Logout',
                href: "/user/logout"
            }]
        }
    ]
});