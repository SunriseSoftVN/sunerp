Ext.define('sunerp.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
        'Ext.tab.Panel',
        'Ext.layout.container.Border',
        'sunerp.view.Header',
        'sunerp.view.LeftPanel',
        'sunerp.view.ContentPanel',
        'sunerp.layout.Center',
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
            width: 230,
            minWidth: 100,
            height: 200,
            split: true,
            stateful: true,
            stateId: 'mainnav.west',
            collapsible: true,
            tools: [
                {
                    type: 'gear',
                    regionTool: true
                }
            ]
        },
        {
            region: 'center',
            xtype: 'contentPanel',
            id: 'content-panel',
            layout: 'ux.center',
            items: [
                {
                    border: false,
                    width: 200,
                    height: '35%', // assign 90% of the container height to the item
                    html: '<div style="text-align: center">' +
                        '<img src="/assets/companylogo.png">' +
                        '<h4>Công ty TNHH SunriseSoft</h4>' +
                        '<p>Website: <a target="_blank" href="http://www.sunrisesoft.vn/">http://sunrisesoft.vn</a></p>' +
                        '</div>',
                    bodyStyle: 'background-image: url(/assets/img/square.gif)'
                }
            ]
        }
    ]
});