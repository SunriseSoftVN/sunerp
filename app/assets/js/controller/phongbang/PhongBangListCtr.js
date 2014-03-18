/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.PhongBangListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['phongBangStore'],
    config: {
        phongBangStore: null
    },
    editView: 'sunerp.view.authority.PhongBangEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getPhongBangStore();
        this.callParent(arguments);
    }
});