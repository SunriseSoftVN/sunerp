/**
 * Created by dungvn3000 on 3/31/14.
 */

Ext.define("sunerp.service.UserService", {
    config: {
        currentUser: null
    },
    constructor: function (config) {
        if (config == null) {
            config = {};
        }
        this.initConfig(config);
        return this.callParent(arguments);
    },
    checkGioiHan: function (domainKey) {
        var me = this;
        var gioiHan = null;
        if (me.currentUser) {
            Ext.Array.forEach(me.currentUser.quyenHanhs, function (quyenHanh) {
                if (quyenHanh.domain == domainKey) {
                    gioiHan = quyenHanh.gioiHan;
                }
            })
        }
        return gioiHan;
    }
});