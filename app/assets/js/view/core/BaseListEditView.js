/**
 * Created by dungvn3000 on 3/14/14.
 */

Ext.define('sunerp.view.core.BaseListEditView', {
    extend: 'Ext.grid.Panel',
    requires: [
        'sunerp.component.plugin.CellEditing',
        'sunerp.component.filter.TextFilter',
        'Ext.selection.CellModel',
        'Ext.toolbar.TextItem',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Text',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox'
    ],
    viewConfig: {
        stripeRows: false
    },
    selModel: {
        selType: 'cellmodel'
    },
    searchField: null,
    config: {
        tbar: null
    },
    afterRender: function () {
        this.store.load();
        this.callParent(arguments);
    },
    initComponent: function () {
        var me = this;
        //clear old filter before add a new one.
        me.store.clearFilter(true);
        me.initTBar();
        me.initBBar();
        me.callParent(arguments);
    },
    initTBar: function () {
        var me = this;
        var tbar = Ext.create('Ext.toolbar.Toolbar', {
            items: [
                {
                    xtype: 'textfilter',
                    fieldName: me.searchField,
                    store: me.store
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
        me.setTbar(tbar);
        me.tbar = tbar;
    },
    initBBar: function () {
        var me = this;
        me.bbar = Ext.create('Ext.PagingToolbar', {
            store: me.store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        });
    }
});