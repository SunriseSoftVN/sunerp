/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['soPhanCongStore'],
    config: {
        soPhanCongStore: null
    },
    editView: 'sunerp.view.sophancong.SoPhanCongEdit',
    searchField: 'nhanVien.firstName',
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.callParent(arguments);
    }
});