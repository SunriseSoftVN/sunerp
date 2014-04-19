/**
 * Created by dungvn3000 on 2/18/14.
 */

Ext.define('sunerp.component.Combobox', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.comboboxx',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    editable: false,
    config: {
        modelName: null
    },
    initComponent: function () {
        this.store.load();
        this.callParent(arguments);
    },
    getSelectedData: function () {
        var value = this.getValue();
        var record = this.findRecordByValue(value);
        if (record) {
            return record.getData();
        } else {
            return null;
        }
    }
});