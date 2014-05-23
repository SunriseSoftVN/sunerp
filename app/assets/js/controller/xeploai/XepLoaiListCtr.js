/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.controller.xeploai.XepLoaiListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['xepLoaiStore'],
    config: {
        xepLoaiStore: null
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
        var year = new Date().getFullYear();
        var month = this.getMonthCb().getValue();
        var view = Ext.create(this.editView, {
            year: year,
            month: month
        });
    }
});