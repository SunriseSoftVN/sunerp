/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
    modelClass: 'sunerp.model.SoPhanCong',
    inject: ['soPhanCongStore', 'userService'],
    config: {
        soPhanCongStore: null,
        phongBangId: null,
        userService: null
    },
    searchField: "nhanVien.maNv",
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.setPhongBangId(this.getUserService().getCurrentUser().phongBangId);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var lastModel = this.getView().getStore().last();
        var rec = Ext.create(this.modelClass);
        rec.set('phongBangId', this.getPhongBangId());
        rec.set('ngayPhanCong', lastModel.get('ngayPhanCong'));
        this.mainStore.insert(this.mainStore.count(), rec);
    }
});