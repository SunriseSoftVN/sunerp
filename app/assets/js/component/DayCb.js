/**
 * Created by dungvn3000 on 4/16/14.
 */

Ext.define('sunerp.component.DayCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.daycb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    editable: false,
    emptyText: 'Ngày',
    value: null,
    store: Ext.create('Ext.data.Store', {
        fields: ['value', 'name'],
        data: [
            {value: null, name: "Tất cả"},
            {value: 1, name: "1"},
            {value: 2, name: "2"},
            {value: 3, name: "3"},
            {value: 4, name: "4"},
            {value: 5, name: "5"},
            {value: 6, name: "6"},
            {value: 7, name: "7"},
            {value: 8, name: "8"},
            {value: 9, name: "9"},
            {value: 10, name: "10"},
            {value: 11, name: "11"},
            {value: 12, name: "12"},
            {value: 13, name: "13"},
            {value: 14, name: "14"},
            {value: 15, name: "15"},
            {value: 16, name: "16"},
            {value: 17, name: "17"},
            {value: 18, name: "18"},
            {value: 19, name: "19"},
            {value: 20, name: "20"},
            {value: 21, name: "21"},
            {value: 22, name: "22"},
            {value: 23, name: "23"},
            {value: 24, name: "24"},
            {value: 25, name: "25"},
            {value: 26, name: "26"},
            {value: 27, name: "27"},
            {value: 28, name: "28"},
            {value: 29, name: "29"},
            {value: 30, name: "30"},
            {value: 31, name: "31"}
        ]
    })
});