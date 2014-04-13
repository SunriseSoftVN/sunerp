/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.DonViCb', {
    extend: 'sunerp.component.Combobox',
    alias: 'widget.donvicb',
    displayField: 'name',
    valueField: 'id',
    //if it is null, that mean load all phongbans.
    donViId: null,
    emptyText: "Đơn vị",
    addShowAll: true,
    initComponent: function () {
        var me = this;
        var store = Ext.create('sunerp.store.DonViStore', {
            listeners: {
                load: function (store, records, successful, eOpts) {
                    if (me.addShowAll) {
                        var fakeModel = new sunerp.model.DonVi({
                            id: null,
                            name: 'Tất cả'
                        });
                        store.insert(0, fakeModel);
                        me.select(fakeModel);
                    }
                }
            }
        });
        me.store = store;
        me.store.load();
        me.callParent(arguments);
    }
});