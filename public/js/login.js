//this file is only run when user haven't login yet
Ext.application({
    name: 'sunerp',
    appFolder: '/assets/js',
    controllers: ['LoginCtr'],
    requires: [
        'sunerp.view.ContentPanel'
    ],

    refs: [
        {
            ref: 'contentPanel',
            selector: '#content-panel'
        }
    ],

    launch: function () {
        //hide loading
        Ext.select("#loading-container").hide();
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                Ext.create('Ext.panel.Panel', {
                    id: 'content-panel',
                    items: [
                        {
                            xtype: 'login'
                        }
                    ]
                })
            ]
        });
        this.centerContent();
    },

    centerContent: function() {
        var contentPanel = this.getContentPanel(),
            body = contentPanel.body,
            item = contentPanel.items.getAt(0),
            align = 'c-c',
            overflowX,
            overflowY,
            offsets;

        if (item) {
            overflowX = (body.getWidth() < (item.getWidth() + 40));
            overflowY = (body.getHeight() < (item.getHeight() + 40));

            if (overflowX && overflowY) {
                align = 'tl-tl';
                offsets = [20, 20];
            } else if (overflowX) {
                align = 'l-l';
                offsets = [20, 0];
            } else if (overflowY) {
                align = 't-t';
                offsets = [0, 20];
            }

            item.alignTo(contentPanel.body, align, offsets);
        }
    }
});