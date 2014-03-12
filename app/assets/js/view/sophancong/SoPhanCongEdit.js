Ext.define('sunerp.view.sophancong.SoPhanCongEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.soPhanCongEdit',
    title: 'Sổ phân công',
    requires: [
        'sunerp.controller.sophancong.SoPhanCongEditCtr',
        'Ext.grid.Panel'
    ],
    controller: 'sunerp.controller.sophancong.SoPhanCongEditCtr',
    inject: ['soPhanCongStore', 'taskStore'],
    config: {
        model: null,
        taskStore: null,
        soPhanCongStore: null
    },
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'name',
                        minLength: 4,
                        fieldLabel: 'Name'
                    }
                ]
            },
            {
                xtype: 'grid',
                store: this.getTaskStore(),
                columns: [
                    { xtype: 'rownumberer' },
                    { text: 'Code', dataIndex: 'code' },
                    { text: 'Name', dataIndex: 'name', flex: 1 }
                ],
                height: 200,
                width: 400
            }
        ];

        this.callParent(arguments);
    }
});