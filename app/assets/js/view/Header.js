Ext.define('sunerp.view.Header', {
    extend: 'Ext.Container',
    xtype: 'appHeader',
    id: 'app-header',
    height: 40,
    layout: {
        type: 'hbox',
        align: 'middle'
    },
    requires: ['sunerp.controller.HeaderCtr'],
    controller: 'sunerp.controller.HeaderCtr',
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'component',
                id: 'app-header-title',
                html: 'SunERP v1.0.1',
                flex: 1
            },
            {
                xtype: 'button',
                menu: [
                    {
                        text: 'Đổi mật khẩu',
                        handler: function() {
                            var window = Ext.create('sunerp.view.ChangePassword');
                            window.show();
                        }
                    },
                    {
                        text: 'Logout',
                        href: "/user/logout"
                    }
                ]
            }
        ];
        me.callParent(arguments);
    }
});