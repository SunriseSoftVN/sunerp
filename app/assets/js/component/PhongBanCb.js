/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.PhongBanCb', {
    extend: 'sunerp.component.Combobox',
    alias: 'widget.phongbancb',
    displayField: 'name',
    valueField: 'id',
    //if it is null, that mean load all phongbans.
    donViId: null,
    emptyText: "Đơn vị",
    initComponent: function () {
        var me = this;
        var store = Ext.create('sunerp.store.PhongBanStore', {
            listeners: {
                load: function (store, records, successful, eOpts) {
                    var fakeModel = new sunerp.model.PhongBan({
                        id: null,
                        name: 'Công ty'
                    });
                    store.insert(0, fakeModel);
                    me.select(fakeModel);
                }
            }
        });
        if (me.donViId != null) {
            store.filter('donViId', sunerp.Utils.toString(me.donViId));
        }
        me.store = store;
        me.store.load();
        me.callParent(arguments);
    }
});