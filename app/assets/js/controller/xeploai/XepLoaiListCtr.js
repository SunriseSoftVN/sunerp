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
        this.callParent(arguments);
    },
    showAddPanel: function () {
        var month = this.getMonthCb().getValue();
        var view = Ext.create(this.editView, {
            month: month
        });
    }
});