Ext.define('sunerp.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
        'Ext.tab.Panel',
        'Ext.layout.container.Border',
        'sunerp.view.Header',
        'sunerp.view.LeftPanel',
        'sunerp.view.ContentPanel',
        'sunerp.controller.NavigationCtr'
    ],

    controller: "sunerp.controller.NavigationCtr",

    layout: 'border',

    items: [
        {
            region: 'north',
            xtype: 'appHeader'
        },
        {
            region: 'west',
            xtype: 'navigation',
            width: 220,
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
            xtype: 'contentPanel',
            id: 'content-panel'
        }
    ]
});