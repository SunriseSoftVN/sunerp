/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.PhongBanCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.phongbancb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'id',
    editable: false,
    //if it is null, that mean load all phongbans.
    donViId: null,
    emptyText: "Đơn vị",
    config: {
        donViFilter: null
    },
    initComponent: function () {
        var me = this;
        var store = Ext.create('sunerp.store.PhongBanStore');
        if (me.donViId != null) {
            var donViFilter = new Ext.util.Filter({
                property: 'donViId',
                value: String(me.donViId)
            });
            store.addFilter([donViFilter]);
            me.setDonViFilter(donViFilter);
        }
        me.store = store;
        me.callParent(arguments);
    }
});