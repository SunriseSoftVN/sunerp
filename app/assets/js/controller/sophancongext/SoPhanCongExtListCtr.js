/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.SoPhanCongExtListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['soPhanCongExtStore'],
    config: {
        soPhanCongExtStore: null
    },
    editView: 'sunerp.view.authority.SoPhanCongExtEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getSoPhanCongExtStore();
        this.callParent(arguments);
    }
});