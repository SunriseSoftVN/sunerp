/**
 * Created by dungvn3000 on 2/18/14.
 */

Ext.define('sunerp.component.Combobox', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.comboboxx',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    listeners: {
        afterRender: function() {
            this.store.reload();
        }
    },
    getSelected: function() {
        var value = this.getValue();
        var record = this.findRecordByValue(value);
        return record.getData();
    }
});