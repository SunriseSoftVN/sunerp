/**
 * Created by dungvn3000 on 4/4/14.
 */

Ext.define('sunerp.component.NhanVienCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.nhanviencb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    valueField: 'id',
    displayField: 'firstName',
    store: Ext.create('sunerp.store.NhanVienStore', {
        proxy: {
            type: 'ajax',
            url: '/nhanvien/findByPhongBangId/1',
            reader: 'json'
        }
    })
});