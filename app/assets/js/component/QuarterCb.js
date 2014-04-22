/**
 * Created by dungvn3000 on 4/8/14.
 */

Ext.define('sunerp.component.QuarterCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.quartercb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    editable: false,
    emptyText: 'Quý',
    value: 1,
    store: Ext.create('Ext.data.Store', {
        fields: ['value', 'name'],
        data: [
            {value: 1, name: "Quý 1"},
            {value: 2, name: "Quý 2"},
            {value: 3, name: "Quý 3"},
            {value: 4, name: "Quý 4"}
        ]
    })
});