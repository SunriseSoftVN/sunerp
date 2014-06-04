/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.controller.diemheso.DiemHeSoListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['diemHeSoStore', 'userService'],
    config: {
        diemHeSoStore: null,
        userService: null
    },
    control: {
        yearCb: {
            selector: 'yearcb'
        }
    },
    editView: 'sunerp.view.diemheso.DiemHeSoEdit',
    init: function () {
        this.mainStore = this.getDiemHeSoStore();
        this.callParent(arguments);
    },
    showAddPanel: function () {
        var year = this.getYearCb().getValue();
        var view = Ext.create(this.editView, {
            year: year
        });
    }
});