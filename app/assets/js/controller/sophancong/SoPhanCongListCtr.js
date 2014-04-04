/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
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
    control: {
        addBtn: {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'addNewRow'
            }
        },
        saveBtn: {
            selector: 'button[action=save]',
            listeners: {
                click: 'doSave'
            }
        }
    },
    init: function () {
        this.setPhongBangId(this.getUserService().getCurrentUser().phongBangId);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = new sunerp.model.SoPhanCong();
        rec.set('phongBangId', this.getPhongBangId());
        this.getSoPhanCongStore().insert(this.getSoPhanCongStore().count(), rec);
    },
    doSave: function() {
        this.getSoPhanCongStore().sync();
    }
});