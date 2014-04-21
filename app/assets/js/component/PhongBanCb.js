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
    addShowAll: true,
    domainKey: null,
    inject: ['userService'],
    config: {
        userService: null,
        donViFilter: null
    },
    initComponent: function () {
        var me = this;
        var store = Ext.create('sunerp.store.PhongBanStore', {
            listeners: {
                load: function (store, records, successful, eOpts) {
                    if (me.addShowAll) {
                        var fakeModel = new sunerp.model.PhongBan({
                            id: null,
                            name: 'Tất cả'
                        });
                        store.insert(0, fakeModel);
                        me.select(fakeModel);
                    }
                }
            }
        });
        me.donViFilter = new Ext.util.Filter({
            property: 'donViId',
            value: sunerp.Utils.toString(me.donViId)
        });
        store.addFilter(me.donViFilter, false);
        me.store = store;
        me.store.load();
        me.callParent(arguments);
    },
    afterRender: function () {
        var me = this;
        var gioiHan = me.getUserService().checkGioiHan(me.domainKey);
        var phongBanId = me.getUserService().getCurrentUser().phongBanId;
        if (gioiHan == "phongban") {
            me.select(phongBanId);
            me.hide();
        }
        me.callParent(arguments);
    }
});