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
    observe: {
        soPhanCongStore: {
            beforeload: 'onStoreBeforeLoad'
        }
    },
    control: {
        monthCb: {
            selector: 'monthcb',
            listeners: {
                select: 'onMonthCbSelect'
            }
        },
        dayCb: {
            selector: 'daycb',
            listeners: {
                select: 'onDayCbSelect'
            }
        },
        yearCb: {
            selector: 'yearcb'
        },
        iniBtn: {
            selector: 'button[action=init]',
            listeners: {
                click: 'onInitBtnClick'
            }
        },
        dayCopyBtn: {
            selector: 'button[action=dayCopy]'
        },
        btnDayMonthCopy: {
            selector: '#btnDayMonthCopy',
            listeners: {
                click: "onBtnDayMonthCopyClick"
            }
        },
        btnYesterdayCopy: {
            selector: '#btnYesterdayCopy',
            listeners: {
                click: "onBtnYesterdayCopyClick"
            }
        }
    },
    phongBanFilter: null,
    init: function () {
        var me = this;
        me.mainStore = me.getSoPhanCongStore();
        me.setPhongBanId(me.getUserService().getCurrentUser().phongBanId);
        me.checkLock();
        me.callParent(arguments);
    },
    checkLock: function() {
        var me = this;
        Ext.Ajax.request({
            url: 'sophancong/islock/' + me.getMonthCb().value,
            success: function (response) {
                var result = Ext.decode(response.responseText);
                me.getDayCopyBtn().setDisabled(result.lock);
                me.getBtnDayMonthCopy().setDisabled(result.lock);
                me.getBtnYesterdayCopy().setDisabled(result.lock);
                me.getIniBtn().setDisabled(result.lock);
                me.getDeleteBtn().setDisabled(result.lock);
                me.getAddBtn().setDisabled(result.lock);
                me.getSaveBtn().setDisabled(result.lock);
                if(result.lock) {
                    Ext.Msg.alert('Status', 'Công ty đã khóa sổ phân công tháng '
                            + me.getMonthCb().value
                            + ', dữ liệu xẽ không được thay đổi!'
                    );
                }
            }
        });
    },
    onMonthCbSelect: function() {
        this.checkLock();
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        var lastModel = this.getView().getStore().last();
        rec.set('phongBanId', this.getPhongBanId());
        rec.set('gio', 0);
        rec.set('khoiLuong', 0);
        if (lastModel != null) {
            rec.set('ngayPhanCong', lastModel.get('ngayPhanCong'));
        } else {
            var year = this.getYearCb().getValue();
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
    },
    onBtnDayMonthCopyClick: function () {
        var me = this;
        Ext.MessageBox.confirm('Copy dữ liệu', 'Dữ liệu cũ xẽ bị xóa đi thay thế bởi dữ liệu mới?', function (btn) {
            if (btn == 'yes') {
                Ext.Ajax.request({
                    url: '/sophancong/dayCopyData/' + me.getMonthCb().getValue() + "/" + me.getDayCb().getValue() + "/" + me.getPhongBanId(),
                    success: function (rep) {
                        me.mainStore.reload();
                    }
                });
            }
        });
    },
    onBtnYesterdayCopyClick: function () {
        var me = this;
        Ext.MessageBox.confirm('Copy dữ liệu', 'Dữ liệu cũ xẽ bị xóa đi thay thế bởi dữ liệu mới?', function (btn) {
            if (btn == 'yes') {
                Ext.Ajax.request({
                    url: '/sophancong/yesterdayCopyData/' + me.getMonthCb().getValue() + "/" + me.getDayCb().getValue() + "/" + me.getPhongBanId(),
                    success: function (rep) {
                        me.mainStore.reload();
                    }
                });
            }
        });
    },
    onDayCbSelect: function (model) {
        this.getDayCopyBtn().setDisabled(!model.value);
    },
    onStoreBeforeLoad: function (store, operation) {
        var me = this;
        if (!me.phongBanFilter) {
            me.phongBanFilter = new Ext.util.Filter({
                property: 'phongBanId',
                value: sunerp.Utils.toString(me.getUserService().getCurrentUser().phongBanId)
            });
            store.addFilter(me.phongBanFilter);
        }
    }
});