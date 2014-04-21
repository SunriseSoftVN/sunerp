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
    domainKey: null,
    inject: ['userService'],
    config: {
        userService: null
    },
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
    },
    afterRender: function () {
        var me = this;
        //check permission of current user with this component
        var gioiHan = me.getUserService().checkGioiHan(me.domainKey);
        var donViId = me.getUserService().getCurrentUser().donViId;
        me.select(donViId);
        if (gioiHan == "donvi" || gioiHan == "phongban") {
            me.hide();
        }
        me.callParent(arguments);
    }
});