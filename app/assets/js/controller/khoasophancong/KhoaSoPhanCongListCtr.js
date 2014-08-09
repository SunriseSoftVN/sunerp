/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.khoasophancong.KhoaSoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['khoaSoPhanCongStore'],
    config: {
        khoaSoPhanCongStore: null
    },
    editView: 'sunerp.view.donvi.DonViEdit',
    init: function () {
        this.mainStore = this.getKhoaSoPhanCongStore();
        this.getAddBtn().setDisabled(true);
        this.callParent(arguments);
    }
});