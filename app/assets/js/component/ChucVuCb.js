/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.ChucVuCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.chucvucb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'id',
    editable: false,
    emptyText: "Chức vụ",
    initComponent: function () {
        var me = this;
        me.store = Ext.create('sunerp.store.ChucVuStore', {
            listeners: {
                load: function (store, records, successful, eOpts) {
                    var fakeModel = new sunerp.model.PhongBan({
                        id: null,
                        name: 'Tất cả'
                    });
                    store.insert(0, fakeModel);
                    me.select(fakeModel);
                }
            }
        });
        me.store.load();
        me.callParent(arguments);
    }
});