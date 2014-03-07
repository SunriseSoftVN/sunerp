Ext.define('sunerp.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
        'Ext.tab.Panel',
        'Ext.layout.container.Border',
        'sunerp.view.Header',
        'sunerp.view.Navigation',
        'sunerp.view.ContentPanel'
    ],

    layout: 'border',

    items: [
        {
            region: 'north',
            xtype: 'appHeader'
        },
        {
            region: 'west',
            xtype: 'navigation',
            width: 250,
            minWidth: 100,
            height: 200,
            split: true,
            stateful: true,
            stateId: 'mainnav.west',
            collapsible: true,
            tools: [{
                type: 'gear',
                regionTool: true
            }]
        },
        {
            region: 'center',
            xtype: 'contentPanel'
        }
    ]
});