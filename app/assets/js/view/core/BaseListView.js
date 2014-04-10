/**
 * Created by dungvn3000 on 2/19/14.
 */

Ext.define('sunerp.view.core.BaseListView', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.toolbar.TextItem',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Text',
        'Ext.toolbar.Paging'
    ],
    store: null,
    searchField: null,
    initComponent: function () {
        var me = this;
        me.bbar = Ext.create('Ext.PagingToolbar', {
            store: me.store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        });
        me.tbar = me.getTBar();
        me.callParent(arguments);
    },
    deleteBtn: function () {
        return {
            icon: '/assets/img/icons/fam/delete.png',
            tooltip: 'Delete',
            handler: function (view, rowIndex, colIndex, item, e, record) {
                this.fireEvent('deleteRecord', this, view, rowIndex, colIndex, item, e, record);
            }
        }
    },
    getFilterComponents: function () {
        var me = this;
        var comps = [];
        var textFilter = Ext.create('sunerp.component.filter.TextFilter', {
            comp: Ext.create('Ext.form.field.Text', {
                name: 'searchField',
                hideLabel: true,
                emptyText: 'Tìm kiếm...',
                width: 200
            }),
            fieldName: me.searchField,
            store: me.store
        });
        comps.push(textFilter.getComponent());
        return comps;
    },
    getTBar: function () {
        var comps = [
            {
                text: 'Thêm mới',
                tooltip: 'Thêm mới',
                iconCls: 'add',
                action: 'addNew'
            }
        ];

        comps = Ext.Array.insert(comps, 0, this.getFilterComponents());
        return comps;
    }
});