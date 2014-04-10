/**
 * Created by dungvn3000 on 3/14/14.
 */

Ext.define('sunerp.view.core.BaseListEditView', {
    extend: 'Ext.grid.Panel',
    requires: [
        'sunerp.component.plugin.CellEditing',
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
    searchField: null,
    getFilterComponents: function () {
        var me = this;
        var comps = [];
        var textFilter = Ext.create('sunerp.component.filter.TextFilter', {
            fieldName: me.searchField,
            store: me.store
        });
        comps.push(textFilter.getComponent());
        return comps;
    },
    afterRender: function () {
        this.store.load();
        this.callParent(arguments);
    },
    getTBar: function () {
        var comps = [
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
        ];
        comps = Ext.Array.insert(comps, 0, this.getFilterComponents());
        return comps;
    },
    initComponent: function () {
        var me = this;
        //clear old filter before add a new one.
        me.store.clearFilter(true);
        me.tbar = me.getTBar();
        me.callParent(arguments);
    }
});