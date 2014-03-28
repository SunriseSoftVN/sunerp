/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
    inject: ['soPhanCongStore'],
    config: {
        soPhanCongStore: null
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
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = new sunerp.model.SoPhanCong();
        this.getSoPhanCongStore().insert(0, rec);
    },
    doSave: function() {
        this.getSoPhanCongStore().sync();
    }
});