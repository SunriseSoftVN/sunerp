/**
 * Created by dungvn3000 on 2/19/14.
 */

Ext.define('sunerp.controller.BaseController', {
    extend: 'Ext.app.Controller',

    mainStore: '',
    editViewName: '',
    listViewName: '',
    searchField: '',

    init: function () {
        var control = {};
        control['contentPanel >' + this.listViewName] = {
            itemdblclick: this.showEditView
        };

        control[this.editViewName + ' button[action=save]'] = {
            click: this.doUpdate
        };

        control[this.listViewName + ' textfield[name=searchField]'] = {
            specialkey: this.onSearchFieldChange
        };

        control[this.listViewName + ' button[action=addNew]'] = {
            click: this.showAddPanel
        };

        control[this.listViewName + ' actioncolumn'] = {
            deleteRecord: this.doDelete
        };

        this.control(control);
    },

    showEditView: function (grid, record) {
        var view = Ext.widget(this.editViewName);
        view.down('form').loadRecord(record);
    },

    doUpdate: function (button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            me = this;
        if (form.isValid()) {
            if (record != null) {
                //update record
                record.set(values);
            } else {
                //add new record
                me.getMainStore().add(values);
            }

            if(record == null || record.dirty) {
                // synchronize the store after editing the record
                me.getMainStore().sync({
                    success: function () {
                        win.close();
                        me.getMainStore().reload();
                    }
                });
            } else {
                win.close();
            }
        }
    },

    onSearchFieldChange: function (f, e) {
        var me = this;
        var searchValue = me.getSearchField().getValue();
        if (e.getKey() == e.ENTER) {
            me.getMainStore().clearFilter();
            me.getMainStore().filter(me.searchField, searchValue);
            me.getMainStore().loadPage(1);
        }
    },

    showAddPanel: function () {
        var view = Ext.widget(this.editViewName);
        view.show();
    },

    doDelete: function (column, view, rowIndex, colIndex, item, e, record) {
        var me = this;
        Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete that?', function (btn) {
            if (btn == 'yes') {
                me.getMainStore().remove(record);
                me.getMainStore().sync();
            }
        });
    },

    getMainStore: function () {
        return this.getStore(this.mainStore);
    },

    getSearchField: function() {
        var fields =  Ext.ComponentQuery.query(this.listViewName + ' textfield[name=searchField]');
        if(fields.length > 0) {
            return fields[0]
        } else {
            return null;
        }
    }
});