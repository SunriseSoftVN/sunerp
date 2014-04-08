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
        userService: null,
        monthFilter: null
    },
    control: {
        monthCb: {
            selector: 'monthcb',
            listeners: {
                change: 'onChange'
            }
        }
    },
    searchField: "nhanVien.maNv",
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.setPhongBangId(this.getUserService().getCurrentUser().phongBangId);
        var currentMonth = this.getMonthCb().getValue();
        this.setMonthFilter(new Ext.util.Filter({
            property: 'month',
            value: String(currentMonth)
        }));
        this.mainStore.addFilter([this.getMonthFilter()], true);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        var lastModel = this.getView().getStore().last();
        rec.set('phongBangId', this.getPhongBangId());
        if (lastModel != null) {
            rec.set('ngayPhanCong', lastModel.get('ngayPhanCong'));
        } else {
            rec.set('ngayPhanCong', new Date());
        }
        this.mainStore.insert(this.mainStore.count(), rec);
    },
    onChange: function (comp, newValue, oldValue, eOpts) {
        this.getMonthFilter().setValue(newValue);
        this.mainStore.loadPage(1);
    }
});