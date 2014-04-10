/**
 * Created by dungvn3000 on 3/14/14.
 */

Ext.define('sunerp.controller.core.BaseListEditController', {
    extend: 'Deft.mvc.ViewController',
    //this property has to be set in subclass
    mainStore: null,
    modelClass: null,
    mainFilter: null,
    control: {},
    constructor: function (config) {
        this.control['deleteBtn'] = {
            selector: 'button[action=delete]',
            listeners: {
                click: 'doDelete'
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
            selector: 'textfield[name=searchField]'
        };
        this.callParent(config);
    },
    init: function () {
        this.callParent(arguments);
    },
    doDelete: function () {
        var me = this;
        var view = me.getView();
        if (view.getSelectionModel().hasSelection()) {
            Ext.MessageBox.confirm('Confirm', 'Are you sure you want to delete that?', function (btn) {
                if (btn == 'yes') {
                    var model = view.getSelectionModel().getLastSelected();
                    me.mainStore.remove(model);
                    me.mainStore.sync();
                }
            });
        }
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        this.mainStore.insert(this.mainStore.count(), rec);
    },
    doSave: function () {
        this.mainStore.sync({
            success: function () {
                Ext.Msg.alert('Status', 'Cập nhật thành công.');
            }
        });
    }
});