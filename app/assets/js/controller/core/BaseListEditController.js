/**
 * Created by dungvn3000 on 3/14/14.
 */

Ext.define('sunerp.controller.core.BaseListEditController', {
    extend: 'Deft.mvc.ViewController',
    //this property has to be set in subclass
    mainStore: null,
    modelClass: null,
    control: {},
    constructor: function(config) {
        this.control['deleteBtn'] = {
            selector: 'actioncolumn',
            listeners: {
                deleteRecord: 'doDelete'
            }
        };
        this.control['addBtn'] = {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'addNewRow'
            }
        };
        this.control['saveBtn'] = {
            selector: 'button[action=save]',
            listeners: {
                click: 'doSave'
            }
        };
        this.control['searchTxt'] = {
            selector: 'textfield[name=searchField]',
            listeners: {
                specialkey: 'onSearchFieldChange'
            }
        };
        this.callParent(config);
    },
    doDelete: function (column, view, rowIndex, colIndex, item, e, record) {
        var me = this;
        Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete that?', function (btn) {
            if (btn == 'yes') {
                me.mainStore.remove(record);
                me.mainStore.sync();
            }
        });
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        this.mainStore.insert(this.mainStore.count(), rec);
    },
    doSave: function() {
        this.mainStore.sync();
    },
    onSearchFieldChange: function (f, e) {
        var me = this;
        var searchValue = me.getSearchTxt().getValue();
        if (e.getKey() == e.ENTER) {
            me.mainStore.clearFilter(true);
            me.mainStore.filter(me.searchField, searchValue);
            me.mainStore.loadPage(1);
        }
    }
});