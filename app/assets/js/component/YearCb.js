/**
 * Created by dungvn3000 on 4/8/14.
 */

Ext.define('sunerp.component.YearCb', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.yearcb',
    triggerAction: 'all',
    forceSelection: true,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    editable: false,
    emptyText: 'Năm',
    value: new Date().getFullYear(),
    store: Ext.create('Ext.data.Store', {
        fields: ['value', 'name'],
        data: [
            {value: 2014, name: 2014},
            {value: 2015, name: 2015},
            {value: 2016, name: 2016},
            {value: 2017, name: 2017},
            {value: 2018, name: 2018},
            {value: 2019, name: 2019},
            {value: 2020, name: 2020}
        ]
    })
});