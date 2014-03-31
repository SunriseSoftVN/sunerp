/**
 * Created by dungvn3000 on 3/31/14.
 */

Ext.define('sunerp.controller.HeaderCtr', {
    extend: 'Deft.mvc.ViewController',
    inject: ['userService'],
    config: {
        userService: null
    },
    control: {
        loginMenu: {
            selector: 'button'
        }
    },
    init: function () {
        var me = this;
        Ext.Ajax.request({
            url: 'loginUser',
            success: function (response) {
                var nhanVien = Ext.decode(response.responseText);
                me.getUserService().setCurrentUser(nhanVien);
                me.getLoginMenu().setText('Welcome ' + nhanVien.maNv);
            }
        });
        me.callParent(arguments);
    }
});