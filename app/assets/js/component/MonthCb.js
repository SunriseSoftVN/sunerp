/**
 * Created by dungvn3000 on 4/8/14.
 */

Ext.define('sunerp.component.MonthCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.monthcb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    editable: false,
    emptyText: 'Tháng',
    value: new Date().getMonth() + 1,
    store: Ext.create('Ext.data.Store', {
        fields: ['value', 'name'],
        data: [
            {value: 1, name: "Tháng 1"},
            {value: 2, name: "Tháng 2"},
            {value: 3, name: "Tháng 3"},
            {value: 4, name: "Tháng 4"},
            {value: 5, name: "Tháng 5"},
            {value: 6, name: "Tháng 6"},
            {value: 7, name: "Tháng 7"},
            {value: 8, name: "Tháng 8"},
            {value: 9, name: "Tháng 9"},
            {value: 10, name: "Tháng 10"},
            {value: 11, name: "Tháng 11"},
            {value: 12, name: "Tháng 12"}
        ]
    })
});