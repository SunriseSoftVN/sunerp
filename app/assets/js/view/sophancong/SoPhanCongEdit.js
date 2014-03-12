Ext.define('sunerp.view.sophancong.SoPhanCongEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.soPhanCongEdit',
    title: 'Sổ phân công',
    requires: ['sunerp.controller.sophancong.SoPhanCongEditCtr'],
    controller: 'sunerp.controller.sophancong.SoPhanCongEditCtr',
    config: {
        model: null
    },
    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        minLength: 4,
                        fieldLabel: 'Name'
                    }
                ]
            }
        ];

        this.callParent(arguments);
    }
});