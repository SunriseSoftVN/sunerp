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
            html: 'SunERP v0.0.1',
            flex: 1
        },
        {
            xtype: 'button',
            menu: [
                {
                    text: 'Logout',
                    href: "/user/logout"
                }
            ],
            listeners: {
                afterRender: function (btn, eOpts) {
                    Ext.Ajax.request({
                        url: 'loginUser',
                        success: function (response) {
                            var maNv = response.responseText;
                            btn.setText('Welcome ' + maNv)
                        }
                    })
                }
            }
        }
    ]
});