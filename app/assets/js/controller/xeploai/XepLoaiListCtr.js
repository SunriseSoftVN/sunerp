/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.controller.xeploai.XepLoaiListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['xepLoaiStore', 'userService'],
    config: {
        xepLoaiStore: null,
        userService: null
    },
    control: {
        monthCb: {
            selector: 'monthcb'
        }
    },
    editView: 'sunerp.view.xeploai.XepLoaiEdit',
    init: function () {
        this.mainStore = this.getXepLoaiStore();
        var gioiHan = this.getUserService().checkGioiHan('xeploai');
        var phongBanId = this.getUserService().getCurrentUser().phongBanId;
        var donViId = this.getUserService().getCurrentUser().donViId;
        if (gioiHan == "phongban") {
            var phongBangFilter = new Ext.util.Filter({
                property: 'phongBanId',
                value: sunerp.Utils.toString(phongBanId)
            });
            this.mainStore.addFilter(phongBangFilter);
        }
        this.callParent(arguments);
    },
    showAddPanel: function () {
        var month = this.getMonthCb().getValue();
        var view = Ext.create(this.editView, {
            month: month
        });
    }
});