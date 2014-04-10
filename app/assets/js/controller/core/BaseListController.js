/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.core.BaseListController', {
    extend: 'Deft.mvc.ViewController',
    editView: null,
    mainStore: null,
    searchField: null,
    //private
    mainFilter: null,
    control: {},
    constructor: function (config) {
        this.control['view'] = {
            itemdblclick: "showEditView"
        };
        this.control['addBtn'] = {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'showAddPanel'
            }
        };
        this.control['deleteBtn'] = {
            selector: 'actioncolumn',
            listeners: {
                deleteRecord: 'doDelete'
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
    init: function () {
        var me = this;
        me.callParent(arguments);
        me.afterInit();
        me.mainFilter = new Ext.util.Filter({
            property: me.searchField,
            value: null
        });
        me.mainStore.addFilter(me.mainFilter, false);
    },
    afterInit: function () {
        this.mainStore.clearFilter();
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
    },
    onSearchFieldChange: function (f, e) {
        var me = this;
        var searchValue = me.getSearchTxt().getValue();
        if (e.getKey() == e.ENTER) {
            me.mainFilter.setValue(searchValue);
            me.mainStore.loadPage(1);
        }
    }
});