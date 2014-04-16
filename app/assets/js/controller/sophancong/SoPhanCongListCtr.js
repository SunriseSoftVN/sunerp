/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
    modelClass: 'sunerp.model.SoPhanCong',
    inject: ['soPhanCongStore', 'userService'],
    config: {
        soPhanCongStore: null,
        phongBanId: null,
        userService: null
    },
    control: {
        monthCb: {
            selector: 'monthcb'
        },
        dayCb: {
            selector: 'daycb'
        },
        iniBtn: {
            selector: 'button[action=init]',
            listeners: {
                click: 'onInitBtnClick'
            }
        }
    },
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.setPhongBanId(this.getUserService().getCurrentUser().phongBanId);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        var lastModel = this.getView().getStore().last();
        rec.set('phongBanId', this.getPhongBanId());
        if (lastModel != null) {
            rec.set('ngayPhanCong', lastModel.get('ngayPhanCong'));
        } else {
            var year = new Date().getFullYear();
            var month = this.getMonthCb().getValue() - 1;
            var day = new Date().getDate();
            if (this.getDayCb().getValue()) {
                day = this.getDayCb().getValue();
            }
            rec.set('ngayPhanCong', new Date(year, month, day));
        }
        this.mainStore.insert(this.mainStore.count(), rec);
    },
    onInitBtnClick: function () {
        var me = this;
        Ext.Ajax.request({
            url: '/sophancong/init/' + me.getMonthCb().getValue() + "/" + this.getPhongBanId(),
            success: function (rep) {
                me.mainStore.reload();
            }
        });
    }
});