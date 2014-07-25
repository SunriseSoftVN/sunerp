Ext.define('sunerp.controller.sophancong.KhoaSoPhanCongCtr', {
    extend: 'Deft.mvc.ViewController',
    control: {
        monthCb: {
            selector: 'monthcb',
            listeners: {
                change: 'onMonthCbChange'
            }
        },
        lockBtn: {
            selector: 'button[action=lock]',
            listeners: {
                click: 'onLockBtnClick'
            }
        },
        unLockBtn: {
            selector: 'button[action=unlock]',
            listeners: {
                click: 'onUnLockBtnClick'
            }
        }
    },
    init: function () {
        var me = this;
        Ext.Ajax.request({
            url: 'sophancong/islock/' + me.getMonthCb().value,
            success: function (response) {
                var result = Ext.decode(response.responseText);
                me.getLockBtn().setDisabled(result.lock);
                me.getUnLockBtn().setDisabled(!result.lock);
            }
        });
        this.callParent(arguments);
    },
    onLockBtnClick: function () {
        var me = this;
        Ext.Ajax.request({
            method: 'POST',
            url: 'sophancong/lock/' + me.getMonthCb().value,
            success: function (response) {
                Ext.Msg.alert('Status', 'Khóa thành công!');
                me.getLockBtn().setDisabled(true);
                me.getUnLockBtn().setDisabled(false);
            }
        });
    },
    onUnLockBtnClick: function () {
        var me = this;
        Ext.Ajax.request({
            method: 'POST',
            url: 'sophancong/unlock/' + me.getMonthCb().value,
            success: function (response) {
                Ext.Msg.alert('Status', 'Mở khóa thành công!');
                me.getLockBtn().setDisabled(false);
                me.getUnLockBtn().setDisabled(true);
            }
        });
    },
    onMonthCbChange: function (comp, newValue, oldValue, eOpts) {
        var me = this;
        Ext.Ajax.request({
            url: 'sophancong/islock/' + newValue,
            success: function (response) {
                var result = Ext.decode(response.responseText);
                me.getLockBtn().setDisabled(result.lock);
                me.getUnLockBtn().setDisabled(!result.lock);
            }
        });
    }
});