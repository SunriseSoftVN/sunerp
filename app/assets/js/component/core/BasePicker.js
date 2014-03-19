/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.component.core.BasePicker', {
    extend: 'Ext.form.field.Trigger',
    triggerBaseCls: 'search-picker-icon',
    gird: null,
    editable: false,
    storeClass: null,
    modelName: null,
    searchEmptyText: null,
    searchField: null,
    title: null,
    config: {
        store: null,
        model: null,
        select: null,
        window: null
    },
    onTriggerClick: function () {
        var me = this;
        me.setStore(Ext.create(me.storeClass));
        me.getStore().clearFilter(true);
        me.getStore().loadPage(1);
        if (me.gird.getSelectionModel().hasSelection()) {
            var model = me.gird.getSelectionModel().getSelection()[0];
            me.setModel(model);
            var window = Ext.create('Ext.window.Window', {
                title: me.title,
                height: 400,
                width: 700,
                layout: 'fit',
                modal: true,
                items: me.createGrid(),
                buttons: [
                    {
                        text: 'Ok',
                        action: 'ok',
                        handler: me.onOk,
                        scope: me
                    },
                    {
                        text: 'Cancel',
                        handler: function () {
                            window.close();
                        }
                    }
                ]
            });
            window.show();
            me.setWindow(window);
        }
    },
    createGrid: function () {
        var me = this;
        return {
            // Let's put an empty grid in just to illustrate fit layout
            xtype: 'grid',
            border: false,
            columns: me.getColumns(),
            store: me.getStore(),
            bbar: Ext.create('Ext.PagingToolbar', {
                store: me.getStore(),
                displayInfo: true,
                displayMsg: 'Displaying topics {0} - {1} of {2}',
                emptyMsg: "No topics to display"
            }),
            tbar: [
                {
                    xtype: 'textfield',
                    name: 'searchField',
                    emptyText: me.searchEmptyText,
                    hideLabel: true,
                    width: 200,
                    listeners: {
                        specialkey: me.onSearchFieldChange,
                        scope: me
                    }
                }
            ],
            listeners: {
                selectionchange: me.onSelected,
                itemdblclick: me.onOk,
                scope: me
            }
        }
    },
    getColumns: function () {
        return [];
    },
    onOk: function () {
        this.getModel().set(this.modelName, this.getSelect());
        this.getWindow().close();
    },
    onSelected: function (model, selected, eOpts) {
        var select = selected[0];
        this.setSelect(select.getData());
    },
    onSearchFieldChange: function (f, e) {
        var me = this;
        var searchValue = f.getValue();
        if (e.getKey() == e.ENTER) {
            me.getStore().clearFilter(true);
            me.getStore().filter(me.searchField, searchValue);
            me.getStore().loadPage(1);
        }
    }
});