/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.core.BaseListController', {
    extend: 'Deft.mvc.ViewController',
    editView: null,
    mainStore: null,
    control: {
        view: {
            itemdblclick: "showEditView"
        },
        addBtn: {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'showAddPanel'
            }
        },
        deleteBtn: {
            selector: 'actioncolumn',
            listeners: {
                deleteRecord: 'doDelete'
            }
        }
    },
    init: function () {
        this.callParent(arguments);
    },
    showEditView: function (grid, record) {
        var view = Ext.create(this.editView, {
            model: record
        });
    },
    showAddPanel: function () {
        var view = Ext.create(this.editView);
    },
    doDelete: function (column, view, rowIndex, colIndex, item, e, record) {
        var me = this;
        Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete that?', function (btn) {
            if (btn == 'yes') {
                me.mainStore.remove(record);
                me.mainStore.sync();
            }
        });
    }
});