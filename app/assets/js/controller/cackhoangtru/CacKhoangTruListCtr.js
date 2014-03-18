/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CacKhoangTruListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['cacKhoangTruStore'],
    config: {
        cacKhoangTruStore: null
    },
    editView: 'sunerp.view.authority.CacKhoangTruEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getCacKhoangTruStore();
        this.callParent(arguments);
    }
});