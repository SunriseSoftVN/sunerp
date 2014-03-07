Ext.application({
    requires: [
        'Ext.window.MessageBox',
        'Ext.tip.QuickTipManager'
    ],
    name: 'sunerp',
    appFolder: '/assets/js',
    controllers: [
        'NavigationCtr',
        'ContentCtr'
    ],
    autoCreateViewport: true,
    init: function() {
        //hide loading
        Ext.select("#loading-container").hide();
        Ext.tip.QuickTipManager.init();
    }
});