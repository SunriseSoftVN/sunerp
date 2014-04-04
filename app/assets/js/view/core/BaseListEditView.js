/**
 * Created by dungvn3000 on 3/14/14.
 */

Ext.define('sunerp.view.core.BaseListEditView', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.grid.plugin.CellEditing',
        'Ext.selection.CellModel',
        'Ext.toolbar.TextItem',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Text',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox'
    ],
    viewConfig: {
        stripeRows: true
    },
    selModel: {
        selType: 'cellmodel'
    },
    tbar: [
        {
            xtype: 'textfield',
            name: 'searchField',
            hideLabel: true,
            width: 200
        },
        {
            text: 'Thêm mới',
            tooltip: 'Thêm mới',
            iconCls: 'add',
            action: 'addNew'
        },
        {
            text: 'Cập nhật',
            tooltip: 'Cập nhật',
            iconCls: 'save',
            action: 'save'
        },
        {
            text: 'Xoá',
            tooltip: 'Xoá',
            iconCls: 'remove',
            action: 'delete'
        }
    ]
});