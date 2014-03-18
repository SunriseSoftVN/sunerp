/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CacKhoanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['cacKhoanCongStore'],
    config: {
        cacKhoanCongStore: null
    },
    editView: 'sunerp.view.authority.CacKhoanCongEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getCacKhoanCongStore();
        this.callParent(arguments);
    }
});
