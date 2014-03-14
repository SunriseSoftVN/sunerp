Ext.define('sunerp.component.CongViecPicker', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.congviecpicker',
    triggerBaseCls: 'search-picker-icon',
    gird: null,
    editable: false,
    config: {
        store: Ext.create('sunerp.store.TaskStore'),
        model: null,
        task: null,
        window: null
    },
    onTriggerClick: function () {
        var me = this;
        if (me.gird.getSelectionModel().hasSelection()) {
            var model = me.gird.getSelectionModel().getSelection()[0];
            me.setModel(model);
            var window = Ext.create('Ext.window.Window', {
                title: 'Chọn công việc',
                height: 400,
                width: 700,
                layout: 'fit',
                modal: true,
                items: {  // Let's put an empty grid in just to illustrate fit layout
                    xtype: 'grid',
                    border: false,
                    columns: [
                        {header: 'Mã CV', dataIndex: 'code'},
                        {header: 'Tên', dataIndex: 'name', flex: 1},
                        {header: 'Khối lượng', dataIndex: 'defaultValue', xtype: 'numbercolumn'}
                    ],
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
                            emptyText: 'Tìm theo tên hoặc mã công việc',
                            hideLabel: true,
                            width: 200,
                            listeners: {
                                specialkey: me.onSearchFieldChange,
                                scope: me
                            }
                        }
                    ],
                    listeners: {
                        selectionchange: me.onTaskSelected,
                        itemdblclick: me.onOk,
                        scope: me
                    }
                },
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
            this.setWindow(window);
        }
    },
    onOk: function() {
        this.getModel().set('task', this.getTask());
        this.getWindow().close();
    },
    onTaskSelected: function(model, selected, eOpts) {
        var task = selected[0];
        this.setTask(task);
    },
    onSearchFieldChange: function (f, e) {
        var me = this;
        var searchValue = f.getValue();
        if (e.getKey() == e.ENTER) {
            me.getStore().clearFilter();
            me.getStore().filter("name", searchValue);
            me.getStore().loadPage(1);
        }
    }
});