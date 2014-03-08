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
        }
    ],
    initComponent: function () {
        var me = this;
//        me.bbar = Ext.create('Ext.PagingToolbar', {
//            store: me.store,
//            displayInfo: true,
//            displayMsg: 'Displaying topics {0} - {1} of {2}',
//            emptyMsg: "No topics to display"
//        });
        me.callParent(arguments);
    },
    deleteBtn: function () {
        return {
            icon: '/assets/img/icons/fam/delete.gif',
            tooltip: 'Delete',
            handler: function (view, rowIndex, colIndex, item, e, record) {
                this.fireEvent('deleteRecord', this, view, rowIndex, colIndex, item, e, record);
            }
        }
    }
});