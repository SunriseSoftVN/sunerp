Ext.define('sunerp.component.CongViecPicker', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.congviecpicker',
    triggerBaseCls: 'search-picker-icon',
    gird: null,
    onTriggerClick: function () {
        var me = this;
        if (me.gird.getSelectionModel().hasSelection()) {
            var model = me.gird.getSelectionModel().getSelection()[0];
            var windown = Ext.create('Ext.window.Window', {
                title: 'Hello',
                height: 200,
                width: 400,
                layout: 'fit',
                modal: true,
                items: {  // Let's put an empty grid in just to illustrate fit layout
                    xtype: 'grid',
                    border: false,
                    columns: [
                        {header: 'World', dataIndex: 'name'}
                    ],                 // One header just for show. There's no data,
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['name'],
                        data: [
                            'ddd',
                            'dddd'
                        ]
                    }) // A dummy empty data store
                },
                buttons: [
                    {
                        text: 'Save',
                        action: 'save',
                        handler: function () {
                            model.set("task.name", "dung ne");
                            windown.close();
                        }
                    },
                    {
                        text: 'Cancel',
                        handler: function () {
                            windown.close();
                        }
                    }
                ]
            });
            windown.show();
            windown.on('close', function () {
                me.setValue("dung ne");
            })
        }
    }
});