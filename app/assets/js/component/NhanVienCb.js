/**
 * Created by dungvn3000 on 4/4/14.
 */

Ext.define('sunerp.component.NhanVienCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.nhanviencb',
    triggerAction: 'all',
    forceSelection: true,
    gird: null,
    queryMode: 'local',
    valueField: 'maNv',
    displayField: 'firstName',
    inject: ['userService'],
    config: {
        userService: null
    },
    initComponent: function () {
        var phongBanId = this.getUserService().getCurrentUser().phongBanId;
        this.store = Ext.create('sunerp.store.NhanVienStore');
        if(phongBanId) {
            this.store.filter('phongBanId', String(phongBanId));
        }
        this.store.load();
        this.callParent(arguments);
    },
    onItemClick: function (picker, record) {
        var me = this;
        me.callParent(arguments);
        if (me.gird.getSelectionModel().hasSelection()) {
            var model = me.gird.getSelectionModel().getLastSelected();
            model.set('nhanVienId', record.getData().id);
        }
    }
});