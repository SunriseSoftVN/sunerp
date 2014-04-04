/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
    modelClass: 'sunerp.model.SoPhanCong',
    inject: ['soPhanCongStore', 'userService'],
    observe: {
        soPhanCongStore: {
            load: "onMainStoreLoad"
        }
    },
    config: {
        soPhanCongStore: null,
        phongBangId: null,
        userService: null
    },
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.setPhongBangId(this.getUserService().getCurrentUser().phongBangId);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        rec.set('phongBangId', this.getPhongBangId());
        this.mainStore.insert(this.mainStore.count(), rec);
    }
});